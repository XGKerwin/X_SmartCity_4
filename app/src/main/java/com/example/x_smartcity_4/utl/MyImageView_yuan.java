package com.example.x_smartcity_4.utl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/1/31  11:47
 */

@SuppressLint("AppCompatCustomView")
public class MyImageView_yuan extends ImageView {
    private int width = 0, height = 0;

    public MyImageView_yuan(Context context) {
        super(context);
    }

    public MyImageView_yuan(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyImageView_yuan(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = getWidth();
        height = getWidth();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        Path path = new Path();
        path.addOval(0,0,width,height,Path.Direction.CW);
        canvas.clipPath(path);
        super.onDraw(canvas);

    }
}