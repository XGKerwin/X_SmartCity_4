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

import com.example.x_smartcity_4.R;
import com.example.x_smartcity_4.adapter.Fuwu_fupin_cun_adapter;
import com.example.x_smartcity_4.bean.FpVillageList;
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
 * date   : 2021/2/2  15:37
 */
public class Fragment_fuwu_fupin_cun extends Fragment {


    private ImageView back;
    private TextView title;
    private TextView txtWode;
    private RecyclerView recyclerview;
    private List<FpVillageList> fpVillageLists;
    private Fuwu_fupin_cun_adapter adapter;
    private TextView txtJiazai;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_fupin_cunqing, container, false);
        initView(view);
        title.setText("村情村貌");

        getOkHttp();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_fuwu_fupin());
            }
        });

        txtJiazai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOkHttp();
            }
        });
        return view;
    }

    private void getOkHttp() {
        if (fpVillageLists == null) {
            fpVillageLists = new ArrayList<>();
        } else {
            fpVillageLists.clear();
        }
        new OKHttpTo()
                .setUrl("fpVillageList")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        fpVillageLists.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<FpVillageList>>() {
                                }.getType()));
                        if (adapter == null) {
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                            recyclerview.setLayoutManager(linearLayoutManager);

                            adapter = new Fuwu_fupin_cun_adapter(fpVillageLists, 3, getActivity());
                        } else {
                            adapter = new Fuwu_fupin_cun_adapter(fpVillageLists, fpVillageLists.size(), getActivity());
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
        fragmentTransaction.replace(R.id.fragment_home, fragment).commit();
    }

    private void initView(View view) {
        back = view.findViewById(R.id.back);
        title = view.findViewById(R.id.title);
        txtWode = view.findViewById(R.id.txt_wode);
        recyclerview = view.findViewById(R.id.recyclerview);
        txtJiazai = view.findViewById(R.id.txt_jiazai);
    }
}
