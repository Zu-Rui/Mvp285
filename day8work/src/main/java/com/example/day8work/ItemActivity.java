package com.example.day8work;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.day8work.adapter.VpAdapter;
import com.example.day8work.api.ApiService;
import com.example.day8work.bean.HomeBean;
import com.example.day8work.bean.ItemBean;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ItemActivity extends AppCompatActivity {


    @BindView(R.id.img_rcl_item)
    ImageView imgRclItem;
    @BindView(R.id.tv_name_item)
    TextView tvNameItem;
    @BindView(R.id.tv_title_item)
    TextView tvTitleItem;
    @BindView(R.id.tv_fuhao_item)
    TextView tvFuhaoItem;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;
    private int id;
    private ArrayList<Fragment> fragments;
    private ArrayList<String> strings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initData() {
        new Retrofit.Builder()
                .baseUrl(ApiService.base_Url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiService.class)
                .getData1(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ItemBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ItemBean itemBean) {
                        if (itemBean != null) {
                            List<ItemBean.BodyBean.ResultBean> result = itemBean.getBody().getResult();
                            fragments = new ArrayList<>();
                            strings = new ArrayList<>();
                            for (int i = 0; i < result.size(); i++) {
                                String s = result.get(i).getDescription();
                                strings.add(s);
                                if (s.equals("介绍")) {
                                    fragments.add(new AFragment());
                                }
                                if (s.equals("课程")) {
                                    fragments.add(new BFragment());
                                }
                                if (s.equals("专题")) {
                                    fragments.add(new CFragment());
                                }
                            }
                        }
                        VpAdapter adapter = new VpAdapter(getSupportFragmentManager(), ItemActivity.this, fragments, strings);
                        vp.setAdapter(adapter);
                        tab.setupWithViewPager(vp);
                        tab.getTabAt(0).setText("介绍");
                        tab.getTabAt(1).setText("课程");
                        tab.getTabAt(2).setText("专题");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    private void initView() {
        HomeBean.BodyBean.ResultBean data = (HomeBean.BodyBean.ResultBean) getIntent().getSerializableExtra("data");
        id = data.getID();
        tvNameItem.setText(data.getTeacherName());
        tvTitleItem.setText(data.getTitle());
        tvFuhaoItem.setText(data.getDescription());
        RequestOptions crop = new RequestOptions().circleCrop();
        Glide.with(this).load(data.getTeacherPic()).apply(crop).into(imgRclItem);
    }

}
