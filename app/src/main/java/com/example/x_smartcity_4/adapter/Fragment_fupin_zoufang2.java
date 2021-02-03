package com.example.x_smartcity_4.adapter;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.x_smartcity_4.App;
import com.example.x_smartcity_4.R;
import com.example.x_smartcity_4.bean.FpVilliagerListByVillid;
import com.example.x_smartcity_4.fragment.fuwu.Fragment_fuwu_fupin_gongzuotai;
import com.example.x_smartcity_4.net.OKHttpTo;
import com.example.x_smartcity_4.net.OkHttpLo;

import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/3  0:45
 */
public class Fragment_fupin_zoufang2 extends Fragment {
    private FpVilliagerListByVillid byVillid;
    private ImageView back;
    private TextView title;
    private TextView txtWode;
    private TextView btnKaishi;
    private TextView btnJieshu;
    private EditText edMudi;
    private Button btnTijiao;
    private String user;

    public Fragment_fupin_zoufang2(FpVilliagerListByVillid byVillid) {
        this.byVillid = byVillid;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_fupin_zoufang2, container, false);
        initView(view);
        title.setText("走访提交");
        user = App.getUserid();


        btn();
        return view;
    }

    private void btn() {
        btnTijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = edMudi.getText().toString();
                String time1 = btnKaishi.getText().toString();
                String time2 = btnJieshu.getText().toString();
                if (time1.equals("0000-00-00")){
                    Toast.makeText(getContext(),"请选择开始时间",Toast.LENGTH_SHORT).show();
                }else if (time2.equals("0000-00-00")){
                    Toast.makeText(getContext(),"请选择结束时间",Toast.LENGTH_SHORT).show();
                }else if (msg.equals("")){
                    Toast.makeText(getContext(),"请输入您要走访的事件",Toast.LENGTH_SHORT).show();
                }else {
                    setokHttp(msg,time1,time2);
                }

            }
        });

        btnKaishi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showshijian(btnKaishi);
            }
        });

        btnJieshu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showshijian(btnJieshu);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_fupin_zoufang1(byVillid.getVillid(),byVillid.getName()));
            }
        });
    }

    private void setokHttp(String msg, String time1, String time2) {
        /**
         * {"villiagerid":"2","starttime":"2020-8-1","endtime":"2020-9-1","intent":"对口帮扶","userid":2}
         */
        new OKHttpTo()
                .setUrl("fpInterviewPlan")
                .setJSONObject("villiagerid",byVillid.getVilliagerid())
                .setJSONObject("starttime",time1)
                .setJSONObject("endtime",time2)
                .setJSONObject("intent",msg)
                .setJSONObject("userid",user)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Toast.makeText(getContext(),"提交成功",Toast.LENGTH_SHORT).show();
                        getFragment(new Fragment_fuwu_fupin_gongzuotai());
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private TimePickerView timePickerView;
    private void showshijian(TextView textView) {
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-M-d");
        timePickerView = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                String str = format.format(date);
                textView.setText(str);

            }
        }).setType(new boolean[]{true, true, true, false, false, false}).isDialog(true).build();
        timePickerView.show();
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    private void initView(View view) {
        back = view.findViewById(R.id.back);
        title = view.findViewById(R.id.title);
        txtWode = view.findViewById(R.id.txt_wode);
        btnKaishi = view.findViewById(R.id.btn_kaishi);
        btnJieshu = view.findViewById(R.id.btn_jieshu);
        edMudi = view.findViewById(R.id.ed_mudi);
        btnTijiao = view.findViewById(R.id.btn_tijiao);
    }
}
