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
import com.example.x_smartcity_4.bean.GetNEWsList;
import com.example.x_smartcity_4.fragment.Fragment_huodongxiangqing;
import com.example.x_smartcity_4.net.OkHttpLoImage;
import com.example.x_smartcity_4.net.OkHttpToImage;

import java.io.IOException;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/2  0:31
 */
public class Zhuye_xinwen_adater extends RecyclerView.Adapter<Zhuye_xinwen_adater.ViewHolder> {
    private List<GetNEWsList> neWsLists;
    private FragmentActivity activity;
    private int s;

    public Zhuye_xinwen_adater(List<GetNEWsList> neWsLists, FragmentActivity activity, int s) {
        this.neWsLists = neWsLists;
        this.activity = activity;
        this.s = s;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_zhuti_show, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GetNEWsList list = neWsLists.get(position+s);
        holder.txt.setText(list.getTitle());
        holder.txt2.setText(list.getAbstractX());

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
                getFragment(new Fragment_huodongxiangqing(list.getNewsid()));
            }
        });
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    @Override
    public int getItemCount() {
        if (neWsLists.size()==0) return 0;
        return 5;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout layout;
        private ImageView img;
        private TextView txt;
        private TextView txt2;

        public ViewHolder(@NonNull View view) {
            super(view);
            layout = view.findViewById(R.id.layout);
            img = view.findViewById(R.id.img);
            txt = view.findViewById(R.id.txt);
            txt2 = view.findViewById(R.id.txt2);
        }
    }
}
