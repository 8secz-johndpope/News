package com.heaven.base.vm;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.databinding.BaseObservable;
import android.support.annotation.NonNull;

import com.heaven.base.presenter.BasePresenter;

/**
 * FileName: com.heaven.base.vm.BaseContextViewModel.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-02-25 13:56
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public abstract class BaseViewModel extends ViewModel implements IViewModel{
   private Application application;

   public Application getApplication() {
      return application;
   }

   public void setApplication(Application application) {
      this.application = application;
   }
}
