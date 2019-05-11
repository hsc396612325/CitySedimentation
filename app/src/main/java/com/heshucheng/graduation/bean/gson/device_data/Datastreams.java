package com.heshucheng.graduation.bean.gson.device_data;

import java.util.List;

/**
 * Created by heshu on 2019/2/19.
 */

public class Datastreams {
    private List<Datapoints> datapoints;
    private String id;
    public void setDatapoints(List<Datapoints> datapoints) {
        this.datapoints = datapoints;
    }
    public List<Datapoints> getDatapoints() {
        return datapoints;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

}
