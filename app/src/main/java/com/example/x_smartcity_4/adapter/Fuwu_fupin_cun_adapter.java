package com.example.x_smartcity_4.adapter;

import android.graphics.Bitmap;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_4.R;
import com.example.x_smartcity_4.bean.FpVillageList;
import com.example.x_smartcity_4.fragment.fuwu.Fragment_fuwu_fupin;
import com.example.x_smartcity_4.net.OkHttpLoImage;
import com.example.x_smartcity_4.net.OkHttpToImage;

import java.io.IOException;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/2  16:16
 */
public class Fuwu_fupin_cun_adapter extends RecyclerView.Adapter<Fuwu_fupin_cun_adapter.ViewHolder> {
    private List<FpVillageList> lists;
    private int pos;
    private FragmentActivity activity;

    public Fuwu_fupin_cun_adapter(List<FpVillageList> fpVillageLists, int i, FragmentActivity activity) {
        this.lists = fpVillageLists;
        this.pos = i;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_fupin_cun, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FpVillageList list = lists.get(position);
        holder.txtTitle.setText(list.getVillname());
        holder.txtMsg.setText(list.getVilldesc());
        holder.txtDizhi.setText("地址：" + list.getVilladdress());

        new OkHttpToImage()
                .setUrl(list.getVillpicture())
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
                getFragment(new Fragment_fuwu_fupin_cun_2(list));
            }
        });
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    @Override
    public int getItemCount() {
        return pos;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout layout;
        private ImageView img;
        private TextView txtTitle;
        private TextView txtMsg;
        private TextView txtDizhi;

        public ViewHolder(@NonNull View view) {
            super(view);
            layout = view.findViewById(R.id.layout);
            img = view.findViewById(R.id.img);
            txtTitle = view.findViewById(R.id.txt_title);
            txtMsg = view.findViewById(R.id.txt_msg);
            txtDizhi = view.findViewById(R.id.txt_dizhi);
        }
    }
}
