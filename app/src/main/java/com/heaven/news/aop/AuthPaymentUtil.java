package com.heaven.news.aop;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.heaven.annotation.aspect.AuthPayment;
import com.orhanobut.logger.Logger;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * FileName: com.heaven.flybetter.aop.AuthPaymentUtil.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2017-09-27 10:41
 *
 * @version V1.0 支付统一回调
 */
public class AuthPaymentUtil {
    private static AuthPaymentInfo mAuthPaymentInfo;

    static void authPayMoney(Activity context, AuthPaymentInfo authPaymentInfo, AuthPayment authPayment) {
        mAuthPaymentInfo = authPaymentInfo;
        if (authPayment.type() == 1) {//微信支付
            weChatPay(context, authPaymentInfo);
            Logger.i("微信支付" + authPaymentInfo.toString());
        } else if (authPayment.type() == 3) {//阿里支付
            aliPay(context, authPaymentInfo);
            Logger.i("支付宝支付" + authPaymentInfo.toString());
        }
    }

    private static void weChatPay(@NonNull Activity currentContext, @NonNull AuthPaymentInfo authPaymentInfo) {
        IWXAPI api = WXAPIFactory.createWXAPI(currentContext, authPaymentInfo.appId);

        if (api.isWXAppInstalled()) {
            try {
                JSONObject jsonObject = new JSONObject(authPaymentInfo.signData);
                //将应用的APPId注册到微信
                api.registerApp(authPaymentInfo.appId);
                PayReq req = new PayReq();
                req.appId = authPaymentInfo.appId;
                req.partnerId = authPaymentInfo.partnerId;
                req.prepayId = jsonObject.getString("prepayid");
                req.nonceStr = jsonObject.getString("noncestr");
                req.timeStamp = jsonObject.getString("timestamp");
                req.packageValue = jsonObject.getString("package");
                req.sign = jsonObject.getString("sign");
                // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
                api.sendReq(req);
            } catch (JSONException e) {
                Toast.makeText(currentContext,"微信SDK支付签名异常" + e.getMessage(),Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(currentContext,"微信SDK支付签名异常",Toast.LENGTH_LONG).show();
        }
    }

    private static void aliPay(@NonNull Activity currentContext, @NonNull AuthPaymentInfo authPaymentInfo) {
        // 构造PayTask 对象
        PayTask alipay = new PayTask(currentContext);
        // 调用支付接口
        Map<String, String> result = alipay.payV2(authPaymentInfo.signData, true);

        PayResult payResult = new PayResult(result);
        /**
         对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
         */
        String resultInfo = payResult.getResult();// 同步返回需要验证的信息

        String resultStatus = payResult.getResultStatus();
        // 判断resultStatus 为9000则代表支付成功
        if (TextUtils.equals(resultStatus, "9000")) {
            paymentResult(true,null);
            // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
            Toast.makeText(currentContext, "支付成功", Toast.LENGTH_SHORT).show();
        } else {
            paymentResult(false,"支付失败");
            // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
            Toast.makeText(currentContext, "支付失败", Toast.LENGTH_SHORT).show();
        }
    }

    public static void paymentResult(boolean isSuccess,@Nullable String errorMsg) {
        if(mAuthPaymentInfo != null && mAuthPaymentInfo.mPayResultListener != null) {
            mAuthPaymentInfo.isPaySuccess = isSuccess;
            mAuthPaymentInfo.errorMsg= errorMsg;
            mAuthPaymentInfo.mPayResultListener.payResult(mAuthPaymentInfo);
            mAuthPaymentInfo = null;
        }
    }

    public interface PayResultListener{
        void payResult(AuthPaymentInfo authPaymentInfo);
    }
}
