package com.example.x_smartcity_4.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_4.R;
import com.example.x_smartcity_4.bean.GetUserInfo;
import com.example.x_smartcity_4.bean.HospitalList;
import com.example.x_smartcity_4.fragment.fuwu.Fragment_fuwu_menzhen_3;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/1  1:18
 */
public class FUwu_menzhen2_adapter extends RecyclerView.Adapter<FUwu_menzhen2_adapter.ViewHolder> {
    private List<GetUserInfo> getUserInfos;
    private FragmentActivity activity;
    private HospitalList list;

    public FUwu_menzhen2_adapter(List<GetUserInfo> getUserInfos, FragmentActivity activity, HospitalList list) {
        this.getUserInfos = getUserInfos;
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_fuwu_menzhen_2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GetUserInfo getUserInfo = getUserInfos.get(position);
        holder.txtName.setText("姓名："+getUserInfo.getName());
        holder.txtSfz.setText("身份证号："+getUserInfo.getId());
        holder.txtTel.setText("手机号："+getUserInfo.getPhone());

        holder.btnYou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_fuwu_menzhen_3(list,getUserInfo));
            }
        });
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    @Override
    public int getItemCount() {
        return getUserInfos.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName;
        private TextView txtSfz;
        private TextView txtTel;
        private TextView txtBl;
        private ImageView btnYou;

        public ViewHolder(@NonNull View view) {
            super(view);
            txtName = view.findViewById(R.id.txt_name);
            txtSfz = view.findViewById(R.id.txt_sfz);
            txtTel = view.findViewById(R.id.txt_tel);
            txtBl = view.findViewById(R.id.txt_bl);
            btnYou = view.findViewById(R.id.btn_you);
        }
    }
}
