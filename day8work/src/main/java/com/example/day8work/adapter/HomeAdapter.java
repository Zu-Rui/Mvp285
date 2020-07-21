package com.example.day8work.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.day8work.R;
import com.example.day8work.bean.HomeBean;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private Context context;
    private ArrayList<HomeBean.BodyBean.ResultBean> list;

    public HomeAdapter(Context context, ArrayList<HomeBean.BodyBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_home, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeBean.BodyBean.ResultBean bean = list.get(position);
        holder.title.setText(bean.getTitle());
        holder.name.setText(bean.getTeacherName());
//        holder.fh.setText();
        RequestOptions crop = new RequestOptions().circleCrop();
        Glide.with(context).load(bean.getTeacherPic()).apply(crop).into(holder.img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onListener.onclick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name;
        TextView title;
        TextView fh;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_rcl);
            name = itemView.findViewById(R.id.tv_name);
            title = itemView.findViewById(R.id.tv_title);
            fh = itemView.findViewById(R.id.tv_fuhao);
        }
    }

    private onListener onListener;

    public void setOnListener(HomeAdapter.onListener onListener) {
        this.onListener = onListener;
    }

    public interface onListener{
        void onclick(int position);
    }

}
