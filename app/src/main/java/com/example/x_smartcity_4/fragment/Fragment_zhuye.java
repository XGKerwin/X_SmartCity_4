package com.example.x_smartcity_4.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_4.R;
import com.example.x_smartcity_4.adapter.Zhuye_fuwu_adapter;
import com.example.x_smartcity_4.adapter.Zhuye_xinwen_adater;
import com.example.x_smartcity_4.adapter.Zhuye_zhuti_adapter;
import com.example.x_smartcity_4.bean.GetImages;
import com.example.x_smartcity_4.bean.GetNEWsList;
import com.example.x_smartcity_4.bean.GetNewsByKeys;
import com.example.x_smartcity_4.bean.Service_info;
import com.example.x_smartcity_4.net.OKHttpTo;
import com.example.x_smartcity_4.net.OkHttpLo;
import com.example.x_smartcity_4.net.OkHttpLoImage;
import com.example.x_smartcity_4.net.OkHttpToImage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/1/30  15:36
 */
public class Fragment_zhuye extends Fragment {

    private EditText edSeek;
    private LinearLayout btnSousuo;
    private ViewFlipper viewFlipper;
    private RecyclerView recyclerViewFuwu;
    private RecyclerView recyclerViewZhuti;
    private RecyclerView recyclerViewXinwen;
    private List<ImageView> imageViews;
    private List<GetImages> getImages;
    private List<Service_info> service_infos;
    private Zhuye_fuwu_adapter fuwu_adapter;
    private FragmentTransaction fragmentTransaction;
    private Zhuye_zhuti_adapter zhuti_adapter;
    private List<GetNEWsList> neWsLists;
    private List<GetNewsByKeys> getNewsByKeys;
    private Zhuye_xinwen_adater xinwen_adater;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_zhuye,container,false);
        initView(view);
        imageViews = new ArrayList<>();
        getImageview(); //轮播图
        getfuwu();      //服务
        getzhuti();     //热门主题
        getxinwen();    //新闻推荐

        btnSousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = edSeek.getText().toString();
                if (s.equals("")){
                    Toast.makeText(getContext(),"请输入内容",Toast.LENGTH_SHORT).show();
                }else {
                    getOkHttp(s);

                }
            }
        });

        return view;
    }

    private void getOkHttp(String s) {
        if (getNewsByKeys == null){
            getNewsByKeys = new ArrayList<>();
        }else {
            getNewsByKeys.clear();
        }
        new OKHttpTo()
                .setUrl("getNewsByKeys")
                .setJSONObject("keys",s)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        getNewsByKeys.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetNewsByKeys>>(){}.getType()));
                        if (getNewsByKeys.size()==0){
                            Toast.makeText(getContext(),"未找到结果",Toast.LENGTH_SHORT).show();
                        }else {
                            getFragment(new Fragment_sousuo(getNewsByKeys));
                        }

                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getxinwen() {
        if (neWsLists == null){
            neWsLists = new ArrayList<>();
        }else {
            neWsLists.clear();
        }
        int s = (int) Math.floor((Math.random()*15)+1);
        new OKHttpTo()
                .setUrl("getNEWsList")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        neWsLists.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetNEWsList>>(){}.getType()));
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                        recyclerViewXinwen.setLayoutManager(linearLayoutManager);

                        xinwen_adater = new Zhuye_xinwen_adater(neWsLists,getActivity(),s);
                        recyclerViewXinwen.setAdapter(xinwen_adater);
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();

    }

    private void getzhuti() {
        new OKHttpTo()
                .setUrl("getAllSubject")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        List<String> strings = new ArrayList<>();
                        String jsonArray[] = jsonObject.optString("ROWS_DETAIL").replace("[", "").replace("]", "").split(",");
                        for (int i = 0; i < jsonArray.length; i++) {
                            strings.add(jsonArray[i].trim());
                        }
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
                        recyclerViewZhuti.setLayoutManager(gridLayoutManager);

                        zhuti_adapter = new Zhuye_zhuti_adapter(strings,getActivity());
                        recyclerViewZhuti.setAdapter(zhuti_adapter);

                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getfuwu() {
        if (service_infos == null) {
            service_infos = new ArrayList<>();
        } else {
            service_infos.clear();
        }

        for (int i = 1; i < 10; i++) {
            getOkHttpinfo(i);
        }

    }

    private void getOkHttpinfo(int i) {
        new OKHttpTo()
                .setUrl("service_info")
                .setJSONObject("serviceid", i)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        JSONObject jsonObject1 = jsonArray.optJSONObject(0);
                        Service_info service_info = new Gson().fromJson(jsonObject1.toString(), Service_info.class);
                        service_infos.add(service_info);
                        if (service_infos.size() == 9) {
                            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),5);
                            gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
                            recyclerViewFuwu.setLayoutManager(gridLayoutManager);
                            fuwu_adapter = new Zhuye_fuwu_adapter(service_infos);
                            recyclerViewFuwu.setAdapter(fuwu_adapter);
                            fuwu_adapter.setItemOnclick(new Zhuye_fuwu_adapter.Item_zhuyeOnclick() {
                                @Override
                                public void onClick(String serviceName) {
//                                    if (serviceName.equals("活动")){
//                                        getFragment(new Fragment_S_huodong());
//                                    }else if (serviceName.equals("更多服务")){
//                                        getFragment(new Fragment_fuwu());
//                                    }
                                }
                            });
                        }
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getImageview() {
        if (getImages == null){
            getImages = new ArrayList<>();
        }else {
            getImages.clear();
        }
        new OKHttpTo()
                .setUrl("getImages")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        getImages.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetImages>>(){}.getType()));
                        getImageshow();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();

    }

    private void getImageshow() {
        for (int i=0;i<getImages.size();i++){
            int finalI = i;
            new OkHttpToImage().setUrl(getImages.get(i).getPath())
                    .setOkHttpLoImage(new OkHttpLoImage() {
                        @Override
                        public void onResponse(Call call, Bitmap bitmap) {
                            try {
                                ImageView imageView = new ImageView(getContext());
                                imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
                                imageView.setImageBitmap(bitmap);
                                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                                imageViews.add(imageView);
                                imageView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        getFragment(new Fragment_huodongxiangqing(getImages.get(finalI).getNewid()));
                                    }
                                });
                                if (imageViews.size() == 5){
                                    for (int i = 0; i < imageViews.size(); i++) {
                                        viewFlipper.addView(imageViews.get(i));
                                    }
                                    viewFlipper.startFlipping();
                                }

                            }catch (NullPointerException e){

                            }

                        }

                        @Override
                        public void onFailure(Call call, IOException obj) {

                        }
                    }).start();
        }
    }

    private void getFragment(Fragment fragment) {
        fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    private void initView(View view) {
        edSeek = view.findViewById(R.id.ed_seek);
        btnSousuo = view.findViewById(R.id.btn_sousuo);
        viewFlipper = view.findViewById(R.id.view_flipper);
        recyclerViewFuwu = view.findViewById(R.id.recyclerView_fuwu);
        recyclerViewZhuti = view.findViewById(R.id.recyclerView_zhuti);
        recyclerViewXinwen = view.findViewById(R.id.recyclerView_xinwen);
    }
}

