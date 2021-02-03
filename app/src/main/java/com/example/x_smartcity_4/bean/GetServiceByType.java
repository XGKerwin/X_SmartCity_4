package com.example.x_smartcity_4.bean;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/1/30  15:43
 */
public class GetServiceByType {

    /**
     * serviceid : 11
     * serviceName : 实时天气
     * icon : http://192.168.43.50:8080/mobileA/images/tubiao11.png
     * url : http://www.weather.com.cn/weather/101120401.shtml
     * serviceType : 智慧养老
     * desc : ccc
     */

    private int serviceid;
    private String serviceName;
    private String icon;
    private String url;
    private String serviceType;
    private String desc;

    public int getServiceid() {
        return serviceid;
    }

    public void setServiceid(int serviceid) {
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
