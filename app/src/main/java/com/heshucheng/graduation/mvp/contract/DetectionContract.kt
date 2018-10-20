package com.heshucheng.graduation.mvp.contract

import com.heshucheng.graduation.bean.ProvinceInfoData
import com.heshucheng.graduation.bean.detection.LocationBean
import com.heshucheng.graduation.mvp.base.BasePresenter
import com.heshucheng.graduation.mvp.base.BaseView

/**
 * Created by heshu on 2018/10/18.
 */
interface DetectionContract{

    interface View :BaseView<Presenter>{
        fun showLocation(locationBean :LocationBean)
        fun showFault()
        fun showProvinceData( ProvinceInfoDataItem: ArrayList<ProvinceInfoData>)
    }

    interface Presenter: BasePresenter {
        fun requestProvinceData()
        fun requestLocation()
    }

}