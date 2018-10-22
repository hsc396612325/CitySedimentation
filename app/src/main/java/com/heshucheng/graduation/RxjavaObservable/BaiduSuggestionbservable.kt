package com.heshucheng.graduation.RxjavaObservable

import android.util.Log
import com.baidu.location.BDLocation
import com.baidu.location.BDLocationListener
import com.baidu.location.LocationClient
import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener
import com.baidu.mapapi.search.sug.SuggestionResult
import com.baidu.mapapi.search.sug.SuggestionSearch
import com.baidu.mapapi.search.sug.SuggestionSearchOption
import com.heshucheng.graduation.bean.detection.LocationBean
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.MainThreadDisposable

/**
 * Created by heshu on 2018/10/22.
 */
class BaiduSuggestionbservable(private val sug: String) : Observable<SuggestionResult>() {

    private val mSuggestionSearch = SuggestionSearch.newInstance()

    override fun subscribeActual(observer: Observer<in SuggestionResult>) {

        val listener = Listener(observer, mSuggestionSearch ,sug)
        mSuggestionSearch.setOnGetSuggestionResultListener(listener)
        mSuggestionSearch.requestSuggestion((SuggestionSearchOption())
                .keyword(sug)
                .city("全国" ))
        observer.onSubscribe(listener)

    }

    internal class Listener(private val observer: Observer<in SuggestionResult>,
                            private val mSuggestionSearch: SuggestionSearch,
                            private val sug:String) : MainThreadDisposable(), OnGetSuggestionResultListener {

        override fun onGetSuggestionResult(p0: SuggestionResult) {
            Log.d("111","111111"+p0)
            observer.onNext(p0)
        }

        override fun onDispose() {
            mSuggestionSearch.destroy();
        }

    }
}