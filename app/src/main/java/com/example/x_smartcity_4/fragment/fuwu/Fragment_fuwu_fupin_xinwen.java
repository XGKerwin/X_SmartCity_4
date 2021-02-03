package com.example.x_smartcity_4.fragment.fuwu;

import android.os.Bundle;
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
import com.example.x_smartcity_4.adapter.FUwu_fupin_xinwen_adapter;
import com.example.x_smartcity_4.bean.FpNewsList;
import com.example.x_smartcity_4.net.OKHttpTo;
import com.example.x_smartcity_4.net.OkHttpLo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/2  11:49
 */
public class Fragment_fuwu_fupin_xinwen extends Fragment {

    List<FpNewsList> fpNews;
    private ImageView back;
    private TextView title;
    private TextView txtWode;
    private RecyclerView recyclerview;
    private FUwu_fupin_xinwen_adapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_fupin_xinwen, container, false);
        initView(view);
        title.setText("扶贫新闻");
        getOkHttp();

        btn();


        return view;
    }

    private void btn() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_fuwu_fupin());
            }
        });
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    private void getOkHttp() {
        if (fpNews == null) {
            fpNews = new ArrayList<>();
        } else {
            fpNews.clear();
        }
        new OKHttpTo()
                .setUrl("fpNewsList")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        fpNews.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<FpNewsList>>() {
                                }.getType()));

                        List<FpNewsList> fpNews1 = new ArrayList<>();
                        for (int i = fpNews.size() - 1; i >= 0; i--) {
                            FpNewsList fpNew = fpNews.get(i);
                            if (fpNew.getIsfront() != null) {
                                if (fpNew.getIsfront().equals("1")) {
                                    fpNews1.add(fpNew);
                                    fpNews.remove(i);
                                }
                            }
                        }

                        Collections.sort(fpNews1, new Comparator<FpNewsList>() {
                            @Override
                            public int compare(FpNewsList o1, FpNewsList o2) {
                                //2020-04-03 00:00:00
                                Date date = null;
                                Date date1 = null;
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                try {
                                    date = format.parse(o1.getReporttime());
                                    date1 = format.parse(o2.getReporttime());

                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                return date1.compareTo(date);
                            }
                        });

                        Collections.sort(fpNews, new Comparator<FpNewsList>() {
                            @Override
                            public int compare(FpNewsList o1, FpNewsList o2) {
                                //2020-04-03 00:00:00
                                Date date = null;
                                Date date1 = null;
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                try {
                                    date = format.parse(o1.getReporttime());
                                    date1 = format.parse(o2.getReporttime());

                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                return date1.compareTo(date);
                            }
                        });

                        for (int i = fpNews1.size() - 1; i >= 0; i--) {
                            fpNews.add(0, fpNews1.get(i));
                        }

                        if (adapter == null){
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                            recyclerview.setLayoutManager(linearLayoutManager);

                            adapter = new FUwu_fupin_xinwen_adapter(fpNews,getActivity());
                        }else {
                            adapter.notifyDataSetChanged();
                        }
                        recyclerview.setAdapter(adapter);



                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void initView(View view) {
        back = view.findViewById(R.id.back);
        title = view.findViewById(R.id.title);
        txtWode = view.findViewById(R.id.txt_wode);
        recyclerview = view.findViewById(R.id.recyclerview);
    }
}
