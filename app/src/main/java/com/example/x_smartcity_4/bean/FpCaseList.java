package com.example.x_smartcity_4.bean;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/2  13:56
 */
public class FpCaseList {
    /**
     * caseid : 1
     * casetitle : 大爱湖北行，千里送爱心
     * casepicture : http://localhost:8080/mobileA/images/fpcasepic1.jpg
     * helperr : 严义军
     * reporttime : 2020-10-02 00:00:00
     * readnum : 9000
     * thumbup : 2006
     * userid : 1
     * caseContent : 人民网银川10月13日电 记者获悉，宁夏原州区金融扶贫案例于近期作为联合国中国扶贫经典案例在联合国网站展示，并成为联合国对发展中国家扶贫开发的课程。
     */

    private int caseid;
    private String casetitle;
    private String casepicture;
    private String helperr;
    private String reporttime;
    private int readnum;
    private int thumbup;
    private int userid;
    private String caseContent;

    public int getCaseid() {
        return caseid;
    }

    public void setCaseid(int caseid) {
        this.caseid = caseid;
    }

    public String getCasetitle() {
        return casetitle;
    }

    public void setCasetitle(String casetitle) {
        this.casetitle = casetitle;
    }

    public String getCasepicture() {
        return casepicture;
    }

    public void setCasepicture(String casepicture) {
        this.casepicture = casepicture;
    }

    public String getHelperr() {
        return helperr;
    }

    public void setHelperr(String helperr) {
        this.helperr = helperr;
    }

    public String getReporttime() {
        return reporttime;
    }

    public void setReporttime(String reporttime) {
        this.reporttime = reporttime;
    }

    public int getReadnum() {
        return readnum;
    }

    public void setReadnum(int readnum) {
        this.readnum = readnum;
    }

    public int getThumbup() {
        return thumbup;
    }

    public void setThumbup(int thumbup) {
        this.thumbup = thumbup;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getCaseContent() {
        return caseContent;
    }

    public void setCaseContent(String caseContent) {
        this.caseContent = caseContent;
    }
}
