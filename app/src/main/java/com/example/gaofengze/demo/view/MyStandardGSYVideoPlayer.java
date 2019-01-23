package com.example.gaofengze.demo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;

import com.example.gaofengze.demo.tools.DensityUtil;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

/**
 * Created by gaofengze on 2019/1/16
 */
public class MyStandardGSYVideoPlayer extends StandardGSYVideoPlayer {
    Paint mPaint;
    int width;
    int height;
    public MyStandardGSYVideoPlayer(Context context, Boolean fullFlag) {
        super(context, fullFlag);
    }

    public MyStandardGSYVideoPlayer(Context context) {
        super(context);
    }

    public MyStandardGSYVideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path path = new Path();
        path.addRoundRect(0,0,width,height,DensityUtil.dip2px(50),DensityUtil.dip2px(50), Path.Direction.CCW);
        canvas.clipPath(path);
    }
}
