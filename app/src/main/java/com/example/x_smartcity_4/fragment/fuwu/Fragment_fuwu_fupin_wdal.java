package com.example.x_smartcity_4.fragment.fuwu;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_4.App;
import com.example.x_smartcity_4.R;
import com.example.x_smartcity_4.adapter.Fuwu_fupin_wdal_adapter;
import com.example.x_smartcity_4.net.OKHttpTo;
import com.example.x_smartcity_4.net.OkHttpLo;

import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/3  1:22
 */
public class Fragment_fuwu_fupin_wdal extends Fragment {

    private ImageView back;
    private TextView title;
    private TextView txtWode;
    private EditText edTitle;
    private EditText edMsg;
    private RecyclerView recyclerview;
    private Button btnTijiao;
    private String user;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_fupin_wdal, container, false);
        initView(view);
        title.setText("我的案例发布");
        user = App.getUserid();

        getshowReaylist();
        
        btn();

        return view;
    }

    private void btn() {
        btnTijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String biaoti = edTitle.getText().toString();
                String msg = edMsg.getText().toString();
                
                if (biaoti.equals("")){
                    Toast.makeText(getContext(),"请输入标题",Toast.LENGTH_SHORT).show();
                }else if (msg.equals("")){
                    Toast.makeText(getContext(),"请输入内容",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(),"提交成功",Toast.LENGTH_SHORT).show();
                    getFragment(new Fragment_fuwu_fupin_gongzuotai());
                    setOkHttp(biaoti,msg);
                }
            }
        });
        
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_fuwu_fupin_gongzuotai());
            }
        });
    }

    private void setOkHttp(String biaoti, String msg) {
        /**
         * {casetitle:"111",casepicture:"a.jpg",reporttime:"2020-10-2","userid":1,"caeContent":""}
         * {casetitle:"111",casepicture:"a.jpg",reporttime:"2020-10-2","userid":1,"caeContent":""}
         */
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        String time = simpleDateFormat.format(date);

        new OKHttpTo()
                .setUrl("fpPublicCase")
                .setJSONObject("casetitle",biaoti)
                .setJSONObject("casepicture","a.jpg")
                .setJSONObject("reporttime",time)
                .setJSONObject("userid",user)
                .setJSONObject("caeContent",msg)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Toast.makeText(getContext(),"提交成功",Toast.LENGTH_SHORT).show();
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Bitmap bm = (Bitmap) data.getExtras().get("data");
        bitmaps.add(0, bm);
        adapter.notifyDataSetChanged();

        super.onActivityResult(requestCode, resultCode, data);
    }

    private List<Bitmap> bitmaps;
    private Fuwu_fupin_wdal_adapter adapter;
    
    private void getshowReaylist() {
        bitmaps = new ArrayList<>();
        bitmaps.add(null);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerview.setLayoutManager(linearLayoutManager);

        adapter = new Fuwu_fupin_wdal_adapter(bitmaps);
        recyclerview.setAdapter(adapter);


        adapter.setItemOnclick(new Fuwu_fupin_wdal_adapter.ItemOnclick() {
            @Override
            public void setOnclick(int position, String s) {
                Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(openCameraIntent, 1);
            }
        });
    }

    private void initView(View view) {
        back = view.findViewById(R.id.back);
        title = view.findViewById(R.id.title);
        txtWode = view.findViewById(R.id.txt_wode);
        edTitle = view.findViewById(R.id.ed_title);
        edMsg = view.findViewById(R.id.ed_msg);
        recyclerview = view.findViewById(R.id.recyclerview);
        btnTijiao = view.findViewById(R.id.btn_tijiao);
    }
}
