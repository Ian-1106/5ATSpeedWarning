package com.speed.vip.ui_process;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.widget.ImageView;
import android.widget.TextView;

import com.speed.vip.MainActivity;
import com.speed.vip.R;

public class UpdateGPSToUI {
    private Activity main;

    public UpdateGPSToUI(){
        main = (Activity) MainActivity.mainContext;
    }

    public void updateGPSToUI(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                main.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updateGPSSignal();
                        updateSpeed();
                        updateDir();
                        updateAlt();
                    }
                });
            }
        }).start();
    }

    public void updateAlt(){
        int alt = (int)Math.abs(MainActivity.myLocation.getAltitude());
        TextView alt_tv = main.findViewById(R.id.alt_tv);
        alt_tv.setText(String.valueOf(alt));
    }

    private void updateGPSSignal(){   //更新GPS訊號強度
        int src = R.drawable.gps_signal_bad;
        if(MainActivity.satelliteCount < 4.0) src = (R.drawable.gps_signal_bad);  //0~5
        else if(4.0 <= MainActivity.satelliteCount && MainActivity.satelliteCount < 7.0) src = (R.drawable.gps_signal_normal);  //5~10
        else if(7.0 < MainActivity.satelliteCount ) src = (R.drawable.gps_signal_good); //10~

        ImageView gps_signal = main.findViewById(R.id.gps_signal);
        gps_signal.setImageResource(src);
    }

    private void updateDir(){
        int src = R.drawable.dashboard_main;
        if(0<= MainActivity.myLocation.getBearing() && MainActivity.myLocation.getBearing() < 45.0 || 315.0 <= MainActivity.myLocation.getBearing() && MainActivity.myLocation.getBearing() <360.0) src = (R.drawable.speed_dashboard_n); //北
        else if(45.0 <= MainActivity.myLocation.getBearing() && MainActivity.myLocation.getBearing() < 135.0) src = (R.drawable.speed_dashboard_e); //東
        else if(135.0 <= MainActivity.myLocation.getBearing() && MainActivity.myLocation.getBearing() < 225.0) src = (R.drawable.speed_dashboard_s);    //南
        else if(225.0 <= MainActivity.myLocation.getBearing() && MainActivity.myLocation.getBearing() < 315.0) src = (R.drawable.speed_dashboard_w);    //西

        ImageView speed_dashboard = (ImageView) main.findViewById(R.id.speed_dashboard);
        speed_dashboard.setImageResource(src);
    }

    private void updateSpeed(){  //更新速度
        int speed = (int)(MainActivity.myLocation.getSpeed() *18 / 5);


        TextView speed_tv = main.findViewById(R.id.speed_tv);

        float targetAngle = getSpeedMaskAngle(speed);
        float startAngle = getSpeedMaskAngle(Integer.parseInt(speed_tv.getText().toString()));
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator rotation = ObjectAnimator.ofFloat(main.findViewById(R.id.speed_mask), "rotation", startAngle, targetAngle);
        rotation.setDuration(100);
        rotation.setRepeatCount(0);
        animatorSet.playTogether(rotation);
        animatorSet.start();

        speed_tv.setText(String.valueOf(speed));    //旋轉完速度條才可以更新在顯示框
    }

    public void clearSpeed(){
        TextView speed_tv = main.findViewById(R.id.speed_tv);

        float targetAngle = 0;
        float startAngle = 0;
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator rotation = ObjectAnimator.ofFloat(main.findViewById(R.id.speed_mask), "rotation", startAngle, targetAngle);
        rotation.setDuration(100);
        rotation.setRepeatCount(0);
        animatorSet.playTogether(rotation);
        animatorSet.start();

        speed_tv.setText(String.valueOf(0));    //旋轉完速度條才可以更新在顯示框
    }

    private float getSpeedMaskAngle(int speed){ //從速度取得角度
        float angle = 0;
        if(speed > 5 && speed <= 10) angle = 7f;
        else if(speed > 10 && speed <= 15) angle = 9f;
        else if(speed > 15 && speed <= 20) angle = 12f;
        else if(speed > 20 && speed <= 25) angle = 14f;
        else if(speed > 25 && speed <= 30) angle = 17f;
        else if(speed > 30 && speed <= 35) angle = 20f;
        else if(speed > 35 && speed <= 40) angle = 24f;
        else if(speed > 40 && speed <= 45) angle = 27f;
        else if(speed > 45 && speed <= 50) angle = 30f;
        else if(speed > 50 && speed <= 55) angle = 33f;
        else if(speed > 55 && speed <= 60) angle = 37f;
        else if(speed > 60 && speed <= 65) angle = 40f;
        else if(speed > 65 && speed <= 70) angle = 44f;
        else if(speed > 70 && speed <= 75) angle = 46f;
        else if(speed > 75 && speed <= 80) angle = 50f;
        else if(speed > 80 && speed <= 85) angle = 53f;
        else if(speed > 85 && speed <= 90) angle = 57f;
        else if(speed > 90 && speed <= 95) angle = 60f;
        else if(speed > 95 && speed <= 100) angle = 65f;
        else if(speed > 100 && speed <= 105) angle = 68f;
        else if(speed > 105 && speed <= 110) angle = 72f;
        else if(speed > 110 && speed <= 115) angle = 75f;
        else if(speed > 115 && speed <= 120) angle = 80f;
        else if(speed > 120 && speed <= 125) angle = 83f;
        else if(speed > 125 && speed <= 130) angle = 87f;
        else if(speed > 130 && speed <= 135) angle = 91f;
        else if(speed > 135 && speed <= 140) angle = 95f;
        else if(speed > 140 && speed <= 145) angle = 99f;
        else if(speed > 145 && speed <= 150) angle = 104f;
        else if(speed > 150 && speed <= 155) angle = 108f;
        else if(speed > 155 && speed <= 160) angle = 113f;
        else if(speed > 160 && speed <= 165) angle = 118f;
        else if(speed > 165 && speed <= 170) angle = 123f;
        else if(speed > 170 && speed <= 175) angle = 128f;
        else if(speed > 175 && speed <= 180) angle = 133f;
        else if(speed > 180 && speed <= 185) angle = 138f;
        else if(speed > 185 && speed <= 190) angle = 144f;
        else if(speed > 190 && speed <= 195) angle = 150f;
        else if(speed > 195 && speed <= 200) angle = 156f;
        else if(speed > 200 && speed <= 205) angle = 161f;
        else if(speed > 205 && speed <= 210) angle = 167f;
        else if(speed > 210 && speed <= 215) angle = 172f;
        else if(speed > 215 && speed <= 220) angle = 179f;

        return angle;
    }
}
