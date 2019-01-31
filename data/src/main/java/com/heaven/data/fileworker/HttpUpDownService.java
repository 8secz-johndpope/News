package com.heaven.data.fileworker;


import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * service统一接口数据
 * Created by WZG on 2016/7/16.
 */
public interface HttpUpDownService {

    /*断点续传下载接口*/
    @Streaming/*大文件需要加入这个判断，防止下载过程中写入到内存中*/
    @GET
    Flowable<ResponseBody> download(@Url String url, @Header("RANGE") String range);

    @Multipart
    @POST("upload")
    Flowable<ResponseBody> upload(@Part("description") RequestBody description,
                              @Part MultipartBody.Part file);
}
