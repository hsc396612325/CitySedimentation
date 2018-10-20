package com.heshucheng.graduation.mvp.presenter

import android.util.Log
import com.heshucheng.graduation.mvp.contract.DetectionContract
import com.heshucheng.graduation.mvp.model.DetectionModel

/**
 * Created by heshu on 2018/10/18.
 */
class DetectionPresenter(view: DetectionContract.View) : DetectionContract.Presenter {
    private val detectionView: DetectionContract.View = view
    private val detectionModel by lazy { DetectionModel() }

    override fun requestProvinceData() {
        detectionModel.loadProvinceData()
                .subscribe({ it ->
                    detectionView.showProvinceData(it)
                    Log.d("数据",""+it)
                }, { e ->
                    Log.e("错误",""+e)
                    detectionView.showFault()
                })
    }


    override fun requestLocation() {
        detectionModel.loadlocation()
                .subscribe({ it ->
                    detectionView.showLocation(it)
                }, { e ->
                    detectionView.showFault()
                })
    }
}
