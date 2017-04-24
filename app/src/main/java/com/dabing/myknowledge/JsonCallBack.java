package com.dabing.myknowledge;

import com.dabing.myknowledge.javabean.BaseResponse;
import com.dabing.myknowledge.utils.JSONUtil;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.AbsCallback;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.logging.Logger;

import okhttp3.Response;

/**
 * Created by myb on 2017/4/24.
 */

public abstract class JsonCallBack<T> extends AbsCallback<T> {
    @Override
    public T convertSuccess(Response response) throws Exception {
        //通过泛型解析
        Type genericSuperclass = getClass().getGenericSuperclass();//获得带有泛型的父类
        Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass).getActualTypeArguments();//获取参数化类型即泛型数组.因为可能泛型不止一层
        Type actualTypeArgument = actualTypeArguments[0];
        if (!(actualTypeArgument instanceof ParameterizedType))
            throw new IllegalStateException("没有填写泛型参数");
        //如果确实还有泛型，那么我们需要取出真实的泛型，
        Type rawType = ((ParameterizedType) actualTypeArgument).getRawType();
        //这里获取最终内部泛型的类型
        Type typeArgument = ((ParameterizedType) actualTypeArgument).getActualTypeArguments()[0];
        String responseString = response.body().string();
        if (typeArgument == Void.class) {
            BaseResponse baseResponse = JSONUtil.fromJson(responseString, BaseResponse.class);
            response.close();
            return (T) baseResponse;
        } else if (rawType == BaseResponse.class) {
            BaseResponse baseResponse = null;
            try {
                baseResponse = JSONUtil.fromJson(responseString, actualTypeArgument);
                response.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            String errorCode = baseResponse.head.errorCode;
//            if(checkServiceCode(errorCode)){//后台错误码自定义所以根据具体情况自行处理
            return (T) baseResponse;
        } else {
//                Type type = new TypeToken<BaseResponse<Body>>() {}.getType();
//                BaseResponse<Body> errMsg = JsonUtil.fromJson(responseString, type);
//                response.close();
            //抛错是为了能够调用callback的onError()方法
            throw new IllegalStateException("各种异常");
        }

    }
}
