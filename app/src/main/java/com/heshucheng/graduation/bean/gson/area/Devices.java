package com.heshucheng.graduation.bean.gson.area;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by heshu on 2019/2/19.
 */

public class Devices {

    @SerializedName("private")
    private boolean privat;
    private String protocol;
    @SerializedName("create_time")
    private String createTime;
    private boolean online;
    private Location location;
    private String id;
//    @SerializedName("auth_info")
//    private AuthInfo authInfo;
    private String title;
    private List<String> tags;

    public void setPrivate(boolean privat) {
        this.privat = privat;
    }
    public boolean getPrivate() {
        return privat;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
    public String getProtocol() {
        return protocol;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public String getCreateTime() {
        return createTime;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }
    public boolean getOnline() {
        return online;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
    public Location getLocation() {
        return location;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

//    public void setAuthInfo(AuthInfo authInfo) {
//        this.authInfo = authInfo;
//    }
//    public  AuthInfo getAuthInfo() {
//        return authInfo;
//    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
    public List<String> getTags() {
        return tags;
    }

}