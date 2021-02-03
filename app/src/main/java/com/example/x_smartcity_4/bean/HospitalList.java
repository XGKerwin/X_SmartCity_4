package com.example.x_smartcity_4.bean;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/1/31  10:56
 */
public class HospitalList {

    /**
     * hospitalId : 1
     * hospitalName : 同仁医院
     * picture : http://192.168.101.7:8080/mobileA/images/h1.jpg
     * desc : 首都医科大学附属北京同仁医院始建于1886年,是一所以眼科学、耳鼻咽喉科学为国家重点学科的大型综合三甲医院。今天的医院分为崇文门院区和亦庄院区…
     * icon : http://192.168.101.7:8080/mobileA/images/h1.jpg
     * url : http://www.baidu.com
     */

    private String hospitalId;
    private String hospitalName;
    private String picture;
    private String desc;
    private String icon;
    private String url;
    private String rank;

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
