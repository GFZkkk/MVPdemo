package com.example.gaofengze.demo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.example.gaofengze.demo.tools.DensityUtil;

/**
 * Created by gaofengze on 2018/12/26
 */
@SuppressWarnings("ALL")
public class MyView extends View {
    Paint paint;
    public MyView(Context context) {
        super(context);
        init();
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private float width;
    private float height;
    int top;
    int left;
    int right;
    int bottom;
    float key;
    int startColor;
    int endColor;

    private void init(){
        paint = new Paint();
        //阴影权重
        top = DensityUtil.dip2px(2);
        left = DensityUtil.dip2px(4);
        right = left;
        bottom = DensityUtil.dip2px(8);
        //权重系数
        key = 100f;
        //阴影颜色
        startColor = Color.parseColor("#3F24385A");
        endColor = Color.parseColor("#0024385A");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        height = getHeight();
        width = getWidth() ;
        Shader shaderTop = new LinearGradient(0,top,0,0, startColor, endColor, Shader.TileMode.CLAMP);
        Shader shaderLeft = new LinearGradient(left,0,0,0, startColor, endColor, Shader.TileMode.CLAMP);
        Shader shaderRight = new LinearGradient(width-right,0,width,0, startColor, endColor, Shader.TileMode.CLAMP);
        Shader shaderBottom = new LinearGradient(0,height-bottom,0,height, startColor,endColor, Shader.TileMode.CLAMP);

        paint.setShader(shaderTop);
        canvas.drawRect(left,0,width-right,top,paint);


        paint.setShader(shaderLeft);
        canvas.drawRect(0, top, left,height-bottom, paint);


        paint.setShader(shaderRight);
        canvas.drawRect(width-right, top, width,height-bottom, paint);


        paint.setShader(shaderBottom);
        canvas.drawRect(left, height-bottom, width-right, height, paint);

        /*Shader shaderLeftTop = new RadialGradient(left, top,(left+top)/2, startColor,endColor, Shader.TileMode.CLAMP);
        paint.setShader(shaderLeftTop);
        canvas.drawRect(0,0,left,top, paint);

        Shader shaderRightTop = new RadialGradient(width-right, top,(right+top)/2, startColor,endColor, Shader.TileMode.CLAMP);
        paint.setShader(shaderRightTop);
        canvas.drawRect(width-right, 0, width, top, paint);

        Shader shaderLeftBottom = new RadialGradient(left,height-bottom,(left+bottom)/2, startColor,endColor, Shader.TileMode.CLAMP);
        paint.setShader(shaderLeftBottom);
        canvas.drawRect(0, height-bottom, left, height, paint);

        Shader shaderRightBottom = new RadialGradient(width-right,height-bottom,(bottom+right)/2, startColor,endColor, Shader.TileMode.CLAMP);
        paint.setShader(shaderRightBottom);
        canvas.drawRect(width-right, height-bottom, width, height, paint);*/

    }

}
