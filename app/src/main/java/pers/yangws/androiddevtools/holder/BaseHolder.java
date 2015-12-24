package pers.yangws.androiddevtools.holder;

import android.content.Context;
import android.view.View;


/***
 * @author 杨文松
 * @version 1.0
 * @描述 面向Holder编程的基类
 */
public abstract class BaseHolder<T> {

    protected T mData;
    private View rootView;
    protected Context mContext;

    public BaseHolder(Context context) {
        this.mContext = context;
        rootView = initView();
        rootView.setTag(this);
    }



    protected abstract View initView();

    protected abstract void refreshView();

    public View getRootView() {
        return this.rootView;
    }

    public void setData(T data) {
        this.mData = data;
        refreshView();
    }

    /**
     * 空实现Item的绑定工作,如果需要实现绑定,用户自己重写该方法,
     *              如果不用绑定,则不用管理
     *  说明:bindView只会调用一次,也就是监听方法只会执行一次,其他时候都会复用.
     *          避免了多次设置监听方法.
     *       这时只需要获取T mData变量即可做相应的操作.
     * */
    public void bindView(){

    }

}
