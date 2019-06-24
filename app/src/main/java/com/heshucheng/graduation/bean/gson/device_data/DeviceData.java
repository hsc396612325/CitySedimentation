/**
 * Copyright 2019 bejson.com
 */
package com.heshucheng.graduation.bean.gson.device_data;

/**
 * Auto-generated: 2019-06-01 0:15:10
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class DeviceData {

    private int errno;
    private Data data;
    private String error;
    public void setErrno(int errno) {
        this.errno = errno;
    }
    public int getErrno() {
        return errno;
    }

    public void setData(Data data) {
        this.data = data;
    }
    public Data getData() {
        return data;
    }

    public void setError(String error) {
        this.error = error;
    }
    public String getError() {
        return error;
    }

}