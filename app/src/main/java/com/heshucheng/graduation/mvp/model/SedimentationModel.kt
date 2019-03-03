package com.heshucheng.graduation.mvp.model

import com.heshucheng.graduation.mvp.model.gson.device_data.DeviceData
import com.heshucheng.graduation.utiles.io_main
import com.heshucheng.graduation.utiles.net.NetWork
import io.reactivex.Observable

/**
 * Created by heshu on 2018/10/18.
 */
class SedimentationModel {
    fun getDeviceData(apiKey: String?, id: String?, start: String?, end: String?, limit: String?): Observable<DeviceData> {


        val url = "devices/" + id + "/datapoints?dev_id=" + id + "&datastream_id=location,difference&start=" + start + "&end=" + end + "&limit=" + limit

       //  val url ="devices/31951230/datapoints?dev_id=31951230&datastream_id=location,difference&start=2018-10-30T00:00:00&end=2018-10-31T10:50:00&limit=200"
        return NetWork.service.getDeviceData(url, apiKey).io_main()
    }
}