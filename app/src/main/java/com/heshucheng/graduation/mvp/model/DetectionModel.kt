package com.heshucheng.graduation.mvp.model

import com.heshucheng.graduation.bean.db.City
import com.heshucheng.graduation.bean.db.County
import com.heshucheng.graduation.bean.db.Province
import com.heshucheng.graduation.io_main
import com.heshucheng.graduation.net.NetWork
import io.reactivex.Observable

/**
 * Created by heshu on 2018/10/18.
 */
class DetectionModel{
    //获取省
    fun loadProvince(url :String): Observable<Province> {
        return NetWork.service.getProvince(url).io_main()
    }

    //获取市
    fun loadCity(url :String): Observable<City> {
        return NetWork.service.getCity(url).io_main()
    }

    //获取市
    fun loadCounty(url :String): Observable<County> {
        return NetWork.service.getCounty(url).io_main()
    }
}