package pers.yangws.androiddevtools.model.bean;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import pers.yangws.androiddevtools.helper.app.ViewHelper;
import pers.yangws.androiddevtools.model.listener.GestureListener;

/**
 * Created by 杨文松 on 2015/12/23.
 * 手势意图
 */
public class GestureDetectorIntent {

    //手势监听
    private GestureListener mlistener;
    private Context mContext;
    private View mView;         //这个是用于判断是在View中的滑动,否则为整个界面的滑动
    private int downX;          //上一次的X位置
    private int downY;          //上一次的Y位置
    private long downTime;      //点击时的时间
    private int lastInViewX;    //记录最后一个在view中的X坐标
    private int lastInViewY;    //记录最后一个在View中的Y坐标
    public boolean mCanRecord;  //是否可以被记录


    public GestureDetectorIntent(Context context,GestureListener listener){
        this.mContext = context;
        this.mlistener = listener;
    }

    public GestureDetectorIntent(Context context,GestureListener listener,View view){
        this.mContext = context;
        this.mlistener = listener;
        this.mView = view;
    }


    /**
     * 在事件中开始调用
     * */
    public void onEvent(MotionEvent event){
        //判断滑动事件
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
            {
                if(mView != null){
                    mCanRecord = ViewHelper.isTouchInView(event , mView);
                    if(mCanRecord){
                        setDownInfo(event);
                    }
                }else{
                    mCanRecord = true;
                    setDownInfo(event);
                }
            }
                break;
            case MotionEvent.ACTION_MOVE:
            {
                if(mCanRecord){
                    setMoveInfo(event);
                }
            }
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
            {
                if(mCanRecord){
                    //可以解析记录
                    onOver(event);
                }
            }
            break;
        }
    }

    private void setDownInfo(MotionEvent event){
        downTime = System.currentTimeMillis();      //系统当前时间
        downX = (int) event.getX();
        downY = (int) event.getY();
    }


    public void setMoveInfo(MotionEvent event){
        if(mView != null){
            mCanRecord = ViewHelper.isTouchInView(event , mView);
            if(mCanRecord){
                //在View中点击
                lastInViewX = (int) event.getX();
                lastInViewY = (int) event.getY();
            }
        }else{
            //在Activity中点击
            lastInViewX = (int) event.getX();
            lastInViewY = (int) event.getY();
        }
    }


    /**
     * 当完成后
     * */
    private void onOver(MotionEvent event){
        if(mView != null){
            mCanRecord = ViewHelper.isTouchInView(event , mView);
            if(mCanRecord){
                //则判断方位
                lastInViewX = (int) event.getX();
                lastInViewY = (int) event.getY();
            }

            adapter(mView.getWidth() , mView.getHeight() , Math.abs(lastInViewX - downX) , Math.abs(lastInViewY - downY));
        }else{
            //判断滑动方位
            DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
            int widthPixels = displayMetrics.widthPixels;
            int heightPixels = displayMetrics.heightPixels;

            adapter(widthPixels ,heightPixels , Math.abs(lastInViewX - downX) , Math.abs(lastInViewY - downY));
        }
    }

    /**
     * 适配方向监听
     * @param refWidth 参考长度
     * @param refHeight 参考高度
     * @param compWidth 对比长度
     * @param compHeight 对比高度
     * */
    private void adapter(int refWidth,int refHeight,int compWidth, int compHeight){
        //判断事件间距,只有在600ms钟内的滑动才是有效的滑动
        if(System.currentTimeMillis() - downTime <= 600){
            float widthRadio = compWidth * 1.0f / refWidth;     //宽度比例
            float heightRadio = compHeight * 1.0f / refHeight;  //高度比例

            if(widthRadio >= heightRadio){
                if(widthRadio >= 0.3f){             //必须占用对照宽度的30%,否则滑动无效
                    //左右滑动
                    if(downX >= lastInViewX)
                        mlistener.onLeft();
                    else
                        mlistener.onRight();
                }
            }else{
                if(heightRadio >= 0.3f){            //必须占用对照高度的30%,否则滑动无效
                    //上下滑动
                    if(downY >= lastInViewY)
                        mlistener.onTop();
                    else
                        mlistener.onBottom();
                }
            }
        }
    }


}
