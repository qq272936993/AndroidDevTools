package pers.yangws.androiddevtools.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.FrameLayout;

import pers.yangws.androiddevtools.R;


/**
 * @类名: RatioLayout
 * @创建时间 : 2015-3-23 上午8:46:10
 * @描述: 控制宽高比的布局：
 * @1. 已知 宽度准确值，通过宽高比例， 要计算出 高度的值
 * @2. 已知 高度准确值，通过宽高比例， 要计算出 宽度的值
 * @更新描述:
 */
public class RatioLayout extends FrameLayout {
    public static final int RELATIVE_WIDTH = 0;
    public static final int RELATIVE_HEIGHT = 1;

    private float mRatio;                            // 宽高比例
    private int mRelative = RELATIVE_WIDTH;    // 相对谁来计算

    public RatioLayout(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public RatioLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RatioLayout);

        // 取值
        mRatio = ta.getFloat(R.styleable.RatioLayout_ratio, 0f);
        mRelative = ta.getInt(R.styleable.RatioLayout_relative, RELATIVE_WIDTH);

        ta.recycle();
    }

    public void setRatio(float ratio) {
        this.mRatio = ratio;
    }

    public void setRelative(int relative) {
        if (relative > 1 || relative < 0) {
            return;
        }
        this.mRelative = relative;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // MeasureSpec
        // 1. widthMeasureSpec : 32位的0101......
        // (头两位表示 模式：UNSPECIFIED 未定义，EXACTLY，AT_MOST)
        // (后30位表示的是大小值：010101---> 30dp)

        // 获取宽度的大小和模式
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        // 获取高度的大小和模式
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (mRatio == 0f) {
            //获取屏幕宽高
            DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
            int screenWidth = metrics.widthPixels;
            int screenHeight = metrics.heightPixels;

            mRatio = (screenWidth * 1.0f) / (screenHeight * 1.0f);
        }


        if (widthMode == MeasureSpec.EXACTLY && mRelative == RELATIVE_WIDTH) {
            // @1. 已知 宽度准确值，通过宽高比例， 要计算出 高度的值

            // 计算出孩子的宽度和高度
            int width = widthSize - getPaddingLeft() - getPaddingRight();
            int height = (int) (width / mRatio + 0.5f);

            // child.measure ： 测量孩子
            int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
            int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
            measureChildren(childWidthMeasureSpec, childHeightMeasureSpec);

            // 设置自己的宽高
            int measuredWidth = widthSize;
            int measuredHeight = height + getPaddingTop() + getPaddingBottom();
            setMeasuredDimension(measuredWidth, measuredHeight);
        } else if (heightMode == MeasureSpec.EXACTLY && mRelative == RELATIVE_HEIGHT) {
            // @2. 已知 高度准确值，通过宽高比例， 要计算出 宽度的值
            int height = heightSize - getPaddingTop() - getPaddingBottom();
            int width = (int) (height * mRatio + 0.5f);

            // child.measure ： 测量孩子
            int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
            int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
            measureChildren(childWidthMeasureSpec, childHeightMeasureSpec);

            // 设置自己的宽高
            int measuredWidth = width + getPaddingLeft() + getPaddingRight();
            int measuredHeight = heightSize;
            setMeasuredDimension(measuredWidth, measuredHeight);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
