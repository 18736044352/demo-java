package com.java.fastjson;

import com.alibaba.fastjson.annotation.JSONField;

public class DubboUserDO {


    private String regTime;

    private String updateTime;

    private String photoServer;
    private Short location;






    public String getRegTime() {
        return regTime;
    }
    @JSONField(name = "reg_ts")
    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }

//    @JSONField(name = "reg_ts")
    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getPhotoServer() {
        return photoServer;
    }

    public void setPhotoServer(String photoServer) {
        this.photoServer = photoServer;
    }

    public Short getLocation() {
        return location;
    }

    public void setLocation(Short location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "DubboUserDO{" +
                "regTime='" + regTime + '\'' +
                ", photoServer='" + photoServer + '\'' +
                ", location=" + location +
                '}';
    }
}
