package com.example.zhangzhao.netimage;

import android.app.Activity;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.zhangzhao.entity.Article;
import com.example.zhangzhao.tool.Config;
import com.example.zhangzhao.tool.GetArticle;
import com.example.zhangzhao.tool.MyScrollView;
import com.example.zhangzhao.tool.ResolveXml;
import com.example.zhangzhao.tool.NetConnection;

import java.util.List;


public class ArticleFragment extends Fragment implements GestureDetector.OnGestureListener{

    public boolean onTouchEvent(MotionEvent event){
        myScrollView.setmGestureDetector(mGestureDetector);
        return myScrollView.onTouchEvent(event);
    }

    private static final String TAG = "ArticleFragment";

    ViewFlipper mViewFlipper;
    TextView textDate;
    TextView textTitle;
    TextView textArticleAuthor;
    TextView textArticle;
    GestureDetector mGestureDetector;
    MyScrollView myScrollView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d(TAG, "onCreateView....");

        View view = inflater.inflate(R.layout.fragment_article, container, false);
        mViewFlipper = (ViewFlipper) view.findViewById(R.id.viewFlipper);
        mGestureDetector = new GestureDetector(getActivity().getApplicationContext(),this);
        textArticle = (TextView) view.findViewById(R.id.textArticle);
        textTitle = (TextView) view.findViewById(R.id.textTitle);
        textArticleAuthor = (TextView) view.findViewById(R.id.textArticleAuthor);
        myScrollView = (MyScrollView) view.findViewById(R.id.myScrollView);

        final ProgressDialog pd = ProgressDialog.show(getActivity(),"","");
        new GetArticle("7",new GetArticle.SuccessCallback() {
            @Override
            public void onSuccess(Article article) {
                pd.dismiss();
                String a = article.getArticle();
                CharSequence cache = Html.fromHtml(a);
                textArticle.setText(cache);
                textTitle.setText(article.getTitle());
                textArticleAuthor.setText(article.getArticleAuthor());
            }
        }, new GetArticle.FailCallback() {
            @Override
            public void onFail(int errorCode) {
                pd.dismiss();
                Toast.makeText(getActivity().getApplicationContext(), Config.FAIL_TO_GET_DATA, Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }


    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        mViewFlipper.getCurrentView().setX(distanceX);
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (e1.getX() > e2.getX()){

            Log.d(TAG, "Fling To Left....");
            //向左滑动
            mViewFlipper.setOutAnimation(getActivity().getApplicationContext(), R.anim.left_out);
            mViewFlipper.setInAnimation(getActivity().getApplicationContext(), R.anim.right_in);
            mViewFlipper.showNext();

        }else if (e2.getX() > e1.getX()){
            //向右滑动
            mViewFlipper.setOutAnimation(getActivity().getApplicationContext(), R.anim.right_out);
            mViewFlipper.setInAnimation(getActivity().getApplicationContext(), R.anim.left_in);
            mViewFlipper.showPrevious();
        }else{
            return false;
        }
        return true;
    }
}
