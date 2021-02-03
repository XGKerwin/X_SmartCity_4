package com.example.x_smartcity_4.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_4.R;
import com.example.x_smartcity_4.adapter.Fuwu_menzheny5_adapter;
import com.example.x_smartcity_4.bean.DoctorList;
import com.example.x_smartcity_4.bean.HospitalList;
import com.example.x_smartcity_4.fragment.fuwu.Fragment_fuwu_menzhen;
import com.example.x_smartcity_4.fragment.fuwu.Fragment_fuwu_menzhen_y4;
import com.example.x_smartcity_4.net.OKHttpTo;
import com.example.x_smartcity_4.net.OkHttpLo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/1  14:00
 */
public class Fragment_fuwu_menzhen_y5 extends Fragment {
    private String name, tel, sfz, deptid;
    private HospitalList list;
    private LinearLayout btnFanhui;
    private TextView title;
    private TextView txtPutong;
    private TextView txtZhuanjia;
    private TextView txtZanwu;
    private RecyclerView recyclerview;
    private List<DoctorList> doctorLists,doctors;
    private Fuwu_menzheny5_adapter adapter;

    public Fragment_fuwu_menzhen_y5(String name, String tel, String sfz, HospitalList list, String deptId) {
        this.name = name;
        this.tel = tel;
        this.sfz = sfz;
        this.deptid = deptId;
        this.list = list;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_menzhen_4, container, false);
        initView(view);
        title.setText("医生选择");

        getOkHttp();

        btnFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_fuwu_menzhen_y4(name,sfz,tel,list));
            }
        });

        txtZhuanjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtZanwu.setVisibility(View.VISIBLE);
                recyclerview.setVisibility(View.GONE);
            }
        });

        txtZhuanjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtZanwu.setVisibility(View.GONE);
                recyclerview.setVisibility(View.VISIBLE);
            }
        });

        return view;
    }

    private void getOkHttp() {
        if (doctorLists == null){
            doctorLists = new ArrayList<>();
        }else {
            doctorLists.clear();
        }
        new OKHttpTo()
                .setUrl("doctorList")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        doctorLists.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<DoctorList>>(){}.getType()));
                        getData();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private String time;
    private void getData() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd E");
        Date date = new Date(System.currentTimeMillis());
        String str = simpleDateFormat.format(date);
        time = str+"下午14：00";

        if (doctors == null){
            doctors = new ArrayList<>();
        }else {
            doctors.clear();
        }

        for (int i=0;i<doctorLists.size();i++){
            DoctorList list = doctorLists.get(i);
            if (list.getHospitalId().equals(list.getHospitalId())){
                doctors.add(list);
            }
        }
        if (adapter == null){
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            recyclerview.setLayoutManager(linearLayoutManager);


            adapter = new Fuwu_menzheny5_adapter(doctors,time);
        }else {
            adapter.notifyDataSetChanged();
        }
        recyclerview.setAdapter(adapter);


        adapter.setItem_y5(new Fuwu_menzheny5_adapter.Item_y5() {
            @Override
            public void setonClick(DoctorList list) {
                setOkHttp(list);
            }
        });
    }

    private void setOkHttp(DoctorList list) {
        new OKHttpTo()
                .setUrl("appointment")
                .setJSONObject("pid",sfz)
                .setJSONObject("name",name)
                .setJSONObject("phone",tel)
                .setJSONObject("doctorId",list.getDeptId())
                .setJSONObject("appTime",time)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Log.d("fasdasdas", "onResponse: "+sfz+name+tel+list.getDeptId()+time);
                        Toast.makeText(getContext(),"提交成功",Toast.LENGTH_SHORT).show();
                        getFragment(new Fragment_fuwu_menzhen());
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
        btnFanhui = view.findViewById(R.id.btn_fanhui);
        title = view.findViewById(R.id.title);
        txtPutong = view.findViewById(R.id.txt_putong);
        txtZhuanjia = view.findViewById(R.id.txt_zhuanjia);
        txtZanwu = view.findViewById(R.id.txt_zanwu);
        recyclerview = view.findViewById(R.id.recyclerview);
    }
}
