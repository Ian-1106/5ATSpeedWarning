package com.speed.vip.gps_process;

import android.location.Location;
import com.speed.vip.MainActivity;

public class ProcAlarm {
    private Location myLocation;  //GPS座標資料

    public void checkBengoFlag(Location loc){
        myLocation = loc;

        int startPointToEndPointCursor = 0, yourPointToEndPointCursor = 0, startPointToYourPointCursor = 0; //起點到終點的角度, 你的座標到結束點的角度, 起點到你的座標的角度
        int startPointToEndPointDistBuffer = 0, startPointToYourPointDistBuffer = 0;    //起點到終點的距離緩衝, 起點到你的終點的距離緩衝
        int check_cursor_weng = 0;  //角度的緩衝

        for(int i = 0; i< MainActivity.pointDataArrayList.size(); i++) {
            switch (MainActivity.pointDataArrayList.get(i).getMode()) {
                case 0: //照相模式
                case 1: //安全模式
                case 3: //自建點
                    double startPointLatitudeTemp = MainActivity.pointDataArrayList.get(i).getStartPointLatitude(); //起點緯度
                    double startPointLongitudeTemp = MainActivity.pointDataArrayList.get(i).getStartPointLongitude();  //起點經度

                    MainActivity.pointDataArrayList.get(i).setYourPointToEndPointDist((int) point2_crs_dist(1, myLocation.getLatitude(), myLocation.getLongitude(), MainActivity.pointDataArrayList.get(i).getEndPointLatitude(), MainActivity.pointDataArrayList.get(i).getEndPointLongitude()));    //取得你的座標到結束點座標的距離
                    MainActivity.pointDataArrayList.get(i).setStartPointToYourPointDist((int) point2_crs_dist(1, startPointLatitudeTemp, startPointLongitudeTemp, myLocation.getLatitude(), myLocation.getLongitude()));
                    MainActivity.pointDataArrayList.get(i).setStartPointToEndPointDist((int) point2_crs_dist(1, startPointLatitudeTemp, startPointLongitudeTemp, MainActivity.pointDataArrayList.get(i).getEndPointLatitude(), MainActivity.pointDataArrayList.get(i).getEndPointLongitude()));
                    startPointToEndPointCursor = (int) point2_crs_dist(2, startPointLatitudeTemp, startPointLongitudeTemp, MainActivity.pointDataArrayList.get(i).getEndPointLatitude(), MainActivity.pointDataArrayList.get(i).getEndPointLongitude());
                    yourPointToEndPointCursor = (int) point2_crs_dist(2, myLocation.getLatitude(), myLocation.getLongitude(), MainActivity.pointDataArrayList.get(i).getEndPointLatitude(), MainActivity.pointDataArrayList.get(i).getEndPointLongitude());
                    startPointToYourPointCursor = (int) point2_crs_dist(2, startPointLatitudeTemp, startPointLongitudeTemp, myLocation.getLatitude(), myLocation.getLongitude());

                    if (MainActivity.pointDataArrayList.get(i).getMode() == 3) {     //自建點
                        startPointToEndPointDistBuffer = 200;
                        startPointToYourPointDistBuffer = 100;
                        check_cursor_weng = 15;
                    } else {
                        if (MainActivity.pointDataArrayList.get(i).getSpeedLimit() < 60) {
                            startPointToEndPointDistBuffer = 100;
                            startPointToYourPointDistBuffer = 50;
                            check_cursor_weng = 10;
                        } else if (MainActivity.pointDataArrayList.get(i).getSpeedLimit() >= 60 && MainActivity.pointDataArrayList.get(i).getSpeedLimit() < 90) {
                            startPointToEndPointDistBuffer = 150;
                            startPointToYourPointDistBuffer = 100;
                            check_cursor_weng = 20;
                        } else {
                            startPointToEndPointDistBuffer = 200;
                            startPointToYourPointDistBuffer = 150;
                            check_cursor_weng = 30;
                        }
                    }

                    if ((MainActivity.pointDataArrayList.get(i).getStartPointToYourPointDist() + MainActivity.pointDataArrayList.get(i).getYourPointToEndPointDist()) <= (MainActivity.pointDataArrayList.get(i).getStartPointToEndPointDist() + startPointToEndPointDistBuffer)) {   //sp_yp_dist + yp_ep_dist <= sp_ep_dist + sep_dist_buffer
                        if (MainActivity.pointDataArrayList.get(i).getBengo_flag() == 0) {
                            if (MainActivity.pointDataArrayList.get(i).getStartPointToYourPointDist() <= startPointToYourPointDistBuffer &&
                                    cur_cmp_func((int) (MainActivity.pointDataArrayList.get(i).getStartPointDirection()), (int) myLocation.getBearing(), 15)) {
                                MainActivity.pointDataArrayList.get(i).setBengo_flag(1);
                                if(!MainActivity.alarmPointIndex.contains(i)) MainActivity.alarmPointIndex.add(i);
                            }
                        }

                        if (MainActivity.pointDataArrayList.get(i).getBengo_flag() == 0) {
                            if (MainActivity.pointDataArrayList.get(i).getYourPointToEndPointDist() < MainActivity.pointDataArrayList.get(i).getStartPointToEndPointDist()) {
                                if (cur_cmp_func((int) yourPointToEndPointCursor, (int) myLocation.getBearing(), 30)) {
                                    if ((cur_cmp_func(((int) MainActivity.pointDataArrayList.get(i).getStartPointDirection()), (int) startPointToEndPointCursor, (int) check_cursor_weng)) ||
                                            (cur_cmp_func(((int) MainActivity.pointDataArrayList.get(i).getEndPointDirection()), (int) startPointToEndPointCursor, (int) check_cursor_weng))) {
                                        if ((cur_cmp_func(((int) MainActivity.pointDataArrayList.get(i).getStartPointDirection()), ((int) MainActivity.pointDataArrayList.get(i).getEndPointDirection()), 10)) &&
                                                (cur_cmp_func(((int) MainActivity.pointDataArrayList.get(i).getStartPointDirection()), (int) startPointToEndPointCursor, 10)) &&
                                                (cur_cmp_func(((int) MainActivity.pointDataArrayList.get(i).getEndPointDirection()), (int) startPointToEndPointCursor, 10))) {
                                            if ((cur_cmp_func(((int) MainActivity.pointDataArrayList.get(i).getStartPointDirection()), (int) myLocation.getBearing(), 10)) &&
                                                    (cur_cmp_func(((int) MainActivity.pointDataArrayList.get(i).getStartPointDirection()), (int) startPointToYourPointCursor, 10)) &&
                                                    (cur_cmp_func(((int) MainActivity.pointDataArrayList.get(i).getEndPointDirection()), (int) myLocation.getBearing(), 10)) &&
                                                    (cur_cmp_func(((int) MainActivity.pointDataArrayList.get(i).getEndPointDirection()), (int) yourPointToEndPointCursor, 10))) {
                                                MainActivity.pointDataArrayList.get(i).setBengo_flag(1);
                                                if(!MainActivity.alarmPointIndex.contains(i)) MainActivity.alarmPointIndex.add(i);
                                            } else {
                                                if ((MainActivity.pointDataArrayList.get(i).getYourPointToEndPointDist() <= 300) &&
                                                        (cur_cmp_func(((int) MainActivity.pointDataArrayList.get(i).getEndPointDirection()), (int) myLocation.getBearing(), 10)) &&
                                                        (cur_cmp_func((int) yourPointToEndPointCursor, ((int) MainActivity.pointDataArrayList.get(i).getEndPointDirection()), 10))) {
                                                    MainActivity.pointDataArrayList.get(i).setBengo_flag(1);
                                                    if(!MainActivity.alarmPointIndex.contains(i)) MainActivity.alarmPointIndex.add(i);
                                                }
                                            }
                                        } else {
                                            if (((cur_cmp_func(((int) MainActivity.pointDataArrayList.get(i).getStartPointDirection()), (int) myLocation.getBearing(), 10)) &&
                                                    (cur_cmp_func(((int) MainActivity.pointDataArrayList.get(i).getStartPointDirection()), (int) startPointToYourPointCursor, 10))) ||
                                                    ((cur_cmp_func(((int) MainActivity.pointDataArrayList.get(i).getEndPointDirection()), (int) myLocation.getBearing(), 10)) &&
                                                            (cur_cmp_func(((int) MainActivity.pointDataArrayList.get(i).getEndPointDirection()), (int) yourPointToEndPointCursor, 10))) ||
                                                    ((cur_cmp_func((int) startPointToEndPointCursor, (int) myLocation.getBearing(), 10)) &&
                                                            ((cur_cmp_func((int) startPointToEndPointCursor, (int) startPointToYourPointCursor, 10)) ||
                                                                    (cur_cmp_func((int) startPointToEndPointCursor, (int) yourPointToEndPointCursor, 10))))) {
                                                MainActivity.pointDataArrayList.get(i).setBengo_flag(1);
                                                if(!MainActivity.alarmPointIndex.contains(i)) MainActivity.alarmPointIndex.add(i);
                                            } else {
                                                if ((MainActivity.pointDataArrayList.get(i).getYourPointToEndPointDist() <= 300) &&
                                                        (cur_cmp_func(((int) MainActivity.pointDataArrayList.get(i).getEndPointDirection()), (int) myLocation.getBearing(), 10)) &&
                                                        (cur_cmp_func((int) yourPointToEndPointCursor, ((int) MainActivity.pointDataArrayList.get(i).getEndPointDirection()), 10))) {
                                                    MainActivity.pointDataArrayList.get(i).setBengo_flag(1);
                                                    if(!MainActivity.alarmPointIndex.contains(i)) MainActivity.alarmPointIndex.add(i);
                                                }
                                            }
                                        }
                                    } else {
                                        if ((MainActivity.pointDataArrayList.get(i).getYourPointToEndPointDist() <= 300) &&
                                                (cur_cmp_func(((int) MainActivity.pointDataArrayList.get(i).getEndPointDirection()), (int) myLocation.getBearing(), 10)) &&
                                                (cur_cmp_func((int) yourPointToEndPointCursor, ((int) MainActivity.pointDataArrayList.get(i).getEndPointDirection()), 10))) {
                                            MainActivity.pointDataArrayList.get(i).setBengo_flag(1);
                                            if(!MainActivity.alarmPointIndex.contains(i)) MainActivity.alarmPointIndex.add(i);
                                        }
                                    }
                                }
                            }
                        }

                        //相同結束點處理
                        if (MainActivity.pointDataArrayList.get(i).getBengo_flag() == 1) {
                            if ((MainActivity.pointDataArrayList.get(i).getMode() == 0) &&
                                    (((MainActivity.pointDataArrayList.get(i).getVoiceTable() >= 0xB2) && (MainActivity.pointDataArrayList.get(i).getVoiceTable() < 0xB8)) ||
                                            ((MainActivity.pointDataArrayList.get(i).getVoiceTable() >= 0xBA) && (MainActivity.pointDataArrayList.get(i).getVoiceTable() < 0xC0)))) {
                            } else {
                                for (int j = 0; j < MainActivity.pointDataArrayList.size(); j++) {
                                    if ((j != i) &&
                                            (MainActivity.pointDataArrayList.get(j).getBengo_flag() == 2) &&
                                            ((MainActivity.pointDataArrayList.get(j).getEndPointLatitude()) == (MainActivity.pointDataArrayList.get(i).getEndPointLatitude())) &&
                                            ((MainActivity.pointDataArrayList.get(j).getEndPointLongitude()) == (MainActivity.pointDataArrayList.get(i).getEndPointLongitude())) &&
                                            ((MainActivity.pointDataArrayList.get(j).getEndPointDirection()) == (MainActivity.pointDataArrayList.get(i).getEndPointDirection()))) {
                                        if ((MainActivity.pointDataArrayList.get(j).getMode() == 0) &&
                                                (((MainActivity.pointDataArrayList.get(j).getVoiceTable() >= 0xB2) && (MainActivity.pointDataArrayList.get(j).getVoiceTable() < 0xB8)) ||
                                                        ((MainActivity.pointDataArrayList.get(j).getVoiceTable() >= 0xBA) && (MainActivity.pointDataArrayList.get(j).getVoiceTable() < 0xC0)))) {
                                        } else {
                                            MainActivity.pointDataArrayList.get(i).setBengo_flag(3);
                                        }
                                    }
                                }
                            }
                        }
                    }

                    //start_point to end_point方向與start_point方向<=15度=>+100米範圍處理,若>15度=>+200米範圍處理
                    if (MainActivity.pointDataArrayList.get(i).getBengo_flag() != 0) {
                        if (MainActivity.pointDataArrayList.get(i).getBengo_flag() == 4) {
                            if (cur_cmp_func(((int) MainActivity.pointDataArrayList.get(i).getStartPointDirection()), (int) startPointToEndPointCursor, 30)) {
                                if (cur_cmp_func(((int) MainActivity.pointDataArrayList.get(i).getStartPointDirection()), (int) myLocation.getBearing(), 120)) {
                                    if (((MainActivity.pointDataArrayList.get(i).getStartPointToYourPointDist()) + (MainActivity.pointDataArrayList.get(i).getYourPointToEndPointDist())) > ((MainActivity.pointDataArrayList.get(i).getStartPointToEndPointDist()) + 100)) {
                                        MainActivity.pointDataArrayList.get(i).setBengo_flag(0);
                                        MainActivity.pointDataArrayList.get(i).setYourPointToEndPointDist(0);
                                        MainActivity.alarmPointIndex.remove(MainActivity.alarmPointIndex.indexOf(i));
                                        MainActivity.alarmController.clearAlarmDisplay();
                                    }
                                } else {
                                    MainActivity.pointDataArrayList.get(i).setBengo_flag(0);
                                    MainActivity.pointDataArrayList.get(i).setYourPointToEndPointDist(0);
                                    MainActivity.alarmPointIndex.remove(MainActivity.alarmPointIndex.indexOf(i));
                                    MainActivity.alarmController.clearAlarmDisplay();
                                }
                            } else {
                                if (((MainActivity.pointDataArrayList.get(i).getStartPointToYourPointDist()) + (MainActivity.pointDataArrayList.get(i).getYourPointToEndPointDist())) > ((MainActivity.pointDataArrayList.get(i).getStartPointToEndPointDist()) + 100)) {
                                    MainActivity.pointDataArrayList.get(i).setBengo_flag(0);
                                    MainActivity.pointDataArrayList.get(i).setYourPointToEndPointDist(0);
                                    MainActivity.alarmPointIndex.remove(MainActivity.alarmPointIndex.indexOf(i));
                                    MainActivity.alarmController.clearAlarmDisplay();
                                }
                            }
                        } else {
                            if(cur_cmp_func(((int)MainActivity.pointDataArrayList.get(i).getStartPointDirection()), (int)startPointToEndPointCursor, 30)){
                                if(cur_cmp_func(((int)MainActivity.pointDataArrayList.get(i).getStartPointDirection()), (int)myLocation.getBearing(), 80)){
                                    if(((MainActivity.pointDataArrayList.get(i).getStartPointToYourPointDist())+(MainActivity.pointDataArrayList.get(i).getYourPointToEndPointDist()))>((MainActivity.pointDataArrayList.get(i).getStartPointToEndPointDist())+150)){
                                        if(MainActivity.pointDataArrayList.get(i).getBengo_flag() == 2) {
                                            if(MainActivity.pointDataArrayList.get(i).getMode() == 3) {

                                            }
                                            else if(MainActivity.pointDataArrayList.get(i).getMode() == 0){

                                            }
                                        }
                                        MainActivity.pointDataArrayList.get(i).setBengo_flag(0);
                                        MainActivity.pointDataArrayList.get(i).setYourPointToEndPointDist(0);
                                        MainActivity.alarmController.clearAlarmDisplay();
                                    }
                                }
                                else {
                                    if(MainActivity.pointDataArrayList.get(i).getBengo_flag() == 2) {
                                        if(MainActivity.pointDataArrayList.get(i).getMode() == 3){

                                        }
                                        else if(MainActivity.pointDataArrayList.get(i).getMode() == 0){

                                        }
                                    }
                                    MainActivity.pointDataArrayList.get(i).setBengo_flag(0);
                                    MainActivity.pointDataArrayList.get(i).setYourPointToEndPointDist(0);
                                    MainActivity.alarmPointIndex.remove(MainActivity.alarmPointIndex.indexOf(i));
                                    MainActivity.alarmController.clearAlarmDisplay();
                                }
                            }
                            else
                            {
                                if(((MainActivity.pointDataArrayList.get(i).getStartPointToYourPointDist())+(MainActivity.pointDataArrayList.get(i).getYourPointToEndPointDist())) > ((MainActivity.pointDataArrayList.get(i).getStartPointToEndPointDist()) + 300)){
                                    if(MainActivity.pointDataArrayList.get(i).getBengo_flag() == 2){
                                        if(MainActivity.pointDataArrayList.get(i).getMode() == 3){

                                        }
                                        else if(MainActivity.pointDataArrayList.get(i).getMode() == 0){

                                        }
                                    }
                                    MainActivity.pointDataArrayList.get(i).setBengo_flag(0);
                                    MainActivity.pointDataArrayList.get(i).setYourPointToEndPointDist(0);
                                    MainActivity.alarmPointIndex.remove(MainActivity.alarmPointIndex.indexOf(i));
                                    MainActivity.alarmController.clearAlarmDisplay();
                                }
                            }
                        }
                    }
                default:
                    break;
            }
        }
    }

