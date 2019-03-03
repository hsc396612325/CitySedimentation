package com.heshucheng.graduation.mvp.contract

import com.heshucheng.graduation.mvp.base.BasePresenter
import com.heshucheng.graduation.mvp.base.BaseView
import com.heshucheng.graduation.mvp.model.gson.device_data.DeviceData

/**
 * Created by heshu on 2018/10/18.
 */
interface SedimentationContract{
    interface View : BaseView<Presenter> {
        fun showDeviceData(deviceDate:DeviceData)
        fun showFault()
    }

    interface Presenter: BasePresenter {
        fun requestDeviceData(apiKey:String?,id:String,start:String?,end:String?,limit:String?)
    }
}