package com.speed.vip.dummy_data;

public class DummyData {
    private int id;
    private int time;
    private double latitude;
    private double longitude;
    private double speed;
    private double cursor;

    DummyData(int id, int time, double latitude, double longitude, double speed, double cursor){
        this.id = id;
        this.time = time;
        this.latitude = latitude;
        this.longitude = longitude;
        this.speed = speed;
        this.cursor = cursor;
    }

    public int getId(){
        return this.id;
    }

    public  int getTime(){
        return this.time;
    }

    public double getLatitude(){
        return this.latitude;
    }

    public double getLongitude(){
        return this.longitude;
    }

    public double getSpeed(){
        return this.speed;
    }

    public double getCursor(){
        return this.cursor;
    }

    public String getAllDataString(){
        return "id:"+this.id+" , time:"+this.time+" , latitude:"+this.latitude+" , longitude:"+this.longitude+" , speed:"+this.speed+" , cursor:"+this.cursor;
    }
}