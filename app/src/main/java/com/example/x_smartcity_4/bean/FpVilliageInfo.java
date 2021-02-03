package com.example.x_smartcity_4.bean;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/2  17:29
 */
public class FpVilliageInfo {

    /**
     * detail_id : 7
     * villid : 7
     * enviroment_pic : http://192.168.101.7:8080/mobileA/images/envirpic7.jpg
     * reporttime : 2020-07-08 00:00:00
     * report : 李昌胜
     * readernum : 306
     */

    private int detailId;
    private int villid;
    private String enviroment_pic;

    public String getEnviroment_pic() {
        return enviroment_pic;
    }

    public void setEnviroment_pic(String enviroment_pic) {
        this.enviroment_pic = enviroment_pic;
    }

    private String reporttime;
    private String report;
    private int readernum;

    public int getDetailId() {
        return detailId;
    }

    public void setDetailId(int detailId) {
        this.detailId = detailId;
    }

    public int getVillid() {
        return villid;
    }

    public void setVillid(int villid) {
        this.villid = villid;
    }


    public String getReporttime() {
        return reporttime;
    }

    public void setReporttime(String reporttime) {
        this.reporttime = reporttime;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public int getReadernum() {
        return readernum;
    }

    public void setReadernum(int readernum) {
        this.readernum = readernum;
    }
}
