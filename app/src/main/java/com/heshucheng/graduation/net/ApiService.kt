package com.heshucheng.graduation.net

import com.heshucheng.graduation.bean.db.City
import com.heshucheng.graduation.bean.db.County
import com.heshucheng.graduation.bean.db.Province
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * Created by heshu on 2018/10/18.
 */
interface ApiService{
    /**
     * 获取省级数据
     */
    @GET
    fun getProvince(@Url url:String):Observable<Province>

    /**
     * 获取市级数据
     */
    @GET
    fun getCity(@Url url:String):Observable<City>

    /**
     * 获取区级数据
     */
    @GET
    fun getCounty(@Url url:String):Observable<County>
}