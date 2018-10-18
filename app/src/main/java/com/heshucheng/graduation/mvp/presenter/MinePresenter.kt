package com.heshucheng.graduation.mvp.presenter

import com.heshucheng.graduation.mvp.contract.MineContract
import com.heshucheng.graduation.mvp.model.MineModel

/**
 * Created by heshu on 2018/10/18.
 */
class MinePresenter(view: MineContract.View): MineContract.Presenter{
    private val mineView : MineContract.View = view
    private val mineModel by lazy { MineModel() }
}