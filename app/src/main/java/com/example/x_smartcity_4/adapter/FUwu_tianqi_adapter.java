package com.example.x_smartcity_4.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_4.R;
import com.example.x_smartcity_4.bean.Get_weather_info;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/1/31  8:58
 */
public class FUwu_tianqi_adapter extends RecyclerView.Adapter<FUwu_tianqi_adapter.ViewHolder> {
    private List<Get_weather_info> weather_infos;


    public FUwu_tianqi_adapter(List<Get_weather_info> get_weather_infos) {
        this.weather_infos = get_weather_infos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_tianqi_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Get_weather_info weather_info = weather_infos.get(position);
        holder.txtWendu.setText(weather_info.getTemperature());
        holder.txtTime.setText(weather_info.getDate());
        switch (weather_info.getWeather()) {
            case "晴":
                holder.img.setImageResource(R.drawable.tianqi_qing);
                break;
            case "多云":
                holder.img.setImageResource(R.drawable.tianqi_duoyun);
                break;
            case "阴":
                holder.img.setImageResource(R.drawable.tianqi_yin);
                break;
            case "霾":
                holder.img.setImageResource(R.drawable.tianqi_mai);
                break;
            case "雨":
                holder.img.setImageResource(R.drawable.tianqi_xiaoyu_11);
                break;
            case "雪":
                holder.img.setImageResource(R.drawable.tianqi_xue);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return weather_infos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTime;
        private ImageView img;
        private TextView txtWendu;

        public ViewHolder(@NonNull View view) {
            super(view);
            txtTime = view.findViewById(R.id.txt_time);
            img = view.findViewById(R.id.img);
            txtWendu = view.findViewById(R.id.txt_wendu);
        }
    }
}
