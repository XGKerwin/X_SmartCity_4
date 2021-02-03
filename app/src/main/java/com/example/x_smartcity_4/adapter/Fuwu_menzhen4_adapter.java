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
 * date   : 2021/2/1  3:14
 */
public class Fuwu_menzhen4_adapter extends RecyclerView.Adapter<Fuwu_menzhen4_adapter.ViewHolder> {
    private List<DoctorList> doctorLists;
    private String time;
    public Item_btn item_btn;

    public Item_btn getItem_btn() {
        return item_btn;
    }

    public void setItem_btn(Item_btn item_btn) {
        this.item_btn = item_btn;
    }

    public interface Item_btn{

        void setonClick(DoctorList list);
    }


    public Fuwu_menzhen4_adapter(List<DoctorList> doctors, String time) {
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
                item_btn.setonClick(list);
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
