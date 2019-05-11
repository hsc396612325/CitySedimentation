package com.heshucheng.graduation.bean.gson.device_data;

/**
 * Created by heshu on 2019/2/19.
 */

public class Datapoints {
    private String at;
    private Value value;
    public void setAt(String at) {
        this.at = at;
    }
    public String getAt() {
        return at;
    }

    public void setValue(Value value) {
        this.value = value;
    }
    public Value getValue() {
        return value;
    }

}
