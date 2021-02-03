package com.example.x_smartcity_4.adapter;

import android.os.Bundle;
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

import com.example.x_smartcity_4.App;
import com.example.x_smartcity_4.R;
import com.example.x_smartcity_4.bean.FpVillageList;
import com.example.x_smartcity_4.net.OKHttpTo;
import com.example.x_smartcity_4.net.OkHttpLo;

import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/2  17:59
 */
public class Fragment_fuwu_fupin_cun_3 extends Fragment {
    private FpVillageList list;
    private ImageView back;
    private TextView title;
    private TextView txtWode;
    private EditText edbiaoti;
    private EditText ed_xuqiu;
    private EditText ed_msg;
    private EditText ed_yongyu;
    private Button btnTijiao;
    private String biaoti,xuqiu,msg,yongyu;
    private String user;

    public Fragment_fuwu_fupin_cun_3(FpVillageList list) {
        this.list = list;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_fupin_cun_3, container, false);
        initView(view);
        title.setText("扶贫信息");

        user = App.getUserid();

        btnTijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                biaoti = edbiaoti.getText().toString();
                xuqiu = ed_xuqiu.getText().toString();
                msg = ed_msg.getText().toString();
                yongyu = ed_yongyu.getText().toString();
                if (biaoti.equals("")){
                    Toast.makeText(getContext(),"标题不能为空",Toast.LENGTH_SHORT).show();
                }else if (xuqiu.equals("")){
                    Toast.makeText(getContext(),"需求不能为空",Toast.LENGTH_SHORT).show();
                }else if (msg.equals("")){
                    Toast.makeText(getContext(),"详细内容不能为空",Toast.LENGTH_SHORT).show();
                }else if (yongyu.equals("")){
                    Toast.makeText(getContext(),"用于不能为空",Toast.LENGTH_SHORT).show();
                }else {
                    getOKHttp();
                }

            }
        });

        btn();
        return view;
    }

    private void getOKHttp() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        String time = simpleDateFormat.format(date);

        //{applytitle:"c",applydesc:"cc",applycontent:"ccc",villid:1,starttime:"2020-8-8",
        // helpdesc:"888",applystate:2,userid:1}
        new OKHttpTo()
                .setUrl("fpApply")
                .setJSONObject("applytitle",biaoti)
                .setJSONObject("applydesc",xuqiu)
                .setJSONObject("applycontent",msg)
                .setJSONObject("villid",list.getVillid())
                .setJSONObject("starttime",time)
                .setJSONObject("helpdesc",yongyu)
                .setJSONObject("applystate",2)
                .setJSONObject("userid",user)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Toast.makeText(getContext(),"发布成功",Toast.LENGTH_SHORT).show();
                        getFragment(new Fragment_fuwu_fupin_cun_2(list));
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void btn() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_fuwu_fupin_cun_2(list));
            }
        });
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    private void initView(View view) {
        back = view.findViewById(R.id.back);
        title = view.findViewById(R.id.title);
        txtWode = view.findViewById(R.id.txt_wode);
        edbiaoti = view.findViewById(R.id.txt_biaoti);
        ed_xuqiu = view.findViewById(R.id.txt_xuqiu);
        ed_msg = view.findViewById(R.id.txt_msg);
        ed_yongyu = view.findViewById(R.id.txt_yongyu);
        btnTijiao = view.findViewById(R.id.btn_tijiao);
    }
}
