package com.example.day9work;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.day9work.api.ApiService;
import com.example.day9work.bean.UpLoadBean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpLoadActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mUploadBtn;
    private ImageView mImg;
    private Button mDownloadBtn;
    private ProgressBar mPb;
    private TextView mProgressTv;
    private File file = new File(Environment.getExternalStorageDirectory()+"/a.png");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_down);
        initView();
    }

    private void initView() {

        mUploadBtn = (Button) findViewById(R.id.btn_upload);
        mUploadBtn.setOnClickListener(this);
        mImg = (ImageView) findViewById(R.id.img);
        mDownloadBtn = (Button) findViewById(R.id.btn_download);
        mDownloadBtn.setOnClickListener(this);
        mPb = (ProgressBar) findViewById(R.id.pb);
        mProgressTv = (TextView) findViewById(R.id.tv_progress);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_upload:
                upload();//上传
                break;
            case R.id.btn_download:
                download();
                break;
            default:
                break;
        }
    }

    private void download() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(ApiService.down_Load)
                .build();
        ApiService service = retrofit.create(ApiService.class);
        Observable<ResponseBody> observable = service.download();
        observable.subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        InputStream inputStream = responseBody.byteStream();
                        long contentLength = responseBody.contentLength();
                        savaFile(inputStream, contentLength, Environment.getExternalStorageDirectory() + "/aaa1.apk");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void savaFile(InputStream inputStream, final long contentLength, String path) {
        try {
            FileOutputStream outputStream = new FileOutputStream(new File(path));
            int len = -1;
            int count = 0;
            byte[] bytes = new byte[1024 * 10];
            while ((len = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
                count += len;
                final long finalCount = count;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        mPb.setProgress((int) finalCount);
                        mProgressTv.setText((int) 100 * finalCount / contentLength + "%");
                    }
                });
            }

            inputStream.close();
            outputStream.close();
            Log.e("TAG", "下载完成");
            InstallUtil.installApk(this, path);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2019) {
            InstallUtil.installApk(this, Environment.getExternalStorageDirectory() + "/a.apk");//再次执行安装流程，包含权限判等
        }
    }

    private void upload() {

        //获取retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.up_Load)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //接口服务对象
        ApiService service = retrofit.create(ApiService.class);
        //被观察者
        RequestBody body = RequestBody.create(MediaType.parse("image/png"), file);
        MultipartBody.Part file = MultipartBody.Part.createFormData("file", this.file.getName(), body);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), "aaa");
        Observable<UpLoadBean> observable = service.upload(requestBody, file);
        //订阅
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UpLoadBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UpLoadBean upLoadBean) {
                        int code = upLoadBean.getCode();
                        if (code == 200) {
                            Glide.with(UpLoadActivity.this).load(upLoadBean.getData().getUrl()).into(mImg);
                        } else {
                            Toast.makeText(UpLoadActivity.this, upLoadBean.getRes(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(UpLoadActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
