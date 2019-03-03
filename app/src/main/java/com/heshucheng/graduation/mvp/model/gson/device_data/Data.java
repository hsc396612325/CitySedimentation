package com.heshucheng.graduation.mvp.model.gson.device_data;

import java.util.List;

/**
 * Created by heshu on 2019/2/19.
 */

public class Data {

    private int count;
    private List<Datastreams> datastreams;
    public void setCount(int count) {
        this.count = count;
    }
    public int getCount() {
        return count;
    }

    public void setDatastreams(List<Datastreams> datastreams) {
        this.datastreams = datastreams;
    }
    public List<Datastreams> getDatastreams() {
        return datastreams;
    }

}

