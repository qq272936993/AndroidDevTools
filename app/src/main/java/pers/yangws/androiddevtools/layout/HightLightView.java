package pers.yangws.androiddevtools.layout;/**
 * Created by Thinkpad on 2015/12/22.
 * <p>
 * 公司:
 * </p>
 * <p>
 * 描述:
 * </p>
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import java.util.List;

import pers.yangws.androiddevtools.model.bean.HighLight;

/**
 * <pre>
 * 文件名称:
 * 包路径:
 * 描述:
 *      首次进入APP界面相关功能界面屏蔽提示,不明白可看HightLight
 *
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
public class HightLightView extends FrameLayout {
    private static final int DEFAULT_WIDTH_BLUR = 15;
    private static final int DEFAULT_RADIUS = 6;
    private static final PorterDuffXfermode MODE_DST_OUT = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);

    private Bitmap mMaskBitmap;
    private Paint mPaint;
    private List<HighLight.ViewPosInfo> mViewRects;
    private HighLight mHighLight;
    private LayoutInflater mInflater;

    //some config
    private boolean isBlur = true;
    private int maskColor = 0xCC000000;


    public HightLightView(Context context, HighLight highLight, int maskColor, boolean isBlur, List<HighLight.ViewPosInfo> viewRects) {
        super(context);
        mHighLight = highLight;
        mInflater = LayoutInflater.from(context);
        mViewRects = viewRects;
        this.maskColor = maskColor;
        this.isBlur = isBlur;
        setWillNotDraw(false);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setDither(true);
        mPaint.setAntiAlias(true);
        if (isBlur)
            mPaint.setMaskFilter(new BlurMaskFilter(DEFAULT_WIDTH_BLUR, BlurMaskFilter.Blur.SOLID));
        mPaint.setStyle(Paint.Style.FILL);

        addViewForTip();


    }

    private void addViewForTip() {
        for (HighLight.ViewPosInfo viewPosInfo : mViewRects) {
            View view = mInflater.inflate(viewPosInfo.layoutId, this, false);
            FrameLayout.LayoutParams lp = buildTipLayoutParams(view, viewPosInfo);

            if (lp == null) continue;

            lp.leftMargin = (int) viewPosInfo.marginInfo.leftMargin;
            lp.topMargin = (int) viewPosInfo.marginInfo.topMargin;
            lp.rightMargin = (int) viewPosInfo.marginInfo.rightMargin;
            lp.bottomMargin = (int) viewPosInfo.marginInfo.bottomMargin;

            if (lp.leftMargin == 0 && lp.topMargin == 0) {
                lp.gravity = Gravity.RIGHT | Gravity.BOTTOM;
            }
            addView(view, lp);
        }
    }

    private void buildMask() {
        mMaskBitmap = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(mMaskBitmap);
        canvas.drawColor(maskColor);
        mPaint.setXfermode(MODE_DST_OUT);
        mHighLight.updateInfo();
        for (HighLight.ViewPosInfo viewPosInfo : mViewRects) {
            canvas.drawRoundRect(viewPosInfo.rectF, DEFAULT_RADIUS, DEFAULT_RADIUS, mPaint);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        measureChildren(MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY),//
                MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
        setMeasuredDimension(width, height);


    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            buildMask();
            updateTipPos();
        }

    }

    private void updateTipPos() {
        for (int i = 0, n = getChildCount(); i < n; i++) {
            View view = getChildAt(i);
            HighLight.ViewPosInfo viewPosInfo = mViewRects.get(i);

            LayoutParams lp = buildTipLayoutParams(view, viewPosInfo);
            if (lp == null) continue;
            view.setLayoutParams(lp);
        }
    }

    private LayoutParams buildTipLayoutParams(View view, HighLight.ViewPosInfo viewPosInfo) {
        LayoutParams lp = (LayoutParams) view.getLayoutParams();
        if (lp.leftMargin == (int) viewPosInfo.marginInfo.leftMargin &&
                lp.topMargin == (int) viewPosInfo.marginInfo.topMargin &&
                lp.rightMargin == (int) viewPosInfo.marginInfo.rightMargin &&
                lp.bottomMargin == (int) viewPosInfo.marginInfo.bottomMargin) return null;

        lp.leftMargin = (int) viewPosInfo.marginInfo.leftMargin;
        lp.topMargin = (int) viewPosInfo.marginInfo.topMargin;
        lp.rightMargin = (int) viewPosInfo.marginInfo.rightMargin;
        lp.bottomMargin = (int) viewPosInfo.marginInfo.bottomMargin;

        if (lp.leftMargin == 0 && lp.topMargin == 0) {
            lp.gravity = Gravity.RIGHT | Gravity.BOTTOM;
        }
        return lp;
    }


    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawBitmap(mMaskBitmap, 0, 0, null);
        super.onDraw(canvas);

    }
}