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
import com.example.x_smartcity_4.adapter.Fuwu_menzhen3_adapter;
import com.example.x_smartcity_4.bean.DeptList;
import com.example.x_smartcity_4.bean.GetUserInfo;
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
 * date   : 2021/2/1  1:54
 */
public class Fragment_fuwu_menzhen_3 extends Fragment {
    private HospitalList list;
    private GetUserInfo getUserInfo;
    private LinearLayout btnFanhui;
    private TextView title;
    private RecyclerView recyclerview;
    private List<DeptList> deptLists;
    private Fuwu_menzhen3_adapter adapter;

    public Fragment_fuwu_menzhen_3(HospitalList list, GetUserInfo getUserInfo) {
        this.list = list;
        this.getUserInfo = getUserInfo;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_menzhen_3, container, false);
        initView(view);
        title.setText("科室选择");

        getOkHttp();


        btnFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_fuwu_menzhen2(list));
            }
        });
        return view;
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    private void getOkHttp() {
        if (deptLists == null){
            deptLists = new ArrayList<>();
        }else {
            deptLists.clear();
        }
        //{hospitalId:"1"}
        new OKHttpTo()
                .setUrl("deptList")
                .setJSONObject("hospitalId",list.getHospitalId())
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        deptLists.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<DeptList>>(){}.getType()));

                        if (adapter == null){
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                            recyclerview.setLayoutManager(linearLayoutManager);

                            adapter = new Fuwu_menzhen3_adapter(deptLists,getActivity(),list,getUserInfo);
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

    private void initView(View view) {
        btnFanhui = view.findViewById(R.id.btn_fanhui);
        title = view.findViewById(R.id.title);
        recyclerview = view.findViewById(R.id.recyclerview);
    }
}
