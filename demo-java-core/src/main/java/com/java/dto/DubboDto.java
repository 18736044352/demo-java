package com.java.dto;

import java.io.Serializable;

/**
 * Created by  on 18/1/18.
 * @author jingchao.zhu
 */
public class DubboDto implements Serializable{

    private String mesage;

    public String getMesage() {
        return mesage;
    }

    public void setMesage(String mesage) {
        this.mesage = mesage;
    }

    @Override
    public String toString() {
        return "DubboDto{" +
                "mesage='" + mesage + '\'' +
                '}';
    }
}
