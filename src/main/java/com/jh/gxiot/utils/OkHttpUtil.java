package com.jh.gxiot.utils;

import com.jh.gxiot.constant.OkhttpMethodEnum;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Administrator
 * @version 1.0
 * @project GxIot
 * @description okHttp工具类
 * @date 2022/4/2 09:34:01
 */

@Component
@Slf4j
public class OkHttpUtil {


  private static   OkHttpClient okHttpClient;

  @Resource
    public  void setOkHttpClient(OkHttpClient okHttpClient) {
        OkHttpUtil.okHttpClient = okHttpClient;
    }

    /**
     * 根据map获取get请求参数
     * @param url
     * @param queries
     * @return
     */
    public static StringBuffer getQueryString(String url, Map<String,String> queries){
        StringBuffer sb = new StringBuffer(url);
        if (queries != null && queries.keySet().size() > 0){
            boolean firstFlag = true;
            Iterator<Map.Entry<String, String>> iterator = queries.entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry<String, String> entry = iterator.next();
                if (firstFlag){
                    sb.append("?").append(entry.getKey()).append("=").append(entry.getValue());
                    firstFlag = false;
                }else {
                    sb.append("&").append(entry.getKey()).append("=").append(entry.getValue());
                }
            }
        }
        return sb;
    }

    /**
     * 调用okhttp 的newCall方法
     * @param request
     * @return
     */
    public static String execNewCall(Request request){
        Response response = null;
        try{
            response = okHttpClient.newCall(request).execute();
            int status =response.code();
            if (response.isSuccessful()){
                return response.body().string();
            }
        }catch (Exception e){
            log.error("okhttp3 put error >> ex ={}",e.getStackTrace());
        }
        finally {
            if (response !=null){
                response.close();
            }
        }
        return "";
    }

    /**
     * 发送get请求
     * @param url
     * @param queries
     * @return
     */
    public static String get(String url, Map<String,String> queries){

        StringBuffer queryString = getQueryString(url, queries);
        Request request = new Request.Builder().url(queries.toString())
                .build();
       return execNewCall(request);
    }

    /**
     * 发送Post 表单提交
     * @param url
     * @param params
     * @param methodEnum
     * @return
     */
    public static String postFormParams(String url,Map<String,String> params,OkhttpMethodEnum methodEnum){
        FormBody.Builder builder = new FormBody.Builder();
        //添加参数
        if (params != null && params.keySet().size() > 0){
            params.forEach((k,v)->{
                builder.add(k,v);
            });
        }

        Request request = new Request.Builder().url(url).
                method(methodEnum.getMethodName(), builder.build())
                .build();
        return execNewCall(request);

    }

    /**
     * Post 发送Json 请求数据
     * @param url
     * @param jsonParams
     * @param methodEnum
     * @return
     */
    public static String PostJson(String url,String jsonParams,OkhttpMethodEnum methodEnum){
        RequestBody requestBody = RequestBody.create(jsonParams,MediaType.parse("application/json"));
        Request request = new Request.Builder()
                .url(url)
                .method(methodEnum.getMethodName(), requestBody)
                .build();
        return execNewCall(request);
    }

    public static String PostXml(String url,String xml){
        RequestBody requestBody = RequestBody.create(xml, MediaType.parse("application/xml; charset=utf-8"));
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        return execNewCall(request);


    }
}
