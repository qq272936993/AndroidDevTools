package pers.yangws.androiddevtools.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import pers.yangws.androiddevtools.holder.BaseHolder;


/***
 * 广告轮询Adapter
 */
public class AdvertPagerAdapter extends BasisPagerAdapter<String> {


    public AdvertPagerAdapter(Context context, List<String> data) {
        super(context, data);
    }

    @Override
    public View getView(int position) {
        //TODO 这里最好是弄成imageView或者其他
        TextView tv = new TextView(mContext);


        return tv;
    }

    @Override
    public BaseHolder<String> getHolder(int position) {
        return new BaseHolder<String>(mContext) {
            private TextView tv;

            @Override
            protected View initView() {
                tv = new TextView(mContext);
                return tv;
            }

            @Override
            protected void refreshView() {
                tv.setText("下标position:"+ mData );
            }
        };
    }


    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public Object instantiateItem(View container, int position) {
        position = position % mData.size();
        return super.instantiateItem(container, position);
    }


}
