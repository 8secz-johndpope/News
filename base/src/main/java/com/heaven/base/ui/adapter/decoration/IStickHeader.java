package com.heaven.base.ui.adapter.decoration;

/**
 * FileName: com.heaven.base.ui.adapter.decoration.IStickHeader.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-05-08 11:45
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public interface IStickHeader {
    boolean isFirstInGroup(int position);

    boolean isLastInGroup(int position);

    String  getTitle(int position);
}
