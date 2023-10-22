package com.speed.vip.gps_process;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.GnssStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;

import com.speed.vip.MainActivity;

public class GPS implements LocationListener {

    private static final int PERMISSION_REQUEST_CODE = 100;
    private static final long MIN_TIME_MS = 1000; // 最小更新時間
    private static final float MIN_DISTANCE_METERS = 0; // 最小更新距離

    private final Context context;
    private final LocationManager locationManager;
    private final GPSListener listener;

    public GPS(Context context, GPSListener listener) {
        this.context = context;
        this.listener = listener;
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    public void start() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSION_REQUEST_CODE);
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_MS, MIN_DISTANCE_METERS, this);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_MS, MIN_DISTANCE_METERS, this);

        locationManager.registerGnssStatusCallback(gnssStatusCallback);
    }

    GnssStatus.Callback gnssStatusCallback = new GnssStatus.Callback() {
        @Override
        public void onSatelliteStatusChanged(GnssStatus status) {
            MainActivity.satelliteCount = status.getSatelliteCount();
            //Log.d("SatelliteCount", "Number of satellites: " + MainActivity.satelliteCount);
        }
    };

    public void stop() {
        locationManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) {
        if (listener != null) {
            listener.onLocationChanged(location);
        }
    }

    @Override
    public void onProviderDisabled(String provider) {
        if (listener != null) {
            listener.onProviderDisabled(provider);
        }
    }

    @Override
    public void onProviderEnabled(String provider) {
        if (listener != null) {
            listener.onProviderEnabled(provider);
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        if (listener != null) {
            listener.onStatusChanged(provider, status, extras);
        }
    }

    public interface GPSListener {
        void onLocationChanged(Location location);

        void onProviderDisabled(String provider);

        void onProviderEnabled(String provider);

        void onStatusChanged(String provider, int status, Bundle extras);
    }
}