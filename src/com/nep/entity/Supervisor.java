package com.nep.entity;

import java.io.Serializable;

/**
 * @author Bobby
 * 公众监督员信息
 */

public class Supervisor extends Operator implements Serializable {

    private static final long serialVersionUID = 1L;
    private String sex ;	//公众监督员性别  男/女

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Supervisor{" +
                "sex='" + sex + '\'' +
                '}';
    }

    public Supervisor() {
    }

    public Supervisor(String sex) {
        this.sex = sex;
    }
    //构造方法及setter/getter方法......
}
