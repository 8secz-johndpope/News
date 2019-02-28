package com.heaven.news.aop;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.heaven.annotation.aspect.Permission;
import com.heaven.base.utils.MPermissionUtils;
import com.heaven.news.engine.AppEngine;
import com.orhanobut.logger.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * 作者:Heaven
 * 时间: on 2017/3/28 10:34
 * 邮箱:heavenisme@aliyun.com
 * 权限请求
 */
@Aspect
public class SysPermissionAspect {
    ProceedingJoinPoint requestJoinPoint;
    String[] permissions;
    @Around("execution(@com.heaven.annotation.aspect.Permission * *(..)) && @annotation(permission)")
    public void aroundJoinPoint(ProceedingJoinPoint joinPoint, Permission permission) throws Throwable {
        Activity ac = AppEngine.getInstance().getCurActivity();
            if (!MPermissionUtils.checkPermissions(ac, permission.value())) {
                requestJoinPoint = joinPoint;
                permissions = permission.value();
                Logger.d("heaven_aop","请允许或拒绝权限申请!!!");
                new AlertDialog.Builder(ac)
                        .setTitle("提示")
                        .setMessage("为了应用可以正常使用，请您点击确认申请权限。")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Logger.d("heaven_aop","拒绝权限申请!!!");
                            }
                        })
                        .setPositiveButton("允许", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Logger.d("heaven_aop","开始申请权限.....");
                                MPermissionUtils.requestPermissionsResult(ac, 1, permission.value()
                                        , new MPermissionUtils.OnPermissionListener() {
                                            @Override
                                            public void onPermissionGranted() {
                                                try {
                                                    Logger.d("heaven_aop","权限申请成功");
                                                    joinPoint.proceed();//获得权限，执行原方法
                                                } catch (Throwable e) {
                                                    e.printStackTrace();
                                                }
                                            }

                                            @Override
                                            public void onPermissionDenied() {
                                                if(permission.force()) {
                                                    Logger.d("heaven_aop","权限申请失败");
                                                    MPermissionUtils.showTipsDialog(ac);
                                                } else {
                                                    try {
                                                        Logger.d("heaven_aop","权限申请失败继续执行方法");
                                                        joinPoint.proceed();//不是强制的权限继续执行
                                                    } catch (Throwable throwable) {
                                                        throwable.printStackTrace();
                                                    }
                                                }
                                            }
                                        });
                            }
                        })
                        .create()
                        .show();
            } else {
                Logger.d("heaven_aop","已有该权限");
                joinPoint.proceed();//获得权限，执行原方法
            }
        }
}


