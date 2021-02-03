package com.example.x_smartcity_4.fragment;

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
import com.example.x_smartcity_4.adapter.Zhuye_sousuo_adapter;
import com.example.x_smartcity_4.bean.GetNewsByKeys;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/2  0:35
 */
public class Fragment_sousuo extends Fragment {
    private ImageView back;
    private TextView title;
    private RecyclerView recyclerview;
    private List<GetNewsByKeys> getNewsByKeys;
    private Zhuye_sousuo_adapter sousuo_adapter;

    public Fragment_sousuo(List<GetNewsByKeys> getNewsByKeys) {
        this.getNewsByKeys = getNewsByKeys;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_sousuo, container, false);
        initView(view);
        title.setText("搜索结果");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerview.setLayoutManager(linearLayoutManager);

        sousuo_adapter = new Zhuye_sousuo_adapter(getNewsByKeys);
        recyclerview.setAdapter(sousuo_adapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_zhuye());
            }
        });

        return view;
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    private void initView(View view) {
        back = view.findViewById(R.id.back);
        title = view.findViewById(R.id.title);
        recyclerview = view.findViewById(R.id.recyclerview);
    }
}
