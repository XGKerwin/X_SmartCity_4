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
import com.example.x_smartcity_4.bean.FpCaseList;
import com.example.x_smartcity_4.bean.GetUserInfo;
import com.example.x_smartcity_4.fragment.fuwu.Fragment_fuwu_fupin_anli;
import com.example.x_smartcity_4.net.OKHttpTo;
import com.example.x_smartcity_4.net.OkHttpLo;
import com.example.x_smartcity_4.net.OkHttpLoImage;
import com.example.x_smartcity_4.net.OkHttpToImage;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/2  13:59
 */
public class Fuwu_fupin_anli_adapter extends RecyclerView.Adapter<Fuwu_fupin_anli_adapter.ViewHolder> {
    private List<FpCaseList> fpCaseLists;
    private String user;
    private FragmentActivity activity;
    public Fuwu_fupin_anli_adapter(List<FpCaseList> examples, String user, FragmentActivity activity) {
        this.user = user;
        this.fpCaseLists = examples;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_fupin_anli, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FpCaseList list = fpCaseLists.get(position);

        holder.itemTitle.setText(list.getCasetitle());

        new OKHttpTo()
                .setUrl("getUserInfo")
                .setJSONObject("userid",list.getUserid())
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        GetUserInfo userInfo = new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").optJSONObject(0).toString()
                                , GetUserInfo.class);
                        holder.itemMsg.setText("参与人：" + userInfo.getName()  +
                                "   查看：" + list.getReadnum() + " 点赞：" + list.getThumbup()+"\n"
                                +"发布日期：" + list.getReporttime() );
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();

        holder.itemContext.setText(list.getCaseContent());

        new OkHttpToImage()
                .setUrl(list.getCasepicture())
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
                getFragment(new Fragment_fuwu_fupin_anli_2(list));
            }
        });

    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    @Override
    public int getItemCount() {
        return fpCaseLists.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout layout;
        private ImageView itemImage;
        private TextView itemTitle;
        private TextView itemContext;
        private TextView itemMsg;

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
