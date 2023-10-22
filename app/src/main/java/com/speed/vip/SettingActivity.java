package com.speed.vip;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultRegistry;
import androidx.appcompat.app.AppCompatActivity;

import com.speed.vip.system.SystemSetting;

public class SettingActivity extends AppCompatActivity {
    private static final int OVERLAY_PERMISSION_REQUEST_CODE = 1000;
    private ActivityResultRegistry activityResultRegistry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initActivityBySetting();    //初始化界面

        ImageButton toMain = (ImageButton) findViewById(R.id.back); //返回主頁面
        toMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SystemSetting().saveSetting();

                MainActivity.isWelcomeFlag = true;
                Intent intent = new Intent();
                intent.setClass(SettingActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button reference = (Button)findViewById(R.id.btn_usebook);    //查看說明手冊
        reference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(SettingActivity.this, ManualActivity.class);
                startActivity(intent);
            }
        });

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.照相系統模式:
                        MainActivity.systemSetting.setDriverMode("照相系統模式");
                        break;
                    case R.id.照相系統限速模式:
                        MainActivity.systemSetting.setDriverMode("照相系統限速模式");
                        break;
                    case R.id.安全駕駛限速模式:
                        MainActivity.systemSetting.setDriverMode("安全駕駛限速模式");
                        break;
                }
            }
        });

        RadioGroup radioGroup2 = (RadioGroup) findViewById(R.id.radioGroup2);
        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.機車模式:
                        MainActivity.systemSetting.setCarMode("機車模式");
                        break;
                    case R.id.汽車模式:
                        MainActivity.systemSetting.setCarMode("汽車模式");
                        break;
                    case R.id.重機模式:
                        MainActivity.systemSetting.setCarMode("重機模式");
                        break;
                    case R.id.完整模式:
                        MainActivity.systemSetting.setCarMode("完整模式");
                        break;
                }
            }
        });

        RadioGroup radioGroup3 = (RadioGroup) findViewById(R.id.radioGroup3);
        radioGroup3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.公里:
                        MainActivity.systemSetting.setSpeedAdjustment_unit("公里");
                        break;
                    case R.id.百分比:
                        MainActivity.systemSetting.setSpeedAdjustment_unit("%");
                        break;
                }
            }
        });

        Button 速度調整Add = (Button) findViewById(R.id.button);
        Button 速度調整Subtract = (Button) findViewById(R.id.button2);

        速度調整Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.systemSetting.getSpeedAdjustment_speed() < 11) {
                    MainActivity.systemSetting.setSpeedAdjustment_speed(MainActivity.systemSetting.getSpeedAdjustment_speed() + 1);
                    if(MainActivity.systemSetting.getSpeedAdjustment_speed() == 10) 速度調整Add.setEnabled(false);
                    else if (MainActivity.systemSetting.getSpeedAdjustment_speed() > 0) 速度調整Subtract.setEnabled(true);
                    int 速度調整值 = MainActivity.systemSetting.getSpeedAdjustment_speed();
                    TextView 速度調整的tv = (TextView) findViewById(R.id.速度調整的tv);
                    if(速度調整值 != 0){
                        速度調整的tv.setText("原始GPS速度" + " + " + String.valueOf(速度調整值));
                    }else 速度調整的tv.setText("原始GPS速度");
                    MainActivity.systemSetting.setSpeedAdjustment_speed(速度調整值);
                }
            }
        });

        速度調整Subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.systemSetting.getSpeedAdjustment_speed() > 0){
                    MainActivity.systemSetting.setSpeedAdjustment_speed(MainActivity.systemSetting.getSpeedAdjustment_speed() - 1);
                    if(MainActivity.systemSetting.getSpeedAdjustment_speed() == 0) 速度調整Subtract.setEnabled(false);
                    else if (MainActivity.systemSetting.getSpeedAdjustment_speed() < 11) 速度調整Add.setEnabled(true);
                    int 速度調整值 = MainActivity.systemSetting.getSpeedAdjustment_speed();
                    TextView 速度調整的tv = (TextView) findViewById(R.id.速度調整的tv);
                    if(速度調整值 != 0){
                        速度調整的tv.setText("原始GPS速度" + " + " + String.valueOf(速度調整值));
                    }else 速度調整的tv.setText("原始GPS速度");
                    MainActivity.systemSetting.setSpeedAdjustment_speed(速度調整值);
                }
            }
        });

        Button 超速語音警報btn = (Button) findViewById(R.id.超速語音警報btn);
        Button 連續響聲設定btn = (Button) findViewById(R.id.連續響聲設定btn);

        超速語音警報btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.systemSetting.setOverSpeedlimitVoice(!MainActivity.systemSetting.getOverSpeedlimitVoice());
                if(MainActivity.systemSetting.getOverSpeedlimitVoice()) {
                    超速語音警報btn.setText("開啟");
                    if(MainActivity.systemSetting.getOverSpeedlimitKeepAlarm()) 連續響聲設定btn.setText("開啟");
                    else 連續響聲設定btn.setText("關閉");
                    連續響聲設定btn.setEnabled(true);
                }
                else if(!MainActivity.systemSetting.getOverSpeedlimitVoice()){
                    超速語音警報btn.setText("關閉");
                    MainActivity.systemSetting.setOverSpeedlimitKeepAlarm(false);
                    連續響聲設定btn.setText("關閉");
                    連續響聲設定btn.setEnabled(false);
                }
            }
        });

        連續響聲設定btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.systemSetting.getOverSpeedlimitVoice() && 連續響聲設定btn.isEnabled()){
                    MainActivity.systemSetting.setOverSpeedlimitKeepAlarm(!MainActivity.systemSetting.getOverSpeedlimitKeepAlarm());
                    if(MainActivity.systemSetting.getOverSpeedlimitKeepAlarm()) 連續響聲設定btn.setText("開啟");
                    else 連續響聲設定btn.setText("關閉");
                } else Toast.makeText(SettingActivity.this, "請先開啟超速語音警告!", Toast.LENGTH_SHORT).show();
            }
        });

        Button reset = (Button) findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.isWelcomeFlag = true;
                new SystemSetting().resetSystemConfig();
                Intent intent = new Intent(SettingActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void initActivityBySetting(){

        {   //初始化駕駛模式
            RadioButton 駕駛模式 = null;
            switch (MainActivity.systemSetting.getDriverMode()) {
                case "照相系統模式":
                    駕駛模式 = (RadioButton) findViewById(R.id.照相系統模式);
                    break;
                case "照相系統限速模式":
                    駕駛模式 = (RadioButton) findViewById(R.id.照相系統限速模式);
                    break;
                case "安全駕駛限速模式":
                    駕駛模式 = (RadioButton) findViewById(R.id.安全駕駛限速模式);
                    break;
            }
            駕駛模式.setChecked(true);
        }

        {   //行車模式
            RadioButton 行車模式 = null;
            switch (MainActivity.systemSetting.getCarMode()) {
                case "機車模式":
                    行車模式 = (RadioButton) findViewById(R.id.機車模式);
                    break;
                case "汽車模式":
                    行車模式 = (RadioButton) findViewById(R.id.汽車模式);
                    break;
                case "重機模式":
                    行車模式 = (RadioButton) findViewById(R.id.重機模式);
                    break;
                case "完整模式":
                    行車模式 = (RadioButton) findViewById(R.id.完整模式);
                    break;
            }
            行車模式.setChecked(true);
        }

        {   //速度調整
            RadioButton 單位 = null;
            switch (MainActivity.systemSetting.getSpeedAdjustment_unit()) {
                case "公里":
                    單位 = (RadioButton) findViewById(R.id.公里);
                    break;
                case "%":
                    單位 = (RadioButton) findViewById(R.id.百分比);
                    break;
            }
            單位.setChecked(true);

            int 速度調整值 = MainActivity.systemSetting.getSpeedAdjustment_speed();
            TextView 速度調整的tv = (TextView) findViewById(R.id.速度調整的tv);
            Button 速度調整Add = (Button) findViewById(R.id.button);
            Button 速度調整Subtract = (Button) findViewById(R.id.button2);
            if(速度調整值 > 0){
                速度調整的tv.setText("原始GPS速度" + " + " + String.valueOf(速度調整值));
            }else if(速度調整值 == 0){
                速度調整的tv.setText("原始GPS速度");
                速度調整Subtract.setEnabled(false);
            } else if (速度調整值 == 5) {
                速度調整Add.setEnabled(false);
            }
        }

        {   //超速語音警報
            Button 超速語音警報btn = (Button) findViewById(R.id.超速語音警報btn);
            if(MainActivity.systemSetting.getOverSpeedlimitVoice()) 超速語音警報btn.setText("開啟");
            else if(!MainActivity.systemSetting.getOverSpeedlimitVoice()) 超速語音警報btn.setText("關閉");
        }

        {   //超速語音警報
            Button 超速語音警報btn = (Button) findViewById(R.id.超速語音警報btn);
            Button 連續響聲設定btn = (Button) findViewById(R.id.連續響聲設定btn);

            if(MainActivity.systemSetting.getOverSpeedlimitVoice()) {
                超速語音警報btn.setText("開啟");
                if(MainActivity.systemSetting.getOverSpeedlimitKeepAlarm()) 連續響聲設定btn.setText("開啟");
                else 連續響聲設定btn.setText("關閉");
                連續響聲設定btn.setEnabled(true);
            }
            else if(!MainActivity.systemSetting.getOverSpeedlimitVoice()){
                超速語音警報btn.setText("關閉");
                連續響聲設定btn.setText("關閉");
                連續響聲設定btn.setEnabled(false);
            }
        }
    }
}