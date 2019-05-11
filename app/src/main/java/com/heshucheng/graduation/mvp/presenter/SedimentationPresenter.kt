package com.heshucheng.graduation.mvp.presenter

import android.util.Log
import com.heshucheng.graduation.mvp.contract.SedimentationContract

import com.heshucheng.graduation.mvp.model.SedimentationModel

/**
 * Created by heshu on 2018/10/18.
 */
class SedimentationPresenter(view: SedimentationContract.View) : SedimentationContract.Presenter {
    private val sedimentationView: SedimentationContract.View = view
    private val sedimentationModel by lazy {  SedimentationModel() }

    override fun requestDeviceData(apiKey:String?,id:String,start:String?,end:String?,limit:String?){


        sedimentationModel.getDeviceData(apiKey,id,start, end, limit)
                .subscribe({ it ->
                    if (it != null) {
                       // Log.d("aaaa内容",""+it.data.datastreams[0].datapoints[0].value.lat)

                        sedimentationView.showDeviceData(it)
                    }
                }, { e ->
                    Log.d("aaaa错误",""+e)
                    sedimentationView.showFault()
                })
    }
}