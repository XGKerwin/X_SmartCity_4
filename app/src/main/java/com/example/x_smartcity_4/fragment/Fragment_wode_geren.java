package com.example.x_smartcity_4.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_smartcity_4.R;
import com.example.x_smartcity_4.bean.GetUserInfo;
import com.example.x_smartcity_4.net.OKHttpTo;
import com.example.x_smartcity_4.net.OkHttpLo;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/2  0:01
 */
public class Fragment_wode_geren extends Fragment {
    private List<GetUserInfo> userInfos;
    private TextView txtFanhui;
    private TextView txtXiugai;
    private EditText txtName;
    private EditText txtSex;
    private EditText txtTel;
    private EditText txtIdcard;
    private String user;

    public Fragment_wode_geren(List<GetUserInfo> userInfos, String user) {
        this.userInfos = userInfos;
        this.user = user;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_wode_geren,container,false);
        initView(view);

        txtName.setFocusable(false);
        txtName.setFocusableInTouchMode(false);
        txtSex.setFocusable(false);
        txtSex.setFocusableInTouchMode(false);
        txtTel.setFocusable(false);
        txtTel.setFocusableInTouchMode(false);
        txtIdcard.setFocusable(false);
        txtIdcard.setFocusableInTouchMode(false);

        txtName.setText(userInfos.get(0).getName());
        txtSex.setText(userInfos.get(0).getSex());
        txtTel.setText(userInfos.get(0).getPhone());
        txtIdcard.setText(userInfos.get(0).getId());

        btn();

        return view;
    }

    private void btn() {
        txtXiugai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtName.setFocusable(true);
                txtName.setFocusableInTouchMode(true);
                txtSex.setFocusable(true);
                txtSex.setFocusableInTouchMode(true);
                txtTel.setFocusable(true);
                txtTel.setFocusableInTouchMode(true);
                txtIdcard.setFocusable(true);
                txtIdcard.setFocusableInTouchMode(true);
                txtName.requestFocus();
                txtXiugai.setText("保存");
                txtXiugai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = txtName.getText().toString();
                        String sex = txtSex.getText().toString();
                        String tel = txtTel.getText().toString();
                        String id = txtIdcard.getText().toString();
                        if (name.equals("")) {
                            Toast.makeText(getContext(),"用户名不能为空",Toast.LENGTH_SHORT).show();
                        }else if (sex.equals("")){
                            Toast.makeText(getContext(),"性别不能为空",Toast.LENGTH_SHORT).show();
                        }else if (tel.equals("")){
                            Toast.makeText(getContext(),"手机号不能为空",Toast.LENGTH_SHORT).show();
                        }else if (id.equals("")){
                            Toast.makeText(getContext(),"身份证号不能为空",Toast.LENGTH_SHORT).show();
                        }else {
                            setUserInfo(name,sex,tel,id);
                            txtXiugai.setText("修改");
                            txtName.setFocusable(false);
                            txtName.setFocusableInTouchMode(false);
                            txtSex.setFocusable(false);
                            txtSex.setFocusableInTouchMode(false);
                            txtTel.setFocusable(false);
                            txtTel.setFocusableInTouchMode(false);
                            txtIdcard.setFocusable(false);
                            txtIdcard.setFocusableInTouchMode(false);
                            btn();
                        }


                    }
                });
            }
        });
        txtFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_wode());
            }
        });
    }

    private void setUserInfo(String name, String sex, String tel, String id) {
        /**
         * {userid:"1","name":"","avatar":"","phone":"phone","id":"1","gender":""}
         */
        Log.d("ffffff", "setUserInfo: "+user+name+sex+tel+id);
        new OKHttpTo()
                .setUrl("setUserInfo")
                .setJSONObject("userid",user)
                .setJSONObject("name",name)
                .setJSONObject("avatar","")
                .setJSONObject("gender",sex)
                .setJSONObject("phone",tel)
                .setJSONObject("id",id)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Toast.makeText(getContext(),"保存成功",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();

    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }


    private void initView(View view) {
        txtFanhui = view.findViewById(R.id.txt_fanhui);
        txtXiugai = view.findViewById(R.id.txt_xiugai);
        txtName = view.findViewById(R.id.txt_name);
        txtSex = view.findViewById(R.id.txt_sex);
        txtTel = view.findViewById(R.id.txt_tel);
        txtIdcard = view.findViewById(R.id.txt_idcard);
    }
}
