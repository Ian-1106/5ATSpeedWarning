package com.speed.vip.database;

import android.util.Log;

import com.speed.vip.MainActivity;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class ReadPointData {
    private int testCount = 0;

    public ReadPointData(){
        decode();
        readFile();
    }

    private void readFile(){
        ArrayList<PointData> data = new ArrayList<>();
        File file = new File(MainActivity.mainContext.getFilesDir(), "gps_point.dbg");
        try{
            FileInputStream fis = new FileInputStream(file);   //開檔
            byte[] buf = new byte[16];
            int[] byte16 = new int[16];
            while (fis.read(buf) != -1) {   //一次讀一行
                for (int i = 0; i < buf.length; i++){
                    byte16[i] = buf[i] & 255;   //去除負號
                }
                int mode = (int)((byte16[0] >> 4) & 0x0f );                                         //模式
                int speedLimit = (int)((byte16[0] & 0x0f) * 10);                                    //速限
                int voiceIndex = byte16[1];                                                         //語句
                int master = (int)((byte16[1] & 0xf0) >> 4);                                        //主項
                int slave = (int)(byte16[1] & 0x0f);                                                //從項
                int endPointDirection = -1;
                if(byte16[2] != 255) endPointDirection = (byte16[2] * 2);                           //結束點角度
                int startPointDirection = -1;
                if(byte16[3] != 255) startPointDirection = (byte16[3] * 2);                         //起點角度
                int voiceTable = -1;                                                                //語音表
                voiceTable = byte16[4] >> 4;
                double endPointLatitude = (byte16[5] << 16 | byte16[6] << 8 | byte16[7])/10000.0;   //結束點緯度
                if (endPointLatitude > 900000.0d) {
                    endPointLatitude = ((-16777216) | (byte16[5] << 16) | (byte16[6] << 8) | byte16[7])/10000.0;
                }
                int averageSpeedDistance = -1;                                                      //平均速度距離
                if(byte16[8] != 255) averageSpeedDistance = byte16[8] * 10;
                double endPointLongitude = (byte16[9] << 16 | byte16[10] << 8 | byte16[11])/10000.0;//結束點經度
                if (endPointLongitude > 1800000.0d) {
                    endPointLongitude = ((-16777216) | (byte16[9] << 16) | (byte16[10] << 8) | byte16[11])/10000.0;
                }

                int latitudeDifference = (byte16[12] << 8) | byte16[13];                                //起終點緯度差
                int longitudeDifference = (byte16[14] << 8) | byte16[15];                               //起終點經度差

                if(latitudeDifference > 32767) latitudeDifference =  -(65536 - latitudeDifference);
                double startPointLatitude = ((int)(endPointLatitude*10000) + latitudeDifference) / 10000.0;
                if(longitudeDifference > 32767) longitudeDifference =  -(65536 - longitudeDifference);
                double startPointLongitude = ((int)(endPointLongitude*10000) + longitudeDifference) / 10000.0;

                int voiceSrc = new VoiceTable().getVoiceSrc(mode, voiceTable, voiceIndex);

                testCount++;

                data.add(new PointData(
                        mode,
                        speedLimit,
                        voiceIndex,
                        voiceSrc,
                        endPointDirection,
                        startPointDirection,
                        voiceTable,
                        endPointLatitude,
                        averageSpeedDistance,
                        endPointLongitude,
                        latitudeDifference,
                        longitudeDifference,
                        startPointLatitude,
                        startPointLongitude
                ));
            }
            data.remove(data.size() - 1);   //移除最後一筆資料(最後一筆為無效資料)
            MainActivity.pointDataArrayList = data;
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private static void decode() {
        InputStream inputStream;
        try {
            // 讀取加密後的檔案
            inputStream = MainActivity.mainContext.getAssets().open("20231002_3101.png");
            byte[] encryptedData = new byte[inputStream.available()];
            inputStream.read(encryptedData);


            // 解密檔案
            byte[] decryptedData =new byte[encryptedData.length];
            for (int i = 0; i < encryptedData.length; i++)
                decryptedData[i] = (byte)(encryptedData[i] ^ MainActivity.KEY[i % MainActivity.KEY.length]);//^是XOR

            // 將解密後的檔案寫入記憶體
            File file = new File(MainActivity.mainContext.getFilesDir(), "gps_point.dbg");
            FileOutputStream outputStream = new FileOutputStream(file);// 使用 FileOutputStream 開啟該路徑的檔案;
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
            bufferedOutputStream.write(decryptedData);
            bufferedOutputStream.close();

            //Toast.makeText(MainActivity.mainContext, "解密完成", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            //Toast.makeText(MainActivity.mainContext, "解密失敗", Toast.LENGTH_SHORT).show();
        }
    }
}