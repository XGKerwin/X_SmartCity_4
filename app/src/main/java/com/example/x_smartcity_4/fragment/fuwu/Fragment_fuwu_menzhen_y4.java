package com.example.x_smartcity_4.fragment.fuwu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_4.R;
import com.example.x_smartcity_4.adapter.Fuwu_menzheny4_adapter;
import com.example.x_smartcity_4.bean.DeptList;
import com.example.x_smartcity_4.bean.HospitalList;
import com.example.x_smartcity_4.net.OKHttpTo;
import com.example.x_smartcity_4.net.OkHttpLo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/1  13:41
 */
public class Fragment_fuwu_menzhen_y4 extends Fragment {

    private String name, sfz, tel;
    private HospitalList list;
    private LinearLayout btnFanhui;
    private TextView title;
    private RecyclerView recyclerview;
    private List<DeptList> deptLists;
    private Fuwu_menzheny4_adapter adapter;


    public Fragment_fuwu_menzhen_y4(String name, String sfz, String tel, HospitalList list) {
        this.name = name;
        this.list = list;
        this.sfz = sfz;
        this.tel = tel;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragmenzhen_y4, container, false);
        initView(view);
        title.setText("科室选择");


        getOkHttp();

        btnFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_fuwu_menzhen_y3(list));
            }
        });
        return view;
    }

    private void getOkHttp() {
        if (deptLists == null){
            deptLists = new ArrayList<>();
        }else {
            deptLists.clear();
        }
        new OKHttpTo()
                .setUrl("deptList")
                .setJSONObject("hospitalId",list.getHospitalId())
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        deptLists.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<DeptList>>(){}.getType()));
                        
                        show();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void show() {
        if (adapter == null){
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            recyclerview.setLayoutManager(linearLayoutManager);

            adapter = new Fuwu_menzheny4_adapter(deptLists,list,name,tel,sfz,getActivity());
        }else {
            adapter.notifyDataSetChanged();
        }
        recyclerview.setAdapter(adapter);
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    private void initView(View view) {
        btnFanhui = view.findViewById(R.id.btn_fanhui);
        title = view.findViewById(R.id.title);
        recyclerview = view.findViewById(R.id.recyclerview);
    }
}
