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
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_4.R;
import com.example.x_smartcity_4.bean.GetNewsByKeys;
import com.example.x_smartcity_4.net.OkHttpLoImage;
import com.example.x_smartcity_4.net.OkHttpToImage;

import java.io.IOException;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/2  0:37
 */
public class Zhuye_sousuo_adapter extends RecyclerView.Adapter<Zhuye_sousuo_adapter.ViewHolder> {

    private List<GetNewsByKeys> getNewsByKeys;

    public Zhuye_sousuo_adapter(List<GetNewsByKeys> getNewsByKeys) {
        this.getNewsByKeys = getNewsByKeys;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_sousuo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GetNewsByKeys byKeys = getNewsByKeys.get(position);
        holder.txt1.setText(byKeys.getAbstractX());
        holder.txtTitle.setText(byKeys.getTitle());
        new OkHttpToImage()
                .setUrl(byKeys.getPicture())
                .setOkHttpLoImage(new OkHttpLoImage() {
                    @Override
                    public void onResponse(Call call, Bitmap bitmap) {
                        holder.img.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onFailure(Call call, IOException obj) {

                    }
                }).start();
    }

    @Override
    public int getItemCount() {
        return getNewsByKeys.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout layout;
        private ImageView img;
        private TextView txtTitle;
        private TextView txt1;

        public ViewHolder(@NonNull View view) {
            super(view);
            layout = view.findViewById(R.id.layout);
            img = view.findViewById(R.id.img);
            txtTitle = view.findViewById(R.id.txt_title);
            txt1 = view.findViewById(R.id.txt1);
        }
    }
}
