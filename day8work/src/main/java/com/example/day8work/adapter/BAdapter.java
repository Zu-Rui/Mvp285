package com.example.day8work.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.day8work.R;
import com.example.day8work.bean.HomeBean;

import java.util.ArrayList;

public class BAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<HomeBean.BodyBean.ResultBean> list;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public BAdapter(Context context, ArrayList<HomeBean.BodyBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_b, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.tv.setText(list.get(position).getTitle());
        String s = list.get(position).getDescription();
        String[] split = s.split("\"");
        Log.d("1111", "onBindViewHolder() returned: " + split[1]);
        Glide.with(context).load(split[1]).into(((ViewHolder) holder).iv);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv;
        public ImageView iv;

        public ViewHolder(View rootView) {
            super(rootView);
            this.tv = (TextView) rootView.findViewById(R.id.tv);
            this.iv = (ImageView) rootView.findViewById(R.id.iv);
        }

    }
    public interface OnItemClickListener{
        void onItemClick(int position);
    }
}
