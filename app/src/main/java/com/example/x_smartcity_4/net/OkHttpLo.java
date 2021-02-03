package com.example.x_smartcity_4.net;

import org.json.JSONObject;

import java.io.IOException;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/1/28  14:42
 */
public interface OkHttpLo {
    void onResponse(JSONObject jsonObject);

    void onFailure(IOException obj);
}
