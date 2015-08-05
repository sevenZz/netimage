package com.example.zhangzhao.tool;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by zhangzhao on 15/6/9.
 */
public class MyScrollView extends ScrollView{

    GestureDetector mGestureDetector;

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setmGestureDetector(GestureDetector gestureDetector){
        this.mGestureDetector = gestureDetector;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        super.onTouchEvent(ev);
        return mGestureDetector.onTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        mGestureDetector.onTouchEvent(ev);

        return super.dispatchTouchEvent(ev);
    }
}
