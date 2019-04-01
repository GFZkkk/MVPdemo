package com.example.gaofengze.demo.diy.glide;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.security.MessageDigest;

import androidx.annotation.NonNull;

public class GlideRoundRectTransform extends BitmapTransformation {
    private static float radius ;
    private static float weight = 0f;

    public GlideRoundRectTransform(){
        this(10);
    }

    public GlideRoundRectTransform(int dp){
        radius = Resources.getSystem().getDisplayMetrics().density * dp;
    }

    public GlideRoundRectTransform(float weight){
        this.weight = weight;
    }

    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        if(weight != 0f){
            radius = Resources.getSystem().getDisplayMetrics().density * (Math.min(toTransform.getWidth(),toTransform.getHeight())) / weight / 2f;
        }
        return roundCrop(pool,toTransform);
    }

    private static Bitmap roundCrop(BitmapPool pool, Bitmap source) {
        if (source == null) return null;

        Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
/*        if (pool == null) {
            result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        }*/

        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
        canvas.drawRoundRect(rectF, radius, radius, paint);
        return result;
    }

    @Override
    public void updateDiskCacheKey(MessageDigest messageDigest) {

    }

}
