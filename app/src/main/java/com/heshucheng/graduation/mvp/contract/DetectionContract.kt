package com.heshucheng.graduation.mvp.contract

import com.heshucheng.graduation.bean.detection.LocationBean
import com.heshucheng.graduation.mvp.base.BasePresenter
import com.heshucheng.graduation.mvp.base.BaseView
import com.heshucheng.graduation.bean.gson.area.Area

/**
 * Created by heshu on 2018/10/18.
 */
interface DetectionContract{

    interface View :BaseView<Presenter>{
        //显示定位
        fun acquireLocation(locationBean :LocationBean)

        //错误回调
        fun showFault()

        //显示区域
        fun showArea(area: Area)
        //api正确的回调
        fun showApiKeyFault()
        //显示设备
        fun showEquipment(area: Area)
    }

    interface Presenter: BasePresenter {
//     请求定位
        fun requestLocation()
       // 请求区域
        fun requestArea(apiKay:String?)
        //群殴灭火器设备
        fun requestEquipment(apiKay:String?,tag:String)


    }

}