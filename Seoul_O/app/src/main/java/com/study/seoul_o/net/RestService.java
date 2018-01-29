package com.study.seoul_o.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by jaehunpark on 2017. 7. 18..
 */

public interface RestService {

    final String ENDPOINT = "http://192.168.0.31:4000/";                  // base url
    final String ENDPOINT_ = "https://210.221.219.131:9443/";
    final String ENDPOINT__ = "http://seoul-o.run.goorm.io/";


    /**
     * User API
     */
    @POST("api/user/getToken")                               // base url + protocol
    Call<Map<Object,Object>> getToken(@Body Map<Object, Object> body, @Header("token") String header);

    @POST("api/user/loginFacebook")
    Call<Map<Object,Object>> loginFacebook(@Body Map<Object, Object> body, @Header("token") String header);

    @POST("api/user/loginGoogle")
    Call<Map<Object,Object>> loginGoogle(@Body Map<Object, Object> body, @Header("token") String header);

    @GET("api/user/getUserInfo")
    Call<Map<Object,Object>> getUserInfo(@Header("token") String header);

    @GET("api/user/getMyLocation/{lat}/{lon}")
    Call<Map<Object, Object>> getMyLocation(@Path("lat") String lat, @Path("lon") String lon, @Header("token") String header);

    @POST("user/getfooddetail")
    Call<ArrayList<Map<String,String>>> getFoodDetail(@Body Map<String, String> body);

    @POST("user/getfoodreview")
    Call<Object> getFoodReview(@Body Map<Object, Object> body);

    @POST("user/getfoodreview2")
    Call<Object> getFoodReview2(@Body Map<Object, Object> body);

    @POST("user/addfoodreview")
    Call<Object> addFoodReview(@Body Map<Object, Object> body);

    class Creator{
        public static RestService newRestService(){

            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(RestService.ENDPOINT__)
                    .addConverterFactory(GsonConverterFactory.create(gson))             // gson converter를 연결함. response 파싱 또는 request 파싱
                    .client(SSLConnect.getInstance().createSSLClient())             // ssl(https) 사용시 주석해제할것
                    .build();
            return retrofit.create(RestService.class);
        }
    }
}
