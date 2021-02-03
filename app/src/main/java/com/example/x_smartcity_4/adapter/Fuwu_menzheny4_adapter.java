package com.example.x_smartcity_4.adapter;

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
import com.example.x_smartcity_4.bean.HospitalList;
import com.example.x_smartcity_4.fragment.Fragment_fuwu_menzhen_y5;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/1  13:50
 */
public class Fuwu_menzheny4_adapter extends RecyclerView.Adapter<Fuwu_menzheny4_adapter.ViewHolder> {

    private List<DeptList> deptLists;
    private HospitalList list;
    private String name, tel, sfz;
    private FragmentActivity activity;


    public Fuwu_menzheny4_adapter(List<DeptList> deptLists, HospitalList list, String name, String tel, String sfz, FragmentActivity activity) {
        this.deptLists = deptLists;
        this.list = list;
        this.name = name;
        this.tel = tel;
        this.sfz = sfz;
        this.activity = activity;
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
                getFragment(new Fragment_fuwu_menzhen_y5(name,tel,sfz,list,deptList.getDeptId()));
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
