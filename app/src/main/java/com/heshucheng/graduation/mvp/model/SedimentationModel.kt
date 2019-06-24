package com.heshucheng.graduation.mvp.model

import com.heshucheng.graduation.bean.gson.device_data.DeviceData
import com.heshucheng.graduation.utiles.io_main
import com.heshucheng.graduation.utiles.net.NetWork
import io.reactivex.Observable

/**
 * Created by heshu on 2018/10/18.
 */
class SedimentationModel {
    fun getDeviceData(apiKey: String?, id: String?, start: String?, end: String?, limit: String?): Observable<DeviceData> {


        val url = "devices/" + id + "/datapoints?dev_id=" + id + "&start=" + start + "&end=" + end + "&limit=" + limit
//        val url= "devices/522310509/datapoints?dev_id=522310509&start=2019-03-10T00:03:00&end=2019-04-10T00:04:00&limit=100"
       //  val url ="devices/31951230/datapoints?dev_id=31951230&datastream_id=location,difference&start=2018-10-30T00:00:00&end=2018-10-31T10:50:00&limit=200"
        return NetWork.service.getDeviceData(url, apiKey).io_main()
    }
}