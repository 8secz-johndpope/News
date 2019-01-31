package com.heaven.data.cache;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.LruCache;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;


/**
 * 作者:Heaven
 * 时间: on 2017/4/6 12:32
 * 邮箱:heavenisme@aliyun.com
 */

public class MemoryCache<K, V> extends LruCache<K, V> {
    private EntryRemovedListener mRemovedListener;

    public interface EntryRemovedListener<K, V> {
        void entryRemoved(K key, V oldValue, V newValue);

        V getSecondCache(K key);
    }

    public MemoryCache(int maxSize, EntryRemovedListener removedListener) {
        super(maxSize);
        this.mRemovedListener = removedListener;
    }

    @Override
    protected int sizeOf(K key, V value) {
        int itemSize = 0;
        if (value != null) {
            if (value instanceof Bitmap) {
                itemSize = ((Bitmap) value).getByteCount() / 1024;
            } else if (value instanceof String) {
                itemSize = ((String) value).getBytes().length / 1024;
            } else if (value instanceof Serializable) {
                ByteArrayOutputStream bo = new ByteArrayOutputStream();
                try {
                    ObjectOutputStream oo = new ObjectOutputStream(bo);
                    oo.writeObject(value);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                itemSize = bo.toByteArray().length / 1024;
            } else if (value instanceof BitmapDrawable) {//计算一个这个BitmapDrawable占用内存
                Bitmap bitmap = ((BitmapDrawable) value).getBitmap();
                itemSize = bitmap.getByteCount() / 1024;
            }
        }
        return itemSize;
    }

    @SuppressWarnings(value = {"unchecked"})
    @Override
    protected void entryRemoved(boolean evicted, K key, V oldValue, V newValue) {
        super.entryRemoved(evicted, key, oldValue, newValue);
        if (mRemovedListener != null && evicted) {
            mRemovedListener.entryRemoved(key, oldValue, newValue);
        }
    }

    @SuppressWarnings(value = {"unchecked"})
    @Override
    protected V create(K key) {
        V v = null;
        if (mRemovedListener != null) {
            v = (V) mRemovedListener.getSecondCache(key);
        }
        return v;
    }
}
