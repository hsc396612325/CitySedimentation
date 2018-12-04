package com.heshucheng.graduation.mvp.presenter

import com.heshucheng.graduation.mvp.contract.SedimentationContract

import com.heshucheng.graduation.mvp.model.SedimentationModel

/**
 * Created by heshu on 2018/10/18.
 */
class SedimentationPresenter(view: SedimentationContract.View) : SedimentationContract.Presenter {
    private val sedimentationView: SedimentationContract.View = view
    private val sedimentationModel by lazy {  SedimentationModel() }


}