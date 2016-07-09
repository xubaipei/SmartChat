package com.xubaipei.smartchat.biz.network.api;

import com.xubaipei.smartchat.common.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by user on 2016/7/7.
 */
public interface HttpApi {

    @GET("contract/{id}")
    Call<List<User>> getContract(@Path("id") String id);
}
