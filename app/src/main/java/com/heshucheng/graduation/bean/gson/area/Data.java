package com.heshucheng.graduation.bean.gson.area;



import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by heshu on 2019/2/19.
 */

public class Data {

    @SerializedName("per_page")
    private int perPage;

    private List<Devices> devices;
    @SerializedName("total_count")
    private int totalCount;
    private int page;
    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }
    public int getPerPage() {
        return perPage;
    }

    public void setDevices(List<Devices> devices) {
        this.devices = devices;
    }
    public List<Devices> getDevices() {
        return devices;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
    public int getTotalCount() {
        return totalCount;
    }

    public void setPage(int page) {
        this.page = page;
    }
    public int getPage() {
        return page;
    }


}
