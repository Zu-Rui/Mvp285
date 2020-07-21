package com.example.day9umeng;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mShareByboxBtn;

    //创建umeng分享的监听
    private UMShareListener umShareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(MainActivity.this, "成功了", Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(MainActivity.this, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(MainActivity.this, "取消了", Toast.LENGTH_LONG).show();

        }
    };

    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {

            Toast.makeText(MainActivity.this, "成功了", Toast.LENGTH_LONG).show();
            Set<Map.Entry<String, String>> entries = data.entrySet();
            for (Map.Entry entry:entries){
                String key = (String) entry.getKey();
                String value = (String) entry.getValue();
                Log.i("111", "onComplete: key:"+key+",value:"+value);
            }
        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {

            Toast.makeText(MainActivity.this, "失败：" + t.getMessage(),                                     Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(MainActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };
    private Button mWeiboBtn;
    private Button mQqBtn;
    private Button mWeixinBtn;
    private UMImage image;
    private Button mWeixinLoginBtn;
    private Button mQqLoginBtn;
    private Button mWeiboLoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        checkPermiss();

    }

    private void initView() {
        mShareByboxBtn = (Button) findViewById(R.id.btn_share_bybox);
        mShareByboxBtn.setOnClickListener(this);
        mWeiboBtn = (Button) findViewById(R.id.btn_weibo);
        mWeiboBtn.setOnClickListener(this);
        mQqBtn = (Button) findViewById(R.id.btn_qq);
        mQqBtn.setOnClickListener(this);
        mWeixinBtn = (Button) findViewById(R.id.btn_weixin);
        mWeixinBtn.setOnClickListener(this);
        mWeixinLoginBtn = (Button) findViewById(R.id.btn_weixin_login);
        mWeixinLoginBtn.setOnClickListener(this);
        mQqLoginBtn = (Button) findViewById(R.id.btn_qq_login);
        mQqLoginBtn.setOnClickListener(this);
        mWeiboLoginBtn = (Button) findViewById(R.id.btn_weibo_login);
        mWeiboLoginBtn.setOnClickListener(this);
    }

    //Android6.0权限适配
    private void checkPermiss() {
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.CALL_PHONE,
                    Manifest.permission.READ_LOGS,
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.SET_DEBUG_APP,
                    Manifest.permission.SYSTEM_ALERT_WINDOW,
                    Manifest.permission.GET_ACCOUNTS,
                    Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this, mPermissionList, 123);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {

    }

    /**
     * QQ与新浪不需要添加Activity，但需要在使用QQ分享或者授权的Activity中，添加：
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_share_bybox:
                shareByBox();
                break;
            case R.id.btn_weibo:// TODO 20/07/16
                shareMySelf(SHARE_MEDIA.SINA);
                break;
            case R.id.btn_qq:// TODO 20/07/16
                shareMySelf(SHARE_MEDIA.QQ);
                break;
            case R.id.btn_weixin:// TODO 20/07/16
                shareMySelf(SHARE_MEDIA.WEIXIN);
                break;
            case R.id.btn_weixin_login:// TODO 20/07/16
                login(SHARE_MEDIA.SINA);
                break;
            case R.id.btn_qq_login:// TODO 20/07/16
                login(SHARE_MEDIA.QQ);
                break;
            case R.id.btn_weibo_login:// TODO 20/07/16
                login(SHARE_MEDIA.WEIXIN);
                break;
            default:
                break;
        }
    }

    private void login(SHARE_MEDIA type) {
        UMShareAPI mShareAPI = UMShareAPI.get(this);
        mShareAPI.getPlatformInfo(MainActivity.this, type, authListener);
    }

    private void shareMySelf(SHARE_MEDIA sina) {
        new ShareAction(MainActivity.this)
                .setPlatform(sina)//传入平台
                .withText("hello 新")//分享内容
                .withMedia(image)
                .setCallback(umShareListener)//回调监听器
                .share();
    }

    private void shareByBox() {
        //网络图片
        image = new UMImage(MainActivity.this, "http://ww1.sinaimg.cn/large/0065oQSqgy1ftt7g8ntdyj30j60op7dq.jpg");
        image.compressStyle = UMImage.CompressStyle.SCALE;
        new ShareAction(MainActivity.this).withText("hello 1909B").setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN)
                .withMedia(image)//分享图片
                .setCallback(umShareListener).open();
    }
}
