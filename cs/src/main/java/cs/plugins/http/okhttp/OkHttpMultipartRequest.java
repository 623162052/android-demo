package cs.plugins.http.okhttp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by shiwx on 2017/5/6.
 */
public class OkHttpMultipartRequest {

    private static final String TAG = "OkHttpMultipartRequest";
    private Gson gson = new GsonBuilder().create();
    private static OkHttpMultipartRequest instance;

    /**
     * 获取单实例
     */
    public static synchronized OkHttpMultipartRequest getInstance() {
        if (instance == null) {
            instance = new OkHttpMultipartRequest();
        }
        return instance;
    }

//    /**
//     * uploadMultipart
//     */
//    public void startTask(final Activity context, final String processId, final String processBh, TaskInfo taskInfo, final boolean uploadDefect) {
//        String requestUrl = HttpAddress.host + HttpAddress.UPLOAD_IMAGES;
//        requestUrl = VolleyHelper.handleUrl(requestUrl);
//
//        OkHttpClient client = HttpsTrustManager.allowSSL();
//        MultipartBody.Builder builder = new MultipartBody.Builder();
//        builder.setType(MediaType.parse("multipart/form-data; charset=utf-8"));
//
//        // 添加附件
//        final List<AttachmentInfo> pictureInfoList = PictureManager.getInstance().queryPictureListCopy(processId, processBh);
//        if (pictureInfoList != null && pictureInfoList.size() > 0) {
//            for (AttachmentInfo pictureInfo : pictureInfoList) {
//                File picFile = new File(pictureInfo.getPicPath());
//                if (picFile.exists()) {
//                    long fileSize = FileUtil.getFileSize(picFile);
//                    double fileMb = FileUtil.formatFileSize(fileSize, FileUtil.SIZETYPE_MB);
//                    pictureInfo.setByteSize(fileMb);
//
//                    RequestBody fileBody = RequestBody.create(MediaType.parse("image/jpeg; charset=utf-8"), picFile);
//                    builder.addFormDataPart("img", pictureInfo.getStorageName(), fileBody);
//                }
//            }
//        }
//
//        builder.addFormDataPart("taskInfo", gson.toJson(taskInfo));
//        builder.addFormDataPart("attachment", gson.toJson(pictureInfoList));
//
//        RequestBody requestBody = builder.build();
//        Request request = new Request.Builder().url(requestUrl).post(requestBody).build();
//
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                if (response.isSuccessful()) {
//                    LogUtil.d(TAG, response.body().string());
//
//                    if (uploadDefect) {
//                        // 从Realm 删除TaskInfo
//                        DefectLocalManager.getInstance().deleteDefectTask(processId);
//                    }
//
//                    // 从Realm中删除
//                    PictureManager.getInstance().delPictureList(processId, processBh);
//                    // 删除物理磁盘文件
//                    PictureManager.getInstance().delPictures(pictureInfoList);
//
//
//                    context.runOnUiThread(new Runnable() {
//                        public void run() {
//                            Toast.makeText(context, "业务操作成功", Toast.LENGTH_LONG).show();
//                        }
//                    });
//
//                    if (context instanceof AddTaskActivity) {
//                        AddTaskActivity mContext = (AddTaskActivity) context;
//                        mContext.finish();
//                    } else if (context instanceof DefectUploadActivity) {
//                        DefectUploadActivity mContext = (DefectUploadActivity) context;
//                        mContext.finish();
//                    }
//                }
//
//                ViewUtil.dismissLoadingDialog(context);
//            }
//
//            @Override
//            public void onFailure(Call call, IOException arg1) {
//                LogUtil.e(TAG, arg1.getMessage(), arg1);
//                ViewUtil.dismissLoadingDialog(context);
//
//                context.runOnUiThread(new Runnable() {
//                    public void run() {
//                        Toast.makeText(context, "业务操作异常", Toast.LENGTH_LONG).show();
//                    }
//                });
//            }
//
//        });
//    }

}
