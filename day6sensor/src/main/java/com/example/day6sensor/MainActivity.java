package com.example.day6sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SensorManager mSensorManager;
    private MediaPlayer mMp;
    private int [] musics = {R.raw.a,R.raw.b,R.raw.c,R.raw.d,R.raw.e,R.raw.f};
    private int index = 0;
    private MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //得到传感器管理器
                                                            //sence service
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);


        //获得所有传感器
//        getAllSensor();
        //方向传感器
//        userOrien();
        //光传感器
//        userLight();

        /*
         * 1.先获取加速度传感器
         * 2.创建相关的监听器--在 onSensorChanged 方法中编写
         * 3.注册
         * 4.运行甩手机测试
         * 5.创建Mediaplayer对象播放歌曲，甩的时候播放 准备歌曲仓库
         * */
        //得到加速度传感器，实现甩歌功能
        getAccele();



    }

    private void getAccele() {
        player = MediaPlayer.create(this, musics[index]);
        player.start();
        //lis1监听器  sensor是感应器  SensorManager.SENSOR_DELAY_NORMAL感应器的灵敏度
        Sensor sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(Lis1, sensor, SensorManager.SENSOR_DELAY_NORMAL);//注册感应器的监听器

    }

    private SensorEventListener Lis1 = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {//加速度感应器发生变化
                float[] value = event.values;//得到xyz三个方向的数据的变化
                float x = value[0];//x轴的新值
                float y = value[1];//y轴的新值
                float z = value[2];//z轴的新值
                Log.i("TAG", "x=" + x + ",y=" + y + ",z=" + z);
                if (z > 12) {//甩动切歌
                    if (mMp != null) {
                        mMp.stop();
                        mMp.release();
                    }
                    index++;//下标加1
                    index = index % musics.length;//防止越界

                    mMp = MediaPlayer.create(MainActivity.this, musics[index]);
                    mMp.start();
                }
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    private SensorEventListener lis2 = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == Sensor.TYPE_LIGHT) {//表示当前监听的是光线传感器
                float f = event.values[0];//得导当前感应到的光线强度的值
                Log.i("111", "光线强度:"+f );
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
    private void userLight() {
        //获得光线传感器
        Sensor sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        //创建传感器监听
        //注册           瑞寨死特
        mSensorManager.registerListener(lis2,sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }


    /*
    方向传感器
    1：获得方法传感器
    2：创建传感器的监听器
    3：注册传感器，添加监听器
    4：在监听器的onSensorChanged方法中获取传感器感应到的数据，进行业务逻辑的处理
     */
    private void userOrien() {
        //获得方向传感器               // 地方的 sence            //奥 瑞忒身
        Sensor orien = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        mSensorManager.registerListener(new Lis1(),orien,SensorManager.SENSOR_DELAY_NORMAL);//注册

    }

    public class  Lis1 implements SensorEventListener{
        @Override
        public void onSensorChanged(SensorEvent event) {
            float i = event.values[0];
            Log.i("111", "方向角度:"+i);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }

    private void getAllSensor() {

        //得到此手机的所有传感器
        List<Sensor> sensorList = mSensorManager.getSensorList(Sensor.TYPE_ALL);

        for (int i = 0; i < sensorList.size(); i++) {
            Sensor sensor = sensorList.get(i);
            String name = sensor.getName();//得到名字
            String vendor = sensor.getVendor();//得到生产厂商
            int version = sensor.getVersion();//得到版本
            Log.i("111", "onCreate: "+name+","+vendor+","+version);
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        //解除监听器的注册，节省开销，省电
        mSensorManager.unregisterListener(Lis1);
        mSensorManager.unregisterListener(lis2);
    }
}
