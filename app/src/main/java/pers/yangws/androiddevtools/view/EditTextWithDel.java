package pers.yangws.androiddevtools.view;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import pers.yangws.androiddevtools.R;

public class EditTextWithDel extends EditText {

    private Drawable imgInable;
    private Drawable imgAble;


    public EditTextWithDel(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public EditTextWithDel(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EditTextWithDel(Context context) {
        super(context);
    }

    public void init() {
        imgInable = getResources().getDrawable(R.mipmap.delete_gray);
        imgAble = getResources().getDrawable(R.mipmap.delete);

        this.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                changeDelImage();
            }
        });

        changeDelImage();
    }

    public void changeDelImage() {
        if (length() > 0) {
            setCompoundDrawablesWithIntrinsicBounds(null, null, imgAble, null);
        } else {
            setCompoundDrawablesWithIntrinsicBounds(null, null, imgInable, null);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (imgAble != null && event.getAction() == MotionEvent.ACTION_UP) {
            int eventX = (int) event.getRawX();
            int eventY = (int) event.getRawY();
            Rect rect = new Rect();
            getGlobalVisibleRect(rect);

            rect.left = rect.right - 50;
            if (rect.contains(eventX, eventY))
                setText("");
        }

        return super.onTouchEvent(event);
    }


}
