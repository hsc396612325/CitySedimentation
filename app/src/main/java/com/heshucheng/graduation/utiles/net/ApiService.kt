package com.heshucheng.graduation.utiles.net

import com.heshucheng.graduation.bean.db.City
import com.heshucheng.graduation.bean.db.County
import com.heshucheng.graduation.bean.db.Province
import com.heshucheng.graduation.mvp.model.gson.Devices
import com.heshucheng.graduation.mvp.model.gson.OneDevices
import com.heshucheng.graduation.mvp.model.gson.OneDevicesDate
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * Created by heshu on 2018/10/18.
 */
interface ApiService{
    /**
     * 获取所有设备
     */
    @GET
    fun getDevices(@Url url:String):Observable<Devices>

    /**
     * 获取指定设备
     */
    @GET
    fun getOneDevices(@Url url:String):Observable<OneDevices>

    /**
     * 获取设备信息
     */
    @GET
    fun getOneDevicesData(@Url url:String):Observable<OneDevicesDate>
}