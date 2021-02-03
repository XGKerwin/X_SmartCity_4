package com.example.x_smartcity_4.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_smartcity_4.Activity.Activity_user;
import com.example.x_smartcity_4.App;
import com.example.x_smartcity_4.R;
import com.example.x_smartcity_4.bean.GetUserInfo;
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
 * date   : 2021/1/30  15:38
 */
public class Fragment_wode extends Fragment {

    private String user;
    private TextView textName;
    private ImageView imgTouxiang;
    private LinearLayout layoutGerenzhongxin;
    private LinearLayout layoutDingdanliebiao;
    private LinearLayout layoutMimaxiugai;
    private LinearLayout layoutYijianfankui;
    private LinearLayout layoutTuichudenglu;
    private List<GetUserInfo> userInfos;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_wode,container,false);
        initView(view);

        getOkHttp();

        btn();

        return view;
    }

    private void btn() {
        textName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Activity_user.class);
                startActivityForResult(intent,1);
            }
        });
        //个人中心
        layoutGerenzhongxin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user==null){
                    Toast.makeText(getContext(),"请登录",Toast.LENGTH_SHORT).show();
                }else {
                    getFragment(new Fragment_wode_geren(userInfos,user));
                }
            }
        });
        //订单列表
        layoutDingdanliebiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user == null){
                    Toast.makeText(getContext(),"请登录",Toast.LENGTH_SHORT).show();
                }else {
                    getFragment(new Fragment_wode_dingdan(user));
                }
            }
        });
        //密码修改
        layoutMimaxiugai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user == null){
                    Toast.makeText(getContext(),"请登录",Toast.LENGTH_SHORT).show();
                }else {
                    getdia_pass();
                }
            }
        });
        //意见反馈
        layoutYijianfankui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user == null){
                    Toast.makeText(getContext(),"请登录",Toast.LENGTH_SHORT).show();
                }else {
                    getdia_yijian();
                }
            }
        });
        //退出登录
        layoutTuichudenglu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user == null){
                    Toast.makeText(getContext(),"您还未登录",Toast.LENGTH_SHORT).show();
                }else {
                    App.setUserid(null);
                    getOkHttp();
                    Toast.makeText(getContext(),"已退出",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void getdia_yijian() {
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
                    Toast.makeText(getContext(),"您的意见已提交,感谢您的支持",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
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

    private void getdia_pass() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_mima,null);
        builder.setView(view);
        builder.setCancelable(false);
        final EditText edpass1 = view.findViewById(R.id.ed_pass1);
        final EditText edpass2 = view.findViewById(R.id.ed_pass2);
        TextView queding = view.findViewById(R.id.txt_queding);
        TextView quxiao = view.findViewById(R.id.txt_quxiao);
        final Dialog dialog = builder.show();

        queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass1 = edpass1.getText().toString();
                String pass2 = edpass2.getText().toString();
                if (pass1.equals("")){
                    Toast.makeText(getContext(),"请输入密码",Toast.LENGTH_SHORT).show();
                }else if (pass2.equals("")){
                    Toast.makeText(getContext(),"请输入密码",Toast.LENGTH_SHORT).show();
                }else if (pass1.equals(pass2)){
                    setPwd(pass1);
                    dialog.dismiss();
                }else {
                    Toast.makeText(getContext(),"两次密码输入的不一致",Toast.LENGTH_SHORT).show();
                }
            }
        });

        quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    private void setPwd(String pass1) {
        new OKHttpTo()
                .setUrl("setPwd")
                .setJSONObject("userid",user)
                .setJSONObject("password",pass1)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Toast.makeText(getContext(),"设置成功",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(IOException obj) {
                        Toast.makeText(getContext(),"设置失败",Toast.LENGTH_SHORT).show();
                    }
                }).start();

    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        getOkHttp();
    }

    private void getOkHttp() {
        user = App.getUserid();
        if (user == null){
            textName.setText("登录/注册");
            imgTouxiang.setImageResource(R.drawable.user6);
        }
        if (userInfos == null){
            userInfos = new ArrayList<>();
        }else{
            userInfos.clear();
        }
        new OKHttpTo()
                .setUrl("getUserInfo")
                .setJSONObject("userid",user)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        userInfos.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetUserInfo>>(){}.getType()));
                        textName.setText(userInfos.get(0).getName());
                        new OkHttpToImage()
                                .setUrl(userInfos.get(0).getAvatar())
                                .setOkHttpLoImage(new OkHttpLoImage() {
                                    @Override
                                    public void onResponse(Call call, Bitmap bitmap) {
                                        imgTouxiang.setImageBitmap(bitmap);
                                    }

                                    @Override
                                    public void onFailure(Call call, IOException obj) {

                                    }
                                }).start();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();

    }

    private void initView(View view) {
        textName = view.findViewById(R.id.text_name);
        imgTouxiang = view.findViewById(R.id.img_touxiang);
        layoutGerenzhongxin = view.findViewById(R.id.layout_gerenzhongxin);
        layoutDingdanliebiao = view.findViewById(R.id.layout_dingdanliebiao);
        layoutMimaxiugai = view.findViewById(R.id.layout_mimaxiugai);
        layoutYijianfankui = view.findViewById(R.id.layout_yijianfankui);
        layoutTuichudenglu = view.findViewById(R.id.layout_tuichudenglu);
    }
}
