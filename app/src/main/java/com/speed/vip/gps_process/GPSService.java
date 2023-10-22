package com.speed.vip.gps_process;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.GnssStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.speed.vip.MainActivity;

public class GPSService extends Service implements LocationListener {

    private LocationManager locationManager;

    @Override
    public void onCreate() {
        super.onCreate();

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        // 檢查是否取得權限
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        // 設定GPS provider和更新時間
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, this);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, this);

        locationManager.registerGnssStatusCallback(gnssStatusCallback);
    }

    GnssStatus.Callback gnssStatusCallback = new GnssStatus.Callback() {
        @Override
        public void onSatelliteStatusChanged(GnssStatus status) {
            MainActivity.satelliteCount = status.getSatelliteCount();
            //Log.d("SatelliteCount", "Number of satellites: " + MainActivity.satelliteCount);
        }
    };

    @Override
    public void onLocationChanged(Location location) {
        Intent intent = new Intent("LOCATION_UPDATE");
        Bundle bundle = new Bundle();
        bundle.putParcelable("location", location);
        intent.putExtras(bundle);
        sendBroadcast(intent);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}

    @Override
    public void onProviderEnabled(String provider) {}

    @Override
    public void onProviderDisabled(String provider) {}

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 停止GPS資訊更新
        locationManager.removeUpdates(this);
    }
}