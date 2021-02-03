package com.example.x_smartcity_4.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_4.R;
import com.example.x_smartcity_4.bean.FpVilliagerListByVillid;
import com.example.x_smartcity_4.fragment.fuwu.Fragment_fuwu_fupin_zoufang;
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
 * date   : 2021/2/3  0:20
 */
public class Fragment_fupin_zoufang1 extends Fragment {
    private int id;
    private String name;
    private ImageView back;
    private TextView title;
    private TextView txtWode;
    private RecyclerView recyclerview;
    private Fuwu_fupin_zoufang1_adapter adapter;
    private List<FpVilliagerListByVillid> fpVilliagerListByVillids;

    public Fragment_fupin_zoufang1(int villid, String villname) {
        this.id = villid;
        this.name = villname;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_fupin_zoufang1, container, false);
        initView(view);
        title.setText("走访户主");

        getOkHttp();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_fuwu_fupin_zoufang());
            }
        });

        return view;
    }

    private void getOkHttp() {
        if (fpVilliagerListByVillids == null){
            fpVilliagerListByVillids = new ArrayList<>();
        }else {
            fpVilliagerListByVillids.clear();
        }
        new OKHttpTo()
                .setUrl("fpVilliagerListByVillid")
                .setJSONObject("villid",id)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        fpVilliagerListByVillids.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<FpVilliagerListByVillid>>(){}.getType()));

                        if (adapter == null){
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                            recyclerview.setLayoutManager(linearLayoutManager);

                            adapter = new Fuwu_fupin_zoufang1_adapter(fpVilliagerListByVillids,getActivity());
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

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    private void initView(View view) {
        back = view.findViewById(R.id.back);
        title = view.findViewById(R.id.title);
        txtWode = view.findViewById(R.id.txt_wode);
        recyclerview = view.findViewById(R.id.recyclerview);
    }
}
