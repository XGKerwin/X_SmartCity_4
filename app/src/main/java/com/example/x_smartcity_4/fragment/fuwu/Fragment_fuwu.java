package com.example.x_smartcity_4.fragment.fuwu;

import android.os.Bundle;
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
import com.example.x_smartcity_4.adapter.Fuwu_adapter;
import com.example.x_smartcity_4.bean.GetServiceByType;
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
 * date   : 2021/1/30  15:37
 */
public class Fragment_fuwu extends Fragment {
    private EditText edSeek;
    private LinearLayout btnSousuo;
    private TextView fuwu;
    private TextView yanglao;
    private TextView lvyou;
    private TextView huanbao;
    private TextView yiliao;
    private RecyclerView recyclerview;
    private List<GetServiceByType> typeList;
    private Fuwu_adapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_fuwu, container, false);
        initView(view);

        getServiceByType("智慧服务");

        btn();

        return view;
    }

    private void btn() {

        fuwu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getServiceByType("智慧服务");
            }
        });

        yanglao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getServiceByType("智慧养老");
            }
        });

        lvyou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getServiceByType("智慧旅游");
            }
        });

        huanbao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getServiceByType("智慧环保");
            }
        });

        yiliao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getServiceByType("智慧医疗");
            }
        });


    }

    private void getServiceByType(String s) {

        if (typeList == null){
            typeList = new ArrayList<>();
        }else {
            typeList.clear();
        }
        new OKHttpTo()
                .setUrl("getServiceByType")
                .setJSONObject("serviceType",s)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        typeList.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetServiceByType>>(){}.getType()));

                        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),3);
                        recyclerview.setLayoutManager(gridLayoutManager);

                        if (adapter == null){
                            adapter = new Fuwu_adapter(typeList);
                            recyclerview.setAdapter(adapter);
                        }else {
                            adapter.notifyDataSetChanged();
                        }

                        adapter.setItem_fuwu_btn(new Fuwu_adapter.Item_fuwu_btn() {
                            @Override
                            public void setonClick(String serviceName) {
                                if (serviceName.equals("实时天气")){
                                    getFragment(new Fragment_Fuwu_tianqi());
                                }else if (serviceName.equals("门诊预约")){
                                    getFragment(new Fragment_fuwu_menzhen());
                                }else if (serviceName.equals("活动")){
                                    getFragment(new Fragment_fuwu_fupin());
                                }
                            }
                        });


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
        edSeek = view.findViewById(R.id.ed_seek);
        btnSousuo = view.findViewById(R.id.btn_sousuo);
        fuwu = view.findViewById(R.id.fuwu);
        yanglao = view.findViewById(R.id.yanglao);
        lvyou = view.findViewById(R.id.lvyou);
        huanbao = view.findViewById(R.id.huanbao);
        yiliao = view.findViewById(R.id.yiliao);
        recyclerview = view.findViewById(R.id.recyclerview);
    }
}
