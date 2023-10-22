package com.speed.vip.system;

import java.util.ArrayList;
import java.util.Arrays;

public class ProcSystemSetting {
    private ArrayList<Integer> NoAlarmOfScooterMode = new ArrayList<>(Arrays.asList( //機車模式
            64, 65, 66, 67, 68, 69, 70, 71, 230, 231, 232,          //高速公路(mode:0)
            128, 129, 130, 197, 243,                                //快速道路(mode:0)
            80, 81, 82, 83, 84, 85, 86, 87, 88, 196, 233, 234, 235  //快車道(mode:0)
    ));

    private ArrayList<Integer> NoAlarmOfCarMode = new ArrayList<>(Arrays.asList( //汽車模式
            96, 97, 98, 99, 100, 101, 102, 103, 104, 195, 236, 237, 238     //慢車道(mode:0)
    ));

    private ArrayList<Integer> NoAlarmOfMotorcycleMode = new ArrayList<>(Arrays.asList( //重機模式
            64, 65, 66, 67, 68, 69, 70, 71, 230, 231, 232          //高速公路(mode:0)
    ));

    public boolean procCarMode(String mode, int src){
        boolean goAlarm = true;
        ArrayList<Integer> target = new ArrayList<>();
        switch(mode){
            case "機車模式":
                target = NoAlarmOfScooterMode;
                break;
            case "汽車模式":
                target = NoAlarmOfCarMode;
                break;
            case "重機模式":
                target = NoAlarmOfMotorcycleMode;
                break;
            default:
                break;
        }
        for(int i : target){
            if(src == i){
                goAlarm = false;
                return goAlarm;
            }
        }

        return goAlarm;
    }
}