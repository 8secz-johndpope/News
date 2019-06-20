package com.heaven.news.ui.model.bean.base;

import com.heaven.news.ui.model.vm.MainVm;

/**
 * FileName: com.heaven.news.ui.vm.model.base.BookData.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-04-01 20:07
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class BookData {
    public static int BOOK_GO = 1;
    public static int BOOK_GO_BACK= 2;
    public static int BOOK_MULT = 3;

    public int bookType = -1;

    public MainVm mainViewModel;

    public BookData(int bookType, MainVm mainViewModel) {
        this.bookType = bookType;
        this.mainViewModel = mainViewModel;
    }
}
