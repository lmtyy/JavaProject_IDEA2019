package com.nep.entity;

import java.io.Serializable;

/**
 * @author Bobby
 * AQI标准指数
 */

public class Aqi implements Serializable {

    private static final long serialVersionUID = 1L;
    private String level;			//AQI指数级别
    private String explain;			//AQI指数描述
    private String impact;			//对健康的影响
    //构造方法及setter/getter方法......

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "Aqi{" +
                "level='" + level + '\'' +
                ", explain='" + explain + '\'' +
                ", impact='" + impact + '\'' +
                '}';
    }

    public String getLevel() {
        return level;
    }

    public String getExplain() {
        return explain;
    }

    public String getImpact() {
        return impact;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public void setImpact(String impact) {
        this.impact = impact;
    }

    public Aqi(String level, String explain, String impact) {
        this.level = level;
        this.explain = explain;
        this.impact = impact;
    }

    public Aqi() {
    }
}
