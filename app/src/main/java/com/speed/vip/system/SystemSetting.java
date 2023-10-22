package com.speed.vip.system;

import android.util.Log;
import com.speed.vip.MainActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class SystemSetting {
    public SystemSetting(){

    }

    public void loadSetting() {
        String result = "";
        File file;
        file = new File(MainActivity.mainContext.getFilesDir(), "user_config.txt");
        if (file.exists()) {
            Log.d("設定參數來源","user");
            try {
                InputStreamReader inputReader = new InputStreamReader(new FileInputStream(file));
                BufferedReader bufReader = new BufferedReader(inputReader);
                String line = "";
                while ((line = bufReader.readLine()) != null) result += line + "\n";
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Log.d("設定參數來源","system");
            try {
                InputStreamReader inputReader = new InputStreamReader(MainActivity.mainContext.getResources().getAssets().open("system_config.txt"));
                BufferedReader bufReader = new BufferedReader(inputReader);
                String line = "";
                while ((line = bufReader.readLine()) != null) result += line + "\n";

                FileOutputStream outputStream = new FileOutputStream(file);// 使用 FileOutputStream 開啟該路徑的檔案
                outputStream.write(result.getBytes());// 將資料寫入檔案中
                outputStream.close();// 關閉 FileOutputStream
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        SettingConfig setting = new SettingConfig();
        String version = result.split("\n")[0].split(":")[1];
        String driverMode = result.split("\n")[1].split(":")[1];
        String carMode = result.split("\n")[2].split(":")[1];
        int speed = Integer.parseInt(result.split("\n")[3].split(":")[1].split(" ")[0]);
        String unit = result.split("\n")[3].split(":")[1].split(" ")[1];
        boolean overSpeedlimitVoice = Boolean.parseBoolean(result.split("\n")[4].split(":")[1]);
        boolean overSpeedlimitKeepAlarm = Boolean.parseBoolean(result.split("\n")[5].split(":")[1]);

        setting.setAppVersion(version);
        setting.setDriverMode(driverMode);
        setting.setCarMode(carMode);
        setting.setSpeedAdjustment_speed(speed);
        setting.setSpeedAdjustment_unit(unit);
        setting.setOverSpeedlimitVoice(overSpeedlimitVoice);
        setting.setOverSpeedlimitKeepAlarm(overSpeedlimitKeepAlarm);

        MainActivity.systemSetting = setting;
    }

    public void saveSetting(){
        String content = MainActivity.systemSetting.getAllData();
        try {
            File file = new File(MainActivity.mainContext.getFilesDir(), "user_config.txt");
            FileOutputStream  outputStream = new FileOutputStream(file);// 使用 FileOutputStream 開啟該路徑的檔案
            outputStream.write(content.getBytes());// 將資料寫入檔案中
            outputStream.close();// 關閉 FileOutputStream
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void resetSystemConfig(){
        File file = new File(MainActivity.mainContext.getFilesDir(), "user_config.txt");
        boolean result = file.delete();
    }
}