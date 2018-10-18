package com.heshucheng.graduation.mvp.presenter

import com.heshucheng.graduation.mvp.contract.DetectionContract
import com.heshucheng.graduation.mvp.model.DetectionModel

/**
 * Created by heshu on 2018/10/18.
 */
class DetectionPresenter(view :DetectionContract.View):DetectionContract.Presenter{
    private val detectionView: DetectionContract.View = view
    private val detectionModel by lazy { DetectionModel() }

    override fun requestCity(url: String) {


    }

    override fun requestCounty(url: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun requestProvince(url: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
