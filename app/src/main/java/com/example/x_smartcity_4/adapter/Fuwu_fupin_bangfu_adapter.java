package com.example.x_smartcity_4.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_4.R;
import com.example.x_smartcity_4.bean.FpApplyListByUserid;
import com.example.x_smartcity_4.bean.FpVillageList;
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
 * date   : 2021/2/2  23:20
 */
public class Fuwu_fupin_bangfu_adapter extends RecyclerView.Adapter<Fuwu_fupin_bangfu_adapter.ViewHolder> {
    private List<FpApplyListByUserid> fpApplyListByUserids;
    private List<FpVillageList> list;

    public Fuwu_fupin_bangfu_adapter(List<FpApplyListByUserid> fpApplyListByUserids) {
        this.fpApplyListByUserids = fpApplyListByUserids;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_fupin_bangfu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FpApplyListByUserid userid = fpApplyListByUserids.get(position);
        if (userid.getApplystate().equals("1")){
            holder.txtMsg.setText("状态：已通过");
        }else {
            holder.txtMsg.setText("状态：审核中");
        }

        holder.txtQiuzhuren.setText("扶贫简介："+userid.getApplydesc());
        holder.txtTime.setText("开始时间："+userid.getStarttime());

        if (list ==null) {
            list = new ArrayList<>();
        }else {
            list.clear();
        }
        new OKHttpTo()
                .setUrl("fpVillageList")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        list.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<FpVillageList>>(){}.getType()));

                        for (int i=0;i<list.size();i++){
                            FpVillageList fpvill = list.get(i);
                            if (fpvill.getVillid() == userid.getVillid()){
                                holder.txtBiaoti.setText(fpvill.getVillname());
                            }
                        }
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    @Override
    public int getItemCount() {
        return fpApplyListByUserids.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtBiaoti;
        private TextView txtMsg;
        private TextView txtQiuzhuren;
        private TextView txtTime;

        public ViewHolder(@NonNull View view) {
            super(view);
            txtBiaoti = view.findViewById(R.id.txt_biaoti);
            txtMsg = view.findViewById(R.id.txt_msg);
            txtQiuzhuren = view.findViewById(R.id.txt_qiuzhuren);
            txtTime = view.findViewById(R.id.txt_time);
        }
    }
}
