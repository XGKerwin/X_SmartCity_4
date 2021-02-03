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
import com.example.x_smartcity_4.bean.FpNewsList;
import com.example.x_smartcity_4.net.OkHttpLoImage;
import com.example.x_smartcity_4.net.OkHttpToImage;

import java.io.IOException;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/2  12:04
 */
public class FUwu_fupin_xinwen_adapter extends RecyclerView.Adapter<FUwu_fupin_xinwen_adapter.ViewHolder> {
    private List<FpNewsList> fpNewsLists;
    private FragmentActivity activity;


    public FUwu_fupin_xinwen_adapter(List<FpNewsList> fpNews, FragmentActivity activity) {
        this.fpNewsLists = fpNews;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_fupin_xinwen, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        FpNewsList fpNews = fpNewsLists.get(position);
        holder.itemTitle.setText(fpNews.getNewstitle());
        holder.itemContext.setText(fpNews.getNewscontent());
        holder.itemMsg.setText("查看人数：" + fpNews.getReadnum() + "\n发布时间：" + fpNews.getReporttime());

        new OkHttpToImage()
                .setUrl(fpNews.getNewspicture())
                .setOkHttpLoImage(new OkHttpLoImage() {
                    @Override
                    public void onResponse(Call call, Bitmap bitmap) {
                        holder.itemImage.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onFailure(Call call, IOException obj) {

                    }
                }).start();

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_fupin_xinwenxiangqin(fpNews));
            }
        });
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    @Override
    public int getItemCount() {
        return fpNewsLists.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView itemImage;
        private TextView itemTitle;
        private TextView itemContext;
        private TextView itemMsg;
        private LinearLayout layout;

        public ViewHolder(@NonNull View view) {
            super(view);
            layout = view.findViewById(R.id.layout);
            itemImage = view.findViewById(R.id.item_image);
            itemTitle = view.findViewById(R.id.item_title);
            itemContext = view.findViewById(R.id.item_context);
            itemMsg = view.findViewById(R.id.item_msg);
        }
    }
}
