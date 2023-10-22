package com.speed.vip.system;

public class SettingConfig {
    private String version = "";                    //版本
    private String driverMode = "";                 //駕駛模式
    private String carMode = "";                    //行車模式
    private int speedAdjustment_speed;              //速度調整
    private String speedAdjustment_unit;            //速度調整
    private boolean overSpeedlimitVoice = false;    //超速語音警報
    private boolean overSpeedlimitKeepAlarm = false;    //超速語音警報

    public void setAppVersion(String version){
        this.version = version;
    }

    public void setDriverMode(String driverMode){
        this.driverMode = driverMode;
    }

    public void setCarMode(String carMode){
        this.carMode = carMode;
    }

    public void setSpeedAdjustment_speed(int speed){
        this.speedAdjustment_speed = speed;
    }

    public void setSpeedAdjustment_unit(String unit){
        this.speedAdjustment_unit = unit;
    }


    public void setOverSpeedlimitVoice(boolean enable){
        this.overSpeedlimitVoice = enable;
    }
    public void setOverSpeedlimitKeepAlarm(boolean enable){
        this.overSpeedlimitKeepAlarm = enable;
    }

    public String getAppVersion(){
        return this.version;
    }

    public String getDriverMode(){
        return this.driverMode;
    }

    public String getCarMode(){
        return this.carMode;
    }

    public int getSpeedAdjustment_speed(){
        return this.speedAdjustment_speed;
    }

    public String getSpeedAdjustment_unit(){
        return this.speedAdjustment_unit;
    }

    public boolean getOverSpeedlimitVoice(){
        return this.overSpeedlimitVoice;
    }
    public boolean getOverSpeedlimitKeepAlarm(){
        return this.overSpeedlimitKeepAlarm;
    }

    public String getAllData(){
        return "版本:"+this.version+"\n"
                +"駕駛模式:"+this.driverMode+"\n"
                +"行車模式:"+this.carMode+"\n"
                +"速度調整:"+this.speedAdjustment_speed+" "+this.speedAdjustment_unit+"\n"
                +"超速語音警告:"+this.overSpeedlimitVoice+"\n"
                +"連續響聲警告:"+this.overSpeedlimitKeepAlarm;
    }
}