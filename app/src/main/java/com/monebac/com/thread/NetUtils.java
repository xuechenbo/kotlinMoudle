package com.monebac.com.thread;

import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetUtils {

    public static void getMsg() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request build = new Request.Builder()
                .url(String.format("https://stock.gtimg.cn/data/index.php?appn=detail&action=data&c=%s&p=%d", "sh600036", 0))
                .get()
                .build();
        okHttpClient.newCall(build).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                toJson(str);
            }
        });
    }

    public static void toJson(String strContent){

    }
}
