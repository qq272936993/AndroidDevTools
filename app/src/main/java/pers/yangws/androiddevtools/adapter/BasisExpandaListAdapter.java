/**
 *
 */
package pers.yangws.androiddevtools.adapter;

import java.util.List;

import pers.yangws.androiddevtools.holder.BaseHolder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;


/**
 * @类名 BasisExpandaListAdapter
 * @作者 杨文松
 * @时间 2015年7月28日
 * @描述 <pre>专用于折叠List的Adapter基类</pre>
 */
public abstract class BasisExpandaListAdapter<E, T> extends BaseExpandableListAdapter {

    protected Context mContext;
    protected List<E> mGroupList;
    protected List<List<T>> mChildList;
    protected BaseHolder<E> mGroupHolder;
    protected BaseHolder<T> mChildHolder;

    public BasisExpandaListAdapter(Context context, List<E> groupList, List<List<T>> childList) {
        this.mContext = context;
        this.mGroupList = groupList;
        this.mChildList = childList;
    }

    /***
     * 设置值并通知更新
     *
     * @param groupList 头集合
     * @param childList 子集合
     */
    public void setDataNotify(List<E> groupList, List<List<T>> childList) {
        this.mGroupList = groupList;
        this.mChildList = childList;
        notifyDataSetChanged();
    }


    @Override
    public int getGroupCount() {
        return mGroupList != null ? mGroupList.size() : 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        List<T> childs = mChildList.get(groupPosition);
        return childs != null ? childs.size() : 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGroupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {

        return mChildList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        if (convertView != null) {
            mGroupHolder = (BaseHolder<E>) convertView.getTag();
        } else {
            mGroupHolder = getGroupHolder(groupPosition, isExpanded, convertView, parent);
        }

        mGroupHolder.setData(mGroupList.get(groupPosition));

        return mGroupHolder.getRootView();
    }

    @SuppressWarnings("unchecked")
    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView != null) {
            mChildHolder = (BaseHolder<T>) convertView.getTag();
        } else {
            mChildHolder = getChildHolder(groupPosition, childPosition, isLastChild, convertView, parent);
        }

        mChildHolder.setData(mChildList.get(groupPosition).get(childPosition));

        return mChildHolder.getRootView();
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    protected abstract BaseHolder<E> getGroupHolder(int groupPosition, boolean isExpanded,
                                                    View convertView, ViewGroup parent);

    protected abstract BaseHolder<T> getChildHolder(int groupPosition, int childPosition,
                                                    boolean isLastChild, View convertView, ViewGroup parent);

}
