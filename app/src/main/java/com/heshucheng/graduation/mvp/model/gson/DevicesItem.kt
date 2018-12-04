package com.heshucheng.graduation.mvp.model.gson

/**
 * Created by heshu on 2018/12/4.
 */
data class DevicesItem(val protocol: String,
                       val create_time: String,
                       val online: Boolean,
                       var location: Location,
                       val id: String,
                       var auth_info: AuthInfo,
                       var datastreamsList :ArrayList<Datastreams>,
                       val title: String,
                       val desc: String,
                       var tags: Tags) {

    data class Location(val lat: String, val lon: String, val ele: String)

    data class AuthInfo(val SyS: String)

    data class Datastreams(var create_time: String, val uuid :String,val id :String)

    data class Tags(val aabb: String, val rover: String)
}