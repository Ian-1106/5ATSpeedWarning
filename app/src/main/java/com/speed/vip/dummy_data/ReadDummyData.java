package com.speed.vip.dummy_data;

import com.speed.vip.MainActivity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ReadDummyData {
    private ArrayList<DummyData> dataList = new ArrayList<DummyData>();

    public ReadDummyData() {
        readFile();
    }

    private void readFile() {
        try {
            InputStream is = MainActivity.mainContext.getAssets().open("dummy.nmea");
            BufferedReader buffile = new BufferedReader(new InputStreamReader(is));
            String data = "";
            int count = 0;
            while ((data = buffile.readLine()) != null) {
                String[] temp = data.split(",");
                if (temp[0].equals("$GPRMC") && temp[2].equals("A")) {     //RMC && 資料有效
                    count++;
                    int time = (int) Double.parseDouble(temp[1]) + 80000;   //原本的資料是UTC，要加上時區

                    double latitude = Double.parseDouble(temp[3]);   //緯度
                    int tempLatitude1 = (int) (latitude / 100);   //度的整數部分
                    double tempLatitude2 = (((latitude / 100) - (double) tempLatitude1) * 100) / 60; //將分除以60轉換為度
                    latitude = (int) ((tempLatitude1 + tempLatitude2) * 10000) / 10000.0;     //只取小數點後四位

                    double longitude = Double.parseDouble(temp[5]);  //經度
                    int tempLongitude1 = (int) (longitude / 100);   //度的整數部分
                    double tempLongitude2 = (((longitude / 100) - (double) tempLongitude1) * 100) / 60; //將分除以60轉換為度
                    longitude = (int) ((tempLongitude1 + tempLongitude2) * 10000) / 10000.0;     //只取小數點後四位

                    double speed = Double.parseDouble(temp[7]) * 1.852;     //速度(1節 = 1.852 km/hr)
                    double cursor = Double.parseDouble(temp[8]);    //角度

                    dataList.add(new DummyData(count, time, latitude, longitude, speed, cursor));
                }
            }
            MainActivity.dummyData = dataList;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
