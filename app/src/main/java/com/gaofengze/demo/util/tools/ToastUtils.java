package com.gaofengze.demo.util.tools;

import android.view.Gravity;
import android.widget.Toast;

import com.gaofengze.demo.data.App;


/**
 * Created by gfz on 2018/10/10
 */
public class ToastUtils {
    private static Toast mToast;

    public static void toast(String text){
        cancel();
        mToast = Toast.makeText(App.getmContext(),text, Toast.LENGTH_SHORT);
        mToast.show();
    }

    public static void toastLong(String text){
        cancel();
        mToast = Toast.makeText(App.getmContext(),text, Toast.LENGTH_LONG);
        mToast.show();
    }

    public static void myToast(String text, float height){
        cancel();
        /*View view = LayoutInflater.from(App.getmContext()).inflate(R.layout.view_error, null);
        TextView textView = view.findViewById(R.id.tv_err);
        textView.setText(text);*/
        mToast = new Toast(App.getmContext());
        mToast.setGravity(Gravity.CENTER_HORIZONTAL,0 , (int) height);
        mToast.setDuration(Toast.LENGTH_SHORT);
//        mToast.setView(view);
        mToast.show();

    }

    public static void cancel(){
        if(mToast != null){
            mToast.cancel();
        }
    }
}
