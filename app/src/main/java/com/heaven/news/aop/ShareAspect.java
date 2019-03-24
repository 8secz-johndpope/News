package com.heaven.news.aop;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.widget.Toast;

import com.heaven.annotation.aspect.Share;
import com.heaven.news.BuildConfig;
import com.heaven.news.R;
import com.heaven.news.engine.AppEngine;
import com.orhanobut.logger.Logger;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXAppExtendObject;
import com.tencent.mm.sdk.modelmsg.WXEmojiObject;
import com.tencent.mm.sdk.modelmsg.WXImageObject;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXMusicObject;
import com.tencent.mm.sdk.modelmsg.WXTextObject;
import com.tencent.mm.sdk.modelmsg.WXVideoObject;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.tauth.Tencent;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.io.ByteArrayOutputStream;
import java.io.File;

/**
 * FileName: com.heaven.flybetter.aop.ShareAspect.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2017-09-05 19:52
 *
 * @version V1.0 分享功能
 */
@Aspect
public class ShareAspect {
    private static final int THUMB_SIZE = 150;

    @Pointcut("call(@com.heaven.annotation.aspect.Share * *(..))")
    public void callMethod() {
    }


    @Pointcut("execution(@com.heaven.annotation.aspect.Share * *(..))")
    public void executionMethod() {
    }


    @Before("executionMethod() && @annotation(share)")
    public void beforeMethodExecution(JoinPoint joinPoint, Share share) {
        Logger.i("before->beforeMethodExecution");
    }

    @Before("callMethod() && @annotation(share)")
    public void beforeMethodCall(JoinPoint joinPoint, Share share) {
        Logger.i("before->beforeMethodCall");
    }


    /**
     * 不能和Before、After一起使用
     * @param joinPoint
     * @throws Throwable
     */
    @Around("callMethod() && @annotation(share)")
    public ShareInfo aroundMethodCall(ProceedingJoinPoint joinPoint, Share share) throws Throwable {
        Logger.i("around->callMethod--"/* + joinPoint.getTarget().toString() + "#" + joinPoint.getSignature().getName()*/);

        // 执行原代码
        return (ShareInfo) joinPoint.proceed();
    }

    /**
     * 不能和Before、After一起使用
     * @param joinPoint
     * @throws Throwable
     */
    @Around("executionMethod() && @annotation(share)")
    public ShareInfo aroundMethodExecution(ProceedingJoinPoint joinPoint, Share share) throws Throwable {
        Logger.i("around->executionMethod--" /*+ joinPoint.getTarget().toString() + "#" + joinPoint.getSignature().getName()*/);

        // 执行原代码
        return (ShareInfo) joinPoint.proceed();
    }

    @After("executionMethod() && @annotation(share)")
    public void afterMethodExecution(JoinPoint joinPoint, Share share) {
        Logger.i("After->afterMethodExecution");
    }

    @After("callMethod() && @annotation(share)")
    public void afterMethodCall(JoinPoint joinPoint, Share share) {
        Logger.i("After->afterMethodCall");
    }

    /**
     * handler
     * 不支持@After、@Around
     */
    @Before("handler(java.lang.ArithmeticException)")
    public void handler() {
        Logger.i("handler");
    }

    /**
     * @param throwable
     *
     * @AfterThrowing
     */
    @AfterThrowing(pointcut = "call(* *..*(..))", throwing = "throwable")
    public void anyFuncThrows(Throwable throwable) {
        Logger.i("hurtThrows: " + throwable);
    }


    @AfterReturning(value = "executionMethod() && @annotation(share)", returning = "shareInfo")
    public void share(ShareInfo shareInfo, Share share) {
        if (share != null) {
            if (share.type() == 1) {
                shareWeiXin(shareInfo);
                Logger.i("微信分享" + shareInfo);
            } else if (share.type() == 2) {
                shareWeiQQ(shareInfo);
                Logger.i("qq分享" + shareInfo);
            }
        }
    }

