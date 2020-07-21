package com.example.mvpwl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.mvpwl.fragment.CollectFragment;

public class CollectFragmentActivity extends AppCompatActivity {

    private FragmentManager sfm;
    private CollectFragment collectFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect_fragment);

        sfm = getSupportFragmentManager();
        collectFragment = new CollectFragment();
        sfm.beginTransaction().add(R.id.rl_fragment,collectFragment).commit();

    }
}
