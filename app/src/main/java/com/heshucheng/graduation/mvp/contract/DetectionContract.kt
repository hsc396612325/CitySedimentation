package com.heshucheng.graduation.mvp.contract

import com.heshucheng.graduation.mvp.base.BasePresenter
import com.heshucheng.graduation.mvp.base.BaseView

/**
 * Created by heshu on 2018/10/18.
 */
interface DetectionContract{

    interface View :BaseView<Presenter>{

    }

    interface Presenter: BasePresenter {
        fun requestProvince(url : String)
        fun requestCity(url : String)
        fun requestCounty(url : String)
    }

}