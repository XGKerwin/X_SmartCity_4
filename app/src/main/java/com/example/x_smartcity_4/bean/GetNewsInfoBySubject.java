package com.example.x_smartcity_4.bean;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/2  0:20
 */
public class GetNewsInfoBySubject {

    /**
     *             "newsid": "1",
     *             "publicTime": "2020-10-01 06:00:00",
     *             "subject": "国庆专题",
     *             "recommand": 1,
     *             "praiseCount": 500,
     *             "audienceCount": "600"
     */

    private String newsid,publicTime,subject,recommand,praiseCount,audienceCount;

    public String getNewsid() {
        return newsid;
    }

    public void setNewsid(String newsid) {
        this.newsid = newsid;
    }

    public String getPublicTime() {
        return publicTime;
    }

    public void setPublicTime(String publicTime) {
        this.publicTime = publicTime;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getRecommand() {
        return recommand;
    }

    public void setRecommand(String recommand) {
        this.recommand = recommand;
    }

    public String getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(String praiseCount) {
        this.praiseCount = praiseCount;
    }

    public String getAudienceCount() {
        return audienceCount;
    }

    public void setAudienceCount(String audienceCount) {
        this.audienceCount = audienceCount;
    }
}
