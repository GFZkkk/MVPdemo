package com.example.gaofengze.demo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.gaofengze.demo.tools.DensityUtil;

/**
 * Created by gaofengze on 2019/1/16
 */
public class MyRLView extends TextView {

    int width;
    int height;

    public MyRLView(Context context) {
        super(context);
    }

    public MyRLView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRLView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyRLView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
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
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        Paint p = new Paint();
        p.setColor(Color.parseColor("#FF0000"));
        canvas.drawRoundRect(0,0,width,height,DensityUtil.dip2px(50),DensityUtil.dip2px(50),p);
        Path path = new Path();
        path.addRoundRect(0,0,width,height,DensityUtil.dip2px(50),DensityUtil.dip2px(50), Path.Direction.CCW);
        canvas.clipPath(path);

    }
}
