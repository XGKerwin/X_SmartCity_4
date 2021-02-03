package com.example.x_smartcity_4.bean;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/2  11:53
 */
public class FpNewsList {

    /**
     * newsid : 1
     * newstitle : 松口古镇：“活化”客家古驿道 激发乡村新活力
     * newsreporter : 王海龙
     * reporttime : 2020-04-03 00:00:00
     * newspicture : http://192.168.101.7:8080/mobileA/images/fpnewspic1.jpeg
     * newscontent : 近年来，松口镇结合乡村振兴战略，加大古驿道文化内涵挖掘力度和保护开发力度，并以此为抓手改善提升农村人居环境，带动农村经济发展，助推乡村振兴。
     * readnum : 34
     * isfront : 1
     */

    private int newsid;
    private String newstitle;
    private String newsreporter;
    private String reporttime;
    private String newspicture;
    private String newscontent;
    private int readnum;
    private String isfront;

    public int getNewsid() {
        return newsid;
    }

    public void setNewsid(int newsid) {
        this.newsid = newsid;
    }

    public String getNewstitle() {
        return newstitle;
    }

    public void setNewstitle(String newstitle) {
        this.newstitle = newstitle;
    }

    public String getNewsreporter() {
        return newsreporter;
    }

    public void setNewsreporter(String newsreporter) {
        this.newsreporter = newsreporter;
    }

    public String getReporttime() {
        return reporttime;
    }

    public void setReporttime(String reporttime) {
        this.reporttime = reporttime;
    }

    public String getNewspicture() {
        return newspicture;
    }

    public void setNewspicture(String newspicture) {
        this.newspicture = newspicture;
    }

    public String getNewscontent() {
        return newscontent;
    }

    public void setNewscontent(String newscontent) {
        this.newscontent = newscontent;
    }

    public int getReadnum() {
        return readnum;
    }

    public void setReadnum(int readnum) {
        this.readnum = readnum;
    }

    public String getIsfront() {
        return isfront;
    }

    public void setIsfront(String isfront) {
        this.isfront = isfront;
    }
}
