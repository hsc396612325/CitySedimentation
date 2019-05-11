package com.heshucheng.graduation.bean.gson.device_data;

/**
 * Created by heshu on 2019/2/19.
 */

public class Value {
    private double lat;
    private double lon;
    private double ele;
    public void setLat(double lat) {
        this.lat = lat;
    }
    public double getLat() {
        return lat;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
    public double getLon() {
        return lon;
    }
    public void setEle(double ele) {
        this.ele = ele;
    }
    public double getEle() {
        return ele;
    }

}
