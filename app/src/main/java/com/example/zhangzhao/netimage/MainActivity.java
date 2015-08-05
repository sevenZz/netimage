package com.example.zhangzhao.netimage;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends FragmentActivity implements MainFragment.OnFragmentInteractionListener{

    private static final String TAG = "MainActivity";

    Button mainButton;
    Button articleButton;

    FragmentManager fragmentManager;

    MainFragment mainFragment;
    ArticleFragment articleFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainButton = (Button) findViewById(R.id.rbMain);
        articleButton = (Button) findViewById(R.id.rbArticle);

        mainButton.setBackgroundColor(Color.BLUE);


        //创建页面时直接把两个Fragment new出来，然后切出去时隐藏，切回来时显示。
        mainFragment = new MainFragment();
        articleFragment = new ArticleFragment();


        fragmentManager = getFragmentManager();

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.container, mainFragment, "mainFragment");
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            fragmentTransaction.commit();


        mainButton.setOnClickListener(new onMainButtonClickListener());
        articleButton.setOnClickListener(new onArticleButtonClickListener());

    }


    private class onMainButtonClickListener implements View.OnClickListener{

        public void onClick(View v) {
            mainButton.setBackgroundColor(Color.BLUE);
            articleButton.setBackgroundColor(808080);

            switchFragment(articleFragment, mainFragment);

        }
    }

    private void switchFragment(Fragment from, Fragment to) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        if (from == mainFragment) {
            if (!to.isAdded()) {
                fragmentTransaction.hide(from).add(R.id.container, to).commit();
            } else {
                fragmentTransaction.hide(from).show(to).commit();
            }
        }
        else {
            fragmentTransaction.hide(from).show(to).commit();

        }

    }

    private class onArticleButtonClickListener implements View.OnClickListener{

        public void onClick(View v) {
            articleButton.setBackgroundColor(Color.BLUE);
            mainButton.setBackgroundColor(808080);

            switchFragment(mainFragment, articleFragment);

        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    //回调接口
    public interface MyTouchListener{
        boolean onTouchEvent(MotionEvent event);
    }

    //保存MyTouchListener接口的列表
    private ArrayList<MyTouchListener> myTouchListeners = new ArrayList<MyTouchListener>();

    //提供给Fragment通过getActivity()方法来注册自己的触摸事件的方法
    public void registerMyTouchListener(MyTouchListener listener){
        myTouchListeners.add(listener);
    }

    //提供给Fragment通过getActivity()方法来取消注册自己的触摸事件的方法
    public void unRegisterMyTouchListener(MyTouchListener listener){
        myTouchListeners.remove(listener);
    }

    //分发触摸事件给所有注册了MyTouchListener的接口
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        for (MyTouchListener listener : myTouchListeners){
            listener.onTouchEvent(ev);
        }
        return super.dispatchTouchEvent(ev);
    }
}
