package com.example.gaofengze.demo.base;


import android.app.Dialog;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;


import com.example.gaofengze.demo.R;
import com.example.gaofengze.demo.util.hepler.LoginHelper;
import com.example.gaofengze.demo.util.tools.DeBugUtil;
import com.example.gaofengze.demo.util.tools.ToastUtils;

import java.util.LinkedList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import static com.example.gaofengze.demo.data.App.activityList;


/**
 * Created by gfz on 2018/10/12
 */
public abstract class BaseActivity extends AppCompatActivity {
    private long clickTime = 0;

    private Fragment currentFragment;

    /**
     * a1.维护全局activity队列
     * 2.创建自己的fragment队列
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (activityList == null) {
            activityList = new LinkedList<>();
        }
        activityList.add(this);
    }

    public void logout(){
        DeBugUtil.error("登出");
        /*AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.myDialog);
        View view = LayoutInflater.from(this).inflate(R.layout.page_logout, null);
        builder.setCancelable(false);
        Dialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setContentView(view);
        TextView btn = view.findViewById(R.id.logout_sure);
        btn.setOnClickListener(v -> {
            dialog.dismiss();
            LoginHelper.logout();
        });*/
    }

    /**
     * 加载新的fragment
     *
     * @param targetFragment 需要加载的fragment
     */
    public void startFragment(Fragment targetFragment) {
        if (currentFragment == targetFragment) return;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (targetFragment.isAdded()) {
            transaction.show(targetFragment);
        } else {
            transaction.add(getFragmentId(), targetFragment, targetFragment.getClass().getName());
        }
        if (currentFragment != null) {
            transaction.hide(currentFragment);
        }
        currentFragment = targetFragment;
/*        if (!targetFragment.isAdded()) {
            transaction.replace(getFragmentId(), targetFragment, targetFragment.getClass().getName());
        }*/
        transaction.commitAllowingStateLoss();
    }

    /**
     * 再按一次退出程序
     */
    private void exit() {
        if (activityList.size() == 1 && (System.currentTimeMillis() - clickTime) > 2100) {
            ToastUtils.toast("再按一次退出");
            clickTime = System.currentTimeMillis();
        } else {
            ToastUtils.cancel();
            this.finish();
        }
    }

    /**
     * 监听回退健
     */
    @Override
    public void onBackPressed() {
        exit();
    }

    public int getFragmentId() {
        return R.id.fragment;
    }

    /**
     * 防止内存泄漏
     * 维护总的activity队列
     */
    @Override
    protected void onDestroy() {
        activityList.remove(this);
        super.onDestroy();
    }

}
