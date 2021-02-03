package com.example.x_smartcity_4.adapter;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_smartcity_4.R;
import com.example.x_smartcity_4.bean.FpNewsList;
import com.example.x_smartcity_4.fragment.fuwu.Fragment_fuwu;
import com.example.x_smartcity_4.fragment.fuwu.Fragment_fuwu_fupin_xinwen;
import com.example.x_smartcity_4.net.OkHttpLoImage;
import com.example.x_smartcity_4.net.OkHttpToImage;

import java.io.IOException;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/2  12:25
 */
public class Fragment_fupin_xinwenxiangqin extends Fragment {
    private FpNewsList fpNewsList;
    private ImageView back;
    private TextView title;
    private TextView txtWode;
    private TextView txtTitle;
    private ImageView imgImage;
    private TextView tvMsg;
    private TextView tvtLook;

    public Fragment_fupin_xinwenxiangqin(FpNewsList fpNews) {
        this.fpNewsList = fpNews;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_fupin_xinwenxiangqing, container, false);
        initView(view);
        title.setText("扶贫新闻");
        txtTitle.setText(fpNewsList.getNewstitle());
        new OkHttpToImage()
                .setUrl(fpNewsList.getNewspicture())
                .setOkHttpLoImage(new OkHttpLoImage() {
                    @Override
                    public void onResponse(Call call, Bitmap bitmap) {
                        imgImage.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onFailure(Call call, IOException obj) {

                    }
                }).start();
        tvMsg.setText(fpNewsList.getNewscontent());
        tvtLook.setText("发布者："+fpNewsList.getNewsreporter()+"\n日期："+fpNewsList.getReporttime()+"   人数："+fpNewsList.getReadnum());

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_fuwu_fupin_xinwen());
            }
        });
        return view;
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    private void initView(View view) {
        back = view.findViewById(R.id.back);
        title = view.findViewById(R.id.title);
        txtWode = view.findViewById(R.id.txt_wode);
        txtTitle = view.findViewById(R.id.txt_title);
        imgImage = view.findViewById(R.id.img_image);
        tvMsg = view.findViewById(R.id.tv_msg);
        tvtLook = view.findViewById(R.id.tvt_look);
    }
}
