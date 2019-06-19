package com.heaven.news.net.convert.fastjson;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.heaven.data.net.DataResponse;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * @author ligboy, wenshao
 * @author Victor.Zxy
 */
public class FastJsonnConverterFactory extends Converter.Factory {
    private static final Charset UTF8 = Charset.forName("UTF-8");
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");

    private FastJsonConfig fastJsonConfig;

    @Deprecated
    private static final Feature[] EMPTY_SERIALIZER_FEATURES = new Feature[0];
    @Deprecated
    private ParserConfig parserConfig = ParserConfig.getGlobalInstance();
    @Deprecated
    private int featureValues = JSON.DEFAULT_PARSER_FEATURE;
    @Deprecated
    private Feature[] features;
    @Deprecated
    private SerializeConfig serializeConfig;
    @Deprecated
    private SerializerFeature[] serializerFeatures;

    public FastJsonnConverterFactory() {
        this.fastJsonConfig = new FastJsonConfig();
    }

    public FastJsonnConverterFactory(FastJsonConfig fastJsonConfig) {
        this.fastJsonConfig = fastJsonConfig;
    }

    public static FastJsonnConverterFactory create() {
        return create(new FastJsonConfig());
    }

    public static FastJsonnConverterFactory create(FastJsonConfig fastJsonConfig) {
        if (fastJsonConfig == null) {
            throw new NullPointerException("fastJsonConfig == null");
        }
        return new FastJsonnConverterFactory(fastJsonConfig);
    }

    @Override
    public Converter<ResponseBody, Object> responseBodyConverter(Type type, //
                                                                 Annotation[] annotations, //
                                                                 Retrofit retrofit) {
        return new ResponseBodyConverter<Object>(type);
    }

    @Override
    public Converter<Object, RequestBody> requestBodyConverter(Type type, //
                                                               Annotation[] parameterAnnotations, //
                                                               Annotation[] methodAnnotations, //
                                                               Retrofit retrofit) {
        return new RequestBodyConverter<Object>();
    }

    public FastJsonConfig getFastJsonConfig() {
        return fastJsonConfig;
    }

    public FastJsonnConverterFactory setFastJsonConfig(FastJsonConfig fastJsonConfig) {
        this.fastJsonConfig = fastJsonConfig;
        return this;
    }

    /**
     * Gets parser config.
     *
     * @return the parser config
     *
     * @see FastJsonConfig#getParserConfig()
     * @deprecated
     */
    @Deprecated
    public ParserConfig getParserConfig() {
        return fastJsonConfig.getParserConfig();
    }

    /**
     * Sets parser config.
     *
     * @param config
     *         the config
     *
     * @return the parser config
     *
     * @see FastJsonConfig#setParserConfig(ParserConfig)
     * @deprecated
     */
    @Deprecated
    public FastJsonnConverterFactory setParserConfig(ParserConfig config) {
        fastJsonConfig.setParserConfig(config);
        return this;
    }

    /**
     * Gets parser feature values.
     *
     * @return the parser feature values
     *
     * @see JSON#DEFAULT_PARSER_FEATURE
     * @deprecated
     */
    @Deprecated
    public int getParserFeatureValues() {
        return JSON.DEFAULT_PARSER_FEATURE;
    }

    /**
     * Sets parser feature values.
     *
     * @param featureValues
     *         the feature values
     *
     * @return the parser feature values
     *
     * @see JSON#DEFAULT_PARSER_FEATURE
     * @deprecated
     */
    @Deprecated
    public FastJsonnConverterFactory setParserFeatureValues(int featureValues) {
        return this;
    }

    /**
     * Get parser features feature [].
     *
     * @return the feature []
     *
     * @see FastJsonConfig#getFeatures()
     * @deprecated
     */
    @Deprecated
    public Feature[] getParserFeatures() {
        return fastJsonConfig.getFeatures();
    }

    /**
     * Sets parser features.
     *
     * @param features
     *         the features
     *
     * @return the parser features
     *
     * @see FastJsonConfig#setFeatures(Feature...)
     * @deprecated
     */
    @Deprecated
    public FastJsonnConverterFactory setParserFeatures(Feature[] features) {
        fastJsonConfig.setFeatures(features);
        return this;
    }

    /**
     * Gets serialize config.
     *
     * @return the serialize config
     *
     * @see FastJsonConfig#getSerializeConfig()
     * @deprecated
     */
    @Deprecated
    public SerializeConfig getSerializeConfig() {
        return fastJsonConfig.getSerializeConfig();
    }

    /**
     * Sets serialize config.
     *
     * @param serializeConfig
     *         the serialize config
     *
     * @return the serialize config
     *
     * @see FastJsonConfig#setSerializeConfig(SerializeConfig)
     * @deprecated
     */
    @Deprecated
    public FastJsonnConverterFactory setSerializeConfig(SerializeConfig serializeConfig) {
        fastJsonConfig.setSerializeConfig(serializeConfig);
        return this;
    }

    /**
     * Get serializer features serializer feature [].
     *
     * @return the serializer feature []
     *
     * @see FastJsonConfig#getSerializerFeatures()
     * @deprecated
     */
    @Deprecated
    public SerializerFeature[] getSerializerFeatures() {
        return fastJsonConfig.getSerializerFeatures();
    }

    /**
     * Sets serializer features.
     *
     * @param features
     *         the features
     *
     * @return the serializer features
     *
     * @see FastJsonConfig#setSerializerFeatures(SerializerFeature...)
     * @deprecated
     */
    @Deprecated
    public FastJsonnConverterFactory setSerializerFeatures(SerializerFeature[] features) {
        fastJsonConfig.setSerializerFeatures(features);
        return this;
    }

    final class ResponseBodyConverter<T> implements Converter<ResponseBody, T> {
        private Type type;

        ResponseBodyConverter(Type type) {
            this.type = type;
        }

        @Override
        public T convert(ResponseBody value) throws IOException {
            DataResponse response = new DataResponse();
            byte[] oriByteData = value.bytes();
            try {
                response.data = new String(oriByteData);
            } catch (Exception e) {
                response.data = new String(oriByteData);
                Log.i("convert", "JSON parse error: " + e.getMessage() + "-----return ori string data---" + response.data);
            } finally {
                value.close();
            }

            return (T) response;
        }
    }

    final class RequestBodyConverter<T> implements Converter<T, RequestBody> {
        RequestBodyConverter() {
        }

        @Override
        public RequestBody convert(T value) throws IOException {
            try {
                byte[] content = JSON.toJSONBytes(fastJsonConfig.getCharset()
                        , value
                        , fastJsonConfig.getSerializeConfig()
                        , fastJsonConfig.getSerializeFilters()
                        , fastJsonConfig.getDateFormat()
                        , JSON.DEFAULT_GENERATE_FEATURE
                        , fastJsonConfig.getSerializerFeatures()
                );
                return RequestBody.create(MEDIA_TYPE, content);
            } catch (Exception e) {
                throw new IOException("Could not write JSON: " + e.getMessage(), e);
            }
        }
    }
}
