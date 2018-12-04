package com.heshucheng.graduation.mvp.model.gson

/**
 * Created by heshu on 2018/12/4.
 */
data class OneDevicesDate(val errno: Int,
                          var data: Data,
                          val error: String){

    data class Data(val update_at: String,
                    var id: String,
                    val create_time: String,
                    val current_value: String)
}