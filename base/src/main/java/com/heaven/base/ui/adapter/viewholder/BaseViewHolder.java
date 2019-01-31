package com.heaven.base.ui.adapter.viewholder;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * 作者:Heaven
 * 时间: on 2017/4/14 09:21
 * 邮箱:heavenisme@aliyun.com
 */

public class BaseViewHolder extends RecyclerView.ViewHolder {
    public ViewHolderManager viewHolderManager;
    public Object itemData;
    private final SparseArray<View> views;
    private View viewItem;
    private Context context;
    public BaseViewHolder(View itemView) {
        super(itemView);
        this.viewItem = itemView;
        views = new SparseArray<>();
    }

    public Object getItemData() {
        return itemData;
    }

    /**
     * header和footer的个数也计算在内
     * {@link #getAdapterPosition()}
     */
    public int getItemPosition() {
        return getAdapterPosition();
    }

    public ViewHolderManager getViewHolderManager() {
        return viewHolderManager;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void startActivity(Intent intent) {
        if(context != null) {
            context.startActivity(intent);
        }
    }

    @SuppressWarnings(value = {"unchecked"})
    public <T extends View> T getView(int id) {
        View view = views.get(id);
        if (view == null) {
            view = itemView.findViewById(id);
            views.put(id, view);
        }
        return (T) view;
    }

    public BaseViewHolder setText(int viewId, String value) {
        TextView view = getView(viewId);
        if (view != null) {
            view.setText(value);
        }
        return this;
    }

    public BaseViewHolder setTextColor(int viewId, int textColor) {
        TextView view = getView(viewId);
        if (view != null) {
            view.setTextColor(textColor);
        }
        return this;
    }

    public BaseViewHolder setImageResource(int viewId, int imageResId) {
        ImageView view = getView(viewId);
        if (view != null) {
            view.setImageResource(imageResId);
        }
        return this;
    }

    public BaseViewHolder setBackgroundColor(int viewId, int color) {
        View view = getView(viewId);
        if (view != null) {
            view.setBackgroundColor(color);
        }
        return this;
    }

    public BaseViewHolder setBackgroundResource(int viewId, int backgroundRes) {
        View view = getView(viewId);
        if (view != null) {
            view.setBackgroundResource(backgroundRes);
        }
        return this;
    }

    public BaseViewHolder setVisible(int viewId, boolean visible) {
        View view = getView(viewId);
        if (view != null) {
            view.setVisibility(visible ? View.VISIBLE : View.GONE);
        }
        return this;
    }

    /**
     * Add an action to set the alpha of a view. Can be called multiple times.
     * Alpha between 0-1.
     */
    public BaseViewHolder setAlpha(int viewId, float value) {
        View view = getView(viewId);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            if (view != null) {
                view.setAlpha(value);
            }
        } else {
            // Pre-honeycomb hack to set Alpha value
            AlphaAnimation alpha = new AlphaAnimation(value, value);
            alpha.setDuration(0);
            alpha.setFillAfter(true);
            if (view != null) {
                view.startAnimation(alpha);
            }
        }
        return this;
    }

    /**
     * Add links into a TextView.
     *
     * @param viewId The id of the TextView to linkify.
     * @return The BaseAdapterHelper for chaining.
     */
    public BaseViewHolder linkify(int viewId) {
        TextView view = getView(viewId);
        if (view != null) {
            Linkify.addLinks(view, Linkify.ALL);
        }
        return this;
    }

    /**
     * Apply the typeface to the given viewId, and enable subpixel rendering.
     */
    public BaseViewHolder setTypeface(int viewId, Typeface typeface) {
        TextView view = getView(viewId);
        if (view != null) {
            view.setTypeface(typeface);
            view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        }
        return this;
    }

    /**
     * Sets the progress of a ProgressBar.
     *
     * @param viewId   The view id.
     * @param progress The progress.
     * @return The BaseAdapterHelper for chaining.
     */
    public BaseViewHolder setProgress(int viewId, int progress) {
        ProgressBar view = getView(viewId);
        if (view != null) {
            view.setProgress(progress);
        }
        return this;
    }

    /**
     * Sets the progress and max of a ProgressBar.
     *
     * @param viewId   The view id.
     * @param progress The progress.
     * @param max      The max value of a ProgressBar.
     * @return The BaseAdapterHelper for chaining.
     */
    public BaseViewHolder setProgress(int viewId, int progress, int max) {
        ProgressBar view = getView(viewId);
        if (view != null) {
            view.setMax(max);
            view.setProgress(progress);
        }
        return this;
    }

    /**
     * Sets the rating (the number of stars filled) of a RatingBar.
     *
     * @param viewId The view id.
     * @param rating The rating.
     * @return The BaseAdapterHelper for chaining.
     */
    public BaseViewHolder setRating(int viewId, float rating) {
        RatingBar view = getView(viewId);
        if (view != null) {
            view.setRating(rating);
        }
        return this;
    }

    /**
     * Sets the rating (the number of stars filled) and max of a RatingBar.
     *
     * @param viewId The view id.
     * @param rating The rating.
     * @param max    The range of the RatingBar to 0...max.
     * @return The BaseAdapterHelper for chaining.
     */
    public BaseViewHolder setRating(int viewId, float rating, int max) {
        RatingBar view = getView(viewId);
        if (view != null) {
            view.setMax(max);
            view.setRating(rating);
        }
        return this;
    }

    public BaseViewHolder setOnWholeItemClickListener(View.OnClickListener listener) {
        viewItem.setOnClickListener(listener);
        return this;
    }

    public BaseViewHolder setOnWholeItemTouchListener(View.OnTouchListener listener) {
        viewItem.setOnTouchListener(listener);
        return this;
    }

    public BaseViewHolder setOnWholeItemLongClickListener(View.OnLongClickListener listener) {
        viewItem.setOnLongClickListener(listener);
        return this;
    }

    public View getViewItem() {
        return viewItem;
    }

    public BaseViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        if (view != null) {
            view.setOnClickListener(listener);
        }
        return this;
    }

    public BaseViewHolder setOnTouchListener(int viewId, View.OnTouchListener listener) {
        View view = getView(viewId);
        if (view != null) {
            view.setOnTouchListener(listener);
        }
        return this;
    }

    public BaseViewHolder setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
        View view = getView(viewId);
        if (view != null) {
            view.setOnLongClickListener(listener);
        }
        return this;
    }

    public BaseViewHolder setTag(int viewId, Object tag) {
        View view = getView(viewId);
        if (view != null) {
            view.setTag(tag);
        }
        return this;
    }

    /**
     * Sets the checked status of a checkable.
     *
     * @param viewId  The view id.
     * @param checked The checked status;
     * @return The BaseAdapterHelper for chaining.
     */
    public BaseViewHolder setChecked(int viewId, boolean checked) {
        Checkable view = getView(viewId);
        if (view != null) {
            view.setChecked(checked);
        }
        return this;
    }

    public BaseViewHolder setEnable(int viewId, boolean isEnable) {
        View view = getView(viewId);
        if (view != null) {
            view.setEnabled(isEnable);
        }
        return this;
    }
    public BaseViewHolder setSelected(int viewId, boolean select) {
        View view = getView(viewId);
        if (view != null) {
            view.setSelected(select);
        }
        return this;
    }

    public boolean isChecked(int viewId) {
        boolean isChecked = false;
        Checkable view = getView(viewId);
        if(view != null) {
            isChecked = view.isChecked();
        }
        return isChecked;
    }
}
