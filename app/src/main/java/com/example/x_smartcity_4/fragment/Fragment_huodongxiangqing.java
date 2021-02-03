package com.example.x_smartcity_4.fragment;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_4.App;
import com.example.x_smartcity_4.R;
import com.example.x_smartcity_4.adapter.Wode_tuijian_adapter;
import com.example.x_smartcity_4.adapter.Zhuye_pinglun_adapter;
import com.example.x_smartcity_4.bean.GetCommitById;
import com.example.x_smartcity_4.bean.GetNEWsList;
import com.example.x_smartcity_4.net.OKHttpTo;
import com.example.x_smartcity_4.net.OkHttpLo;
import com.example.x_smartcity_4.net.OkHttpLoImage;
import com.example.x_smartcity_4.net.OkHttpToImage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/2  0:23
 */
public class Fragment_huodongxiangqing extends Fragment {
    private String id;
    private ImageView back;
    private TextView ABiaoti;
    private TextView xinwenTitle;
    private ImageView imgXinwen;
    private TextView txtXinwen;
    private RecyclerView recyclerviewTuijian;
    private TextView btnXiepinglun;
    private RecyclerView recyclerviewPinglun;
    private FragmentTransaction fragmentTransaction;
    private List<GetNEWsList> neWsLists,newlists;
    private Wode_tuijian_adapter adapter_tuijian;
    private Zhuye_pinglun_adapter pinglun_adapter;
    private String user;
    private List<GetCommitById> commitByIds;

    public Fragment_huodongxiangqing(String finalI) {
        this.id = finalI;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_huodongxiangqing, null);
        initView(view);

        user = App.getUserid();
        if (user == null){
            Toast.makeText(getContext(),"请登录",Toast.LENGTH_SHORT).show();
        }

        getOkHttpmsg();
        gettuijian();   //推荐新闻
        getpinglun();   //评论

        btnXiepinglun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user == null){
                    Toast.makeText(getContext(),"请先登录",Toast.LENGTH_SHORT).show();
                }else {
                    getxiepinglun();
                }

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_zhuye());
            }
        });

        return view;
    }

    private void getpinglun() {
        if (commitByIds == null){
            commitByIds = new ArrayList<>();
        }else {
            commitByIds.clear();
        }
        new OKHttpTo()
                .setUrl("getCommitById")
                .setJSONObject("id",id)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        commitByIds.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetCommitById>>(){}.getType()));

                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                        recyclerviewPinglun.setLayoutManager(linearLayoutManager);

                        pinglun_adapter = new Zhuye_pinglun_adapter(commitByIds);
                        recyclerviewPinglun.setAdapter(pinglun_adapter);
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();

    }

    private void getxiepinglun() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_yijian,null);
        builder.setView(view);
        builder.setCancelable(false);

        TextView fanhui = view.findViewById(R.id.dia_yijian_quxiao);
        TextView queding = view.findViewById(R.id.dia_yijian_queding);
        final EditText editText = view.findViewById(R.id.dia_yijian_edit);

        final Dialog dialog = builder.show();

        queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String neirong = editText.getText().toString();
                if (neirong.equals("")){
                    Toast.makeText(getContext(),"请输入您的意见",Toast.LENGTH_SHORT).show();
                }else {
                    getpublicOpinion(neirong,dialog);
                }
            }
        });

        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    private void getpublicOpinion(String neirong, final Dialog dialog) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String time = simpleDateFormat.format(date);
        /**
         * {"userid":"1","content":"内容","time":"yyyy.MM.dd HH:mm:ss"}
         */
        new OKHttpTo()
                .setUrl("publicOpinion")
                .setJSONObject("userid",user)
                .setJSONObject("content",neirong)
                .setJSONObject("time",time)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Toast.makeText(getContext(),"提交成功",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void gettuijian() {
        int s = (int) Math.floor((Math.random()*15)+1);
        if (neWsLists == null){
            neWsLists = new ArrayList<>();
        }else {
            neWsLists.clear();
        }
        new OKHttpTo()
                .setUrl("getNEWsList")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        neWsLists.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetNEWsList>>(){}.getType()));

                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                        recyclerviewTuijian.setLayoutManager(linearLayoutManager);
                        adapter_tuijian = new Wode_tuijian_adapter(neWsLists,id,s);
                        recyclerviewTuijian.setAdapter(adapter_tuijian);
                        adapter_tuijian.setTuijian(new Wode_tuijian_adapter.Item_tuijian() {
                            @Override
                            public void setOnclick(String s) {
                                getFragment(new Fragment_huodongxiangqing(s));
                            }
                        });
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getFragment(Fragment fragment) {
        fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    private void getOkHttpmsg() {
        if (newlists == null){
            newlists = new ArrayList<>();
        }else {
            newlists.clear();
        }
        Log.d("vvvvvvvv", "getOkHttpmsg: "+id);
        new OKHttpTo()
                .setUrl("getNEWsList")
                .setJSONObject("id",id)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        newlists.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetNEWsList>>(){}.getType()));
                        getdata();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getdata() {
        for (int i=0;i<newlists.size();i++){
            GetNEWsList getNEWsList = newlists.get(i);
            String tu = getNEWsList.getNewsid();
            if (id.equals(tu)){
                getImage(getNEWsList.getPicture());
            }
        }
    }

    private void getImage(String tu) {
        Log.d("fffffff", "getImage: "+tu);
        new OkHttpToImage()
                .setUrl(tu)
                .setOkHttpLoImage(new OkHttpLoImage() {
                    @Override
                    public void onResponse(Call call, Bitmap bitmap) {
                        imgXinwen.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onFailure(Call call, IOException obj) {

                    }
                }).start();
    }

    private void initView(View view) {
        back = view.findViewById(R.id.back);
        ABiaoti = view.findViewById(R.id.A_biaoti);
        xinwenTitle = view.findViewById(R.id.xinwen_title);
        imgXinwen = view.findViewById(R.id.img_xinwen);
        txtXinwen = view.findViewById(R.id.txt_xinwen);
        recyclerviewTuijian = view.findViewById(R.id.recyclerview_tuijian);
        btnXiepinglun = view.findViewById(R.id.btn_xiepinglun);
        recyclerviewPinglun = view.findViewById(R.id.recyclerview_pinglun);
    }
}
