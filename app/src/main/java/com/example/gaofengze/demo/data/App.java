package com.example.gaofengze.demo.data;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.model.VideoOptionModel;
import com.shuyu.gsyvideoplayer.utils.GSYVideoType;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import androidx.annotation.NonNull;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * Created by gfz on 2018/10/16
 */
public class App extends Application {
    public static App instance;
    public static Context mContext ;
    public static LinkedList<Activity> activityList = new LinkedList<>();
    public static Handler mHandlers = null;
    public static Thread mainThread = null;
    public static int mainThreadId = 0;
    public static int myPid;
    private static Long lastSend;

    public static Long getLastSend() {
        return lastSend;
    }

    public static void setLastSend(Long lastSend) {
        App.lastSend = lastSend;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        mHandlers = new Handler();
        mainThread = Thread.currentThread();
        mainThreadId = android.os.Process.myTid();
        myPid = android.os.Process.myPid();
        instance = this;
        lastSend = 0L;
        initPlayer();
        Thread.setDefaultUncaughtExceptionHandler(restartHandler);
    }

    private  Thread.UncaughtExceptionHandler restartHandler = new Thread.UncaughtExceptionHandler(){

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            e.printStackTrace();
            restartApp();
        }
    };
    public void restartApp(){
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    public static App getInstance(){
        return instance;
    }

    public static Context getmContext() {
        return mContext;
    }

    public static Handler getmHandlers() {
        return mHandlers;
    }

    static {

        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @NonNull
            @Override
            public RefreshFooter createRefreshFooter(@NonNull Context context, @NonNull RefreshLayout layout) {
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }

    private void initPlayer(){

        GSYVideoManager.instance().setOptionModelList(getPlayerOption());
//        CacheFactory.setCacheManager(ExoPlayerCacheManager.class);
        GSYVideoType.setShowType(GSYVideoType.SCREEN_MATCH_FULL);
    }

    public List<VideoOptionModel> getPlayerOption(){
        List<VideoOptionModel> list = new ArrayList<>();
        VideoOptionModel videoOptionModel = new VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "rtsp_transport", "tcp");
        list.add(videoOptionModel);
        videoOptionModel = new VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "rtsp_flags", "prefer_tcp");
        list.add(videoOptionModel);
        videoOptionModel = new VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "allowed_media_types", "video"); //根据媒体类型来配置
        list.add(videoOptionModel);
        videoOptionModel = new VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "buffer_size", 1316);
        list.add(videoOptionModel);
        videoOptionModel = new VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "infbuf", 1);  // 无限读
        list.add(videoOptionModel);
        videoOptionModel = new VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "analyzemaxduration", 100);
        list.add(videoOptionModel);
        videoOptionModel = new VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "probesize", 10240);
        list.add(videoOptionModel);
        videoOptionModel = new VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "flush_packets", 1);
        list.add(videoOptionModel);
        //  关闭播放器缓冲，这个必须关闭，否则会出现播放一段时间后，一直卡主，控制台打印 FFP_MSG_BUFFERING_START
        videoOptionModel = new VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "packet-buffering", 0);
        list.add(videoOptionModel);
        videoOptionModel = new VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "timeout", 20000);
        list.add(videoOptionModel);
        videoOptionModel = new VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "dns_cache_clear", 1);
        list.add(videoOptionModel);
        videoOptionModel = new VideoOptionModel( IjkMediaPlayer.OPT_CATEGORY_PLAYER, "framedrop", 1);
        list.add(videoOptionModel);
        return  list;
    }

}
