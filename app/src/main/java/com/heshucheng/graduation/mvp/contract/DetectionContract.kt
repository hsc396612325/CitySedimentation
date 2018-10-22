package com.heshucheng.graduation.mvp.contract

import com.baidu.mapapi.map.OverlayOptions
import com.baidu.mapapi.search.sug.SuggestionResult
import com.heshucheng.graduation.bean.MarkerBeas
import com.heshucheng.graduation.bean.ProvinceInfoData
import com.heshucheng.graduation.bean.detection.LocationBean
import com.heshucheng.graduation.mvp.base.BasePresenter
import com.heshucheng.graduation.mvp.base.BaseView

/**
 * Created by heshu on 2018/10/18.
 */
interface DetectionContract{

    interface View :BaseView<Presenter>{
        fun acquireLocation(locationBean :LocationBean)
        fun showFault()
        fun showProvinceData( ProvinceInfoDataItem: ArrayList<ProvinceInfoData>)
        fun showSug(sug:SuggestionResult)
        fun showMarker(marks: List<MarkerBeas>)
    }

    interface Presenter: BasePresenter {
        fun requestProvinceData()
        fun requestLocation()
        fun requestSug(sug :String)
        fun requestMaker()
    }

}