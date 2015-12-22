package pers.yangws.androiddevtools.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.widget.TextView;


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
        tv.setText("下标position:" + position);

        return tv;
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
