package com.example.x_smartcity_4.fragment.fuwu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_smartcity_4.App;
import com.example.x_smartcity_4.R;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/1  15:25
 */

public class Fragment_fuwu_fupin extends Fragment {
    private ImageView back;
    private TextView title;
    private TextView txtWode;
    private ImageView btnXinwen;
    private ImageView btnFupinanli;
    private ImageView btnCunqing;
    private String user;
    private ImageView btnGongzuotai;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_fupin, container, false);
        initView(view);
        title.setText("精准扶贫");
        user = App.getUserid();
        btn();


        return view;
    }

    private void btn() {
        btnGongzuotai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user == null){
                    Toast.makeText(getContext(),"请登录",Toast.LENGTH_SHORT).show();
                }else {
                    getFragment(new Fragment_fuwu_fupin_gongzuotai());
                }

            }
        });

        btnCunqing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_fuwu_fupin_cun());
            }
        });

        btnFupinanli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user == null) {
                    Toast.makeText(getContext(), "请登录", Toast.LENGTH_SHORT).show();
                } else {
                    getFragment(new Fragment_fuwu_fupin_anli());
                }

            }
        });

        btnXinwen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_fuwu_fupin_xinwen());
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_fuwu());
            }
        });

    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home, fragment).commit();
    }

    private void initView(View view) {
        back = view.findViewById(R.id.back);
        title = view.findViewById(R.id.title);
        txtWode = view.findViewById(R.id.txt_wode);
        btnXinwen = view.findViewById(R.id.btn_xinwen);
        btnFupinanli = view.findViewById(R.id.btn_fupinanli);
        btnCunqing = view.findViewById(R.id.btn_cunqing);
        btnGongzuotai = view.findViewById(R.id.btn_gongzuotai);
    }
}
