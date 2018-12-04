package com.heshucheng.graduation.mvp.presenter

import android.util.Log
import com.heshucheng.graduation.mvp.contract.DetectionContract
import com.heshucheng.graduation.mvp.model.DetectionModel
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by heshu on 2018/10/18.
 */
class DetectionPresenter(view: DetectionContract.View) : DetectionContract.Presenter {
    private val detectionView: DetectionContract.View = view
    private val detectionModel by lazy { DetectionModel() }
    private val compositeDisposable = CompositeDisposable()
    override fun requestProvinceData() {
       detectionModel.loadProvinceData()
                .subscribe({ it ->
                    Log.d("数据", "" + it)
                    detectionView.showProvinceData(it)

                }, { e ->
                    Log.e("错误", "" + e)
                    detectionView.showFault()
                })
    }


    override fun requestLocation() {
        detectionModel.loadLocation()
                .subscribe({ it ->
                    detectionView.acquireLocation(it)
                }, { e ->
                    detectionView.showFault()
                })
    }

    override fun requestSug(sug: String) {
        detectionModel.loadSug(sug)
                .subscribe({ it ->
                    if (it != null) {
                        detectionView.showSug(it)
                    }
                }, { e ->
                    detectionView.showFault()
                })
    }

    override fun requestMaker() {
        detectionModel.loadMark()
                .subscribe({ it ->
                    if (it != null) {
                        detectionView.showMarker(it)
                    }
                }, { e ->
                    detectionView.showFault()
                })
    }


}
