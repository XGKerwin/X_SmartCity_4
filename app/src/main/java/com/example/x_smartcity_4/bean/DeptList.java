package com.example.x_smartcity_4.bean;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/1  2:16
 */
public class DeptList {

    /**
     * hospitalId : 1
     * deptId : 1
     * deptName : 神经内科
     * desc : 神经内科是关于神经方面的二级学科。不属于内科概念。主要收治脑血管疾病（脑梗塞、脑出血）、偏头痛、脑部炎症性疾病（脑炎、脑膜炎）、脊髓炎、癫痫、痴呆、代谢病和遗传倾向疾病、三叉神经痛、坐骨神经病、周围神经病及重症肌无力等。
     * tag : MRI，CT，ECT
     */

    private String hospitalId;
    private String deptId;
    private String deptName;
    private String desc;
    private String tag;

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
