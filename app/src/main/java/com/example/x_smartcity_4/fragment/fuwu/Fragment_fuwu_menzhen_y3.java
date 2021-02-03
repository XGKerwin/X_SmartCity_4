package com.example.x_smartcity_4.fragment.fuwu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_smartcity_4.R;
import com.example.x_smartcity_4.bean.HospitalList;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/1  11:45
 */
public class Fragment_fuwu_menzhen_y3 extends Fragment {

    private LinearLayout btnFanhui;
    private TextView title;
    private EditText edName;
    private TextView name;
    private EditText edSfz;
    private EditText edTel;
    private Button btnTijiao;
    private HospitalList list;

    public Fragment_fuwu_menzhen_y3(HospitalList list) {
        this.list = list;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_menzhen_y3, container, false);
        initView(view);

        btn();

        return view;
    }

    private void btn() {
        btnTijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edName.getText().toString();
                String sfz = edSfz.getText().toString();
                String tel = edTel.getText().toString();
                if (name.equals("")){
                    Toast.makeText(getContext(),"请输入姓名",Toast.LENGTH_SHORT).show();
                }else if (sfz.equals("")){
                    Toast.makeText(getContext(),"请输入身份证",Toast.LENGTH_SHORT).show();
                }else if (tel.equals("")){
                    Toast.makeText(getContext(),"请输入手机号",Toast.LENGTH_SHORT).show();
                }else {
                    getFragment(new Fragment_fuwu_menzhen_y4(name,sfz,tel,list));
                }

            }
        });

        btnFanhui.setOnClickListener(new View.OnClickListener() {
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

    private void initView(View view) {
        btnFanhui = view.findViewById(R.id.btn_fanhui);
        title = view.findViewById(R.id.title);
        edName = view.findViewById(R.id.ed_name);
        name = view.findViewById(R.id.name);
        edSfz = view.findViewById(R.id.ed_sfz);
        edTel = view.findViewById(R.id.ed_tel);
        btnTijiao = view.findViewById(R.id.btn_tijiao);
    }
}
