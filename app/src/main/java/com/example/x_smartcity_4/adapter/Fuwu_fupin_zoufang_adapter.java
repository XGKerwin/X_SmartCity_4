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
import com.example.x_smartcity_4.bean.FpVillageList;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/3  0:02
 */
public class Fuwu_fupin_zoufang_adapter extends RecyclerView.Adapter<Fuwu_fupin_zoufang_adapter.ViewHolder> {

    private List<FpVillageList> fpVillageLists;
    private FragmentActivity activity;


    public Fuwu_fupin_zoufang_adapter(List<FpVillageList> fpVillageLists, FragmentActivity activity) {
        this.fpVillageLists = fpVillageLists;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_fupin_zoufang, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FpVillageList fpVillageList = fpVillageLists.get(position);
        holder.txt.setText(fpVillageList.getVillname());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_fupin_zoufang1(fpVillageList.getVillid(),fpVillageList.getVillname()));
            }
        });
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    @Override
    public int getItemCount() {
        return fpVillageLists.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txt;
        private LinearLayout layout;

        public ViewHolder(@NonNull View view) {
            super(view);
            txt = view.findViewById(R.id.txt);
            layout = view.findViewById(R.id.layout);
        }
    }
}
