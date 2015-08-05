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



public class ArticleFragment extends Fragment implements GestureDetector.OnGestureListener{

    private static final String TAG = "ArticleFragment";

    ViewFlipper mViewFlipper;
    TextView textDate;
    TextView textTitle;
    TextView textArticleAuthor;
    TextView textArticle;
    GestureDetector mGestureDetector;
    MyScrollView myScrollView;

    private MainActivity.MyTouchListener myTouchListener;


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

        myScrollView.setmGestureDetector(mGestureDetector);


        myTouchListener = new MainActivity.MyTouchListener() {
            @Override
            public boolean onTouchEvent(MotionEvent event) {

                return myScrollView.onTouchEvent(event);
            }
        };
        ((MainActivity)getActivity()).registerMyTouchListener(myTouchListener);



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
        if (e1.getX() - e2.getX() > 30 || e2.getX() - e1.getX() > 30){
            mViewFlipper.getCurrentView().setX(e2.getX() - e1.getX());
        }


        //Log.d("CurrentView.X", String.valueOf(mViewFlipper.getCurrentView().getX()));
        //Log.d("DistanceX", String.valueOf(distanceX));
        //Log.d("RealDistanceX", String.valueOf(e2.getX() - e1.getX()));

        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (e1.getX() - e2.getX() > 200){

            Log.d(TAG, "Fling To Left....");
            //向左滑动
            mViewFlipper.setOutAnimation(getActivity().getApplicationContext(), R.anim.left_out);
            mViewFlipper.setInAnimation(getActivity().getApplicationContext(), R.anim.right_in);
            mViewFlipper.showNext();
        }else if (e2.getX() - e1.getX() > 200){
            //向右滑动
            mViewFlipper.setOutAnimation(getActivity().getApplicationContext(), R.anim.right_out);
            mViewFlipper.setInAnimation(getActivity().getApplicationContext(), R.anim.left_in);
            mViewFlipper.showPrevious();
        }else{
            mViewFlipper.getCurrentView().setX(0);
            return false;
        }
        mViewFlipper.getCurrentView().setX(0);
        return true;
    }
}
