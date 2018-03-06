package cs.plugins.http.okhttp.samples;

import okhttp3.*;

import java.io.IOException;

public class OkHttpDemo {

    /**
     * GET A URL
     * @param url
     * @return
     * @throws IOException
     */
    String run(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    /**
     * POST TO A SERVER
     * @param url
     * @param json
     * @return
     * @throws IOException
     */
    String post(String url, String json) throws IOException {
        final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

}
