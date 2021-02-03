package com.example.x_smartcity_4.fragment.A_Page;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.x_smartcity_4.R;

import java.util.ArrayList;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/1/30  15:15
 */
public class Fragment_main extends Fragment {
    private ViewPager viewpager;
    private LinearLayout linear;
    private List<Fragment> fragments;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_main, container, false);
        initView(view);

        getFragment();

        return view;
    }

    private void getFragment() {
        fragments = new ArrayList<>();
        fragments.add(new Fragment_1());
        fragments.add(new Fragment_2());
        fragments.add(new Fragment_3());
        fragments.add(new Fragment_4());
        fragments.add(new Fragment_5());


        viewpager.setAdapter(new PagerAdapter(getActivity().getSupportFragmentManager()));
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i <linear.getChildCount();i++){
                    ImageView imageView = (ImageView) linear.getChildAt(i);
                    if (position == i){
                        imageView.setImageResource(R.drawable.bai_dayuan);
                    }else {
                        imageView.setImageResource(R.drawable.bai_xiaoyuan);
                    }
                }
                if (position==4){
                    linear.setVisibility(View.GONE);
                }else {
                    linear.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        for (int i=0;i<fragments.size();i++){
            ImageView imageView = new ImageView(getContext());
            if (i==0){
                imageView.setImageResource(R.drawable.bai_dayuan);
            }else {
                imageView.setImageResource(R.drawable.bai_xiaoyuan);
            }
            imageView.setLayoutParams(new ViewGroup.LayoutParams(60,60));
            imageView.setPadding(10,10,10,10);
            linear.addView(imageView);
        }
    }

    class PagerAdapter extends FragmentPagerAdapter {
        public PagerAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

    private void initView(View view) {
        viewpager = view.findViewById(R.id.viewpager);
        linear = view.findViewById(R.id.linear);
    }
}
