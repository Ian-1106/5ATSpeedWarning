package com.speed.vip.database;

import com.speed.vip.R;

public class VoiceTable {
    private int mode = -1;
    private int table = -1;
    private int master = -1;
    private int slave = -1;
    private int src = -1;

    public int getVoiceSrc(int mode, int voiceTable, int voiceIndex){
        this.mode = mode;
        this.table = voiceTable;
        this.master = (int)((voiceIndex & 0xf0) >> 4);
        this.slave = (int)(voiceIndex & 0x0f);
        switch(this.mode){
            case 0:
                switch(this.table){
                    case 15:
                        switch(this.master){
                            case 0:                                                                 //mode:0, table:15, master:0
                                switch(this.slave){
                                    case 0:
                                        this.src = R.raw.mode0_table15_master0_slave0;
                                        break;
                                    case 1:
                                        this.src = R.raw.mode0_table15_master0_slave1;
                                        break;
                                    case 2:
                                        this.src = R.raw.mode0_table15_master0_slave2;
                                        break;
                                    case 3:
                                        this.src = R.raw.mode0_table15_master0_slave3;
                                        break;
                                    case 4:
                                        this.src = R.raw.mode0_table15_master0_slave4;
                                        break;
                                    case 5:
                                        this.src = R.raw.mode0_table15_master0_slave5;
                                        break;
                                    case 6:
                                        this.src = R.raw.mode0_table15_master0_slave6;
                                        break;
                                    case 7:
                                        this.src = R.raw.mode0_table15_master0_slave7;
                                        break;
                                    case 8:
                                        this.src = R.raw.mode0_table15_master0_slave8;
                                        break;
                                    case 9:
                                        this.src = R.raw.mode0_table15_master0_slave9;
                                        break;
                                    case 10:
                                        this.src = R.raw.mode0_table15_master0_slave10;
                                        break;
                                    case 11:
                                        this.src = R.raw.mode0_table15_master0_slave11;
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case 1:                                                                 //mode:0, table:15, master:1
                                switch(this.slave){
                                    case 0:
                                        this.src = R.raw.mode0_table15_master1_slave0;
                                        break;
                                    case 1:
                                        this.src = R.raw.mode0_table15_master1_slave1;
                                        break;
                                    case 2:
                                        this.src = R.raw.mode0_table15_master1_slave2;
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case 2:                                                                 //mode:0, table:15, master:2
                                switch(this.slave){
                                    case 0:
                                        this.src = R.raw.mode0_table15_master2_slave0;
                                        break;
                                    case 1:
                                        this.src = R.raw.mode0_table15_master2_slave1;
                                        break;
                                    case 2:
                                        this.src = R.raw.mode0_table15_master2_slave2;
                                        break;
                                    case 5:
                                        this.src = R.raw.mode0_table15_master2_slave5;
                                        break;
                                    case 10:
                                        this.src = R.raw.mode0_table15_master2_slave10;
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case 3:                                                                 //mode:0, table:15, master:3
                                switch(this.slave){
                                    case 0:
                                        this.src = R.raw.mode0_table15_master3_slave0;
                                        break;
                                    case 1:
                                        this.src = R.raw.mode0_table15_master3_slave1;
                                        break;
                                    case 2:
                                        this.src = R.raw.mode0_table15_master3_slave2;
                                        break;
                                    case 3:
                                        this.src = R.raw.mode0_table15_master3_slave3;
                                        break;
                                    case 4:
                                        this.src = R.raw.mode0_table15_master3_slave4;
                                        break;
                                    case 5:
                                        this.src = R.raw.mode0_table15_master3_slave5;
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case 4:                                                                 //mode:0, table:15, master:4
                                switch(this.slave){
                                    case 0:
                                        this.src = R.raw.mode0_table15_master4_slave0;
                                        break;
                                    case 1:
                                        this.src = R.raw.mode0_table15_master4_slave1;
                                        break;
                                    case 2:
                                        this.src = R.raw.mode0_table15_master4_slave2;
                                        break;
                                    case 3:
                                        this.src = R.raw.mode0_table15_master4_slave3;
                                        break;
                                    case 4:
                                        this.src = R.raw.mode0_table15_master4_slave4;
                                        break;
                                    case 5:
                                        this.src = R.raw.mode0_table15_master4_slave5;
                                        break;
                                    case 6:
                                        this.src = R.raw.mode0_table15_master4_slave6;
                                        break;
                                    case 7:
                                        this.src = R.raw.mode0_table15_master4_slave7;
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case 5:                                                                 //mode:0, table:15, master:5
                                switch(this.slave){
                                    case 0:
                                        this.src = R.raw.mode0_table15_master5_slave0;
                                        break;
                                    case 1:
                                        this.src = R.raw.mode0_table15_master5_slave1;
                                        break;
                                    case 2:
                                        this.src = R.raw.mode0_table15_master5_slave2;
                                        break;
                                    case 3:
                                        this.src = R.raw.mode0_table15_master5_slave3;
                                        break;
                                    case 4:
                                        this.src = R.raw.mode0_table15_master5_slave4;
                                        break;
                                    case 5:
                                        this.src = R.raw.mode0_table15_master5_slave5;
                                        break;
                                    case 6:
                                        this.src = R.raw.mode0_table15_master5_slave6;
                                        break;
                                    case 7:
                                        this.src = R.raw.mode0_table15_master5_slave7;
                                        break;
                                    case 8:
                                        this.src = R.raw.mode0_table15_master5_slave8;
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case 6:                                                                 //mode:0, table:15, master:6
                                switch(this.slave){
                                    case 0:
                                        this.src = R.raw.mode0_table15_master6_slave0;
                                        break;
                                    case 1:
                                        this.src = R.raw.mode0_table15_master6_slave1;
                                        break;
                                    case 2:
                                        this.src = R.raw.mode0_table15_master6_slave2;
                                        break;
                                    case 3:
                                        this.src = R.raw.mode0_table15_master6_slave3;
                                        break;
                                    case 4:
                                        this.src = R.raw.mode0_table15_master6_slave4;
                                        break;
                                    case 5:
                                        this.src = R.raw.mode0_table15_master6_slave5;
                                        break;
                                    case 6:
                                        this.src = R.raw.mode0_table15_master6_slave6;
                                        break;
                                    case 7:
                                        this.src = R.raw.mode0_table15_master6_slave7;
                                        break;
                                    case 8:
                                        this.src = R.raw.mode0_table15_master6_slave8;
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case 7:                                                                 //mode:0, table:15, master:7
                                switch(this.slave){
                                    case 0:
                                        this.src = R.raw.mode0_table15_master7_slave0;
                                        break;
                                    case 1:
                                        this.src = R.raw.mode0_table15_master7_slave1;
                                        break;
                                    case 2:
                                        this.src = R.raw.mode0_table15_master7_slave2;
                                        break;
                                    case 3:
                                        this.src = R.raw.mode0_table15_master7_slave3;
                                        break;
                                    case 4:
                                        this.src = R.raw.mode0_table15_master7_slave4;
                                        break;
                                    case 5:
                                        this.src = R.raw.mode0_table15_master7_slave5;
                                        break;
                                    case 6:
                                        this.src = R.raw.mode0_table15_master7_slave6;
                                        break;
                                    case 7:
                                        this.src = R.raw.mode0_table15_master7_slave7;
                                        break;
                                    case 8:
                                        this.src = R.raw.mode0_table15_master7_slave8;
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case 8:                                                                 //mode:0, table:15, master:8
                                switch(this.slave){
                                    case 0:
                                        this.src = R.raw.mode0_table15_master8_slave0;
                                        break;
                                    case 1:
                                        this.src = R.raw.mode0_table15_master8_slave1;
                                        break;
                                    case 2:
                                        this.src = R.raw.mode0_table15_master8_slave2;
                                        break;
                                    case 8:
                                        this.src = R.raw.mode0_table15_master8_slave8;
                                        break;
                                    case 9:
                                        this.src = R.raw.mode0_table15_master8_slave9;
                                        break;
                                    case 10:
                                        this.src = R.raw.mode0_table15_master8_slave10;
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case 9:                                                                 //mode:0, table:15, master:9
                                switch(this.slave){
                                    case 0:
                                        this.src = R.raw.mode0_table15_master9_slave0;
                                        break;
                                    case 1:
                                        this.src = R.raw.mode0_table15_master9_slave1;
                                        break;
                                    case 2:
                                        this.src = R.raw.mode0_table15_master9_slave2;
                                        break;
                                    case 3:
                                        this.src = R.raw.mode0_table15_master9_slave3;
                                        break;
                                    case 4:
                                        this.src = R.raw.mode0_table15_master9_slave4;
                                        break;
                                    case 5:
                                        this.src = R.raw.mode0_table15_master9_slave5;
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case 10:                                                                //mode:0, table:15, master:10
                                switch(this.slave){
                                    case 0:
                                        this.src = R.raw.mode0_table15_master10_slave0;
                                        break;
                                    case 1:
                                        this.src = R.raw.mode0_table15_master10_slave1;
                                        break;
                                    case 2:
                                        this.src = R.raw.mode0_table15_master10_slave2;
                                        break;
                                    case 3:
                                        this.src = R.raw.mode0_table15_master10_slave3;
                                        break;
                                    case 4:
                                        this.src = R.raw.mode0_table15_master10_slave4;
                                        break;
                                    case 5:
                                        this.src = R.raw.mode0_table15_master10_slave5;
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case 11:                                                                //mode:0, table:15, master:11
                                switch(this.slave){
                                    case 0:
                                        this.src = R.raw.mode0_table15_master11_slave0;
                                        break;
                                    case 1:
                                        this.src = R.raw.mode0_table15_master11_slave1;
                                        break;
                                    case 8:
                                        this.src = R.raw.mode0_table15_master11_slave8;
                                        break;
                                    case 9:
                                        this.src = R.raw.mode0_table15_master11_slave9;
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case 12:                                                                //mode:0, table:15, master:12
                                switch(this.slave){
                                    case 0:
                                        this.src = R.raw.mode0_table15_master12_slave0;
                                        break;
                                    case 1:
                                        this.src = R.raw.mode0_table15_master12_slave1;
                                        break;
                                    case 2:
                                        this.src = R.raw.mode0_table15_master12_slave2;
                                        break;
                                    case 3:
                                        this.src = R.raw.mode0_table15_master12_slave3;
                                        break;
                                    case 4:
                                        this.src = R.raw.mode0_table15_master12_slave4;
                                        break;
                                    case 5:
                                        this.src = R.raw.mode0_table15_master12_slave5;
                                        break;
                                    case 6:
                                        this.src = R.raw.mode0_table15_master12_slave6;
                                        break;
                                    case 7:
                                        this.src = R.raw.mode0_table15_master12_slave7;
                                        break;
                                    case 8:
                                        this.src = R.raw.mode0_table15_master12_slave8;
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case 13:                                                                //mode:0, table:15, master:13
                                switch(this.slave){
                                    case 0:
                                        this.src = R.raw.mode0_table15_master13_slave0;
                                        break;
                                    case 1:
                                        this.src = R.raw.mode0_table15_master13_slave1;
                                        break;
                                    case 2:
                                        this.src = R.raw.mode0_table15_master13_slave2;
                                        break;
                                    case 3:
                                        this.src = R.raw.mode0_table15_master13_slave3;
                                        break;
                                    case 4:
                                        this.src = R.raw.mode0_table15_master13_slave4;
                                        break;
                                    case 8:
                                        this.src = R.raw.mode0_table15_master13_slave8;
                                        break;
                                    case 9:
                                        this.src = R.raw.mode0_table15_master13_slave9;
                                        break;
                                    case 10:
                                        this.src = R.raw.mode0_table15_master13_slave10;
                                        break;
                                    case 11:
                                        this.src = R.raw.mode0_table15_master13_slave11;
                                        break;
                                    case 12:
                                        this.src = R.raw.mode0_table15_master13_slave12;
                                        break;
                                    case 13:
                                        this.src = R.raw.mode0_table15_master13_slave13;
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case 14:                                                                //mode:0, table:15, master:14
                                switch(this.slave){
                                    case 0:
                                        this.src = R.raw.mode0_table15_master14_slave0;
                                        break;
                                    case 1:
                                        this.src = R.raw.mode0_table15_master14_slave1;
                                        break;
                                    case 2:
                                        this.src = R.raw.mode0_table15_master14_slave2;
                                        break;
                                    case 3:
                                        this.src = R.raw.mode0_table15_master14_slave3;
                                        break;
                                    case 4:
                                        this.src = R.raw.mode0_table15_master14_slave4;
                                        break;
                                    case 6:
                                        this.src = R.raw.mode0_table15_master14_slave6;
                                        break;
                                    case 7:
                                        this.src = R.raw.mode0_table15_master14_slave7;
                                        break;
                                    case 8:
                                        this.src = R.raw.mode0_table15_master14_slave8;
                                        break;
                                    case 9:
                                        this.src = R.raw.mode0_table15_master14_slave9;
                                        break;
                                    case 10:
                                        this.src = R.raw.mode0_table15_master14_slave10;
                                        break;
                                    case 11:
                                        this.src = R.raw.mode0_table15_master14_slave11;
                                        break;
                                    case 12:
                                        this.src = R.raw.mode0_table15_master14_slave12;
                                        break;
                                    case 13:
                                        this.src = R.raw.mode0_table15_master14_slave13;
                                        break;
                                    case 14:
                                        this.src = R.raw.mode0_table15_master14_slave14;
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case 15:                                                                //mode:0, table:15, master:15
                                switch(this.slave){
                                    case 0:
                                        this.src = R.raw.mode0_table15_master15_slave0;
                                        break;
                                    case 1:
                                        this.src = R.raw.mode0_table15_master15_slave1;
                                        break;
                                    case 2:
                                        this.src = R.raw.mode0_table15_master15_slave2;
                                        break;
                                    case 3:
                                        this.src = R.raw.mode0_table15_master15_slave3;
                                        break;
                                    case 4:
                                        this.src = R.raw.mode0_table15_master15_slave4;
                                        break;
                                    case 5:
                                        this.src = R.raw.mode0_table15_master15_slave5;
                                        break;
                                    case 6:
                                        this.src = R.raw.mode0_table15_master15_slave6;
                                        break;
                                    case 7:
                                        this.src = R.raw.mode0_table15_master15_slave7;
                                        break;
                                    case 8:
                                        this.src = R.raw.mode0_table15_master15_slave8;
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            default:
                                break;
                        }
                        break;
                }
                break;
            case 1:
                switch(this.table){
                    case 15:
                        switch(this.master){
                            case 0:                                                                 //mode:1, table:15, master:0
                            case 8:                                                                 //mode:1, table:15, master:8
                                switch(this.slave){
                                    case 0:
                                        this.src = R.raw.mode1_table15_master0_slave0;
                                        break;
                                    case 1:
                                        this.src = R.raw.mode1_table15_master0_slave1;
                                        break;
                                    case 2:
                                        this.src = R.raw.mode1_table15_master0_slave2;
                                        break;
                                    case 3:
                                        this.src = R.raw.mode1_table15_master0_slave3;
                                        break;
                                    case 4:
                                        this.src = R.raw.mode1_table15_master0_slave4;
                                        break;
                                    case 5:
                                        this.src = R.raw.mode1_table15_master0_slave5;
                                        break;
                                    case 6:
                                        this.src = R.raw.mode1_table15_master0_slave6;
                                        break;
                                    case 7:
                                        this.src = R.raw.mode1_table15_master0_slave7;
                                        break;
                                    case 8:
                                        this.src = R.raw.mode1_table15_master0_slave8;
                                        break;
                                    case 9:
                                        this.src = R.raw.mode1_table15_master0_slave9;
                                        break;
                                    case 10:
                                        this.src = R.raw.mode1_table15_master0_slave10;
                                        break;
                                    case 11:
                                        this.src = R.raw.mode1_table15_master0_slave11;
                                        break;
                                    case 12:
                                        this.src = R.raw.mode1_table15_master0_slave12;
                                        break;
                                    case 13:
                                        this.src = R.raw.mode1_table15_master0_slave13;
                                        break;
                                    case 14:
                                        this.src = R.raw.mode1_table15_master0_slave14;
                                        break;
                                    case 15:
                                        this.src = R.raw.mode1_table15_master0_slave15;
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case 1:                                                                 //mode:1, table:15, master:1
                            case 9:                                                                 //mode:1, table:15, master:9
                                switch(this.slave){
                                    case 0:
                                        this.src = R.raw.mode1_table15_master1_slave0;
                                        break;
                                    case 1:
                                        this.src = R.raw.mode1_table15_master1_slave1;
                                        break;
                                    case 2:
                                        this.src = R.raw.mode1_table15_master1_slave2;
                                        break;
                                    case 3:
                                        this.src = R.raw.mode1_table15_master1_slave3;
                                        break;
                                    case 4:
                                        this.src = R.raw.mode1_table15_master1_slave4;
                                        break;
                                    case 5:
                                        this.src = R.raw.mode1_table15_master1_slave5;
                                        break;
                                    case 6:
                                        this.src = R.raw.mode1_table15_master1_slave6;
                                        break;
                                    case 7:
                                        this.src = R.raw.mode1_table15_master1_slave7;
                                        break;
                                    case 8:
                                        this.src = R.raw.mode1_table15_master1_slave8;
                                        break;
                                    case 10:
                                        this.src = R.raw.mode1_table15_master1_slave10;
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case 2:                                                                 //mode:1, table:15, master:2
                            case 10:                                                                //mode:1, table:15, master:10
                                switch(this.slave){
                                    case 0:
                                        this.src = R.raw.mode1_table15_master2_slave0;
                                        break;
                                    case 5:
                                        this.src = R.raw.mode1_table15_master2_slave5;
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case 3:                                                                 //mode:1, table:15, master:3
                            case 11:                                                                 //mode:1, table:15, master:11
                                switch(this.slave){
                                    case 0:
                                        this.src = R.raw.mode1_table15_master3_slave0;
                                        break;
                                    case 1:
                                        this.src = R.raw.mode1_table15_master3_slave1;
                                        break;
                                    case 2:
                                        this.src = R.raw.mode1_table15_master3_slave2;
                                        break;
                                    case 3:
                                        this.src = R.raw.mode1_table15_master3_slave3;
                                        break;
                                    case 4:
                                        this.src = R.raw.mode1_table15_master3_slave4;
                                        break;
                                    case 5:
                                        this.src = R.raw.mode1_table15_master3_slave5;
                                        break;
                                    case 6:
                                        this.src = R.raw.mode1_table15_master3_slave6;
                                        break;
                                    case 7:
                                        this.src = R.raw.mode1_table15_master3_slave7;
                                        break;
                                    case 8:
                                        this.src = R.raw.mode1_table15_master3_slave8;
                                        break;
                                    case 9:
                                        this.src = R.raw.mode1_table15_master3_slave9;
                                        break;
                                    case 10:
                                        this.src = R.raw.mode1_table15_master3_slave10;
                                        break;
                                    case 11:
                                        this.src = R.raw.mode1_table15_master3_slave11;
                                        break;
                                    case 12:
                                        this.src = R.raw.mode1_table15_master3_slave12;
                                        break;
                                    case 13:
                                        this.src = R.raw.mode1_table15_master3_slave13;
                                        break;
                                    case 14:
                                        this.src = R.raw.mode1_table15_master3_slave14;
                                        break;
                                    case 15:
                                        this.src = R.raw.mode1_table15_master3_slave15;
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case 4:                                                                 //mode:1, table:15, master:4
                            case 12:                                                                //mode:1, table:15, master:12
                                switch(this.slave){
                                    case 0:
                                        this.src = R.raw.mode1_table15_master4_slave0;
                                        break;
                                    case 1:
                                        this.src = R.raw.mode1_table15_master4_slave1;
                                        break;
                                    case 2:
                                        this.src = R.raw.mode1_table15_master4_slave2;
                                        break;
                                    case 3:
                                        this.src = R.raw.mode1_table15_master4_slave3;
                                        break;
                                    case 8:
                                        this.src = R.raw.mode1_table15_master4_slave8;
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case 5:                                                                 //mode:1, table:15, master:5
                            case 13:                                                                //mode:1, table:15, master:13
                                switch(this.slave){
                                    case 0:
                                        this.src = R.raw.mode1_table15_master5_slave0;
                                        break;
                                    case 1:
                                        this.src = R.raw.mode1_table15_master5_slave1;
                                        break;
                                    case 2:
                                        this.src = R.raw.mode1_table15_master5_slave2;
                                        break;
                                    case 3:
                                        this.src = R.raw.mode1_table15_master5_slave3;
                                        break;
                                    case 4:
                                        this.src = R.raw.mode1_table15_master5_slave4;
                                        break;
                                    case 5:
                                        this.src = R.raw.mode1_table15_master5_slave5;
                                        break;
                                    case 6:
                                        this.src = R.raw.mode1_table15_master5_slave6;
                                        break;
                                    case 7:
                                        this.src = R.raw.mode1_table15_master5_slave7;
                                        break;
                                    case 8:
                                        this.src = R.raw.mode1_table15_master5_slave8;
                                        break;
                                    case 9:
                                        this.src = R.raw.mode1_table15_master5_slave9;
                                        break;
                                    case 10:
                                        this.src = R.raw.mode1_table15_master5_slave10;
                                        break;
                                    case 11:
                                        this.src = R.raw.mode1_table15_master5_slave11;
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case 6:                                                                 //mode:1, table:15, master:6
                            case 14:                                                                //mode:1, table:15, master:14
                                switch(this.slave){
                                    case 0:
                                        this.src = R.raw.mode1_table15_master6_slave0;
                                        break;
                                    case 1:
                                        this.src = R.raw.mode1_table15_master6_slave1;
                                        break;
                                    case 2:
                                        this.src = R.raw.mode1_table15_master6_slave2;
                                        break;
                                    case 8:
                                        this.src = R.raw.mode1_table15_master6_slave8;
                                        break;
                                    case 9:
                                        this.src = R.raw.mode1_table15_master6_slave9;
                                        break;
                                    case 10:
                                        this.src = R.raw.mode1_table15_master6_slave10;
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case 7:                                                                 //mode:1, table:15, master:7
                            case 15:                                                                //mode:1, table:15, master:15
                                switch(this.slave){
                                    case 0:
                                        this.src = R.raw.mode1_table15_master7_slave0;
                                        break;
                                    case 1:
                                        this.src = R.raw.mode1_table15_master7_slave1;
                                        break;
                                    case 2:
                                        this.src = R.raw.mode1_table15_master7_slave2;
                                        break;
                                    case 3:
                                        this.src = R.raw.mode1_table15_master7_slave3;
                                        break;
                                    case 4:
                                        this.src = R.raw.mode1_table15_master7_slave4;
                                        break;
                                    case 5:
                                        this.src = R.raw.mode1_table15_master7_slave5;
                                        break;
                                    case 8:
                                        this.src = R.raw.mode1_table15_master7_slave8;
                                        break;
                                    case 9:
                                        this.src = R.raw.mode1_table15_master7_slave9;
                                        break;
                                    case 10:
                                        this.src = R.raw.mode1_table15_master7_slave10;
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            default:
                                break;
                        }
                        break;
                    case 0:
                        switch(this.master){
                            case 0:                                                                 //mode:1, table:0, master:0
                                switch(this.slave){
                                    case 0:
                                        this.src = R.raw.mode1_table0_master0_slave0;
                                        break;
                                    case 1:
                                        this.src = R.raw.mode1_table0_master0_slave1;
                                        break;
                                    case 2:
                                        this.src = R.raw.mode1_table0_master0_slave2;
                                        break;
                                    case 3:
                                        this.src = R.raw.mode1_table0_master0_slave3;
                                        break;
                                    case 4:
                                        this.src = R.raw.mode1_table0_master0_slave4;
                                        break;
                                    case 5:
                                        this.src = R.raw.mode1_table0_master0_slave5;
                                        break;
                                    case 6:
                                        this.src = R.raw.mode1_table0_master0_slave6;
                                        break;
                                    case 7:
                                        this.src = R.raw.mode1_table0_master0_slave7;
                                        break;
                                    case 8:
                                        this.src = R.raw.mode1_table0_master0_slave8;
                                        break;
                                    case 9:
                                        this.src = R.raw.mode1_table0_master0_slave9;
                                        break;
                                    case 10:
                                        this.src = R.raw.mode1_table0_master0_slave10;
                                        break;
                                    case 11:
                                        this.src = R.raw.mode1_table0_master0_slave11;
                                        break;
                                    case 12:
                                        this.src = R.raw.mode1_table0_master0_slave12;
                                        break;
                                    case 13:
                                        this.src = R.raw.mode1_table0_master0_slave13;
                                        break;
                                    case 14:
                                        this.src = R.raw.mode1_table0_master0_slave14;
                                        break;
                                    case 15:
                                        this.src = R.raw.mode1_table0_master0_slave15;
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case 1:                                                                 //mode:1, table:0, master:1
                                switch(this.slave){
                                    case 0:
                                        this.src = R.raw.mode1_table0_master1_slave0;
                                        break;
                                    case 1:
                                        this.src = R.raw.mode1_table0_master1_slave1;
                                        break;
                                    case 2:
                                        this.src = R.raw.mode1_table0_master1_slave2;
                                        break;
                                    case 3:
                                        this.src = R.raw.mode1_table0_master1_slave3;
                                        break;
                                    case 4:
                                        this.src = R.raw.mode1_table0_master1_slave4;
                                        break;
                                    case 5:
                                        this.src = R.raw.mode1_table0_master1_slave5;
                                        break;
                                    case 6:
                                        this.src = R.raw.mode1_table0_master1_slave6;
                                        break;
                                    case 7:
                                        this.src = R.raw.mode1_table0_master1_slave7;
                                        break;
                                    case 8:
                                        this.src = R.raw.mode1_table0_master1_slave8;
                                        break;
                                    case 9:
                                        this.src = R.raw.mode1_table0_master1_slave9;
                                        break;
                                    case 10:
                                        this.src = R.raw.mode1_table0_master1_slave10;
                                        break;
                                    case 11:
                                        this.src = R.raw.mode1_table0_master1_slave11;
                                        break;
                                    case 12:
                                        this.src = R.raw.mode1_table0_master1_slave12;
                                        break;
                                    case 13:
                                        this.src = R.raw.mode1_table0_master1_slave13;
                                        break;
                                    case 14:
                                        this.src = R.raw.mode1_table0_master1_slave14;
                                        break;
                                    case 15:
                                        this.src = R.raw.mode1_table0_master1_slave15;
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case 2:                                                                 //mode:1, table:0, master:2
                                switch(this.slave){
                                    case 0:
                                        this.src = R.raw.mode1_table0_master2_slave0;
                                        break;
                                    case 1:
                                        this.src = R.raw.mode1_table0_master2_slave1;
                                        break;
                                    case 2:
                                        this.src = R.raw.mode1_table0_master2_slave2;
                                        break;
                                    case 3:
                                        this.src = R.raw.mode1_table0_master2_slave3;
                                        break;
                                    case 4:
                                        this.src = R.raw.mode1_table0_master2_slave4;
                                        break;
                                    case 5:
                                        this.src = R.raw.mode1_table0_master2_slave5;
                                        break;
                                    case 6:
                                        this.src = R.raw.mode1_table0_master2_slave6;
                                        break;
                                    case 7:
                                        this.src = R.raw.mode1_table0_master2_slave7;
                                        break;
                                    case 8:
                                        this.src = R.raw.mode1_table0_master2_slave8;
                                        break;
                                    case 9:
                                        this.src = R.raw.mode1_table0_master2_slave9;
                                        break;
                                    case 10:
                                        this.src = R.raw.mode1_table0_master2_slave10;
                                        break;
                                    case 11:
                                        this.src = R.raw.mode1_table0_master2_slave11;
                                        break;
                                    case 12:
                                        this.src = R.raw.mode1_table0_master2_slave12;
                                        break;
                                    case 13:
                                        this.src = R.raw.mode1_table0_master2_slave13;
                                        break;
                                    case 14:
                                        this.src = R.raw.mode1_table0_master2_slave14;
                                        break;
                                    case 15:
                                        this.src = R.raw.mode1_table0_master2_slave15;
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case 3:                                                                 //mode:1, table:0, master:3
                                switch(this.slave){
                                    case 0:
                                        this.src = R.raw.mode1_table0_master3_slave0;
                                        break;
                                    case 1:
                                        this.src = R.raw.mode1_table0_master3_slave1;
                                        break;
                                    case 2:
                                        this.src = R.raw.mode1_table0_master3_slave2;
                                        break;
                                    case 3:
                                        this.src = R.raw.mode1_table0_master3_slave3;
                                        break;
                                    case 4:
                                        this.src = R.raw.mode1_table0_master3_slave4;
                                        break;
                                    case 5:
                                        this.src = R.raw.mode1_table0_master3_slave5;
                                        break;
                                    case 6:
                                        this.src = R.raw.mode1_table0_master3_slave6;
                                        break;
                                    case 7:
                                        this.src = R.raw.mode1_table0_master3_slave7;
                                        break;
                                    case 8:
                                        this.src = R.raw.mode1_table0_master3_slave8;
                                        break;
                                    case 9:
                                        this.src = R.raw.mode1_table0_master3_slave9;
                                        break;
                                    case 10:
                                        this.src = R.raw.mode1_table0_master3_slave10;
                                        break;
                                    case 11:
                                        this.src = R.raw.mode1_table0_master3_slave11;
                                        break;
                                    case 12:
                                        this.src = R.raw.mode1_table0_master3_slave12;
                                        break;
                                    case 13:
                                        this.src = R.raw.mode1_table0_master3_slave13;
                                        break;
                                    case 14:
                                        this.src = R.raw.mode1_table0_master3_slave14;
                                        break;
                                    case 15:
                                        this.src = R.raw.mode1_table0_master3_slave15;
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case 4:                                                                 //mode:1, table:0, master:4
                                switch(this.slave){
                                    case 0:
                                        this.src = R.raw.mode1_table0_master4_slave0;
                                        break;
                                    case 1:
                                        this.src = R.raw.mode1_table0_master4_slave1;
                                        break;
                                    case 2:
                                        this.src = R.raw.mode1_table0_master4_slave2;
                                        break;
                                    case 3:
                                        this.src = R.raw.mode1_table0_master4_slave3;
                                        break;
                                    case 4:
                                        this.src = R.raw.mode1_table0_master4_slave4;
                                        break;
                                    case 5:
                                        this.src = R.raw.mode1_table0_master4_slave5;
                                        break;
                                    case 6:
                                        this.src = R.raw.mode1_table0_master4_slave6;
                                        break;
                                    case 7:
                                        this.src = R.raw.mode1_table0_master4_slave7;
                                        break;
                                    case 8:
                                        this.src = R.raw.mode1_table0_master4_slave8;
                                        break;
                                    case 9:
                                        this.src = R.raw.mode1_table0_master4_slave9;
                                        break;
                                    case 10:
                                        this.src = R.raw.mode1_table0_master4_slave10;
                                        break;
                                    case 11:
                                        this.src = R.raw.mode1_table0_master4_slave11;
                                        break;
                                    case 12:
                                        this.src = R.raw.mode1_table0_master4_slave12;
                                        break;
                                    case 13:
                                        this.src = R.raw.mode1_table0_master4_slave13;
                                        break;
                                    case 14:
                                        this.src = R.raw.mode1_table0_master4_slave14;
                                        break;
                                    case 15:
                                        this.src = R.raw.mode1_table0_master4_slave15;
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case 5:                                                                 //mode:1, table:0, master:5
                                switch(this.slave){
                                    case 0:
                                        this.src = R.raw.mode1_table0_master5_slave0;
                                        break;
                                    case 1:
                                        this.src = R.raw.mode1_table0_master5_slave1;
                                        break;
                                    case 2:
                                        this.src = R.raw.mode1_table0_master5_slave2;
                                        break;
                                    case 3:
                                        this.src = R.raw.mode1_table0_master5_slave3;
                                        break;
                                    case 4:
                                        this.src = R.raw.mode1_table0_master5_slave4;
                                        break;
                                    case 5:
                                        this.src = R.raw.mode1_table0_master5_slave5;
                                        break;
                                    case 6:
                                        this.src = R.raw.mode1_table0_master5_slave6;
                                        break;
                                    case 7:
                                        this.src = R.raw.mode1_table0_master5_slave7;
                                        break;
                                    case 8:
                                        this.src = R.raw.mode1_table0_master5_slave8;
                                        break;
                                    case 9:
                                        this.src = R.raw.mode1_table0_master5_slave9;
                                        break;
                                    case 10:
                                        this.src = R.raw.mode1_table0_master5_slave10;
                                        break;
                                    case 11:
                                        this.src = R.raw.mode1_table0_master5_slave11;
                                        break;
                                    case 12:
                                        this.src = R.raw.mode1_table0_master5_slave12;
                                        break;
                                    case 13:
                                        this.src = R.raw.mode1_table0_master5_slave13;
                                        break;
                                    case 14:
                                        this.src = R.raw.mode1_table0_master5_slave14;
                                        break;
                                    case 15:
                                        this.src = R.raw.mode1_table0_master5_slave15;
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case 6:                                                                 //mode:1, table:0, master:6
                                switch(this.slave){
                                    case 0:
                                        this.src = R.raw.mode1_table0_master6_slave0;
                                        break;
                                    case 1:
                                        this.src = R.raw.mode1_table0_master6_slave1;
                                        break;
                                    case 2:
                                        this.src = R.raw.mode1_table0_master6_slave2;
                                        break;
                                    case 3:
                                        this.src = R.raw.mode1_table0_master6_slave3;
                                        break;
                                    case 4:
                                        this.src = R.raw.mode1_table0_master6_slave4;
                                        break;
                                    case 5:
                                        this.src = R.raw.mode1_table0_master6_slave5;
                                        break;
                                    case 6:
                                        this.src = R.raw.mode1_table0_master6_slave6;
                                        break;
                                    case 7:
                                        this.src = R.raw.mode1_table0_master6_slave7;
                                        break;
                                    case 8:
                                        this.src = R.raw.mode1_table0_master6_slave8;
                                        break;
                                    case 9:
                                        this.src = R.raw.mode1_table0_master6_slave9;
                                        break;
                                    case 10:
                                        this.src = R.raw.mode1_table0_master6_slave10;
                                        break;
                                    case 11:
                                        this.src = R.raw.mode1_table0_master6_slave11;
                                        break;
                                    case 12:
                                        this.src = R.raw.mode1_table0_master6_slave12;
                                        break;
                                    case 13:
                                        this.src = R.raw.mode1_table0_master6_slave13;
                                        break;
                                    case 14:
                                        this.src = R.raw.mode1_table0_master6_slave14;
                                        break;
                                    case 15:
                                        this.src = R.raw.mode1_table0_master6_slave15;
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case 7:                                                                 //mode:1, table:0, master:7
                                switch(this.slave){
                                    case 0:
                                        this.src = R.raw.mode1_table0_master7_slave0;
                                        break;
                                    case 1:
                                        this.src = R.raw.mode1_table0_master7_slave1;
                                        break;
                                    case 2:
                                        this.src = R.raw.mode1_table0_master7_slave2;
                                        break;
                                    case 3:
                                        this.src = R.raw.mode1_table0_master7_slave3;
                                        break;
                                    case 4:
                                        this.src = R.raw.mode1_table0_master7_slave4;
                                        break;
                                    case 5:
                                        this.src = R.raw.mode1_table0_master7_slave5;
                                        break;
                                    case 6:
                                        this.src = R.raw.mode1_table0_master7_slave6;
                                        break;
                                    case 7:
                                        this.src = R.raw.mode1_table0_master7_slave7;
                                        break;
                                    case 8:
                                        this.src = R.raw.mode1_table0_master7_slave8;
                                        break;
                                    case 9:
                                        this.src = R.raw.mode1_table0_master7_slave9;
                                        break;
                                    case 10:
                                        this.src = R.raw.mode1_table0_master7_slave10;
                                        break;
                                    case 11:
                                        this.src = R.raw.mode1_table0_master7_slave11;
                                        break;
                                    case 12:
                                        this.src = R.raw.mode1_table0_master7_slave12;
                                        break;
                                    case 13:
                                        this.src = R.raw.mode1_table0_master7_slave13;
                                        break;
                                    case 14:
                                        this.src = R.raw.mode1_table0_master7_slave14;
                                        break;
                                    case 15:
                                        this.src = R.raw.mode1_table0_master7_slave15;
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case 8:                                                                 //mode:1, table:0, master:8
                                switch(this.slave){
                                    case 0:
                                        this.src = R.raw.mode1_table0_master8_slave0;
                                        break;
                                    case 1:
                                        this.src = R.raw.mode1_table0_master8_slave1;
                                        break;
                                    case 2:
                                        this.src = R.raw.mode1_table0_master8_slave2;
                                        break;
                                    case 3:
                                        this.src = R.raw.mode1_table0_master8_slave3;
                                        break;
                                    case 4:
                                        this.src = R.raw.mode1_table0_master8_slave4;
                                        break;
                                    case 5:
                                        this.src = R.raw.mode1_table0_master8_slave5;
                                        break;
                                    case 6:
                                        this.src = R.raw.mode1_table0_master8_slave6;
                                        break;
                                    case 8:
                                        this.src = R.raw.mode1_table0_master8_slave8;
                                        break;
                                    case 9:
                                        this.src = R.raw.mode1_table0_master8_slave9;
                                        break;
                                    case 10:
                                        this.src = R.raw.mode1_table0_master8_slave10;
                                        break;
                                    case 11:
                                        this.src = R.raw.mode1_table0_master8_slave11;
                                        break;
                                    case 12:
                                        this.src = R.raw.mode1_table0_master8_slave12;
                                        break;
                                    case 13:
                                        this.src = R.raw.mode1_table0_master8_slave13;
                                        break;
                                    case 14:
                                        this.src = R.raw.mode1_table0_master8_slave14;
                                        break;
                                    case 15:
                                        this.src = R.raw.mode1_table0_master8_slave15;
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case 9:                                                                 //mode:1, table:0, master:9
                                switch(this.slave){
                                    case 0:
                                        this.src = R.raw.mode1_table0_master9_slave0;
                                        break;
                                    case 1:
                                        this.src = R.raw.mode1_table0_master9_slave1;
                                        break;
                                    case 2:
                                        this.src = R.raw.mode1_table0_master9_slave2;
                                        break;
                                    case 3:
                                        this.src = R.raw.mode1_table0_master9_slave3;
                                        break;
                                    case 4:
                                        this.src = R.raw.mode1_table0_master9_slave4;
                                        break;
                                    case 5:
                                        this.src = R.raw.mode1_table0_master9_slave5;
                                        break;
                                    case 6:
                                        this.src = R.raw.mode1_table0_master9_slave6;
                                        break;
                                    case 7:
                                        this.src = R.raw.mode1_table0_master9_slave7;
                                        break;
                                    case 8:
                                        this.src = R.raw.mode1_table0_master9_slave8;
                                        break;
                                    case 9:
                                        this.src = R.raw.mode1_table0_master9_slave9;
                                        break;
                                    case 10:
                                        this.src = R.raw.mode1_table0_master9_slave10;
                                        break;
                                    case 11:
                                        this.src = R.raw.mode1_table0_master9_slave11;
                                        break;
                                    case 12:
                                        this.src = R.raw.mode1_table0_master9_slave12;
                                        break;
                                    case 13:
                                        this.src = R.raw.mode1_table0_master9_slave13;
                                        break;
                                    case 14:
                                        this.src = R.raw.mode1_table0_master9_slave14;
                                        break;
                                    case 15:
                                        this.src = R.raw.mode1_table0_master9_slave15;
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case 10:                                                                //mode:1, table:0, master:10
                                switch(this.slave){
                                    case 0:
                                        this.src = R.raw.mode1_table0_master10_slave0;
                                        break;
                                    case 1:
                                        this.src = R.raw.mode1_table0_master10_slave1;
                                        break;
                                    case 2:
                                        this.src = R.raw.mode1_table0_master10_slave2;
                                        break;
                                    case 3:
                                        this.src = R.raw.mode1_table0_master10_slave3;
                                        break;
                                    case 4:
                                        this.src = R.raw.mode1_table0_master10_slave4;
                                        break;
                                    case 5:
                                        this.src = R.raw.mode1_table0_master10_slave5;
                                        break;
                                    case 6:
                                        this.src = R.raw.mode1_table0_master10_slave6;
                                        break;
                                    case 7:
                                        this.src = R.raw.mode1_table0_master10_slave7;
                                        break;
                                    case 8:
                                        this.src = R.raw.mode1_table0_master10_slave8;
                                        break;
                                    case 9:
                                        this.src = R.raw.mode1_table0_master10_slave9;
                                        break;
                                    case 10:
                                        this.src = R.raw.mode1_table0_master10_slave10;
                                        break;
                                    case 11:
                                        this.src = R.raw.mode1_table0_master10_slave11;
                                        break;
                                    case 12:
                                        this.src = R.raw.mode1_table0_master10_slave12;
                                        break;
                                    case 13:
                                        this.src = R.raw.mode1_table0_master10_slave13;
                                        break;
                                    case 14:
                                        this.src = R.raw.mode1_table0_master10_slave14;
                                        break;
                                    case 15:
                                        this.src = R.raw.mode1_table0_master10_slave15;
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case 11:                                                                //mode:1, table:0, master:11
                                switch(this.slave){
                                    case 0:
                                        this.src = R.raw.mode1_table0_master11_slave0;
                                        break;
                                    case 1:
                                        this.src = R.raw.mode1_table0_master11_slave1;
                                        break;
                                    case 2:
                                        this.src = R.raw.mode1_table0_master11_slave2;
                                        break;
                                    case 3:
                                        this.src = R.raw.mode1_table0_master11_slave3;
                                        break;
                                    case 4:
                                        this.src = R.raw.mode1_table0_master11_slave4;
                                        break;
                                    case 5:
                                        this.src = R.raw.mode1_table0_master11_slave5;
                                        break;
                                    case 6:
                                        this.src = R.raw.mode1_table0_master11_slave6;
                                        break;
                                    case 7:
                                        this.src = R.raw.mode1_table0_master11_slave7;
                                        break;
                                    case 8:
                                        this.src = R.raw.mode1_table0_master11_slave8;
                                        break;
                                    case 9:
                                        this.src = R.raw.mode1_table0_master11_slave9;
                                        break;
                                    case 10:
                                        this.src = R.raw.mode1_table0_master11_slave10;
                                        break;
                                    case 11:
                                        this.src = R.raw.mode1_table0_master11_slave11;
                                        break;
                                    case 12:
                                        this.src = R.raw.mode1_table0_master11_slave12;
                                        break;
                                    case 13:
                                        this.src = R.raw.mode1_table0_master11_slave13;
                                        break;
                                    case 14:
                                        this.src = R.raw.mode1_table0_master11_slave14;
                                        break;
                                    case 15:
                                        this.src = R.raw.mode1_table0_master11_slave15;
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case 12:                                                                //mode:1, table:0, master:12
                                switch(this.slave){
                                    case 0:
                                        this.src = R.raw.mode1_table0_master12_slave0;
                                        break;
                                    case 1:
                                        this.src = R.raw.mode1_table0_master12_slave1;
                                        break;
                                    case 2:
                                        this.src = R.raw.mode1_table0_master12_slave2;
                                        break;
                                    case 3:
                                        this.src = R.raw.mode1_table0_master12_slave3;
                                        break;
                                    case 4:
                                        this.src = R.raw.mode1_table0_master12_slave4;
                                        break;
                                    case 5:
                                        this.src = R.raw.mode1_table0_master12_slave5;
                                        break;
                                    case 6:
                                        this.src = R.raw.mode1_table0_master12_slave6;
                                        break;
                                    case 7:
                                        this.src = R.raw.mode1_table0_master12_slave7;
                                        break;
                                    case 8:
                                        this.src = R.raw.mode1_table0_master12_slave8;
                                        break;
                                    case 9:
                                        this.src = R.raw.mode1_table0_master12_slave9;
                                        break;
                                    case 10:
                                        this.src = R.raw.mode1_table0_master12_slave10;
                                        break;
                                    case 11:
                                        this.src = R.raw.mode1_table0_master12_slave11;
                                        break;
                                    case 12:
                                        this.src = R.raw.mode1_table0_master12_slave12;
                                        break;
                                    case 13:
                                        this.src = R.raw.mode1_table0_master12_slave13;
                                        break;
                                    case 14:
                                        this.src = R.raw.mode1_table0_master12_slave14;
                                        break;
                                    case 15:
                                        this.src = R.raw.mode1_table0_master12_slave15;
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case 13:                                                                //mode:1, table:0, master:13
                                switch(this.slave){
                                    case 0:
                                        this.src = R.raw.mode1_table0_master13_slave0;
                                        break;
                                    case 1:
                                        this.src = R.raw.mode1_table0_master13_slave1;
                                        break;
                                    case 2:
                                        this.src = R.raw.mode1_table0_master13_slave2;
                                        break;
                                    case 3:
                                        this.src = R.raw.mode1_table0_master13_slave3;
                                        break;
                                    case 4:
                                        this.src = R.raw.mode1_table0_master13_slave4;
                                        break;
                                    case 5:
                                        this.src = R.raw.mode1_table0_master13_slave5;
                                        break;
                                    case 6:
                                        this.src = R.raw.mode1_table0_master13_slave6;
                                        break;
                                    case 7:
                                        this.src = R.raw.mode1_table0_master13_slave7;
                                        break;
                                    case 8:
                                        this.src = R.raw.mode1_table0_master13_slave8;
                                        break;
                                    case 9:
                                        this.src = R.raw.mode1_table0_master13_slave9;
                                        break;
                                    case 10:
                                        this.src = R.raw.mode1_table0_master13_slave10;
                                        break;
                                    case 11:
                                        this.src = R.raw.mode1_table0_master13_slave11;
                                        break;
                                    case 12:
                                        this.src = R.raw.mode1_table0_master13_slave12;
                                        break;
                                    case 13:
                                        this.src = R.raw.mode1_table0_master13_slave13;
                                        break;
                                    case 14:
                                        this.src = R.raw.mode1_table0_master13_slave14;
                                        break;
                                    case 15:
                                        this.src = R.raw.mode1_table0_master13_slave15;
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case 14:                                                                //mode:1, table:0, master:14
                                switch(this.slave){
                                    case 0:
                                        this.src = R.raw.mode1_table0_master14_slave0;
                                        break;
                                    case 1:
                                        this.src = R.raw.mode1_table0_master14_slave1;
                                        break;
                                    case 2:
                                        this.src = R.raw.mode1_table0_master14_slave2;
                                        break;
                                    case 3:
                                        this.src = R.raw.mode1_table0_master14_slave3;
                                        break;
                                    case 4:
                                        this.src = R.raw.mode1_table0_master14_slave4;
                                        break;
                                    case 5:
                                        this.src = R.raw.mode1_table0_master14_slave5;
                                        break;
                                    case 6:
                                        this.src = R.raw.mode1_table0_master14_slave6;
                                        break;
                                    case 7:
                                        this.src = R.raw.mode1_table0_master14_slave7;
                                        break;
                                    case 8:
                                        this.src = R.raw.mode1_table0_master14_slave8;
                                        break;
                                    case 9:
                                        this.src = R.raw.mode1_table0_master14_slave9;
                                        break;
                                    case 10:
                                        this.src = R.raw.mode1_table0_master14_slave10;
                                        break;
                                    case 11:
                                        this.src = R.raw.mode1_table0_master14_slave11;
                                        break;
                                    case 12:
                                        this.src = R.raw.mode1_table0_master14_slave12;
                                        break;
                                    case 13:
                                        this.src = R.raw.mode1_table0_master14_slave13;
                                        break;
                                    case 14:
                                        this.src = R.raw.mode1_table0_master14_slave14;
                                        break;
                                    case 15:
                                        this.src = R.raw.mode1_table0_master14_slave15;
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case 15:                                                                //mode:1, table:0, master:15
                                switch(this.slave){
                                    case 0:
                                        this.src = R.raw.mode1_table0_master15_slave0;
                                        break;
                                    case 1:
                                        this.src = R.raw.mode1_table0_master15_slave1;
                                        break;
                                    case 2:
                                        this.src = R.raw.mode1_table0_master15_slave2;
                                        break;
                                    case 3:
                                        this.src = R.raw.mode1_table0_master15_slave3;
                                        break;
                                    case 4:
                                        this.src = R.raw.mode1_table0_master15_slave4;
                                        break;
                                    case 5:
                                        this.src = R.raw.mode1_table0_master15_slave5;
                                        break;
                                    case 6:
                                        this.src = R.raw.mode1_table0_master15_slave6;
                                        break;
                                    case 7:
                                        this.src = R.raw.mode1_table0_master15_slave7;
                                        break;
                                    case 8:
                                        this.src = R.raw.mode1_table0_master15_slave8;
                                        break;
                                    case 9:
                                        this.src = R.raw.mode1_table0_master15_slave9;
                                        break;
                                    case 10:
                                        this.src = R.raw.mode1_table0_master15_slave10;
                                        break;
                                    case 11:
                                        this.src = R.raw.mode1_table0_master15_slave11;
                                        break;
                                    case 12:
                                        this.src = R.raw.mode1_table0_master15_slave12;
                                        break;
                                    case 13:
                                        this.src = R.raw.mode1_table0_master15_slave13;
                                        break;
                                    case 14:
                                        this.src = R.raw.mode1_table0_master15_slave14;
                                        break;
                                    case 15:
                                        this.src = R.raw.mode1_table0_master15_slave15;
                                        break;
                                    default:
                                        break;
                                }
                                break;
                        }
                        break;
                    case 1:
                        switch(this.master){
                            case 0:                                                                 //mode:1, table:1, master:0
                                switch(this.slave){
                                    case 0:
                                        this.src = R.raw.mode1_table1_master0_slave0;
                                        break;
                                    case 1:
                                        this.src = R.raw.mode1_table1_master0_slave1;
                                        break;
                                    case 2:
                                        this.src = R.raw.mode1_table1_master0_slave2;
                                        break;
                                    case 3:
                                        this.src = R.raw.mode1_table1_master0_slave3;
                                        break;
                                    case 4:
                                        this.src = R.raw.mode1_table1_master0_slave4;
                                        break;
                                    case 5:
                                        this.src = R.raw.mode1_table1_master0_slave5;
                                        break;
                                    case 6:
                                        this.src = R.raw.mode1_table1_master0_slave6;
                                        break;
                                    case 7:
                                        this.src = R.raw.mode1_table1_master0_slave7;
                                        break;
                                    case 8:
                                        this.src = R.raw.mode1_table1_master0_slave8;
                                        break;
                                    case 9:
                                        this.src = R.raw.mode1_table1_master0_slave9;
                                        break;
                                    case 10:
                                        this.src = R.raw.mode1_table1_master0_slave10;
                                        break;
                                    case 11:
                                        this.src = R.raw.mode1_table1_master0_slave11;
                                        break;
                                    case 12:
                                        this.src = R.raw.mode1_table1_master0_slave12;
                                        break;
                                    case 13:
                                        this.src = R.raw.mode1_table1_master0_slave13;
                                        break;
                                    case 14:
                                        this.src = R.raw.mode1_table1_master0_slave14;
                                        break;
                                    case 15:
                                        this.src = R.raw.mode1_table1_master0_slave15;
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case 1:                                                                 //mode:1, table:1, master:1
                                switch(this.slave){
                                    case 0:
                                        this.src = R.raw.mode1_table1_master1_slave0;
                                        break;
                                    case 1:
                                        this.src = R.raw.mode1_table1_master1_slave1;
                                        break;
                                    case 2:
                                        this.src = R.raw.mode1_table1_master1_slave2;
                                        break;
                                    case 3:
                                        this.src = R.raw.mode1_table1_master1_slave3;
                                        break;
                                    case 4:
                                        this.src = R.raw.mode1_table1_master1_slave4;
                                        break;
                                    case 5:
                                        this.src = R.raw.mode1_table1_master1_slave5;
                                        break;
                                    case 6:
                                        this.src = R.raw.mode1_table1_master1_slave6;
                                        break;
                                    case 7:
                                        this.src = R.raw.mode1_table1_master1_slave7;
                                        break;
                                    case 8:
                                        this.src = R.raw.mode1_table1_master1_slave8;
                                        break;
                                    case 9:
                                        this.src = R.raw.mode1_table1_master1_slave9;
                                        break;
                                    case 10:
                                        this.src = R.raw.mode1_table1_master1_slave10;
                                        break;
                                    case 11:
                                        this.src = R.raw.mode1_table1_master1_slave11;
                                        break;
                                    case 12:
                                        this.src = R.raw.mode1_table1_master1_slave12;
                                        break;
                                    case 13:
                                        this.src = R.raw.mode1_table1_master1_slave13;
                                        break;
                                    case 14:
                                        this.src = R.raw.mode1_table1_master1_slave14;
                                        break;
                                    case 15:
                                        this.src = R.raw.mode1_table1_master1_slave15;
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case 2:                                                                 //mode:1, table:1, master:2
                                switch(this.slave){
                                    case 0:
                                        this.src = R.raw.mode1_table1_master2_slave0;
                                        break;
                                    case 1:
                                        this.src = R.raw.mode1_table1_master2_slave1;
                                        break;
                                    case 2:
                                        this.src = R.raw.mode1_table1_master2_slave2;
                                        break;
                                    case 3:
                                        this.src = R.raw.mode1_table1_master2_slave3;
                                        break;
                                    case 4:
                                        this.src = R.raw.mode1_table1_master2_slave4;
                                        break;
                                    case 5:
                                        this.src = R.raw.mode1_table1_master2_slave5;
                                        break;
                                    case 6:
                                        this.src = R.raw.mode1_table1_master2_slave6;
                                        break;
                                    case 7:
                                        this.src = R.raw.mode1_table1_master2_slave7;
                                        break;
                                    case 8:
                                        this.src = R.raw.mode1_table1_master2_slave8;
                                        break;
                                    case 9:
                                        this.src = R.raw.mode1_table1_master2_slave9;
                                        break;
                                    case 10:
                                        this.src = R.raw.mode1_table1_master2_slave10;
                                        break;
                                    case 11:
                                        this.src = R.raw.mode1_table1_master2_slave11;
                                        break;
                                    case 12:
                                        this.src = R.raw.mode1_table1_master2_slave12;
                                        break;
                                    case 13:
                                        this.src = R.raw.mode1_table1_master2_slave13;
                                        break;
                                    case 14:
                                        this.src = R.raw.mode1_table1_master2_slave14;
                                        break;
                                    case 15:
                                        this.src = R.raw.mode1_table1_master2_slave15;
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case 3:                                                                 //mode:1, table:1, master:3
                                switch(this.slave){
                                    case 0:
                                        this.src = R.raw.mode1_table1_master3_slave0;
                                        break;
                                    case 1:
                                        this.src = R.raw.mode1_table1_master3_slave1;
                                        break;
                                    case 2:
                                        this.src = R.raw.mode1_table1_master3_slave2;
                                        break;
                                    case 3:
                                        this.src = R.raw.mode1_table1_master3_slave3;
                                        break;
                                    case 4:
                                        this.src = R.raw.mode1_table1_master3_slave4;
                                        break;
                                    case 5:
                                        this.src = R.raw.mode1_table1_master3_slave5;
                                        break;
                                    case 6:
                                        this.src = R.raw.mode1_table1_master3_slave6;
                                        break;
                                    case 7:
                                        this.src = R.raw.mode1_table1_master3_slave7;
                                        break;
                                    case 8:
                                        this.src = R.raw.mode1_table1_master3_slave8;
                                        break;
                                    case 9:
                                        this.src = R.raw.mode1_table1_master3_slave9;
                                        break;
                                    case 10:
                                        this.src = R.raw.mode1_table1_master3_slave10;
                                        break;
                                    case 11:
                                        this.src = R.raw.mode1_table1_master3_slave11;
                                        break;
                                    case 12:
                                        this.src = R.raw.mode1_table1_master3_slave12;
                                        break;
                                    case 13:
                                        this.src = R.raw.mode1_table1_master3_slave13;
                                        break;
                                    case 14:
                                        this.src = R.raw.mode1_table1_master3_slave14;
                                        break;
                                    case 15:
                                        this.src = R.raw.mode1_table1_master3_slave15;
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case 4:                                                                 //mode:1, table:1, master:4
                                switch(this.slave){
                                    case 0:
                                        this.src = R.raw.mode1_table1_master4_slave0;
                                        break;
                                    case 1:
                                        this.src = R.raw.mode1_table1_master4_slave1;
                                        break;
                                    case 2:
                                        this.src = R.raw.mode1_table1_master4_slave2;
                                        break;
                                    case 3:
                                        this.src = R.raw.mode1_table1_master4_slave3;
                                        break;
                                    case 4:
                                        this.src = R.raw.mode1_table1_master4_slave4;
                                        break;
                                    case 5:
                                        this.src = R.raw.mode1_table1_master4_slave5;
                                        break;
                                    case 6:
                                        this.src = R.raw.mode1_table1_master4_slave6;
                                        break;
                                    case 7:
                                        this.src = R.raw.mode1_table1_master4_slave7;
                                        break;
                                    case 8:
                                        this.src = R.raw.mode1_table1_master4_slave8;
                                        break;
                                    case 9:
                                        this.src = R.raw.mode1_table1_master4_slave9;
                                        break;
                                    case 10:
                                        this.src = R.raw.mode1_table1_master4_slave10;
                                        break;
                                    case 11:
                                        this.src = R.raw.mode1_table1_master4_slave11;
                                        break;
                                    case 12:
                                        this.src = R.raw.mode1_table1_master4_slave12;
                                        break;
                                    case 13:
                                        this.src = R.raw.mode1_table1_master4_slave13;
                                        break;
                                    case 14:
                                        this.src = R.raw.mode1_table1_master4_slave14;
                                        break;
                                    case 15:
                                        this.src = R.raw.mode1_table1_master4_slave15;
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case 5:                                                                 //mode:1, table:1, master:5
                                switch(this.slave){
                                    case 0:
                                        this.src = R.raw.mode1_table1_master5_slave0;
                                        break;
                                    case 1:
                                        this.src = R.raw.mode1_table1_master5_slave1;
                                        break;
                                    case 2:
                                        this.src = R.raw.mode1_table1_master5_slave2;
                                        break;
                                    case 3:
                                        this.src = R.raw.mode1_table1_master5_slave3;
                                        break;
                                    case 4:
                                        this.src = R.raw.mode1_table1_master5_slave4;
                                        break;
                                    case 5:
                                        this.src = R.raw.mode1_table1_master5_slave5;
                                        break;
                                    case 6:
                                        this.src = R.raw.mode1_table1_master5_slave6;
                                        break;
                                    case 7:
                                        this.src = R.raw.mode1_table1_master5_slave7;
                                        break;
                                    case 8:
                                        this.src = R.raw.mode1_table1_master5_slave8;
                                        break;
                                    case 9:
                                        this.src = R.raw.mode1_table1_master5_slave9;
                                        break;
                                    case 10:
                                        this.src = R.raw.mode1_table1_master5_slave10;
                                        break;
                                    case 11:
                                        this.src = R.raw.mode1_table1_master5_slave11;
                                        break;
                                    case 12:
                                        this.src = R.raw.mode1_table1_master5_slave12;
                                        break;
                                    case 13:
                                        this.src = R.raw.mode1_table1_master5_slave13;
                                        break;
                                    case 14:
                                        this.src = R.raw.mode1_table1_master5_slave14;
                                        break;
                                    case 15:
                                        this.src = R.raw.mode1_table1_master5_slave15;
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case 6:                                                                 //mode:1, table:1, master:6
                                switch(this.slave){
                                    case 0:
                                        this.src = R.raw.mode1_table1_master6_slave0;
                                        break;
                                    case 1:
                                        this.src = R.raw.mode1_table1_master6_slave1;
                                        break;
                                    case 2:
                                        this.src = R.raw.mode1_table1_master6_slave2;
                                        break;
                                    case 3:
                                        this.src = R.raw.mode1_table1_master6_slave3;
                                        break;
                                    case 4:
                                        this.src = R.raw.mode1_table1_master6_slave4;
                                        break;
                                    case 5:
                                        this.src = R.raw.mode1_table1_master6_slave5;
                                        break;
                                    case 6:
                                        this.src = R.raw.mode1_table1_master6_slave6;
                                        break;
                                    case 7:
                                        this.src = R.raw.mode1_table1_master6_slave7;
                                        break;
                                    default:
                                        break;
                                }
                                break;
                        }
                        break;
                    case 2:
                        switch(this.master){
                            case 0:                                                                 //mode:1, table:2, master:0
                                switch(this.slave){
                                    case 0:
                                        this.src = R.raw.mode1_table2_master0_slave0;
                                        break;
                                    case 1:
                                        this.src = R.raw.mode1_table2_master0_slave1;
                                        break;
                                    case 2:
                                        this.src = R.raw.mode1_table2_master0_slave2;
                                        break;
                                    case 3:
                                        this.src = R.raw.mode1_table2_master0_slave3;
                                        break;
                                    case 4:
                                        this.src = R.raw.mode1_table2_master0_slave4;
                                        break;
                                    case 5:
                                        this.src = R.raw.mode1_table2_master0_slave5;
                                        break;
                                    case 6:
                                        this.src = R.raw.mode1_table2_master0_slave6;
                                        break;
                                    case 7:
                                        this.src = R.raw.mode1_table2_master0_slave7;
                                        break;
                                    case 8:
                                        this.src = R.raw.mode1_table2_master0_slave8;
                                        break;
                                    case 9:
                                        this.src = R.raw.mode1_table2_master0_slave9;
                                        break;
                                    case 10:
                                        this.src = R.raw.mode1_table2_master0_slave10;
                                        break;
                                    case 11:
                                        this.src = R.raw.mode1_table2_master0_slave11;
                                        break;
                                    case 12:
                                        this.src = R.raw.mode1_table2_master0_slave12;
                                        break;
                                    case 13:
                                        this.src = R.raw.mode1_table2_master0_slave13;
                                        break;
                                    case 14:
                                        this.src = R.raw.mode1_table2_master0_slave14;
                                        break;
                                    case 15:
                                        this.src = R.raw.mode1_table2_master0_slave15;
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case 1:                                                                 //mode:1, table:2, master:1
                                switch(this.slave){
                                    case 0:
                                        this.src = R.raw.mode1_table2_master1_slave0;
                                        break;
                                    case 1:
                                        this.src = R.raw.mode1_table2_master1_slave1;
                                        break;
                                    case 2:
                                        this.src = R.raw.mode1_table2_master1_slave2;
                                        break;
                                    case 3:
                                        this.src = R.raw.mode1_table2_master1_slave3;
                                        break;
                                    case 4:
                                        this.src = R.raw.mode1_table2_master1_slave4;
                                        break;
                                    case 5:
                                        this.src = R.raw.mode1_table2_master1_slave5;
                                        break;
                                    case 6:
                                        this.src = R.raw.mode1_table2_master1_slave6;
                                        break;
                                    case 7:
                                        this.src = R.raw.mode1_table2_master1_slave7;
                                        break;
                                    case 8:
                                        this.src = R.raw.mode1_table2_master1_slave8;
                                        break;
                                    default:
                                        break;
                                }
                                break;
                        }
                        break;
                }
                break;
        }

        return this.src;
    }

}