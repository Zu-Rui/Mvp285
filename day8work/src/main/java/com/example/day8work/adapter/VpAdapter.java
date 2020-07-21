package com.example.day8work.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

public class VpAdapter extends FragmentPagerAdapter {
    private Context context;
    private ArrayList<Fragment> list;
    private ArrayList<String> title;

    public VpAdapter(@NonNull FragmentManager fm, Context context, ArrayList<Fragment> list, ArrayList<String> title) {
        super(fm);
        this.context = context;
        this.list = list;
        this.title = title;
    }

    public VpAdapter(@NonNull FragmentManager fm, int behavior, Context context, ArrayList<Fragment> list, ArrayList<String> title) {
        super(fm, behavior);
        this.context = context;
        this.list = list;
        this.title = title;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
