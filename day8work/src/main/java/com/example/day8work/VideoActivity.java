package com.example.day8work;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class VideoActivity extends AppCompatActivity {

    private JCVideoPlayerStandard mJcv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video2);
        initView();
    }

    private void initView() {
        mJcv = (JCVideoPlayerStandard) findViewById(R.id.jcv);
        String string = "https://yunxue-bucket.oss-cn-shanghai.aliyuncs.com/classfile/0/从技术走向管理/001.从技术到管理_第1节_从技术到管理的内外部因素.mp4";
        mJcv.setUp(string, JCVideoPlayer.SCREEN_LAYOUT_NORMAL);
        mJcv.thumbImageView.setScaleType(ImageView.ScaleType.FIT_XY);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mJcv.releaseAllVideos();
    }
}
