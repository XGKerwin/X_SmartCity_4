package com.example.x_smartcity_4.fragment.fuwu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_4.R;
import com.example.x_smartcity_4.adapter.FUwu_menzhen2_adapter;
import com.example.x_smartcity_4.bean.GetUserInfo;
import com.example.x_smartcity_4.bean.HospitalList;
import com.example.x_smartcity_4.bean.ShowCaseById;
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
 * date   : 2021/2/1  0:00
 */
public class Fragment_fuwu_menzhen2 extends Fragment {

    private LinearLayout btnFanhui;
    private TextView title;
    private RecyclerView recyclerview;
    private Button btSave;
    private HospitalList list;
    private FUwu_menzhen2_adapter adapter;

    public Fragment_fuwu_menzhen2(HospitalList list) {
        this.list = list;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_menzhen_2, container, false);
        initView(view);
        title.setText("就诊人卡片");
        btn();

        getOkHttp();

        return view;

    }

    private List<GetUserInfo> getUserInfos;
    private void getOkHttp() {
        if (getUserInfos == null){
            getUserInfos = new ArrayList<>();
        }else {
            getUserInfos.clear();
        }
        new OKHttpTo()
                .setUrl("getUserInfo")
                .setJSONObject("userid","1")    //----------------------------
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        getUserInfos.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetUserInfo>>(){}.getType()));
                        getOkhttp1();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();



    }

    private List<ShowCaseById> showCaseByIds;
    private void getOkhttp1() {
        if (showCaseByIds == null){
            showCaseByIds = new ArrayList<>();
        }else {
            showCaseByIds.clear();
        }
        new OKHttpTo()
                .setUrl("showCaseById")
                .setJSONObject("ID",getUserInfos.get(0).getId())
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        showCaseByIds.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<ShowCaseById>>(){}.getType()));
                        if (adapter == null){
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                            recyclerview.setLayoutManager(linearLayoutManager);

                            adapter = new FUwu_menzhen2_adapter(getUserInfos,getActivity(),list);
                        }else {
                            adapter.notifyDataSetChanged();
                        }
                        recyclerview.setAdapter(adapter);

                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();

    }

    private void btn() {
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_fuwu_menzhen_y3(list));
            }
        });

        btnFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_fuwu_menzhen1(list));
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
        recyclerview = view.findViewById(R.id.recyclerview);
        btSave = view.findViewById(R.id.bt_save);
    }
}
