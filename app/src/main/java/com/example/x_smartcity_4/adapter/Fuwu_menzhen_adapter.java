package com.example.x_smartcity_4.adapter;

import android.graphics.Bitmap;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_4.R;
import com.example.x_smartcity_4.bean.GetRankByHospitalId;
import com.example.x_smartcity_4.bean.HospitalList;
import com.example.x_smartcity_4.fragment.fuwu.Fragment_fuwu_menzhen1;
import com.example.x_smartcity_4.net.OkHttpLoImage;
import com.example.x_smartcity_4.net.OkHttpToImage;
import com.example.x_smartcity_4.utl.MyImageView_yuan;

import java.io.IOException;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/1/31  11:12
 */
public class Fuwu_menzhen_adapter extends RecyclerView.Adapter<Fuwu_menzhen_adapter.ViewHolder> {

    private List<HospitalList> hospitalLists;
    private List<GetRankByHospitalId> getRankByHospitalIds;
    private FragmentActivity activity;

    public Fuwu_menzhen_adapter(List<HospitalList> hospitalLists, List<GetRankByHospitalId> getRankByHospitalIds, FragmentActivity activity) {
        this.hospitalLists = hospitalLists;
        this.getRankByHospitalIds = getRankByHospitalIds;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_fuwu_menzhen, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HospitalList list = hospitalLists.get(position);
        GetRankByHospitalId getRankByHospitalId = getRankByHospitalIds.get(position);
        holder.title.setText(list.getHospitalName());
        Log.d("ffffffffff", "onBindViewHolder: "+list.getHospitalName());
        Log.d("ffffffffff", "onBindViewHolder: "+list.getRank());
        int s = getRankByHospitalId.getRank();
        holder.ratingbar.setRating(5-s);

        new OkHttpToImage()
                .setUrl(list.getPicture())
                .setOkHttpLoImage(new OkHttpLoImage() {
                    @Override
                    public void onResponse(Call call, Bitmap bitmap) {
                        holder.img.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onFailure(Call call, IOException obj) {

                    }
                }).start();

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_fuwu_menzhen1(list));
            }
        });
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    @Override
    public int getItemCount() {
        return hospitalLists.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout layout;
        private MyImageView_yuan img;
        private TextView title;
        private RatingBar ratingbar;

        public ViewHolder(@NonNull View view) {
            super(view);
            layout = view.findViewById(R.id.layout);
            img = view.findViewById(R.id.img);
            title = view.findViewById(R.id.title);
            ratingbar = view.findViewById(R.id.rating_bar);
        }
    }
}
