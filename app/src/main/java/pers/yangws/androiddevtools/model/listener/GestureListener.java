package pers.yangws.androiddevtools.model.listener;

/**
 * Created by 1yyg-安卓 on 2015/12/23.
 *
 * 手势监听
 * */
public interface GestureListener {

    /**
     * 向左滑动时调用
     * */
    public void onLeft();

    /**
     * 向右滑动时调用
     * */
    public void onRight();

    /**
     * 向上滑动时调用
     * */
    public void onTop();

    /**
     * 向下滑动时调用
     * */
    public void onBottom();

}
