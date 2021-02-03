package com.example.x_smartcity_4.fragment.fuwu;

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

import com.example.x_smartcity_4.App;
import com.example.x_smartcity_4.R;
import com.example.x_smartcity_4.adapter.Fuwu_fupin_qiuzhu_adapter;
import com.example.x_smartcity_4.bean.FpSeekByUserid;
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
 * date   : 2021/2/2  20:01
 */
public class Fragment_fupin_qiuzhu extends Fragment {
    private ImageView back;
    private TextView title;
    private TextView txtWode;
    private RecyclerView recyclerview;
    private String user;
    private Fuwu_fupin_qiuzhu_adapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_fupin_qiuzhu, container, false);
        initView(view);
        title.setText("收到求助");
        btn();
        user = App.getUserid();

        getOkHttp();


        return view;
    }
    private List<FpSeekByUserid> fpSeekByUserids;

    private void getOkHttp() {
        if (fpSeekByUserids == null){
            fpSeekByUserids = new ArrayList<>();
        }else {
            fpSeekByUserids.clear();
        }
        new OKHttpTo()
                .setUrl("fpSeekByUserid")
                .setJSONObject("userid",user)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        fpSeekByUserids.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<FpSeekByUserid>>(){}.getType()));
                        if (adapter == null){
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                            recyclerview.setLayoutManager(linearLayoutManager);

                            adapter = new Fuwu_fupin_qiuzhu_adapter(fpSeekByUserids);
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
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_fuwu_fupin_gongzuotai());
            }
        });
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