    private void shareWeiXin(ShareInfo shareInfo) {
        if (shareInfo != null) {
            Activity context = AppEngine.instance().getCurActivity();
            IWXAPI api = WXAPIFactory.createWXAPI(context, BuildConfig.WX_APP_ID);
            if (!api.isWXAppInstalled()) {
                Toast.makeText(context, "您手机尚未安装微信，请安装后再登录", Toast.LENGTH_LONG).show();
                return;
            }

            SendMessageToWX.Req req = new SendMessageToWX.Req(); //创建一个请求对象
            WXMediaMessage msg = new WXMediaMessage(); //这个对象是用来包裹发送信息的对象
            req.message = msg; //把msg放入请求对象中
            msg.title = shareInfo.title;
            msg.description = shareInfo.description;
            switch (shareInfo.wx_type) {
                case ShareInfo.WX_FRIEND:
                    req.scene = SendMessageToWX.Req.WXSceneSession;//设置发送给朋友
                    break;
                case ShareInfo.WX_SQUARE:
                    req.scene = SendMessageToWX.Req.WXSceneTimeline;//表示发送朋友圈
                    break;
                case ShareInfo.WX_COLLECT:
                    req.scene = SendMessageToWX.Req.WXSceneFavorite;//表示发送朋友圈
                    break;
            }

            switch (shareInfo.wx_send_ype) {
                case ShareInfo.WX_TXT://文本
                    req.transaction = buildTransaction("text");
                    WXTextObject textObj = new WXTextObject();
                    textObj.text = shareInfo.description;
                    msg.mediaObject = textObj;
                    break;
                case ShareInfo.WX_IMG://图片
                    req.transaction = buildTransaction("img");
                    WXImageObject imgObj = new WXImageObject();
                    Bitmap bitmap;
                    if (!TextUtils.isEmpty(shareInfo.imagePath)) {
                        File file = new File(shareInfo.imagePath);
                        if (!file.exists()) {
                            String tip = context.getString(R.string.image_path_not_exit);
                            Toast.makeText(context, tip + " path = " + shareInfo.imagePath, Toast.LENGTH_LONG).show();
                            break;
                        }
                        imgObj.setImagePath(shareInfo.imagePath);
                        bitmap = BitmapFactory.decodeFile(shareInfo.imagePath);
                    } else {
                        bitmap = BitmapFactory.decodeResource(context.getResources(), shareInfo.rImageId);
                    }

                    if (bitmap != null) {
                        imgObj.imageData = bmpToByteArray(bitmap, true);
                        bitmap.recycle();
                    }

                    fillThumbBmp(context, msg, shareInfo);

                    msg.mediaObject = imgObj;
                    break;
                case ShareInfo.WX_MUSIC://音乐
                    req.transaction = buildTransaction("music");
                    WXMusicObject music = new WXMusicObject();
                    music.musicUrl = shareInfo.musicUrl;

                    fillThumbBmp(context, msg, shareInfo);

                    msg.mediaObject = music;
                    break;
                case ShareInfo.WX_VIDEO://视频
                    req.transaction = buildTransaction("video");
                    WXVideoObject video = new WXVideoObject();
                    video.videoUrl = shareInfo.videoUrl;

                    fillThumbBmp(context, msg, shareInfo);

                    msg.mediaObject = video;
                    break;
                case ShareInfo.WX_WEB://网页
                    req.transaction = buildTransaction("web");
                    WXWebpageObject webpageObject = new WXWebpageObject();
                    webpageObject.webpageUrl = shareInfo.webpageUrl;

                    fillThumbBmp(context, msg, shareInfo);

                    msg.mediaObject = webpageObject;
                    break;
                case ShareInfo.WX_APP_INFO://app信息
                    req.transaction = buildTransaction("app_info");
                    final WXAppExtendObject appdata = new WXAppExtendObject();

                    fillThumbBmp(context, msg, shareInfo);

                    msg.mediaObject = appdata;
                    break;
                case ShareInfo.WX_EMOJI://表情
                    req.transaction = buildTransaction("emoji");
                    WXEmojiObject emoji = new WXEmojiObject();
                    emoji.emojiPath = shareInfo.emojiPath;

                    fillThumbBmp(context, msg, shareInfo);

                    msg.mediaObject = emoji;
                    break;
            }
            boolean b = AppEngine.iWXApi.sendReq(req); //如果调用成功微信,会返回true
        }
    }


    private void fillThumbBmp(Context context, WXMediaMessage msg, ShareInfo shareInfo) {
        Bitmap bitmap;
        Bitmap thumbBmp;
        if (!TextUtils.isEmpty(shareInfo.thumbBmpPath)) {
            File file = new File(shareInfo.thumbBmpPath);
            if (!file.exists()) {
                String tip = context.getString(R.string.image_path_not_exit);
                Toast.makeText(context, tip + " path = " + shareInfo.thumbBmpPath, Toast.LENGTH_LONG).show();
            }
            bitmap = BitmapFactory.decodeFile(shareInfo.thumbBmpPath);
            thumbBmp = Bitmap.createScaledBitmap(bitmap, THUMB_SIZE, THUMB_SIZE, true);
        } else {
            bitmap = BitmapFactory.decodeResource(context.getResources(), shareInfo.rThumbBmpId);
            thumbBmp = Bitmap.createScaledBitmap(bitmap, THUMB_SIZE, THUMB_SIZE, true);
        }

        if (bitmap != null) {
            bitmap.recycle();
        }

        if (thumbBmp != null) {
            msg.thumbData = bmpToByteArray(thumbBmp, true);
            thumbBmp.recycle();
        }
    }


    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }


    private void shareWeiQQ(ShareInfo shareInfo) {
        Activity context = AppEngine.instance().getCurActivity();
        Tencent tencent = Tencent.createInstance(BuildConfig.QQ_APP_ID, context);
    }


    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }

        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


    private void onClickWeChatLogin() {
        if (!AppEngine.iWXApi.isWXAppInstalled()) {
//            ToastUtils.toast("您手机尚未安装微信，请安装后再登录");
            return;
        }
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk_xb_live_state";//官方说明：用于保持请求和回调的状态，授权请求后原样带回给第三方。该参数可用于防止csrf攻击（跨站请求伪造攻击），建议第三方带上该参数，可设置为简单的随机数加session进行校验
        AppEngine.iWXApi.sendReq(req);
    }
}
