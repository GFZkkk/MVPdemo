package com.gaofengze.demo.ui.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.gaofengze.demo.util.tools.ToastUtils;
import com.gaofengze.demo.data.App;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getmHandlers().post(new Runnable() {
            @Override
            public void run() {
                selectActivity();
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 10000) {
            boolean isAllGranted = true;

            // 判断是否所有的权限都已经授予了
            for (int grant : grantResults) {
                if (grant != PackageManager.PERMISSION_GRANTED) {
                    isAllGranted = false;
                    break;
                }
            }

            if (isAllGranted) {
                selectActivity();
            } else {
                // 弹出对话框告诉用户需要权限的原因, 并引导用户去应用权限管理中手动打开权限按钮
                //openAppDetails();
                ToastUtils.toast("取消授权将导致APP不能使用,请主动授权");
            }
        }
    }
    private void selectActivity(){
        startActivity(new Intent(this, TestActivity.class));
        /*if(SPUtil.getBoolean("login",false)){
            startActivity(new Intent(this,MainActivity.class));
        }else{
            startActivity(new Intent(this,LoginActivity.class));
        }*/
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }
    @Override
    protected void onDestroy() {
        App.getmHandlers().removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
