package com.speed.vip.alarm_process;

import android.app.Activity;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.speed.vip.MainActivity;
import com.speed.vip.R;
import com.speed.vip.system.ProcSystemSetting;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AlarmController{
    private Activity main;
    private MediaPlayer mediaPlayer;
    private ArrayList<Integer> src = new ArrayList<>();
    private int corePoolSize = 1; // 核心线程池大小
    private int maximumPoolSize = 1; // 最大线程池大小
    private long keepAliveTime = 2000; // 线程空闲时间
    private TimeUnit unit = TimeUnit.MILLISECONDS; // 时间单位
    private BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(); // 任务队列
    private int countOverSpeedTimes = 1;
    private int lastSec = -1;

    private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            corePoolSize,
            maximumPoolSize,
            keepAliveTime,
            unit,
            workQueue
    );

    public AlarmController(){
         main= (Activity) MainActivity.mainContext;
    }

    private Runnable alarm = new Runnable() {
        @Override
        public void run() {
            if(MainActivity.isWelcomeFlag){     //處理歡迎詞
                playVoice(R.raw.welcome);
                playVoice(R.raw.gps_connect);

                main.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ConstraintLayout home = main.findViewById(R.id.home);
                        home.setVisibility(View.INVISIBLE);
                        ConstraintLayout speedometer = main.findViewById(R.id.speedometer);
                        speedometer.setVisibility(View.VISIBLE);
                    }
                });
                MainActivity.isWelcomeFlag = false;
            } else if (!MainActivity.isWelcomeFlag && MainActivity.isOpenBg) {//從背景回到前台
                main.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MainActivity.isOpenBg=false;
                        ConstraintLayout home = main.findViewById(R.id.home);
                        home.setVisibility(View.INVISIBLE);
                        ConstraintLayout speedometer = main.findViewById(R.id.speedometer);
                        speedometer.setVisibility(View.VISIBLE);
                    }
                });
            } else{
                removeBengoFlagIs0();   //刪除bengo_flag為0的資料
                for(int i : MainActivity.alarmPointIndex){
                    boolean goAlarmFlag = new ProcSystemSetting().procCarMode(MainActivity.systemSetting.getCarMode(), MainActivity.pointDataArrayList.get(i).getVoiceSrc());
                    switch(MainActivity.systemSetting.getDriverMode()){
                        case "照相系統模式":
                            if (!proc_same_ep(i) && MainActivity.pointDataArrayList.get(i).getMode() == 0 && goAlarmFlag) {
                                if (MainActivity.pointDataArrayList.get(i).getBengo_flag() == 1 && MainActivity.pointDataArrayList.get(i).getVoiceSrc() != -1) {
                                    MainActivity.pointDataArrayList.get(i).setBengo_flag(2);
                                    display();  //顯示icon
                                    playVoice(MainActivity.pointDataArrayList.get(i).getVoiceSrc());    //設定主要語句的音檔
                                    playVoice(R.raw.speedlimit);    //設定提醒的音檔(請依速限行駛)
                                }else if(MainActivity.pointDataArrayList.get(i).getBengo_flag() == 2){
                                    display();  //顯示icon
                                    double distTemp = MainActivity.pointDataArrayList.get(i).getYourPointToEndPointDist();
                                    if (distTemp < ReciprocalOverRang()) {
                                        MainActivity.pointDataArrayList.get(i).setBengo_flag(4);             //將bengo_flag設定為4
                                        display();  //刷新/清除 icon
                                        playVoice(R.raw.camera);    //設定通過的音檔
                                    }
                                }
                            }
                            break;
                        case "照相系統限速模式":
                            if (!proc_same_ep(i) && MainActivity.pointDataArrayList.get(i).getMode() == 0 && goAlarmFlag) {
                                if (MainActivity.pointDataArrayList.get(i).getBengo_flag() == 1 && MainActivity.pointDataArrayList.get(i).getVoiceSrc() != -1) {
                                    MainActivity.pointDataArrayList.get(i).setBengo_flag(2);
                                    display();  //顯示icon
                                    playVoice(MainActivity.pointDataArrayList.get(i).getVoiceSrc());    //設定主要語句的音檔
                                    playVoice(getSpeedlimitVoiceSrc(MainActivity.pointDataArrayList.get(i).getSpeedLimit()));   //設定速限音檔
                                    playVoice(R.raw.speedlimit);    //設定提醒的音檔(請依速限行駛)

                                    if(MainActivity.myLocation.getSpeed() * 3.6 > MainActivity.pointDataArrayList.get(i).getSpeedLimit()){
                                        if(MainActivity.systemSetting.getOverSpeedlimitVoice() && !MainActivity.overSpeedlimitVoiceFlag){
                                            playVoice(R.raw.overspeed);
                                            MainActivity.overSpeedlimitVoiceFlag = true;
                                        }
                                    }
                                }else if(MainActivity.pointDataArrayList.get(i).getBengo_flag() == 2){
                                    display();  //顯示icon
                                    double distTemp = MainActivity.pointDataArrayList.get(i).getYourPointToEndPointDist();
                                    if(distTemp >= ReciprocalOverRang()){
                                        if(MainActivity.myLocation.getSpeed() * 3.6 > MainActivity.pointDataArrayList.get(i).getSpeedLimit()){
                                            double distBuffer = MainActivity.myLocation.getSpeed() * 3.6 - MainActivity.pointDataArrayList.get(i).getSpeedLimit();
                                            if(distBuffer > 0){
                                                distBuffer += 80;   //結束點前80+buffer停止連續響聲
                                                if(distTemp > distBuffer){
                                                    playVoice(R.raw.ding);
                                                }
                                            }
                                        }
                                    }else if (distTemp < ReciprocalOverRang()) {
                                        MainActivity.pointDataArrayList.get(i).setBengo_flag(4);             //將bengo_flag設定為4
                                        display();  //刷新/清除 icon
                                        MainActivity.overSpeedlimitVoiceFlag = false;
                                        playVoice(R.raw.camera);    //設定通過的音檔
                                    }
                                }
                            }
                            break;
                        case "安全駕駛限速模式":
                            if (!proc_same_ep(i) && goAlarmFlag) {
                                if(MainActivity.pointDataArrayList.get(i).getMode() == 0){  //照相類別
                                    if (MainActivity.pointDataArrayList.get(i).getBengo_flag() == 1 && MainActivity.pointDataArrayList.get(i).getVoiceSrc() != -1) {
                                        MainActivity.pointDataArrayList.get(i).setBengo_flag(2);
                                        display();  //顯示icon
                                        playVoice(MainActivity.pointDataArrayList.get(i).getVoiceSrc());    //設定主要語句的音檔
                                        playVoice(getSpeedlimitVoiceSrc(MainActivity.pointDataArrayList.get(i).getSpeedLimit()));//設定速限音檔
                                        playVoice(R.raw.speedlimit);    //設定提醒的音檔(請依速限行駛)

                                        if(MainActivity.myLocation.getSpeed() * 3.6 > MainActivity.pointDataArrayList.get(i).getSpeedLimit()){
                                            if(MainActivity.systemSetting.getOverSpeedlimitVoice() && !MainActivity.overSpeedlimitVoiceFlag){
                                                playVoice(R.raw.overspeed);
                                                MainActivity.overSpeedlimitVoiceFlag = true;
                                            }
                                        }
                                    }else if(MainActivity.pointDataArrayList.get(i).getBengo_flag() == 2){
                                        display();  //顯示icon
                                        double distTemp = MainActivity.pointDataArrayList.get(i).getYourPointToEndPointDist();
                                        if(distTemp >= ReciprocalOverRang()){
                                            if(MainActivity.myLocation.getSpeed() * 3.6 > MainActivity.pointDataArrayList.get(i).getSpeedLimit()){
                                                double distBuffer = MainActivity.myLocation.getSpeed() * 3.6 - MainActivity.pointDataArrayList.get(i).getSpeedLimit();
                                                if(distBuffer > 0){
                                                    distBuffer += 80;
                                                    if(distTemp > distBuffer){
                                                        playVoice(R.raw.ding);
                                                    }
                                                }
                                            }
                                        }else if (distTemp < ReciprocalOverRang()) {
                                            MainActivity.pointDataArrayList.get(i).setBengo_flag(4);             //將bengo_flag設定為4
                                            display();  //刷新/清除 icon
                                            MainActivity.overSpeedlimitVoiceFlag = false;
                                            playVoice(R.raw.camera);    //設定通過的音檔
                                        }
                                    }
                                }else if(MainActivity.pointDataArrayList.get(i).getMode() == 1){    //安全類別
                                    if(MainActivity.pointDataArrayList.get(i).getVoiceSrc() != -1 && MainActivity.pointDataArrayList.get(i).getBengo_flag() == 1){
                                        MainActivity.pointDataArrayList.get(i).setBengo_flag(2);            //將bengo_flag設定為2
                                        playVoice(MainActivity.pointDataArrayList.get(i).getVoiceSrc());
                                        playVoice(R.raw.slow);
                                    }
                                }
                            }
                            break;
                    }
                }
            }
        }

        private void removeBengoFlagIs0(){
            for(int i=0;i<MainActivity.alarmPointIndex.size(); i++){
                int index = MainActivity.alarmPointIndex.get(i);
                if(MainActivity.pointDataArrayList.get(index).getBengo_flag() == 0){
                    MainActivity.alarmPointIndex.remove(i);
                }
            }
        }

        private void playVoice(int src){
            mediaPlayer = MediaPlayer.create(MainActivity.mainContext, src);    //設定提醒的音檔(請依速限行駛)
            mediaPlayer.start();    //開始撥放
            do{}while(mediaPlayer.isPlaying());
            mediaPlayer.release();  //釋放資源
            mediaPlayer = null;
        }

        private int getSpeedlimitVoiceSrc(int speedlimit){
            int src = -1;
            switch(speedlimit){
                case 10:
                    src = R.raw.speed_10;
                    break;
                case 20:
                    src = R.raw.speed_20;
                    break;
                case 30:
                    src = R.raw.speed_30;
                    break;
                case 40:
                    src = R.raw.speed_40;
                    break;
                case 50:
                    src = R.raw.speed_50;
                    break;
                case 60:
                    src = R.raw.speed_60;
                    break;
                case 70:
                    src = R.raw.speed_70;
                    break;
                case 80:
                    src = R.raw.speed_80;
                    break;
                case 90:
                    src = R.raw.speed_90;
                    break;
                case 100:
                    src = R.raw.speed_100;
                    break;
                case 110:
                    src = R.raw.speed_110;
                    break;
                case 120:
                    src = R.raw.speed_120;
                    break;
                case 130:
                    src = R.raw.speed_130;
                    break;
                case 140:
                    src = R.raw.speed_140;
                    break;
                case 150:
                    src = R.raw.speed_150;
                    break;
            }
            return src;
        }

        private boolean proc_same_ep(int j) {   //判斷相同點
            for(int i : MainActivity.alarmPointIndex){
                if(i != j &&
                        MainActivity.pointDataArrayList.get(i).getBengo_flag() == 4 &&
                        MainActivity.pointDataArrayList.get(i).getEndPointLatitude() == MainActivity.pointDataArrayList.get(j).getEndPointLatitude() &&
                        MainActivity.pointDataArrayList.get(i).getEndPointLongitude() == MainActivity.pointDataArrayList.get(j).getEndPointLongitude() &&
                        MainActivity.pointDataArrayList.get(i).getEndPointDirection() == MainActivity.pointDataArrayList.get(j).getEndPointDirection()){
                    MainActivity.pointDataArrayList.get(j).setBengo_flag(4);
                    return true;
                }
            }
            return false;
        }

        private int ReciprocalOverRang() {
            if (MainActivity.myLocation.getSpeed() < 15) {
                return 15;
            }
            return (int)MainActivity.myLocation.getSpeed();
        }
    };

    public void play(){
        threadPoolExecutor.execute(alarm);
    }

    public void display(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                main.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ImageView alarmIcon = main.findViewById(R.id.alarm_icon);
                        TextView speedlimit_tv = main.findViewById(R.id.speedlimit_tv);
                        TextView reciprocalTv = main.findViewById(R.id.reciprocal);
                        ImageView alarmIcon_bg=null;
                        TextView speedlimit_bg=null;
                        TextView reciprocal_bg=null;
                        if(MainActivity.floatingView != null) {
                            alarmIcon_bg = MainActivity.floatingView.findViewById(R.id.alarmIcon_Bg);
                            speedlimit_bg=MainActivity.floatingView.findViewById(R.id.speedLimit_Bg);
                            reciprocal_bg=MainActivity.floatingView.findViewById(R.id.speedReciprocal_Bg);
                        }
                        for(int i : MainActivity.alarmPointIndex){
                            if(MainActivity.pointDataArrayList.get(i).getBengo_flag() == 2 && MainActivity.pointDataArrayList.get(i).getMode() == 0){
                                int dist = 0;
                                if(MainActivity.pointDataArrayList.get(i).getYourPointToEndPointDist() < 1000) dist = MainActivity.pointDataArrayList.get(i).getYourPointToEndPointDist();
                                else dist = 999;
                                Log.e("AGA","我進來了");
                                speedlimit_tv.setVisibility(View.VISIBLE);
                                speedlimit_tv.setText(MainActivity.pointDataArrayList.get(i).getSpeedLimit()+"");


                                alarmIcon.setImageResource(procAlarmIcon(MainActivity.pointDataArrayList.get(i).getVoiceIndex()));
                                alarmIcon.setVisibility(View.VISIBLE);

                                reciprocalTv.setVisibility(View.VISIBLE);
                                reciprocalTv.setText(dist + "M");

                                //不要動我
                                //img在這裡(判斷mainactivity有沒有在畫面上)
                                Log.e("AGA",String.valueOf(alarmIcon_bg != null));
                                if(alarmIcon_bg != null) {
                                    alarmIcon_bg.setImageResource(procAlarmIcon(MainActivity.pointDataArrayList.get(i).getVoiceIndex()));
                                    speedlimit_bg.setText(MainActivity.pointDataArrayList.get(i).getSpeedLimit()+"");
                                    reciprocal_bg.setText(dist+"M");
                                    alarmIcon_bg.setVisibility(View.VISIBLE);
                                    speedlimit_bg.setVisibility(View.VISIBLE);
                                    reciprocal_bg.setVisibility(View.VISIBLE);
                                    Log.e("AGA","成功顯示");
                                }
                            }else if(MainActivity.pointDataArrayList.get(i).getBengo_flag() == 3 || MainActivity.pointDataArrayList.get(i).getBengo_flag() == 4){
                                //MainActivity.alarmPointIndex.remove(MainActivity.alarmPointIndex.indexOf(i));
                                alarmIcon.setVisibility(View.INVISIBLE);
                                speedlimit_tv.setVisibility(View.INVISIBLE);
                                reciprocalTv.setVisibility(View.INVISIBLE);
                                Log.d("GPSS","我在第"+MainActivity.alarmPointIndex.indexOf(i));
                                //MainActivity.alarmPointIndex.remove(MainActivity.alarmPointIndex.indexOf(i));
                                //Img消失在這裡
                                if(alarmIcon_bg != null) {
                                    alarmIcon_bg.setVisibility(View.INVISIBLE);//隱藏
                                    speedlimit_bg.setVisibility(View.INVISIBLE);
                                    reciprocal_bg.setVisibility(View.INVISIBLE);
                                    Log.e("AGA","成功引藏");
                                }
                            }
                        }
                    }
                });
            }
        }).start();
    }

    private int procAlarmIcon(int voiceIndex){
        int[] laserArray = { 192, 218, 219, 224, 225, 226, 227, 228, 230, 231, 232, 233, 234, 235, 236, 237, 238, 240, 241, 242, 243, 246, 249, 250, 252, 253 };    //雷射
        int[] radarArray = { 6, 7, 8, 52, 53, 69, 70, 71, 86, 87, 88, 102, 103, 104, 118, 119, 120, 130, 138, 146, 149, 162, 165, 193 };    //雷達

        for(int index : laserArray){
            if(voiceIndex == index) return R.drawable.laser;//符合雷射點
        }

        for(int index : radarArray){
            if(voiceIndex == index) return R.drawable.radar;//符合雷達點
        }

        return R.drawable.camera;//排除雷射雷達
    }

    public void clearAlarmDisplay(){
        //前景
        ImageView alarmIcon = main.findViewById(R.id.alarm_icon);
        TextView speedlimit_tv = main.findViewById(R.id.speedlimit_tv);
        TextView reciprocalTv = main.findViewById(R.id.reciprocal);
        alarmIcon.setVisibility(View.INVISIBLE);
        speedlimit_tv.setVisibility(View.INVISIBLE);
        reciprocalTv.setVisibility(View.INVISIBLE);
        //背景
        ImageView alarmIcon_bg=null;
        TextView speedlimit_bg=null;
        TextView reciprocal_bg=null;
        if(MainActivity.floatingView != null) {//背景view宣告
            alarmIcon_bg = MainActivity.floatingView.findViewById(R.id.alarmIcon_Bg);
            speedlimit_bg=MainActivity.floatingView.findViewById(R.id.speedLimit_Bg);
            reciprocal_bg=MainActivity.floatingView.findViewById(R.id.speedReciprocal_Bg);
        }
        if(alarmIcon_bg != null) {
            alarmIcon_bg.setVisibility(View.INVISIBLE);//隱藏
            speedlimit_bg.setVisibility(View.INVISIBLE);
            reciprocal_bg.setVisibility(View.INVISIBLE);
        }
    }

    public void stopVoice(){
        if(mediaPlayer != null && mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer = null;
        }
    }
}