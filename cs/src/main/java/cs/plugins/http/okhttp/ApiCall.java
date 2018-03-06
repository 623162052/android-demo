package cs.plugins.http.okhttp;

import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ApiCall {

    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(3000l, TimeUnit.MILLISECONDS)
            .readTimeout(3000l, TimeUnit.MILLISECONDS)
            .addInterceptor( new LogInterceptor())
            .build();


    /**
     * GET network request
     */
    public static String GET(OkHttpClient client, HttpUrl url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    /**
     * POST network request
     */
    public static String POST(OkHttpClient client, HttpUrl url, RequestBody body) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }


    /**
     * 取消请求
     */
    public void cancelCall(){
//        //隊列中的call
//        for (Call call : getInstance().dispatcher().queuedCalls()) {
//            if (object.equals(call.request().tag())) {
//                call.cancel();
//            }
//        }
//        //運行中的call
//        for (Call call : getInstance().dispatcher().runningCalls()) {
//            if (object.equals(call.request().tag())) {
//                call.cancel();
//            }
//        }

    }
}
