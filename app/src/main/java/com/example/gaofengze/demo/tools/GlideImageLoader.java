package com.example.gaofengze.demo.tools;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.gaofengze.demo.GlideApp.GlideApp;

public class GlideImageLoader  {
    public static void displayImage(ImageView imageView, String imageUrl) {
        Glide.with(imageView.getContext()).load(imageUrl)
//                .placeholder(R.drawable.img_two_bi_one)
//                .change_success(R.drawable.img_two_bi_one)
//                .crossFade(1000)
                .into(imageView);
    }
    public static void displayCircle(ImageView imageView, String imageUrl) {
        GlideApp.with(imageView.getContext())
                .load(imageUrl)
//                .crossFade(500)
//                .change_success(R.drawable.ic_avatar_default)
                .transform(new MultiTransformation<Bitmap>(new CenterCrop(),new GlideCircleTransform()))
                .into(imageView);
    }


    public static void displayRoundRect(ImageView imageView, String imageUrl){
        GlideApp.with(imageView.getContext())
                .load(imageUrl)
//                .crossFade(500)
//                .change_success(R.drawable.ic_avatar_default)
                .transform(new MultiTransformation<Bitmap>(new CenterCrop(),new GlideRoundRectTransform(8f)))
                .into(imageView);
    }
}
