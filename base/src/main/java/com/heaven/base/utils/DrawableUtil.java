package com.heaven.base.utils;

import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * FileName: com.heaven.base.utils.DrawableUtil.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-04-02 13:06
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class DrawableUtil {

    /**
     * TextView四周drawable的序号。
     * 0 left,  1 top, 2 right, 3 bottom
     */
    private static final int LEFT = 0;
    private static final int RIGHT = 2;

    public static void bindDrawableListener(TextView textView, OnDrawableListener listener) {
        View.OnTouchListener touchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        if (listener != null) {
                            Drawable drawableLeft = textView.getCompoundDrawables()[LEFT];
                            if (drawableLeft != null && event.getRawX() <= (textView.getLeft() + drawableLeft.getBounds().width())) {
                                listener.onLeft(v, drawableLeft);
                                return true;
                            }

                            Drawable drawableRight = textView.getCompoundDrawables()[RIGHT];
                            if (drawableRight != null && event.getRawX() >= (textView.getRight() - drawableRight.getBounds().width())) {
                                listener.onRight(v, drawableRight);
                                return true;
                            }
                        }

                        break;
                    default:
                        return false;
                }

                return false;
            }
        };
        textView.setOnTouchListener(touchListener);
    }


    public interface OnDrawableListener {
        public void onLeft(View v, Drawable left);

        public void onRight(View v, Drawable right);
    }
}
