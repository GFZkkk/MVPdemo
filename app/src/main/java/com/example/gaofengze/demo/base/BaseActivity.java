package com.example.gaofengze.demo.base;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.example.gaofengze.demo.tools.LoadingUtil;
import com.example.gaofengze.demo.tools.ToastUtils;

import java.util.LinkedList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import static com.example.gaofengze.demo.data.App.activityList;


/**
 * Created by gfz on 2018/10/12
 */
public abstract class BaseActivity extends AppCompatActivity {
    private long clickTime = 0;

    private Fragment currentFragment;
    private List<Fragment> fragmentList;

    /**
     * a1.维护全局activity队列
     * 2.创建自己的fragment队列
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(activityList == null){
            activityList = new LinkedList<>();
        }
        activityList.add(this);
        fragmentList = new LinkedList<>();

    }

    /**
     * 加载新的fragment
     * @param targetFragment 需要加载的fragment
     */
    public void startFragment(Fragment targetFragment){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        if (currentFragment != null) {
            transaction.hide(currentFragment);
        }
        transaction.add(getFragmentId(), targetFragment, targetFragment.getClass().getName());
        currentFragment = targetFragment;
        transaction.commit();
        fragmentList.add(targetFragment);
    }

    /**
     * 适用于activity不处理主视图的情况
     * a1.如果加载了fragment先回退fragment
     * 2.如果是最后一个fragment了就退出activity
     */
    public void goBack(){
        if(fragmentList.size()>1){
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            fragmentList.remove(currentFragment);
            transaction.remove(currentFragment);
            currentFragment = fragmentList.get(fragmentList.size()-1);
            transaction.show(currentFragment);
            transaction.commit();
        }else{
            exit();
        }
    }

    /**
     * 只保留第一个fragment
     */
    public void toIndex(){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        while(fragmentList.size()>1){
            fragmentList.remove(currentFragment);
            transaction.remove(currentFragment);
            currentFragment = fragmentList.get(fragmentList.size()-1);
        }
        transaction.show(currentFragment);
        transaction.commit();
    }

    /**
     * 防止内存泄漏
     * 维护总的activity队列
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityList.remove(this);
    }

    /**
     * 监听回退健
     */
    @Override
    public void onBackPressed() {
        goBack();
    }

    /**
     * 再按一次退出程序
     */
    private void exit(){
        if (activityList.size() == 1 && (System.currentTimeMillis() - clickTime) > 2100) {
            ToastUtils.toast("再按一次退出");
            clickTime = System.currentTimeMillis();
        } else {
            ToastUtils.cancel();
            LoadingUtil.loadFinish();
            this.finish();
        }
    }

    public int getFragmentId(){
        return 1;
    }
}
