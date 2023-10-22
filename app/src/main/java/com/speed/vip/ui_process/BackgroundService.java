package com.speed.vip.ui_process;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.speed.vip.MainActivity;
import com.speed.vip.R;
import com.speed.vip.alarm_process.AlarmController;
import com.speed.vip.dummy_data.DummyData;
import com.speed.vip.gps_process.GPS;

public class BackgroundService extends Service implements GPS.GPSListener {

    private LocationManager locationManager;
    private LocationListener locationListener;
    private WindowManager windowManager;
    private WindowManager.LayoutParams params;
    private GPS gps;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("LocationService", "Location Service", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

            Notification notification = new NotificationCompat.Builder(this, "LocationService")
                    .setContentTitle("Location Service")
                    .setContentText("Running in the background")
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .build();

            startForeground(1, notification);
        }
        gps = new GPS(MainActivity.mainContext,this);
        if(MainActivity.myLocation != null)
            gps.start();
        initFloatingWindow();
        dragFloatingWindow();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                // 這裡可以將獲取到的位置資訊發送到你的應用中
                //location = setLocationByDummyData(location);
                Log.d("GPSS","index:"+MainActivity.testCount+" , YP("+location.getLatitude()+","+location.getLongitude()+")");

                if(MainActivity.myLocation == null) MainActivity.myLocation = location;
                else if(location.getSpeed() * 18 / 5 <= 5) MainActivity.myLocation = location; //myLocation不等於null時，只會取速度大於5km/hr的資料

                MainActivity.procAlarm.checkBengoFlag(location);
                if(MainActivity.pointDataArrayList.size() > 0) {
                    for(int i : MainActivity.alarmPointIndex) {
                        Log.d("GPSS",i+" , mode:"+MainActivity.pointDataArrayList.get(i).getMode()+" , ep("+MainActivity.pointDataArrayList.get(i).getEndPointLatitude()+","+MainActivity.pointDataArrayList.get(i).getEndPointLongitude()+") , bengo_flag:"+MainActivity.pointDataArrayList.get(i).getBengo_flag());
                        Log.d("GPSS","--------------------------------------->>>>");
                    }
                    MainActivity.alarmController.play();
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        if (ContextCompat.checkSelfPermission(MainActivity.mainContext, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(MainActivity.mainContext, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        }

        // 設定 GPS Provider 的更新間隔時間和距離變化
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locationListener);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, locationListener);

        return START_STICKY;
    }
    private void initFloatingWindow() {
        MainActivity.floatingView = LayoutInflater.from(this).inflate(R.layout.service_floating_window, null);
        //初始化
        params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                PixelFormat.TRANSLUCENT
        );
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = 0;
        params.flags= WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

        // 獲得WindowManager，將FloatingWindow顯示到螢幕上
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        windowManager.addView(MainActivity.floatingView, params);
    }
    private void dragFloatingWindow() {
        MainActivity.floatingView.setOnTouchListener(new View.OnTouchListener() {
            private int offsetX,offsetY;
            private float startTouchX,startTouchY;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN://按下
                        offsetX = params.x;
                        offsetY = params.y;
                        startTouchX = event.getRawX();
                        startTouchY = event.getRawY();
                        return true;
                    case MotionEvent.ACTION_MOVE://拖曳
                        params.x = offsetX + (int) (event.getRawX() - startTouchX);
                        params.y = offsetY + (int) (event.getRawY() - startTouchY);
                        windowManager.updateViewLayout(MainActivity.floatingView, params);
                        return true;
                    case MotionEvent.ACTION_UP://放開
                        if (event.getRawX() == startTouchX && event.getRawY() == startTouchY) {//懸浮視窗沒有被拖曳
                            ImageView returnButton = MainActivity.floatingView.findViewById(R.id.floating_image_view);
                            returnButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    // 返回應用程式
                                    Intent intent = new Intent(BackgroundService.this, MainActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                    startActivity(intent);
                                    // 關閉FloatingWindow
                                    stopSelf();
                                }
                            });
                        }
                        return  true;
                }
                return false;
            }
        });
    }

    private Location setLocationByDummyData(Location location) {
        if (MainActivity.testCount < MainActivity.dummyData.size() - 1) MainActivity.testCount++;
        else MainActivity.testCount = 0;
        DummyData dd = MainActivity.dummyData.get(MainActivity.testCount);
        location.setLatitude(dd.getLatitude());
        location.setLongitude(dd.getLongitude());
        location.setSpeed((float) (dd.getSpeed() / 3.6));
        location.setBearing((float) dd.getCursor());
        return location;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 停止更新拖曳位置資訊
        locationManager.removeUpdates(locationListener);
        if (MainActivity.floatingView != null)
            windowManager.removeView(MainActivity.floatingView);
    }

    @Override
    public void onLocationChanged(Location location) {
        TextView speed_tv= (TextView)MainActivity.floatingView.findViewById(R.id.speed_bg);
        speed_tv.setTypeface(Typeface.createFromAsset(getAssets(), "digital-7.ttf"));   //設定速度顯示的字型
        int speed = (int)(MainActivity.myLocation.getSpeed() *18 / 5);
        speed_tv.setText(String.valueOf(speed));
    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }
}