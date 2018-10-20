package com.heshucheng.graduation.bean.detection

import com.baidu.mapapi.model.LatLng

import java.io.Serializable

/**
 * Created by heshu on 2018/1/29.
 */

data class LocationBean(var latitude: Double,
                        val longitude: Double,
                        var radius: Float,
                        var province: String,
                        var city: String,
                        var district: String,
                        var street: String)

//longitude 纬度信息
//latitude 经度信息
//province 省
//city 市
//district 区县
//street 街道

