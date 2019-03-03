package com.heshucheng.graduation.mvp.contract

import com.baidu.mapapi.map.OverlayOptions
import com.baidu.mapapi.search.sug.SuggestionResult
import com.heshucheng.graduation.bean.MarkerBeas
import com.heshucheng.graduation.bean.ProvinceInfoData
import com.heshucheng.graduation.bean.detection.LocationBean
import com.heshucheng.graduation.mvp.base.BasePresenter
import com.heshucheng.graduation.mvp.base.BaseView
import com.heshucheng.graduation.mvp.model.gson.area.Area

/**
 * Created by heshu on 2018/10/18.
 */
interface DetectionContract{

    interface View :BaseView<Presenter>{
        fun acquireLocation(locationBean :LocationBean)
        fun showFault()
//        fun showProvinceData( ProvinceInfoDataItem: ArrayList<ProvinceInfoData>)
//        fun showSug(sug:SuggestionResult)

        fun showArea(area: Area)
        fun showApiKeyFault()

        fun showEquipment(area: Area)
    }

    interface Presenter: BasePresenter {
//        fun requestProvinceData()
        fun requestLocation()
//        fun requestSug(sug :String)

        fun requestArea(apiKay:String?)
        fun requestEquipment(apiKay:String?,tag:String)


    }

}