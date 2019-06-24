package com.heshucheng.graduation.utiles.net

import com.heshucheng.graduation.bean.gson.area.Area
import com.heshucheng.graduation.bean.gson.device_data.DeviceData

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Url

/**
 * Created by heshu on 2018/10/18.
 */
interface ApiService{
    /**
     * 获取区域
     */
    @GET
    fun getArea(@Url url:String,@Header("api-key") api_key:String?):Observable<Area>

    /**
     * 获取区域下设备
     */
    @GET
    fun getEquipment(@Url url:String,@Header("api-key") api_key:String?):Observable<Area>

    /**
     * 获取设备信息
     */
    @GET
    fun getDeviceData(@Url url:String,@Header("api-key") api_key:String?):Observable<DeviceData>
}