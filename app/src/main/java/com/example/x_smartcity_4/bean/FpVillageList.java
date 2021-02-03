package com.example.x_smartcity_4.bean;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/2  16:14
 */
public class FpVillageList {

    /**
     * villid : 1
     * villname : 樵山村
     * villpicture : http://192.168.101.7:8080/mobileA/images/villpic1.jpg
     * villaddress : 德州市湖滨路
     * villdesc : 樵山村以前以太平至泾县主干路的南北分别隶属泾县与旌德县管辖，57年后划归原太平县管辖至今。

     樵山村位于新明乡东北部，距乡政府15公里，地势东南高，西北低。座落在高山之中，东与泾县接壤，南、西、北分别与新明村、三合村相邻，平均海拔在500米以上。
     */

    private int villid;
    private String villname;
    private String villpicture;
    private String villaddress;
    private String villdesc;

    public int getVillid() {
        return villid;
    }

    public void setVillid(int villid) {
        this.villid = villid;
    }

    public String getVillname() {
        return villname;
    }

    public void setVillname(String villname) {
        this.villname = villname;
    }

    public String getVillpicture() {
        return villpicture;
    }

    public void setVillpicture(String villpicture) {
        this.villpicture = villpicture;
    }

    public String getVilladdress() {
        return villaddress;
    }

    public void setVilladdress(String villaddress) {
        this.villaddress = villaddress;
    }

    public String getVilldesc() {
        return villdesc;
    }

    public void setVilldesc(String villdesc) {
        this.villdesc = villdesc;
    }
}
