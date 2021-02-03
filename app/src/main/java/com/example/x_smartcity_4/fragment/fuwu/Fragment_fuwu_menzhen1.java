package com.example.x_smartcity_4.fragment.fuwu;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_smartcity_4.R;
import com.example.x_smartcity_4.bean.GetImagesByHospitalId;
import com.example.x_smartcity_4.bean.HospitalList;
import com.example.x_smartcity_4.net.OKHttpTo;
import com.example.x_smartcity_4.net.OkHttpLo;
import com.example.x_smartcity_4.net.OkHttpLoImage;
import com.example.x_smartcity_4.net.OkHttpToImage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/1/31  19:34
 */
public class Fragment_fuwu_menzhen1 extends Fragment {
    private HospitalList list;
    private LinearLayout btnFanhui;
    private TextView title;
    private ViewFlipper viewFlipper;
    private TextView txtMsg;
    private TextView btnYuyue;
    private String user;

    public Fragment_fuwu_menzhen1(HospitalList list) {
        this.list = list;
    }

    private List<GetImagesByHospitalId> images;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_menzhen_1, container, false);
        initView(view);
        title.setText("门诊预约");
        txtMsg.setText(list.getDesc());

        getImageview(); //轮播图

        btn();


        return view;
    }

    private void btn() {
        btnFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_fuwu_menzhen());
            }
        });

        btnYuyue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_fuwu_menzhen2(list));
            }
        });


    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    private List<String> strings;
    private void getImageview() {
        images = new ArrayList<>();
        strings = new ArrayList<>();
        new OKHttpTo()
                .setUrl("getImagesByHospitalId")
                .setJSONObject("hospitalId",list.getHospitalId())
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        images.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetImagesByHospitalId>>(){}.getType()));

                        strings.add(images.get(0).getImage1());
                        strings.add(images.get(0).getImage2());
                        strings.add(images.get(0).getImage3());
                        strings.add(images.get(0).getImage4());

                        for (int i = 0; i < strings.size(); i++) {
                            final ImageView imageView = new ImageView(getActivity());
                            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                            new OkHttpToImage()
                                    .setUrl(strings.get(i))
                                    .setOkHttpLoImage(new OkHttpLoImage() {
                                        @Override
                                        public void onResponse(Call call, Bitmap bitmap) {
                                            imageView.setImageBitmap(bitmap);
                                            viewFlipper.addView(imageView);
                                        }

                                        @Override
                                        public void onFailure(Call call, IOException obj) {

                                        }
                                    }).start();
                        }

                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void initView(View view) {
        btnFanhui = view.findViewById(R.id.btn_fanhui);
        title = view.findViewById(R.id.title);
        viewFlipper = view.findViewById(R.id.view_flipper);
        txtMsg = view.findViewById(R.id.txt_msg);
        btnYuyue = view.findViewById(R.id.btn_yuyue);
    }
}
