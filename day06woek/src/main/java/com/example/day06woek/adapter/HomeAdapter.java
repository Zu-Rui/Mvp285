package com.example.day06woek.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.day06woek.R;
import com.example.day06woek.bean.HomeBean;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<HomeBean.T1348654151579Bean> list;

    private int VIEW_TYPE_ONE = 1;
    private int VIEW_TYPE_TWO = 2;

    public HomeAdapter(Context context, ArrayList<HomeBean.T1348654151579Bean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ONE) {
            View view = LayoutInflater.from(context).inflate(R.layout.recycler_one, null);
            return new ViewHolderOne(view);
        }else {
            View view = LayoutInflater.from(context).inflate(R.layout.recycler_two, null);
            return new ViewHolderTwo(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == VIEW_TYPE_ONE) {
            HomeBean.T1348654151579Bean bean = list.get(position);
            ViewHolderOne one = (ViewHolderOne) holder;
            one.name.setText(bean.getTname());
            one.time.setText(bean.getPtime());
            one.title.setText(bean.getTitle());
        }else {
            HomeBean.T1348654151579Bean bean = list.get(position);
            ViewHolderTwo two = (ViewHolderTwo) holder;
            two.name_t.setText(bean.getTname());
            two.time_t.setText(bean.getPtime());
            two.title_t.setText(bean.getTitle());
            Glide.with(context).load(bean.getImgsrc()).into(two.img);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onListener.onlistener(position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position %2 ==0) {
            return VIEW_TYPE_TWO;
        }else {
            return VIEW_TYPE_ONE;
        }
    }

    public class ViewHolderOne extends RecyclerView.ViewHolder {
        TextView name;
        TextView time;
        TextView title;

        public ViewHolderOne(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name_one);
            time = itemView.findViewById(R.id.time_one);
            title = itemView.findViewById(R.id.title_one);
        }
    }

    public class ViewHolderTwo extends RecyclerView.ViewHolder {
        TextView name_t;
        TextView time_t;
        TextView title_t;
        ImageView img;
        public ViewHolderTwo(@NonNull View itemView) {
            super(itemView);
            name_t = itemView.findViewById(R.id.tv_name_two);
            time_t = itemView.findViewById(R.id.time_two);
            title_t = itemView.findViewById(R.id.title_two);
            img = itemView.findViewById(R.id.img);
        }
    }
    public interface onListener{
        void onlistener(int position);
    }
    onListener onListener;

    public void setOnListener(HomeAdapter.onListener onListener) {
        this.onListener = onListener;
    }
}
