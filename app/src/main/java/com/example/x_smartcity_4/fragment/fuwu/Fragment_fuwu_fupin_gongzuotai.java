package com.example.x_smartcity_4.fragment.fuwu;

import android.os.Bundle;
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
import com.example.x_smartcity_4.adapter.Fragment_fuwu_fupin_anli_2;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/2  19:46
 */
public class Fragment_fuwu_fupin_gongzuotai extends Fragment {

    private ImageView back;
    private TextView title;
    private TextView txtWode;
    private ImageView btnQiuzhu;
    private ImageView btnBangfu;
    private ImageView btnZoufang;
    private ImageView btnAnli;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_fupin_gongzuotai, container, false);
        initView(view);
        title.setText("我的工作台");
        btn();

        return view;
    }

    private void btn() {
        btnAnli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_fuwu_fupin_wdal());
            }
        });

        btnZoufang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_fuwu_fupin_zoufang());
            }
        });

        btnBangfu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_fupin_bangfu());
            }
        });

        btnQiuzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_fupin_qiuzhu());
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_fuwu_fupin());
            }
        });
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    private void initView(View view) {
        back = view.findViewById(R.id.back);
        title = view.findViewById(R.id.title);
        txtWode = view.findViewById(R.id.txt_wode);
        btnQiuzhu = view.findViewById(R.id.btn_qiuzhu);
        btnBangfu = view.findViewById(R.id.btn_bangfu);
        btnZoufang = view.findViewById(R.id.btn_zoufang);
        btnAnli = view.findViewById(R.id.btn_anli);
    }
}
