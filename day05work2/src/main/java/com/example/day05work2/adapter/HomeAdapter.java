package com.example.day05work2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.day05work2.R;
import com.example.day05work2.bean.HomeBean;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    public boolean check;
    private Context context;
    private ArrayList<HomeBean.T1348647909107Bean> list;

    public HomeAdapter(Context context, ArrayList<HomeBean.T1348647909107Bean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (!check) {
            //                            隐藏
            holder.ck.setVisibility(View.GONE);
        }else {
            //                            显示
            holder.ck.setVisibility(View.VISIBLE);
        }
        HomeBean.T1348647909107Bean bean = list.get(position);
        holder.tv.setText(bean.getTitle());
        Glide.with(context).load(bean.getImgsrc()).into(holder.img);

        holder.ck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                bean.setOnShow(isChecked);
            }
        });

        if (bean.onShow()) {
            holder.ck.setChecked(true);
        }else {
            holder.ck.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox ck;
        ImageView img;
        TextView tv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_pic);
            tv = itemView.findViewById(R.id.tv_title);
            ck =itemView.findViewById(R.id.ck);
        }
    }
}
