package cs.app.task;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import butterknife.Bind;
import cs.app.R;
import cs.plugins.base.BaseActivity;

/**
 * TODO: 移除HttpClient
 */
public class AsyncTaskActivity extends BaseActivity {
    private static String TAG = "AsyncTaskActivity";
    @Bind(R.id.imageView)
    ImageView mImageView;
    @Bind(R.id.button)
    Button mButton;
    @Bind(R.id.progressBar)
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestTask task = new TestTask();
                task.execute("http://www.chiphell.com/data/attachment/forum/201412/31/041559f6fp6fomb6mvbc7b.jpg");
            }
        });
    }

    /**
     * TASK
     */
    class TestTask extends AsyncTask<String, Integer, Bitmap> {

        /**
         * 准备运行
         * 在任务被执行之后立即由UI线程调用，通常用来建立任务，在用户接口（UI）上显示进度条
         */
        protected void onPreExecute() {//在 doInBackground(Params...)之前被调用，在ui线程执行
            //mImageView.setImageBitmap(null);
            mProgressBar.setProgress(0);//进度条复位
            Log.d(TAG, "onPreExecute() --> ExternalStorageState: " + Environment.getExternalStorageState());
        }

        /**
         * 正在后台运行
         * 在onPreExecute()方法执行结束后立即调用，通常在这里执行耗时的后台计算，计算的结果必须由该函数返回，并被传递到onPostExecute()中
         */
        @Override
        protected Bitmap doInBackground(String... params) {
            // publishProgress(0);//将会调用onProgressUpdate(Integer... progress)方法
            HttpClient hc = new DefaultHttpClient();
            publishProgress(10);
            HttpGet hg = new HttpGet(params[0]);//获取csdn的logo
            Log.d(TAG, "doInBackground() --> JSON: " + JSON.toJSONString(params));
            final Bitmap bm;
            try {
                HttpResponse hr = hc.execute(hg);
                bm = BitmapFactory.decodeStream(hr.getEntity().getContent());
            } catch (Exception e) {
                Log.e(TAG, "doInBackground() --> error: " + e.getMessage());
                return null;
            }
            publishProgress(100);
            //mImageView.setImageBitmap(result); 不能在后台线程操作ui
            return bm;
        }


        /**
         * 进度更新
         * 该函数由UI线程在publishProgress(Progress...)方法调用完后被调用。一般用于动态地显示一个进度条
         */
        protected void onProgressUpdate(Integer... progress) {//在调用publishProgress之后被调用，在ui线程执行
            Log.e(TAG, "onProgressUpdate() -- progress: " + JSON.toJSONString(progress));
            mProgressBar.setProgress(progress[0]);//更新进度条的进度
        }

        /**
         * 完成后台任务
         * 后台计算的结果会被作为参数传递给这一函数
         */
        protected void onPostExecute(Bitmap result) {//后台任务执行完之后被调用，在ui线程执行
            if (result != null) {
                Toast.makeText(AsyncTaskActivity.this, "成功获取图片", Toast.LENGTH_LONG).show();
                mImageView.setImageBitmap(result);
            } else {
                Toast.makeText(AsyncTaskActivity.this, "获取图片失败", Toast.LENGTH_LONG).show();
            }
        }

        /**
         * 取消任务
         * 在调用AsyncTask的cancel()方法时调用
         */
        protected void onCancelled() {//在ui线程执行
            mProgressBar.setProgress(0);//进度条复位
        }

    }

}

