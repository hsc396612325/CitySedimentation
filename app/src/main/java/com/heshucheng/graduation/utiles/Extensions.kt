package com.heshucheng.graduation.utiles

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * Created by heshu on 2018/10/18.
 */
fun <T> Observable<T>.io_main(): Observable<T> {
    return subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}


