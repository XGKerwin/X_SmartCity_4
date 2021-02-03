package com.example.x_smartcity_4.fragment.fuwu;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_4.R;
import com.example.x_smartcity_4.adapter.FUwu_tianqi_adapter;
import com.example.x_smartcity_4.bean.Get_weather_info;
import com.example.x_smartcity_4.net.OKHttpTo;
import com.example.x_smartcity_4.net.OkHttpLo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/1/30  15:57
 */
public class Fragment_Fuwu_tianqi extends Fragment {

    private List<Get_weather_info> get_weather_infos;
    private String weather, wendu, data;
    private ImageView back;
    private TextView title;
    private TextView txtWode;
    private TextView txtTianqi;
    private ImageView image;
    private TextView txtQiwen;


    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            getOkHttp();
            getOkHttp1();
            return false;
        }
    });


    private ImageView imgShuaxin;
    private RecyclerView recyclerview;
    private FUwu_tianqi_adapter adapter;
    private TextView txtZiwaixian;
    private TextView txtGanmao;
    private TextView txtChuanyi;
    private TextView txtYundong;
    private TextView txtKongqi;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_fuwu_tianqi, container, false);
        initView(view);
        title.setText("实时天气");

        new Thread(new Runnable() {
            @Override
            public void run() {
                do {
                    handler.sendEmptyMessage(0);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } while (true);
            }
        }).start();

        imgShuaxin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOkHttp();
                getOkHttp1();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_fuwu());
            }
        });


        return view;
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    private void getOkHttp1() {
        new OKHttpTo()
                .setUrl("get_sense")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        int wendu = jsonObject.optInt("temperature");
                        int guangzhao = jsonObject.optInt("illumination");
                        int co2 = jsonObject.optInt("co2");
                        int pm = jsonObject.optInt("pm2.5");
                        getSHow1(wendu, guangzhao, co2, pm);

                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getSHow1(int wendu, int guangzhao, int co2, int pm) {
        if (guangzhao < 1000){
            txtZiwaixian.setText("紫外线指数：弱    辐射较弱，涂擦SPF12~15、PA+护肤品");
        }else if (guangzhao > 3000){
            txtZiwaixian.setText("紫外线指数：强    尽量减少外出，需要涂抹高倍数防晒霜");
        }else {
            txtZiwaixian.setText("紫外线指数：中等    涂擦SPF大于15、PA+防晒护肤品");
        }
        if (wendu<8){
            txtGanmao.setText("感冒指数：较易发    温度低，风较大，较易发生感冒，注意防护");
        }else {
            txtGanmao.setText("感冒指数：少发    无明显降温，感冒机率较低");
        }
        if (wendu<12){
            txtChuanyi.setText("穿衣指数：冷    建议穿长袖衬衫、单裤等服装");
        }else if (wendu>21){
            txtChuanyi.setText("穿衣指数：热    适合穿T恤、短薄外套等夏季服装");
        }else {
            txtChuanyi.setText("穿衣指数：舒适    建议穿短袖衬衫、单裤等服装");
        }
        if (co2<3000){
            txtYundong.setText("运动指数：适宜    气候适宜，推荐您进行户外运动");
        }else if (co2>6000){
            txtYundong.setText("运动指数：较不宜    空气氧气含量低，请在室内进行休闲运动");
        }else {
            txtYundong.setText("运动指数：中    易感人群应适当减少室外活动");
        }

        if (pm<30){
            txtKongqi.setText("空气污染：优    空气质量非常好，非常适合户外活动，趁机出去多呼吸新鲜空气");
        }else if (pm > 100){
            txtKongqi.setText("空气污染：污染    空气质量差，不适合户外活动");
        }else {
            txtKongqi.setText("空气污染：良    易感人群应适当减少室外活动");
        }


    }

    private void getOkHttp() {
        if (get_weather_infos == null) {
            get_weather_infos = new ArrayList<>();
        } else {
            get_weather_infos.clear();
        }
        new OKHttpTo()
                .setUrl("get_weather_info")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        weather = jsonObject.optString("weather");
                        data = jsonObject.optString("date");
                        wendu = jsonObject.optString("temperature");
                        get_weather_infos.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<Get_weather_info>>() {
                                }.getType()));
                        getSHow();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getSHow() {
        switch (weather) {
            case "晴":
                image.setImageResource(R.drawable.tianqi_qing);
                break;
            case "多云":
                image.setImageResource(R.drawable.tianqi_duoyun);
                break;
            case "阴":
                image.setImageResource(R.drawable.tianqi_yin);
                break;
            case "霾":
                image.setImageResource(R.drawable.tianqi_mai);
                break;
            case "雨":
                image.setImageResource(R.drawable.tianqi_xiaoyu_11);
                break;
            case "雪":
                image.setImageResource(R.drawable.tianqi_xue);
                break;
        }
        txtQiwen.setText(wendu + "°");
        txtTianqi.setText("2021年" + data + "   " + weather);
        if (adapter == null) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            recyclerview.setLayoutManager(linearLayoutManager);

            adapter = new FUwu_tianqi_adapter(get_weather_infos);
        } else {
            adapter.notifyDataSetChanged();
        }
        recyclerview.setAdapter(adapter);

    }

    private void initView(View view) {
        back = view.findViewById(R.id.back);
        title = view.findViewById(R.id.title);
        txtWode = view.findViewById(R.id.txt_wode);
        txtTianqi = view.findViewById(R.id.txt_tianqi);
        image = view.findViewById(R.id.image);
        txtQiwen = view.findViewById(R.id.txt_qiwen);
        imgShuaxin = view.findViewById(R.id.img_shuaxin);
        recyclerview = view.findViewById(R.id.recyclerview);
        txtZiwaixian = view.findViewById(R.id.txt_ziwaixian);
        txtGanmao = view.findViewById(R.id.txt_ganmao);
        txtChuanyi = view.findViewById(R.id.txt_chuanyi);
        txtYundong = view.findViewById(R.id.txt_yundong);
        txtKongqi = view.findViewById(R.id.txt_kongqi);
    }
}
