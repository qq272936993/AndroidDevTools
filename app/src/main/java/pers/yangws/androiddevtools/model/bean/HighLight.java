package pers.yangws.androiddevtools.model.bean;

import android.app.Activity;
import android.content.Context;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import java.util.ArrayList;
import java.util.List;
import pers.yangws.androiddevtools.helper.app.ViewHelper;
import pers.yangws.androiddevtools.layout.HightLightView;


/**
 * <pre>
 * 文件名称:
 * 包路径:
 * 描述:
 * 内容摘要
 *    作者: 杨文松
 *    版本: 1.0
 *    时间:
 *    邮箱: 272936993@qq.com
 * 修改历史:
 * 修改日期           修改人员        版本	       修改内容  		说明
 * ----------------------------------------------
 *
 * </pre>
 */
public class HighLight {
    public static class ViewPosInfo {
        public int layoutId = -1;
        public RectF rectF;
        public MarginInfo marginInfo;
        public View view;
        public OnPosCallback onPosCallback;
    }

    public static class MarginInfo {
        public float topMargin;
        public float leftMargin;
        public float rightMargin;
        public float bottomMargin;

    }

    public interface OnPosCallback {
        void getPos(float rightMargin, float bottomMargin, RectF rectF, MarginInfo marginInfo);
    }


    private View mAnchor;
    private List<ViewPosInfo> mViewRects;
    private Context mContext;
    private HightLightView mHightLightView;

    private boolean intercept = true;
    private boolean shadow = true;
    private int maskColor = 0xCC000000;

    public HighLight(Context context) {
        mContext = context;
        mViewRects = new ArrayList<ViewPosInfo>();
        mAnchor = ((Activity) mContext).findViewById(android.R.id.content);
    }

    public HighLight anchor(View anchor) {
        mAnchor = anchor;
        return this;
    }

    public HighLight intercept(boolean intercept) {
        this.intercept = intercept;
        return this;
    }

    public HighLight shadow(boolean shadow) {
        this.shadow = shadow;
        return this;
    }

    public HighLight maskColor(int maskColor) {
        this.maskColor = maskColor;
        return this;
    }


    public HighLight addHighLight(int viewId, int decorLayoutId, OnPosCallback onPosCallback) {
        ViewGroup parent = (ViewGroup) mAnchor;
        View view = parent.findViewById(viewId);
        addHighLight(view, decorLayoutId, onPosCallback);
        return this;
    }

    public void updateInfo() {
        ViewGroup parent = (ViewGroup) mAnchor;
        for (HighLight.ViewPosInfo viewPosInfo : mViewRects) {

            RectF rect = new RectF(ViewHelper.getLocationInView(parent, viewPosInfo.view));
//            if (!rect.equals(viewPosInfo.rectF))//TODO bug dismissed...fc...
            {
                viewPosInfo.rectF = rect;
                viewPosInfo.onPosCallback.getPos(parent.getWidth() - rect.right, parent.getHeight() - rect.bottom, rect, viewPosInfo.marginInfo);
            }
        }

    }


    public HighLight addHighLight(View view, int decorLayoutId, OnPosCallback onPosCallback) {
        ViewGroup parent = (ViewGroup) mAnchor;
        RectF rect = new RectF(ViewHelper.getLocationInView(parent, view));
        ViewPosInfo viewPosInfo = new ViewPosInfo();
        viewPosInfo.layoutId = decorLayoutId;
        viewPosInfo.rectF = rect;
        viewPosInfo.view = view;
        if (onPosCallback == null && decorLayoutId != -1) {
            throw new IllegalArgumentException("onPosCallback can not be null.");
        }
        MarginInfo marginInfo = new MarginInfo();
        onPosCallback.getPos(parent.getWidth() - rect.right, parent.getHeight() - rect.bottom, rect, marginInfo);
        viewPosInfo.marginInfo = marginInfo;
        viewPosInfo.onPosCallback = onPosCallback;
        mViewRects.add(viewPosInfo);

        return this;
    }


    public void show() {

        if (mHightLightView != null) return;

        HightLightView hightLightView = new HightLightView(mContext, this, maskColor, shadow, mViewRects);
        if (mAnchor.getClass().getSimpleName().equals("FrameLayout")) {
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams
                    (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            ((ViewGroup) mAnchor).addView(hightLightView, ((ViewGroup) mAnchor).getChildCount(), lp);

        } else {
            FrameLayout frameLayout = new FrameLayout(mContext);
            ViewGroup parent = (ViewGroup) mAnchor.getParent();
            parent.removeView(mAnchor);
            parent.addView(frameLayout, mAnchor.getLayoutParams());
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams
                    (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            frameLayout.addView(mAnchor, lp);

            frameLayout.addView(hightLightView);
        }

        if (intercept) {
            hightLightView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    remove();
                }
            });
        }

        mHightLightView = hightLightView;
    }

    public void remove() {
        if (mHightLightView == null) return;
        ViewGroup parent = (ViewGroup) mHightLightView.getParent();
        if (parent instanceof RelativeLayout || parent instanceof FrameLayout) {
            parent.removeView(mHightLightView);
        } else {
            parent.removeView(mHightLightView);
            View origin = parent.getChildAt(0);
            ViewGroup graParent = (ViewGroup) parent.getParent();
            graParent.removeView(parent);
            graParent.addView(origin, parent.getLayoutParams());
        }
        mHightLightView = null;
    }


}
