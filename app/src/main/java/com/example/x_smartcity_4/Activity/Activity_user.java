package com.example.x_smartcity_4.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.x_smartcity_4.App;
import com.example.x_smartcity_4.R;
import com.example.x_smartcity_4.bean.GetUsers;
import com.example.x_smartcity_4.net.OKHttpTo;
import com.example.x_smartcity_4.net.OkHttpLo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Activity_user extends AppCompatActivity {

    private TextView btnFanhui;
    private EditText edUser;
    private EditText edPass;
    private Button btnLogin;
    private String user,pass;
    private List<GetUsers> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        initView();

        btn();
    }

    private void btn() {
        btnFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = edUser.getText().toString();
                pass = edPass.getText().toString();
                if (user.equals("")){
                    Toast.makeText(Activity_user.this,"用户名不能为空",Toast.LENGTH_SHORT).show();
                }else if (pass.equals("")){
                    Toast.makeText(Activity_user.this,"密码不能为空",Toast.LENGTH_SHORT).show();
                }else {
                    getOkHttp();
                }
            }
        });
    }

    private void getOkHttp() {
        if (users ==null){
            users = new ArrayList<>();
        }else {
            users.clear();
        }
        new OKHttpTo()
                .setUrl("getUsers")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        users.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetUsers>>(){}.getType()));
                        getdata();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getdata() {
        for (GetUsers u :users){
            if (user.equals(u.getUsername())){
                getOkHttppass(u.getUserid());
            }
        }
    }

    private void getOkHttppass(String userid) {
        new OKHttpTo()
                .setUrl("getPwd")
                .setJSONObject("userid",userid)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        String pass1 = jsonObject.optString("password");
                        if (pass.equals(pass1)){
                            Toast.makeText(Activity_user.this,"登录成功",Toast.LENGTH_SHORT).show();
                            App.setUserid(userid);
                            finish();
                        }else {
                            Toast.makeText(Activity_user.this,"密码错误",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void initView() {
        btnFanhui = findViewById(R.id.btn_fanhui);
        edUser = findViewById(R.id.ed_user);
        edPass = findViewById(R.id.ed_pass);
        btnLogin = findViewById(R.id.btn_login);
    }
}