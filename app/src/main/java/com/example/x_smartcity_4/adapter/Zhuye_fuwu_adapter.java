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
import com.example.x_smartcity_4.bean.Service_info;
import com.example.x_smartcity_4.net.OkHttpLoImage;
import com.example.x_smartcity_4.net.OkHttpToImage;

import java.io.IOException;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/2  0:14
 */
public class Zhuye_fuwu_adapter extends RecyclerView.Adapter<Zhuye_fuwu_adapter.ViewHolder> {
    private List<Service_info> service_infos;
    public Item_zhuyeOnclick itemOnclick;

    public void setItemOnclick(Item_zhuyeOnclick itemOnclick) {
        this.itemOnclick = itemOnclick;
    }

    public interface Item_zhuyeOnclick{
        void onClick(String serviceName);
    }

    public Zhuye_fuwu_adapter(List<Service_info> service_infos) {
        this.service_infos = service_infos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_zhuye_fuwu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position==9){
            holder.img.setImageResource(R.drawable.fuwu_gengduo);
            holder.txt.setText("更多服务");

        }else {
            Service_info info = service_infos.get(position);
            holder.txt.setText(info.getServiceName());
            new OkHttpToImage()
                    .setUrl(info.getIcon())
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
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemOnclick.onClick(holder.txt.getText().toString());
            }
        });


    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout layout;
        private ImageView img;
        private TextView txt;

        public ViewHolder(@NonNull View view) {
            super(view);
            layout = view.findViewById(R.id.layout);
            img = view.findViewById(R.id.img);
            txt = view.findViewById(R.id.txt);
        }
    }
}
