package com.example.x_smartcity_4.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_4.R;
import com.example.x_smartcity_4.adapter.Wode_dingdan_adapter;
import com.example.x_smartcity_4.bean.GetOrderById;
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
 * date   : 2021/2/2  0:03
 */
public class Fragment_wode_dingdan extends Fragment {
    private String user;
    private TextView txtFanhui;
    private TextView txtLeixing;
    private TextView txtTime;
    private TextView txtJine;
    private List<GetOrderById> orderByIds;
    private RecyclerView Recyclerview;
    private Wode_dingdan_adapter adapter;

    public Fragment_wode_dingdan(String user) {
        this.user = user;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_wode_dingdan,container,false);
        initView(view);

        getOrderById();


        txtFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_wode());
            }
        });
        return view;
    }

    private void getOrderById() {
        if (orderByIds == null) {
            orderByIds = new ArrayList<>();
        } else {
            orderByIds.clear();
        }
        new OKHttpTo()
                .setUrl("getOrderById")
                .setJSONObject("id", user)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        txtJine.setText("金额："+jsonObject.optString("cost"));
                        txtLeixing.setText(jsonObject.optString("type"));
                        txtTime.setText(jsonObject.optString("date"));
                        orderByIds.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetOrderById>>() {
                                }.getType()));
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                        Recyclerview.setLayoutManager(linearLayoutManager);

                        adapter = new Wode_dingdan_adapter(orderByIds);
                        Recyclerview.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home, fragment).commit();
    }

    private void initView(View view) {
        txtFanhui = view.findViewById(R.id.txt_fanhui);
        txtLeixing = view.findViewById(R.id.txt_leixing);
        txtTime = view.findViewById(R.id.txt_time);
        txtJine = view.findViewById(R.id.txt_jine);
        Recyclerview = view.findViewById(R.id.Recyclerview);
    }
}
