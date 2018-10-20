package com.heshucheng.graduation.mvp.model

import android.content.Context
import android.util.Log
import com.baidu.location.*
import com.google.gson.Gson
import com.heshucheng.graduation.bean.db.City
import com.heshucheng.graduation.bean.db.County
import com.heshucheng.graduation.bean.db.Province
import com.heshucheng.graduation.io_main
import com.heshucheng.graduation.net.NetWork
import io.reactivex.Observable
import com.heshucheng.graduation.App
import com.heshucheng.graduation.App.Companion.context
import com.heshucheng.graduation.bean.ProvinceInfoData

import com.heshucheng.graduation.bean.detection.LocationBean
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.json.JSONArray
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


/**
 * Created by heshu on 2018/10/18.
 */
class DetectionModel {
    private val mLocationClient: LocationClient = LocationClient(App.context)

    init {
        val option = LocationClientOption()
        option.isOpenGps = true // 打开gps
        option.setCoorType("bd09ll") // 设置坐标类型
        option.setScanSpan(1000)
        option.setIsNeedAddress(true)
        mLocationClient.locOption = option

    }

    //获取市
    fun loadProvinceData(): Observable<ArrayList<ProvinceInfoData>> {
        //return NetWork.service.getCity(url).io_main()

        return Observable
                .create(ObservableOnSubscribe<ArrayList<ProvinceInfoData>> { e ->
                    val str = getJson("Province.Json", App.context!!) //读取本地数据
                    val detail: ArrayList<ProvinceInfoData> = parseJsonData(str) //解析json
                    e.onNext(detail)
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())


    }

    fun loadlocation(): Observable<LocationBean> {

        return Observable.create({ e ->
            mLocationClient.start()
            mLocationClient.registerLocationListener({ location ->
                val locationBean = LocationBean(
                        location.latitude,
                        location.longitude,
                        location.radius,
                        location.province,
                        location.city,
                        location.district,
                        location.street
                )
                e.onNext(locationBean)
                mLocationClient.stop() //关闭定位
                e.onComplete()
            })
        })
    }


    private fun getJson(fileName: String, context: Context): String { //读取文件中的数据
        //将json数据变成字符串
        val stringBuilder = StringBuilder()
        try {
            //获取assets资源管理器
            val assetManager = context.getAssets()

            //通过管理器打开文件并读取
            val bf = BufferedReader(InputStreamReader(
                    assetManager.open(fileName)))
            var line = bf.readLine()

            while (line != null) {
                stringBuilder.append(line)
                line = bf.readLine()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return stringBuilder.toString()
    }

    private fun parseJsonData(result: String): ArrayList<ProvinceInfoData> {
        val detail: ArrayList<ProvinceInfoData> = ArrayList<ProvinceInfoData>()

        try {
            var data = JSONArray(result)
            var gson = Gson()

            for (i in 0..(data.length() - 1)) {
                val entity: ProvinceInfoData = gson.fromJson(data.optJSONObject(i).toString(), ProvinceInfoData::class.java)
                detail.add(entity)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return detail
    }

}


