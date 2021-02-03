package com.example.x_smartcity_4.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_4.R;
import com.example.x_smartcity_4.bean.FpSeekByUserid;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/2  20:44
 */
public class Fuwu_fupin_qiuzhu_adapter extends RecyclerView.Adapter<Fuwu_fupin_qiuzhu_adapter.ViewHolder> {

    private List<FpSeekByUserid> fpSeekByUserids;

    public Fuwu_fupin_qiuzhu_adapter(List<FpSeekByUserid> fpSeekByUserids) {
        this.fpSeekByUserids = fpSeekByUserids;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_fupin_qiuzhu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FpSeekByUserid userid = fpSeekByUserids.get(position);
        holder.txtBiaoti.setText(userid.getSeektitle());
        holder.txtMsg.setText(userid.getSeekcontent());
        holder.qiuzhuren.setText("求助人："+userid.getSeeker()+" 电话："+userid.getTel());
        holder.txt_time.setText("求助时间："+userid.getSeektime());
    }

    @Override
    public int getItemCount() {
        return fpSeekByUserids.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtBiaoti;
        private TextView txtMsg;
        private TextView qiuzhuren;
        private TextView txt_time;

        public ViewHolder(@NonNull View view) {
            super(view);
            txtBiaoti = view.findViewById(R.id.txt_biaoti);
            txtMsg = view.findViewById(R.id.txt_msg);
            qiuzhuren = view.findViewById(R.id.txt_qiuzhuren);
            txt_time = view.findViewById(R.id.txt_time);
        }
    }
}
