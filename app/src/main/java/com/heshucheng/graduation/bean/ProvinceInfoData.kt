package com.heshucheng.graduation.bean

/**
 * Created by heshu on 2018/10/20.
 */
data class ProvinceInfoData(val name: String, var city: ArrayList<CityInfoModel>?)
data class CityInfoModel(val name: String, var area: ArrayList<String>)
data class DistrictInfoModel(val name: String)