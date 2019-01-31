package com.heaven.model.ifilm;

/**
 * Created by Leo on 2015/3/9.
 */

/**
 * 有返回数据的操作结果类
 *
 * @param <T> 模型泛型
 */
public class BaseModelJson<T> extends BaseModel {

    public T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
