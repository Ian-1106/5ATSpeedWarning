package com.speed.vip;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Location;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.speed.vip.alarm_process.AlarmController;
import com.speed.vip.database.PointData;
import com.speed.vip.database.ReadPointData;
import com.speed.vip.dummy_data.DummyData;
import com.speed.vip.dummy_data.ReadDummyData;
import com.speed.vip.gps_process.GPS;
import com.speed.vip.gps_process.GPSService;
import com.speed.vip.gps_process.ProcAlarm;
import com.speed.vip.system.SettingConfig;
import com.speed.vip.system.SystemSetting;
import com.speed.vip.ui_process.BackgroundService;
import com.speed.vip.ui_process.HandleMenu;
import com.speed.vip.ui_process.UpdateGPSToUI;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GPS.GPSListener{
    public static Context mainContext;
    public static int[] KEY = {0x2B,0x62,0xCA};
    public static String IV = "location20230325";
    public static boolean isWelcomeFlag = true; //true:需要播報歡迎詞
    public static boolean isOpenBg=false;
    public static ArrayList<PointData> pointDataArrayList = new ArrayList<>();
    public static ArrayList<DummyData> dummyData = new ArrayList<>();
    public static SettingConfig systemSetting = null;
    public static AlarmController alarmController = null;
    public static ArrayList<Integer> systemVoiceList = new ArrayList<>();
    public static Activity activity;
    public static GPS gps;
    public static Location myLocation = null;
    public static int satelliteCount = -1;
    public static ProcAlarm procAlarm = new ProcAlarm();;
    public static ArrayList<Integer> alarmPointIndex = new ArrayList<>();
    public static boolean overSpeedlimitVoiceFlag = false;
    private ActivityResultLauncher<Intent> overlayPermissionLauncher;//懸浮視窗權限需求
    public static int overSpeedlimitKeepAlarm = 5;
    public static View floatingView=null;
    private UpdateGPSToUI updateGPSToUI;
    private double SPEED_GATE = 1.39;
    private double DISTANCE_GATE = 10;
    private double ACCURACY_GATE = 30;

    //private int testCount = -1;
    public static int testCount = 183;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity=this;
        mainContext = this;
        gps = new GPS(this, this);
        updateGPSToUI = new UpdateGPSToUI();
        alarmController = new AlarmController();

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {   //要求使用權限
           /*沒有權限則返回*/
              if (ContextCompat.checkSelfPermission(MainActivity.mainContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(MainActivity.mainContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                showPopup();
            }else{
                // 請求權限成功
                gps.start();
                initActivity();
                loadData();
            }
        }
    }

    public void showPopup() {

        if (ContextCompat.checkSelfPermission(MainActivity.mainContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(MainActivity.mainContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED&&
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {   //要求使用權限
            AlertDialog.Builder builder_GPS = new AlertDialog.Builder(this);
            builder_GPS.setTitle("提示");
            builder_GPS.setMessage("為測速照相警示功能，此應用程式需要收集位置資訊；當應用程式處於關閉或未使用狀態，不會收集這類資料。");
            builder_GPS.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // 按下 OK 按鈕時的操作
                    dialog.dismiss();

                    ActivityCompat.requestPermissions((Activity) MainActivity.mainContext, new String[]{
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION,

                    }, 100);
                }
            });
            AlertDialog dialog_GPS = builder_GPS.create();
            dialog_GPS.show();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M&&!Settings.canDrawOverlays(this)) {
            //懸浮視窗權限請求
            AlertDialog.Builder builder_bgWorking = new AlertDialog.Builder(this);
            builder_bgWorking.setTitle("浮動視窗權限");
            builder_bgWorking.setMessage("為使用背景執行，需要您同意開啟此app的浮動視窗權限");
            builder_bgWorking.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                    intent.setData(Uri.parse("package:" + MainActivity.mainContext.getPackageName()));
                    Log.e("QAQ",intent.toString());
                    startActivity(intent);
                }
            });
            AlertDialog dialog_bgWorking = builder_bgWorking.create();
            dialog_bgWorking.show();
        }
            return;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 請求權限成功
                gps.start();
                initActivity();
                loadData();
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        //location = setLocationByDummyData(location);//測試模式

        location = procSpeedAdj(location);  //速度調整設定(設定頁面)

        if(myLocation == null){
            myLocation = location;
            updateGPSToUI.updateAlt();
        }
        //location.getSpeed()單位為ms->km/hr
        else if(location.getSpeed() > SPEED_GATE && location.getBearing() != 0 && location.getAccuracy() < ACCURACY_GATE){   //速度大於1.39且行進角度不等於0且精度小於30
            myLocation = location;
            updateGPSToUI.updateGPSToUI();
            procAlarm.checkBengoFlag(location);
        }
        else if(location.getSpeed() == 0 && location.getBearing() == 0){ //行進方向(方位角)等於0代表沒有在移動(停車或是等紅燈)
            updateGPSToUI.clearSpeed();
        }

        Log.d("GPSS","index:"+testCount+" , YP("+myLocation.getLatitude()+","+myLocation.getLongitude()+") , Speed:"+myLocation.getSpeed()+" , Alt:"+myLocation.getAltitude()+" , Accuracy:"+location.getAccuracy()+" , Cursor:"+location.getBearing());
        //Log.d("GPSS","index:"+testCount+" , YP("+location.getLatitude()+","+location.getLongitude()+") , Speed:"+location.getSpeed());
        if(pointDataArrayList.size() > 0) {
            for(int i : alarmPointIndex) {
                Log.d("GPSS",i+" , mode:"+pointDataArrayList.get(i).getMode()+" , ep("+pointDataArrayList.get(i).getEndPointLatitude()+","+pointDataArrayList.get(i).getEndPointLongitude()+") , bengo_flag:"+pointDataArrayList.get(i).getBengo_flag());//getBengo_flag 初始0;前方有測速照相1;2;3;超過測速照相4
                Log.d("GPSS","--------------------------------------->>>>");
            }
            alarmController.play();
        }

    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("GPSS", "Provider disabled: " + provider);
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("GPSS", "Provider enabled: " + provider);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("GPSS", "Status changed: " + provider + " status: " + status);
    }

    @Override
    protected void onStart() {
        super.onStart();
        gps.start();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent bg = new Intent(MainActivity.this, BackgroundService.class);
        stopService(bg);

        gps.start();
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent(MainActivity.this, BackgroundService.class);
        stopService(intent);
    }

    private Location setLocationByDummyData(Location location) {
        if (testCount < MainActivity.dummyData.size() - 1) testCount++;
        else testCount = 0;
        DummyData dd = MainActivity.dummyData.get(testCount);
        location.setLatitude(dd.getLatitude());
        location.setLongitude(dd.getLongitude());
        location.setSpeed((float) (dd.getSpeed() / 3.6));
        location.setBearing((float) dd.getCursor());
        return location;
    }

    private void initActivity(){    //系統初始化頁面及語音

        TextView speed_tv= (TextView)findViewById(R.id.speed_tv);    //速度顯示
        speed_tv.setTypeface(Typeface.createFromAsset(getAssets(), "digital-7.ttf"));   //設定速度顯示的字型

        TextView alt_tv = (TextView)findViewById(R.id.alt_tv);   //高度顯示
        alt_tv.setTypeface(Typeface.createFromAsset(getAssets(), "digital-7.ttf"));     //設定高度顯示的字型


        ImageView battery = (ImageView)findViewById(R.id.battery);
        IntentFilter mIntentFilter = new IntentFilter();//監聽電池狀態物件
        mIntentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (action.equals(Intent.ACTION_BATTERY_CHANGED)) {
                    int status = intent.getIntExtra("status", 0); //電池狀態
                    int level = intent.getIntExtra("level", 0); //電池電量

                    if(status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL){
                        //Log.d("test","充電中，"+level);
                        if(0 <= level && level <30) battery.setImageResource(R.drawable.battery_bad_charging);
                        else if(30 <= level && level <50) battery.setImageResource(R.drawable.battery_normal_charging);
                        else if(50 <= level && level <=100) battery.setImageResource(R.drawable.battery_good_charging);
                    }else{
                        //Log.d("test","未充電，"+level);
                        if(0 <= level && level <30) battery.setImageResource(R.drawable.battery_bad);
                        else if(30 <= level && level <50) battery.setImageResource(R.drawable.battery_normal);
                        else if(50 <= level && level <=100) battery.setImageResource(R.drawable.battery_good);
                    }
                }
            }
        }, mIntentFilter);

        HandleMenu handleMenu = new HandleMenu();
        ImageButton menu = (ImageButton) findViewById(R.id.menu);
        menu.setOnClickListener(handleMenu.listener);
        handleMenu.initPopupWindow();

        alarmController.play();
    }

    private void loadData(){
        ReadPointData readPointData = new ReadPointData();  //讀取測速照相座標資料
        ReadDummyData rdd = new ReadDummyData();    //取得測試資料
        SystemSetting ss = new SystemSetting();
        ss.loadSetting();   //取得系統設定參數
        Log.d("系統參數",systemSetting.getAllData());
    }

    private Location procSpeedAdj(Location location){
        if(systemSetting.getSpeedAdjustment_speed() > 0){
            float speedTmp = 0;
            switch(systemSetting.getSpeedAdjustment_unit()){
                case "公里":
                    Log.d("設定","有嗎");
                    speedTmp = ((location.getSpeed() * 18 / 5) + systemSetting.getSpeedAdjustment_speed()) * 5 / 18; //轉km/hr後加上加權值，最後轉回m/s
                    break;
                case "%":
                    speedTmp = (float)(location.getSpeed() + (location.getSpeed() * (systemSetting.getSpeedAdjustment_speed() / 100.0)));
                    break;
            }
            location.setSpeed(speedTmp);
        }

        return location;
    }
}