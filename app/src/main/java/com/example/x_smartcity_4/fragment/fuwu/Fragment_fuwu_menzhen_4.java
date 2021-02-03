package com.example.x_smartcity_4.fragment.fuwu;

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
import com.example.x_smartcity_4.adapter.Fuwu_menzhen4_adapter;
import com.example.x_smartcity_4.bean.DeptList;
import com.example.x_smartcity_4.bean.DoctorList;
import com.example.x_smartcity_4.bean.GetUserInfo;
import com.example.x_smartcity_4.bean.HospitalList;
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
 * date   : 2021/2/1  2:40
 */
public class Fragment_fuwu_menzhen_4 extends Fragment {
    private HospitalList hospitalList;
    private DeptList deptList;
    private LinearLayout btnFanhui;
    private TextView title;
    private TextView txtPutong;
    private TextView txtZhuanjia;
    private TextView txtZanwu;
    private RecyclerView recyclerview;
    private List<DoctorList> doctorLists,doctors;
    private Fuwu_menzhen4_adapter adapter;
    private String id;
    private String name;
    private String tel;
    private GetUserInfo info;

    public Fragment_fuwu_menzhen_4(HospitalList list, DeptList deptList, String id, String name, String phone, GetUserInfo info) {
        this.hospitalList = list;
        this.deptList = deptList;
        this.id = id;
        this.name = name;
        this.tel = phone;
        this.info = info;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_menzhen_4, container, false);
        initView(view);

        title.setText("医生选择");

        getOkhttp();

        btn();

        return view;
    }

    private void btn() {
        btnFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_fuwu_menzhen_3(hospitalList,info));
            }
        });

        txtZhuanjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtZanwu.setVisibility(View.VISIBLE);
                recyclerview.setVisibility(View.GONE);
            }
        });

        txtPutong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtZanwu.setVisibility(View.GONE);
                recyclerview.setVisibility(View.VISIBLE);
            }
        });
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    private void getOkhttp() {
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
            if (list.getHospitalId().equals(hospitalList.getHospitalId())){
                doctors.add(list);
            }
        }

        if (adapter == null){
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            recyclerview.setLayoutManager(linearLayoutManager);

            adapter = new Fuwu_menzhen4_adapter(doctors,time);
        }else {
            adapter.notifyDataSetChanged();
        }
        recyclerview.setAdapter(adapter);

        adapter.setItem_btn(new Fuwu_menzhen4_adapter.Item_btn() {
            @Override
            public void setonClick(DoctorList list) {
                setOkHttp(list);
            }
        });

    }

    private void setOkHttp(DoctorList list) {

        /**
         * {"pid":"371402199902041133","name":"赵子涵",
         * "phone":"13505347777","doctorId":2,"appTime":"2020-10-2 周四 下午14：00"}
         */
        Log.d("fffffffff", "setOkHttp: +"+id);
        Log.d("fffffffff", "setOkHttp: +"+name);
        Log.d("fffffffff", "setOkHttp: +"+tel);
        Log.d("fffffffff", "setOkHttp: +"+list.getDeptId());
        Log.d("fffffffff", "setOkHttp: +"+time);


        new OKHttpTo()
                .setUrl("appointment")
                .setJSONObject("pid",id)
                .setJSONObject("name",name)
                .setJSONObject("phone",tel)
                .setJSONObject("doctorId",list.getDeptId())
                .setJSONObject("appTime",time)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Toast.makeText(getContext(),"提交成功",Toast.LENGTH_SHORT).show();
                        getFragment(new Fragment_fuwu_menzhen());
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
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
