/**
 * Copyright 2019 bejson.com
 */
package com.heshucheng.graduation.bean.gson.device_data;
import java.util.List;

/**
 * Auto-generated: 2019-06-01 0:15:10
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
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