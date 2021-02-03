package com.example.x_smartcity_4.adapter;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_smartcity_4.App;
import com.example.x_smartcity_4.R;
import com.example.x_smartcity_4.bean.FpVillageList;
import com.example.x_smartcity_4.bean.FpVilliageInfo;
import com.example.x_smartcity_4.fragment.fuwu.Fragment_fuwu_fupin_cun;
import com.example.x_smartcity_4.net.OKHttpTo;
import com.example.x_smartcity_4.net.OkHttpLo;
import com.example.x_smartcity_4.net.OkHttpLoImage;
import com.example.x_smartcity_4.net.OkHttpToImage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/2  17:03
 */
public class Fragment_fuwu_fupin_cun_2 extends Fragment {
    private FpVillageList list;
    private ImageView back;
    private TextView title;
    private TextView txtWode;
    private ImageView img;
    private TextView txtMag;
    private TextView txtName;
    private TextView txtTime;
    private TextView btnFupin;
    private List<FpVilliageInfo> fpVilliageInfos;
    private String user;

    public Fragment_fuwu_fupin_cun_2(FpVillageList villid) {
        this.list = villid;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_fupin_cun_2, container, false);
        initView(view);

        user = App.getUserid();
        title.setText(list.getVillname());
        txtMag.setText(list.getVilldesc());
        getOkhttp();


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_fuwu_fupin_cun());
            }
        });

        btnFupin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user == null){
                    Toast.makeText(getContext(),"请登录",Toast.LENGTH_SHORT).show();
                }else {
                    getFragment(new Fragment_fuwu_fupin_cun_3(list));
                }

            }
        });


        return view;
    }

    private void getImage() {
        Log.d("fffffffffffffff", "getImage: "+fpVilliageInfos.get(0).getEnviroment_pic());
        new OkHttpToImage()
                .setUrl(fpVilliageInfos.get(0).getEnviroment_pic())
                .setOkHttpLoImage(new OkHttpLoImage() {
                    @Override
                    public void onResponse(Call call, Bitmap bitmap) {
                        img.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onFailure(Call call, IOException obj) {

                    }
                }).start();
    }

    private void getOkhttp() {
        if (fpVilliageInfos == null){
            fpVilliageInfos = new ArrayList<>();
        }else {
            fpVilliageInfos.clear();
        }
        new OKHttpTo()
                .setUrl("fpVilliageInfo")
                .setJSONObject("villid",list.getVillid())
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        fpVilliageInfos.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<FpVilliageInfo>>(){}.getType()));
                        txtName.setText("发布人："+fpVilliageInfos.get(0).getReport());
                        txtTime.setText("时间："+fpVilliageInfos.get(0).getReporttime());
                        Log.d("fffffffffffffff", "getImage: "+fpVilliageInfos.get(0).getEnviroment_pic());
                        getImage();
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
        img = view.findViewById(R.id.img);
        txtMag = view.findViewById(R.id.txt_mag);
        txtName = view.findViewById(R.id.txt_name);
        txtTime = view.findViewById(R.id.txt_time);
        btnFupin = view.findViewById(R.id.btn_fupin);
    }
}
