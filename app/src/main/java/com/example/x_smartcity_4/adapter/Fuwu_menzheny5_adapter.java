package com.example.x_smartcity_4.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_4.R;
import com.example.x_smartcity_4.bean.DoctorList;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/1  14:32
 */
public class Fuwu_menzheny5_adapter extends RecyclerView.Adapter<Fuwu_menzheny5_adapter.ViewHolder> {
    private List<DoctorList> doctorLists;
    private String time;
    private Item_y5 item_y5;
    public interface Item_y5{

        void setonClick(DoctorList list);
    }

    public Item_y5 getItem_y5() {
        return item_y5;
    }

    public void setItem_y5(Item_y5 item_y5) {
        this.item_y5 = item_y5;
    }

    public Fuwu_menzheny5_adapter(List<DoctorList> doctors, String time) {
        this.doctorLists = doctors;
        this.time = time;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_fuwu_menzhen_4, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DoctorList list = doctorLists.get(position);
        holder.name.setText("姓名：" + list.getDoctorName());
        holder.msg.setText("简介：" + list.getDesc());
        holder.jianjie.setText("擅长：" + list.getTag());
        holder.txtTime.setText(time);

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item_y5.setonClick(list);
            }
        });
    }

    @Override
    public int getItemCount() {
        return doctorLists.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout layout;
        private TextView name;
        private TextView txtTime;
        private TextView btn;
        private TextView msg;
        private TextView jianjie;

        public ViewHolder(@NonNull View view) {
            super(view);
            layout = view.findViewById(R.id.layout);
            name = view.findViewById(R.id.name);
            txtTime = view.findViewById(R.id.txt_time);
            btn = view.findViewById(R.id.btn);
            msg = view.findViewById(R.id.msg);
            jianjie = view.findViewById(R.id.jianjie);
        }
    }
}
