package com.example.day6myview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import java.util.ArrayList;

//自定义SpinnerView 通过继承相对布局RelativeLayout，所以需要加载一个布局

public class SpinnerView extends RelativeLayout {

    private EditText mEt;
    private ImageView mImg;
    private ArrayList<String> mData;
    private PopupWindow pw;

    public SpinnerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        //加载布局
        LayoutInflater.from(getContext()).inflate(R.layout.view_spinner, this);
        initView();//加载组件
        initData();//初始化数据：准备数据
    }


    private void initView() {
        mEt = findViewById(R.id.et_key);
        mImg = findViewById(R.id.img_down);
        mImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //弹出popwindow
                dealPopup();
            }
        });
    }

    private void dealPopup() {
        ListView listView = new ListView(getContext());
        //给listview配置数据
        //给android.R.layout.simple_expandable_list_item_1
        listView.setAdapter(new ArrayAdapter<String>(getContext(),android.R.layout.simple_expandable_list_item_1,mData));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String s = mData.get(position);//点击的条目的数据
                mEt.setText(s);//把数据设置到输入框中
                mEt.setSelection(s.length());//设置光标在最后

                pw.dismiss();//关闭
            }
        });

        //准备popupwindow
        //创建popupwindow
        //设置界面，宽，高
        pw = new PopupWindow(listView, mEt.getWidth(), 600);
        pw.setBackgroundDrawable(null);
        pw.setOutsideTouchable(true);
        pw.showAsDropDown(mEt);//弹出ss
    }

    //初始化数据
    private void initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mData.add("班级"+i);
        }
    }
}
