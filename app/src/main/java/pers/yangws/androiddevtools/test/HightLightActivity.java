package pers.yangws.androiddevtools.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import pers.yangws.androiddevtools.R;
import pers.yangws.androiddevtools.helper.PromptHelper;
import pers.yangws.androiddevtools.model.bean.GestureDetectorIntent;
import pers.yangws.androiddevtools.model.listener.GestureListener;

/**
 * Created by 1yyg-安卓 on 2015/12/21.
 */
public class HightLightActivity extends Activity implements GestureListener {


    private Button mHightLightBtn;
    private GestureDetectorIntent gestureDetectorIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        View view = findViewById(R.id.tv);

//        gestureDetectorIntent = new GestureDetectorIntent(this , this ,view);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //gestureDetectorIntent.onEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public void onLeft() {
        PromptHelper.showToast(this , "向左滑动了...");
    }

    @Override
    public void onRight() {
        PromptHelper.showToast(this , "向右滑动了...");
    }

    @Override
    public void onTop() {
        PromptHelper.showToast(this , "向上滑动了...");
    }

    @Override
    public void onBottom() {
        PromptHelper.showToast(this , "向下滑动了...");
    }
}
