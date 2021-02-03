package com.example.x_smartcity_4.fragment.A_Page;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.x_smartcity_4.R;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/1/30  15:19
 */
public class Fragment_1 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_1,container,false);


        return view;
    }
}
