package com.example.x_smartcity_4.bean;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/2  20:08
 */
public class FpSeekByUserid {

    /**
     * seekid : 1
     * seektitle : 公路硬化
     * seektime : 2020-07-06 00:00:00
     * seeker : 焦泽松
     * seekcontent : 公路硬化
     * tel : 13456781290
     * userid : 1
     */

    private int seekid;
    private String seektitle;
    private String seektime;
    private String seeker;
    private String seekcontent;
    private String tel;
    private int userid;

    public int getSeekid() {
        return seekid;
    }

    public void setSeekid(int seekid) {
        this.seekid = seekid;
    }

    public String getSeektitle() {
        return seektitle;
    }

    public void setSeektitle(String seektitle) {
        this.seektitle = seektitle;
    }

    public String getSeektime() {
        return seektime;
    }

    public void setSeektime(String seektime) {
        this.seektime = seektime;
    }

    public String getSeeker() {
        return seeker;
    }

    public void setSeeker(String seeker) {
        this.seeker = seeker;
    }

    public String getSeekcontent() {
        return seekcontent;
    }

    public void setSeekcontent(String seekcontent) {
        this.seekcontent = seekcontent;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}
