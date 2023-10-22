package com.speed.vip.database;

public class PointData {
    /*共同項目*/
    private int mode;                      //模式
    private int speedLimit;                 //速限
    private int voiceIndex;
    private int voiceTable;                 //語音表
    private int voiceSrc = -1;
    private double endPointLatitude;        //結束點緯度
    private double endPointLongitude;       //結束點經度

    /*雙座標特有項目*/
    private int startPointDirection;        //起點方向
    private int averageSpeedDistance;    //平均速度距離
    private long latitudeDifference;      //緯度差
    private long longitudeDifference;     //經度差

    /*雙座標與單座標(含ep行進方向)特有項目*/
    private final int endPointDirection;       //結束點方向


    /*測速警告條件判斷所需計算之參數*/
    private int yourPointToEndPointDist;
    private int startPointToYourPointDist;
    private int startPointToEndPointDist;

    private double startPointLatitude;        //起始點緯度
    private double startPointLongitude;       //起始點經度

    private int bengo_flag = 0; //判讀座標狀態(該不該警告、該不該釋放)

    public PointData(int mode, int speedLimit, int voiceIndex, int voiceSrc, int endPointDirection, int startPointDirection, int voiceTable, double endPointLatitude, int averageSpeedDistance, double endPointLongitude, long latitudeDifference, long longitudeDifference, double startPointLatitude, double startPointLongitude){
        this.mode = mode;
        this.speedLimit = speedLimit;
        this.voiceIndex = voiceIndex;
        this.endPointDirection = endPointDirection;
        this.startPointDirection = startPointDirection;
        this.voiceTable = voiceTable;
        this.voiceSrc = voiceSrc;
        this.endPointLatitude = endPointLatitude;
        this.averageSpeedDistance = averageSpeedDistance;
        this.endPointLongitude = endPointLongitude;
        this.latitudeDifference = latitudeDifference;
        this.longitudeDifference = longitudeDifference;
        this.startPointLatitude = startPointLatitude;
        this.startPointLongitude = startPointLongitude;
    }

    /*設定測速警告條件判斷所需計算之參數*/
    public void setYourPointToEndPointDist(int dist){
        this.yourPointToEndPointDist = dist;
    }

    public void setStartPointToYourPointDist(int dist){
        this.startPointToYourPointDist = dist;
    }

    public void setStartPointToEndPointDist(int dist){
        this.startPointToEndPointDist = dist;
    }

    public void setStartPointLatitude(double latitude){
        this.startPointLatitude = latitude;
    }

    public void setStartPointLongitude(double longitude){
        this.startPointLongitude = longitude;
    }

    public void setSpeedLimit(int speed){
        this.speedLimit = speed;
    }

    /*取得測速警告條件判斷所需計算之參數*/
    public int getYourPointToEndPointDist(){
        return this.yourPointToEndPointDist;
    }

    public int getStartPointToYourPointDist(){
        return this.startPointToYourPointDist;
    }

    public int getStartPointToEndPointDist(){
        return this.startPointToEndPointDist;
    }

    public double getStartPointLatitude(){
        return this.startPointLatitude;
    }

    public double getStartPointLongitude(){
        return this.startPointLongitude;
    }

    /*設定旗標*/
    public void setBengo_flag(int status){
        this.bengo_flag = status;
    }

    /*取得旗標*/
    public int getBengo_flag(){
        return this.bengo_flag;
    }

    /*取得共同項目*/
    public int getMode(){
        return this.mode;
    }

    public int getSpeedLimit(){
        return this.speedLimit;
    }

    public int getVoiceIndex(){
        return this.voiceIndex;
    }

    public int getVoiceTable(){
        return this.voiceTable;
    }

    public int getVoiceSrc(){
        return this.voiceSrc;
    }

    public double getEndPointLatitude(){
        return this.endPointLatitude;
    }

    public double getEndPointLongitude(){
        return this.endPointLongitude;
    }

    /*取得雙座標特有項目*/
    public double getStartPointDirection(){
        return this.startPointDirection;
    }

    public int getAverageSpeedDistance(){
        return this.averageSpeedDistance;
    }

    public long getLatitudeDifference(){
        return this.latitudeDifference;
    }

    public long getLongitudeDifference(){
        return this.longitudeDifference;
    }

    /*取得雙座標與單座標(含ep行進方向)特有項目*/
    public int getEndPointDirection(){
        return this.endPointDirection;
    }

    /*測試用*/
    public String getAllDataToLog(){
        return "mode:"+this.mode+"， speedLimit:"+this.speedLimit+"， startPointDirection:"+this.startPointDirection+"， endPointDirection:"+this.endPointDirection+"， voiceTable:"+this.voiceTable+"， endPointLatitude:"+this.endPointLatitude+"， endPointLongitude:"+this.endPointLongitude+"， averageSpeedDistance:"+this.averageSpeedDistance+"， latitudeDifference:"+this.latitudeDifference+"， longitudeDifference:"+this.longitudeDifference+" ， startPointLatitude:"+this.startPointLatitude+" ， startPointLongitude:"+this.startPointLongitude;
    }

    public boolean equals(PointData target){
        return this.getMode() == target.getMode()
                && this.getSpeedLimit() == target.getMode()
                && this.getVoiceSrc() == target.getVoiceSrc()
                && this.getEndPointLatitude() == target.getEndPointLatitude()
                && this.getEndPointLongitude() == target.getEndPointLongitude()
                && this.getEndPointDirection() == target.getEndPointDirection();
    }
}