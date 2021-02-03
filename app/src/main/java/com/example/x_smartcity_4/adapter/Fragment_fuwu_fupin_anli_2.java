package com.example.x_smartcity_4.adapter;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_smartcity_4.R;
import com.example.x_smartcity_4.bean.FpCaseList;
import com.example.x_smartcity_4.bean.GetUserInfo;
import com.example.x_smartcity_4.fragment.fuwu.Fragment_fuwu_fupin_anli;
import com.example.x_smartcity_4.net.OKHttpTo;
import com.example.x_smartcity_4.net.OkHttpLo;
import com.example.x_smartcity_4.net.OkHttpLoImage;
import com.example.x_smartcity_4.net.OkHttpToImage;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.internal.Util;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/2  14:25
 */
public class Fragment_fuwu_fupin_anli_2 extends Fragment {
    private FpCaseList list;
    private ImageView back;
    private TextView title;
    private TextView txtWode;
    private TextView tvTitle;
    private ImageView ivImage;
    private TextView tvMsg;
    private TextView tvTime;
    private TextView tvLook;
    private Button btSubmit;

    public Fragment_fuwu_fupin_anli_2(FpCaseList list) {
        this.list = list;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_fupin_anli2, container, false);
        initView(view);
        title.setText("扶贫案例");

        getOkHttp();
        tvTitle.setText(list.getCasetitle());
        getOkHttp1();

        tvLook.setText("点赞人数：" + list.getThumbup());
        tvTime.setText("发布日期" + list.getReporttime());

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOkHttp();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_fuwu_fupin_anli());
            }
        });

        return view;
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    private void setOkHttp() {
        int s = +list.getThumbup();
        new OKHttpTo().setUrl("fpThumbup")
                .setJSONObject("caseid",list.getCaseid())
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        tvLook.setText("点赞人数：" + (s + 1));
                        Toast.makeText(getContext(),"点赞成功",Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(IOException obj) {
                        Toast.makeText(getContext(),"点赞失败",Toast.LENGTH_SHORT).show();
                    }
                }).start();
    }

    private void getOkHttp1() {
        new OKHttpTo()
                .setUrl("getUserInfo")
                .setJSONObject("userid",list.getUserid())
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        GetUserInfo userInfo = new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").optJSONObject(0).toString()
                                , GetUserInfo.class);

                        tvMsg.setText(list.getCaseContent() + "\n发布者：" + userInfo.getName());
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getOkHttp() {
        new OkHttpToImage()
                .setUrl(list.getCasepicture())
                .setOkHttpLoImage(new OkHttpLoImage() {
                    @Override
                    public void onResponse(Call call, Bitmap bitmap) {
                        ivImage.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onFailure(Call call, IOException obj) {

                    }
                }).start();
    }

    private void initView(View view) {
        back = view.findViewById(R.id.back);
        title = view.findViewById(R.id.title);
        txtWode = view.findViewById(R.id.txt_wode);
        tvTitle = view.findViewById(R.id.tv_title);
        ivImage = view.findViewById(R.id.iv_image);
        tvMsg = view.findViewById(R.id.tv_msg);
        tvTime = view.findViewById(R.id.tv_time);
        tvLook = view.findViewById(R.id.tv_look);
        btSubmit = view.findViewById(R.id.bt_submit);
    }
}
