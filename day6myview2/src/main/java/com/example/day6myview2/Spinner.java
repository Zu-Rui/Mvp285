package com.example.day6myview2;

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

public class Spinner extends RelativeLayout {

    private ArrayList<String> list;
    private EditText mEt;
    private ImageView mImg;
    private PopupWindow pw;

    public Spinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_spinner,this);
        initView();
        initData();
    }

    private void initView() {
        mEt = findViewById(R.id.et);
        mImg = findViewById(R.id.img);
        mImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dealPopup();
            }
        });
    }

    private void dealPopup() {

        ListView listView = new ListView(getContext());
        listView.setAdapter(new ArrayAdapter<String>(getContext(),android.R.layout.simple_expandable_list_item_1,list));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = list.get(position);
                mEt.setText(s);
                pw.dismiss();
            }
        });

        pw = new PopupWindow(listView, mEt.getWidth(), 600);
        pw.setOutsideTouchable(true);
        pw.setBackgroundDrawable(null);
        pw.showAsDropDown(mEt);
    }

    private void initData() {
        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("班级"+i);
        }
    }
}
