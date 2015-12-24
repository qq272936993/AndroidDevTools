package pers.yangws.androiddevtools.adapter;

import java.util.List;


import pers.yangws.androiddevtools.holder.BaseHolder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


/**
 * @author 杨文松
 * @version 1.0
 * @描述 <pre>
 * 专用于ListView的Adapter基类
 * </pre>
 */
public abstract class BasisListViewAdapter<T> extends BaseAdapter {

    protected List<T> mDatas;
    private BaseHolder<T> holder;
    protected Context mContext;

    public BasisListViewAdapter(Context context, List<T> datas) {
        this.mDatas = datas;
        this.mContext = context;
    }

    public void setDataNotify(List<T> datas) {
        this.mDatas = datas;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return this.mDatas == null ? 0 : this.mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return this.mDatas == null ? null : this.mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    /**
     * 说明:当传递的参数有改变时,需要重写该方法
     *
     * @param position    下标位置
     * @param convertView
     * @param parent
     * @return {@link View}	rootView
     */
    @SuppressWarnings("unchecked")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView != null) {
            holder = (BaseHolder<T>) convertView.getTag();
            holder.bindView();
        } else {
            holder = getHolder();
        }

        holder.setData(mDatas.get(position));

        return holder.getRootView();
    }


    protected abstract BaseHolder<T> getHolder();


}
