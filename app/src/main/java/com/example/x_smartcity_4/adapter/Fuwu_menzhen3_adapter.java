package com.example.x_smartcity_4.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_4.R;
import com.example.x_smartcity_4.bean.DeptList;
import com.example.x_smartcity_4.bean.GetUserInfo;
import com.example.x_smartcity_4.bean.HospitalList;
import com.example.x_smartcity_4.fragment.fuwu.Fragment_fuwu_menzhen_4;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/1  2:20
 */
public class Fuwu_menzhen3_adapter extends RecyclerView.Adapter<Fuwu_menzhen3_adapter.ViewHolder> {
    private List<DeptList> deptLists;
    private FragmentActivity activity;
    private HospitalList list;
    private GetUserInfo info;

    public Fuwu_menzhen3_adapter(List<DeptList> deptLists, FragmentActivity activity, HospitalList list, GetUserInfo getUserInfo) {
        this.list = list;
        this.deptLists = deptLists;
        this.activity = activity;
        this.info = getUserInfo;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_fuwu_menzhen_3, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DeptList deptList = deptLists.get(position);
        holder.txtKeshi.setText("科室：" + deptList.getDeptName());
        holder.msg.setText("详情：" + deptList.getDesc());
        holder.tag.setText("主要：" + deptList.getTag());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ffffffaaa", "onClick: "+info.getPhone());
                getFragment(new Fragment_fuwu_menzhen_4(list,deptList,info.getId(),info.getName(),info.getPhone(),info));
            }
        });
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    @Override
    public int getItemCount() {
        return deptLists.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout layout;
        private TextView txtKeshi;
        private TextView msg;
        private TextView tag;

        public ViewHolder(@NonNull View view) {
            super(view);
            layout = view.findViewById(R.id.layout);
            txtKeshi = view.findViewById(R.id.txt_keshi);
            msg = view.findViewById(R.id.msg);
            tag = view.findViewById(R.id.tag);
        }
    }
}
