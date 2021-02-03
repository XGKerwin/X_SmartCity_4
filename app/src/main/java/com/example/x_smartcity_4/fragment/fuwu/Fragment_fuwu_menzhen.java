package com.example.x_smartcity_4.fragment.fuwu;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_4.R;
import com.example.x_smartcity_4.adapter.Fuwu_menzhen_adapter;
import com.example.x_smartcity_4.bean.GetRankByHospitalId;
import com.example.x_smartcity_4.bean.HospitalList;
import com.example.x_smartcity_4.net.OKHttpTo;
import com.example.x_smartcity_4.net.OkHttpLo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/1/30  16:04
 */
public class Fragment_fuwu_menzhen extends Fragment {

    private LinearLayout btnFanhui;
    private TextView title;
    private EditText edSousuo;
    private LinearLayout btnSousuo;
    private RecyclerView recyclerview;
    private List<HospitalList> hospitalLists;
    private Fuwu_menzhen_adapter adapter;
    private List<GetRankByHospitalId> getRankByHospitalIds;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_menzhen, container, false);
        initView(view);
        title.setText("门诊预约");

         getOkHttp();
        
        
        btn();

        return view;
    }

    private void getOkHttp() {
        if (hospitalLists == null){
            hospitalLists = new ArrayList<>();
        }else {
            hospitalLists.clear();
        }
        new OKHttpTo()
                .setUrl("hospitalList")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        hospitalLists.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<HospitalList>>(){}.getType()));

                        getfor();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getfor() {
        if (getRankByHospitalIds == null){
            getRankByHospitalIds = new ArrayList<>();
        }else {
            getRankByHospitalIds.clear();
        }
        for (int i=0;i<4;i++){
            getOkhttp1(i);
        }
    }

    private void getOkhttp1(int i) {
        int s = i+1;
        Log.d("vaaaaaa1", "getOkhttp1: "+i);
        Log.d("vaaaaaa2", "getOkhttp1: "+s);
        new OKHttpTo()
                .setUrl("getRankByHospitalId")
                .setJSONObject("hospitalId",s)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        GetRankByHospitalId getRankByHospitalId = new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").optJSONObject(0).toString(),GetRankByHospitalId.class);

                        getRankByHospitalIds.add(getRankByHospitalId);

                        show(i);
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();

    }

    private void show(int i) {

        if (getRankByHospitalIds.size()==4){
            if (adapter == null){
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),3);
                recyclerview.setLayoutManager(gridLayoutManager);

                adapter = new Fuwu_menzhen_adapter(hospitalLists,getRankByHospitalIds,getActivity());
            }else {
                adapter.notifyDataSetChanged();
            }
            recyclerview.setAdapter(adapter);
        }
    }

    private void btn() {
        
        btnFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_fuwu());
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
        edSousuo = view.findViewById(R.id.ed_sousuo);
        btnSousuo = view.findViewById(R.id.btn_sousuo);
        recyclerview = view.findViewById(R.id.recyclerview);
    }
}
