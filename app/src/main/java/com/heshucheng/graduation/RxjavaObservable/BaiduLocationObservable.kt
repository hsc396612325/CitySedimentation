package com.heshucheng.graduation.RxjavaObservable

import com.baidu.location.BDLocation
import com.baidu.location.BDLocationListener
import com.baidu.location.LocationClient
import com.heshucheng.graduation.bean.detection.LocationBean
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.MainThreadDisposable

/**
 * Created by heshu on 2018/10/22.
 */
class BaiduLocationObservable(locationClient: LocationClient) : Observable<LocationBean>() {
    var mLocationClient = locationClient

    override fun subscribeActual(observer: Observer<in LocationBean>) {
        val listener = Listener(observer, mLocationClient)
        observer.onSubscribe(listener)
        mLocationClient.start()
        mLocationClient.registerLocationListener(listener)
    }

    internal class Listener(private val observer: Observer<in LocationBean>,locationClient: LocationClient) : MainThreadDisposable(), BDLocationListener {
        var mLocationClient = locationClient
        override fun onReceiveLocation(p0: BDLocation?) {
            val locationBean = LocationBean(
                    p0!!.latitude,
                    p0.longitude,
                    p0.radius,
                    p0.province,
                    p0.city,
                    p0.district,
                    p0.street
            )
            observer.onNext(locationBean)
            mLocationClient.stop()
        }

        override fun onDispose() {
            mLocationClient.stop()
        }

    }
}