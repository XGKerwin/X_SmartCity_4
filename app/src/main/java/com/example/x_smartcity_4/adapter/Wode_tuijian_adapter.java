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
import com.example.x_smartcity_4.bean.GetNEWsList;
import com.example.x_smartcity_4.net.OkHttpLoImage;
import com.example.x_smartcity_4.net.OkHttpToImage;

import java.io.IOException;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/2  0:25
 */
public class Wode_tuijian_adapter extends RecyclerView.Adapter<Wode_tuijian_adapter.ViewHolder> {

    private List<GetNEWsList> neWsLists;
    private String id;
    public Item_tuijian item_tuijian;
    private int s;

    public Item_tuijian getTuijian() {
        return item_tuijian;
    }

    public void setTuijian(Item_tuijian tuijian) {
        this.item_tuijian = tuijian;
    }

    public interface Item_tuijian{
        void setOnclick(String s);
    }


    public Wode_tuijian_adapter(List<GetNEWsList> neWsLists, String id, int s) {
        this.neWsLists = neWsLists;
        this.id = id;
        this.s = s;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_tu_3, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GetNEWsList list = neWsLists.get(position+s);
        holder.title.setText(list.getTitle());
        holder.msg.setText(list.getAbstractX());
        new OkHttpToImage()
                .setUrl(list.getPicture())
                .setOkHttpLoImage(new OkHttpLoImage() {
                    @Override
                    public void onResponse(Call call, Bitmap bitmap) {
                        holder.image.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onFailure(Call call, IOException obj) {

                    }
                }).start();



        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item_tuijian.setOnclick(list.getNewsid());
            }
        });

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout layout;
        private ImageView image;
        private TextView title;
        private TextView msg;

        public ViewHolder(@NonNull View view) {
            super(view);
            layout = view.findViewById(R.id.layout);
            image = view.findViewById(R.id.image);
            title = view.findViewById(R.id.title);
            msg = view.findViewById(R.id.msg);
        }
    }
}
