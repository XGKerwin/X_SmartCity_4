package com.example.x_smartcity_4.bean;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/2  0:14
 */
public class Service_info {
    /**
     *             "serviceid": 10,
     *             "serviceName": "电影演出",
     *             "icon": "http://192.168.101.7:8080/mobileA/images/tubiao10.png",
     *             "url": "https://baijiahao.baidu.com/s?id=1679517932182439546&wfr=spider&for=pc",
     *             "serviceType": "智慧服务",
     *             "desc": "bbb"
     */
    private String serviceid,serviceName,icon,url,serviceType,desc;

    public String getServiceid() {
        return serviceid;
    }

    public void setServiceid(String serviceid) {
        this.serviceid = serviceid;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
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

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
