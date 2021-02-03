package com.example.x_smartcity_4.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_4.R;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/2  0:17
 */
public class Zhuye_zhuti_adapter extends RecyclerView.Adapter<Zhuye_zhuti_adapter.ViewHolder> {

    private List<String> strings;
    private FragmentActivity activity;

    public Zhuye_zhuti_adapter(List<String> strings, FragmentActivity activity) {
        this.strings = strings;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_zhuti, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String s = strings.get(position);
        switch (s) {
            case "电影":
                holder.zhuyeZhutiImg.setImageResource(R.drawable.zhuti_dianying);
                break;
            case "国庆专题":
                holder.zhuyeZhutiImg.setImageResource(R.drawable.zhuti_guoqing);
                break;
            case "抗肺炎":
                holder.zhuyeZhutiImg.setImageResource(R.drawable.zhuti_kangfeiyan);
                break;
            case "烈士纪念日":
                holder.zhuyeZhutiImg.setImageResource(R.drawable.zhuti_lieshi);
                break;
        }
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_zhuti_xinwen(s));
            }
        });
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    @Override
    public int getItemCount() {
        if (strings.size() == 0) return 0;
        return strings.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout layout;
        private ImageView zhuyeZhutiImg;

        public ViewHolder(@NonNull View view) {
            super(view);
            layout = view.findViewById(R.id.layout);
            zhuyeZhutiImg = view.findViewById(R.id.zhuye_zhuti_img);
        }
    }
}
