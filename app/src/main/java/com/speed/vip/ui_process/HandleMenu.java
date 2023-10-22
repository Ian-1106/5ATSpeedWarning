package com.speed.vip.ui_process;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;


import com.speed.vip.MainActivity;
import com.speed.vip.R;
import com.speed.vip.SettingActivity;
import com.speed.vip.gps_process.GPSService;

public class HandleMenu {
    private PopupWindow popupWindow = null;


    public void initPopupWindow() {
        View view = LayoutInflater.from(MainActivity.mainContext).inflate(R.layout.activity_menu, null);
        popupWindow = new PopupWindow(view);
        popupWindow.setAnimationStyle(R.style.popwin_anim_style);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);

        Button bgWorking = (Button) view.findViewById(R.id.bgWorking);
        bgWorking.setOnClickListener(bgWorkingListener);
        /*bgWorking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.mainContext, "此為正式版專屬功能，請至Google Play下載正式版", Toast.LENGTH_SHORT).show();
            }
        });*/

        Button setting = (Button) view.findViewById(R.id.drawable);
        setting.setOnClickListener(settingListener);

        Button btnConfirm = (Button) view.findViewById(R.id.btnConform);
        btnConfirm.setOnClickListener(listener);


    }

    public final View.OnClickListener bgWorkingListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Context context=view.getContext();//獲得Button的Context
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M&&!Settings.canDrawOverlays(context)) {
                //懸浮視窗權限請求
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                intent.setData(Uri.parse("package:" + context.getPackageName()));
                MainActivity.mainContext.startActivity(intent);//開啟請求權限視窗

            }else{
                Toast.makeText(MainActivity.mainContext, "應用程式正在背景執行", Toast.LENGTH_SHORT).show();
                MainActivity.isOpenBg=true;
                Intent g = new Intent(MainActivity.mainContext, GPSService.class);
                MainActivity.mainContext.stopService(g);
                Intent intent = new Intent(MainActivity.mainContext, BackgroundService.class);
                MainActivity.mainContext.startService(intent);
                Intent it = new Intent();
                it.setAction(Intent.ACTION_MAIN);
                it.addCategory(Intent.CATEGORY_HOME);
                MainActivity.mainContext.startActivity(it);
                popupWindow.dismiss();
            }
        }
    };
    public View.OnClickListener settingListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Log.d("test", "Setting");
            Intent intent = new Intent();
            intent.setClass(MainActivity.mainContext, SettingActivity.class);
            MainActivity.mainContext.startActivity(intent);
            //MainActivity.speedometer_go = true;
            popupWindow.dismiss();

        }
    };
    public View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view.getId() == R.id.menu) popupWindow.showAtLocation(view, Gravity.CENTER_HORIZONTAL, 0, 400);
            else if(view.getId() == R.id.btnConform) popupWindow.dismiss();
        }
    };
}