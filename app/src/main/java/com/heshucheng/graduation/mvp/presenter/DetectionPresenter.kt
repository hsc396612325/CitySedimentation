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

//    override fun requestProvinceData() {
//       detectionModel.loadProvinceData()
//                .subscribe({ it ->
//
//                    detectionView.showProvinceData(it)
//
//                }, { e ->
//                    Log.e("错误", "" + e)
//                    detectionView.showFault()
//                })
//    }


    override fun requestLocation() {
        detectionModel.loadLocation()
                .subscribe({ it ->
                    detectionView.acquireLocation(it)
                }, { e ->
                    detectionView.showFault()
                })
    }

//    override fun requestSug(sug: String) {
//        detectionModel.loadSug(sug)
//                .subscribe({ it ->
//                    if (it != null) {
//                        detectionView.showSug(it)
//                    }
//                }, { e ->
//                    detectionView.showFault()
//                })
//    }
//

    override fun requestArea(apiKay:String?) {
        detectionModel.getArea(apiKay)
                .subscribe({ it ->
                    Log.d("aaaa区域","sssss")
                    if (it != null) {
                  //      Log.d("aaaa区域",""+it.data.devices[1].tags[1])
                        detectionView.showArea(it)
                    }
                }, { e ->
                    Log.d("aaaa区域",""+e)
                    detectionView.showApiKeyFault()
                })
    }

    override fun requestEquipment(apiKay:String?,tag:String){
        detectionModel.getEquipment(apiKay,tag)
                .subscribe({ it ->
                    if (it != null) {
                        detectionView.showEquipment(it)
                       // Log.d("aaaa设备",""+it.data.devices[0].id);
                    }
                }, { e ->
                    detectionView.showFault()
                })
    }


}
