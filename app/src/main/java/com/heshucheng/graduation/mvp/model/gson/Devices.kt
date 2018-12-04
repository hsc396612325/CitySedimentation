package com.heshucheng.graduation.mvp.model.gson

/**
 * Created by heshu on 2018/12/4.
 */
data class Devices(val errno: Int,
                   var data: Data,
                   val error: String) {

    data class Data(val per_page: Int,
                    var dataList: ArrayList<DevicesItem>,
                    val total_count: Int,
                    val page: Int)
}