    private boolean cur_cmp_func(int scur, int tcur, int cur_diff) {
        int cur_dif_tmp;
        if (scur >= tcur) {
            cur_dif_tmp = scur - tcur;
        } else {
            cur_dif_tmp = tcur - scur;
        }
        return cur_dif_tmp >= 180 ? 360 - cur_dif_tmp <= cur_diff : cur_dif_tmp <= cur_diff;
    }

    private double point2_crs_dist(int i, double _lat1, double _lon1, double _lat2, double _lon2) {
        int lat1 = 0, lon1 = 0, lat2 = 0, lon2 = 0;
        lat1 = (int)(_lat1*10000);
        lon1 = (int)(_lon1*10000);
        lat2 = (int)(_lat2*10000);
        lon2 = (int)(_lon2*10000);

        char p1_ns;
        char p1_ew;
        char p2_ns;
        char p2_ew;
        double crs12;
        double crs122 = 0.0d;
        if (lat1 < 0) {
            lat1 = 0 - lat1;
            p1_ns = 2;
        } else {
            p1_ns = 1;
        }
        if (lon1 < 0) {
            lon1 = 0 - lon1;
            p1_ew = 2;
        } else {
            p1_ew = 1;
        }
        if (lat2 < 0) {
            lat2 = 0 - lat2;
            p2_ns = 2;
        } else {
            p2_ns = 1;
        }
        if (lon2 < 0) {
            lon2 = 0 - lon2;
            p2_ew = 2;
        } else {
            p2_ew = 1;
        }

        double t_lat1 = ((lat1 / 10000.0d) * 3.141592653589793d) / 180.0d;
        double t_lon1 = ((lon1 / 10000.0d) * 3.141592653589793d) / 180.0d;
        double t_lat2 = ((lat2 / 10000.0d) * 3.141592653589793d) / 180.0d;
        double t_lon2 = ((lon2 / 10000.0d) * 3.141592653589793d) / 180.0d;

        if (p1_ns == 2) {
            t_lat1 = 0.0d - t_lat1;
        }
        if (p1_ew == 1) {
            t_lon1 = 0.0d - t_lon1;
        }
        if (p2_ns == 2) {
            t_lat2 = 0.0d - t_lat2;
        }
        if (p2_ew == 1) {
            t_lon2 = 0.0d - t_lon2;
        }
        if (t_lat1 + t_lat2 == 0.0d && Math.abs(t_lon1 - t_lon2) == 3.141592653589793d) {
            return -1.0d;
        }
        double dist_tmp1 = Math.sin((t_lat1 - t_lat2) / 2.0d);
        double dist_tmp2 = Math.sin((t_lon1 - t_lon2) / 2.0d);
        double dist2 = 6378137.0d * Math.asin(Math.sqrt((dist_tmp1 * dist_tmp1) + (Math.cos(t_lat1) * Math.cos(t_lat2) * dist_tmp2 * dist_tmp2))) * 2.0d;
        if (i == 2) {
            double dist = (((3.141592653589793d * dist2) / 180.0d) / 60.0d) / 1852.0d;
            if (Math.sin(dist) * Math.cos(t_lat1) == 0.0d) {
                crs12 = 0.0d;
            } else {
                double argacos = (Math.sin(t_lat2) - (Math.sin(t_lat1) * Math.cos(dist))) / (Math.sin(dist) * Math.cos(t_lat1));
                if (argacos > 1.0d) {
                    argacos = 1.0d;
                } else if (argacos < -1.0d) {
                    argacos = -1.0d;
                }
                if (Math.sin(t_lon2 - t_lon1) < 0.0d) {
                    crs12 = Math.acos(argacos);
                } else {
                    Math.acos(argacos);
                    crs12 = 6.283185307179586d - Math.acos(argacos);
                }
            }
            crs122 = (180.0d * crs12) / 3.141592653589793d;
        }
        if (i != 1) {
            if (i == 2) {
                return crs122;
            }
            return -1.0d;
        }
        return dist2;
    }
}
