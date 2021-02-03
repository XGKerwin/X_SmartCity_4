package com.example.x_smartcity_4.adapter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_4.R;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/3  1:28
 */
public class Fuwu_fupin_wdal_adapter extends RecyclerView.Adapter<Fuwu_fupin_wdal_adapter.ViewHolder> {

    private List<Bitmap> bitmaps;
    public ItemOnclick itemOnclick;

    public ItemOnclick getItemOnclick() {
        return itemOnclick;
    }

    public void setItemOnclick(ItemOnclick itemOnclick) {
        this.itemOnclick = itemOnclick;
    }

    public interface ItemOnclick{

        void setOnclick(int position, String s);
    }

    public Fuwu_fupin_wdal_adapter(List<Bitmap> bitmaps) {
        this.bitmaps = bitmaps;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_fupin_wdal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemOnclick.setOnclick(position,"");
            }
        });
    }

    @Override
    public int getItemCount() {
        return bitmaps.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout itemLayout;

        public ViewHolder(@NonNull View view) {
            super(view);
            itemLayout = view.findViewById(R.id.item_layout);
        }
    }
}
