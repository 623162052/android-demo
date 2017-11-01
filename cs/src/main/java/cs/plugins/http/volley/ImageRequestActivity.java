package cs.plugins.http.volley;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.NetworkImageView;

import butterknife.Bind;
import cs.app.R;
import cs.plugins.base.BaseActivity;
import cs.plugins.http.volley.util.RequestQueueUtil;

/**
 * 请求图片的几种方式
 */
public class ImageRequestActivity extends BaseActivity {
    private static final String TAG = "ImageRequestActivity";

    @Bind(R.id.img_imageRequest)
    ImageView imgImageRequest;
    @Bind(R.id.img_imageLoader)
    ImageView imgImageLoader;
    @Bind(R.id.networkImageView)
    NetworkImageView networkImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_request);

        imageRequest("http://www.baidu.com/img/bd_logo1.png");
        imageLoader("http://www.baidu.com/img/bd_logo1.png");
        networkImageView("http://www.baidu.com/img/bd_logo1.png");
    }

    /**
     * ImageRequest
     */
    public void imageRequest(String url) {
        ImageRequest imgRequest = new ImageRequest(url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        imgImageRequest.setImageBitmap(bitmap);
                    }
                }, 0, 0, null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        imgImageRequest.setImageResource(R.drawable.ic_action_search);
                    }
                });

        RequestQueueUtil.getInstance(this).addToRequestQueue(imgRequest);
    }


    /**
     * ImageLoader
     */
    public void imageLoader(String url) {
        ImageLoader mImageLoader = RequestQueueUtil.getInstance(this).getImageLoader();
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(imgImageLoader, R.drawable.ic_action_stop, R.drawable.ic_action_cancel);
        mImageLoader.get(url, listener);
    }

    /**
     * NetworkImageView
     * TODO：图片大时有问题
     */
    public void networkImageView(String url) {
        // Get the ImageLoader through your singleton class.
        ImageLoader mImageLoader = RequestQueueUtil.getInstance(this).getImageLoader();

        // Set the URL of the image that should be loaded into this view, and
        // specify the ImageLoader that will be used to make the request.
        networkImageView.setImageUrl(url, mImageLoader);
    }

}
