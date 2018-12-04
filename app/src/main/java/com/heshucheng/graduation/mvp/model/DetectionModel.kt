package com.heshucheng.graduation.mvp.model

import android.content.Context
import android.util.Log
import com.baidu.location.*
import com.baidu.mapapi.model.LatLng
import com.baidu.mapapi.search.sug.SuggestionResult
import com.baidu.mapapi.search.sug.SuggestionSearch
import com.google.gson.Gson
import io.reactivex.Observable
import com.heshucheng.graduation.utiles.App
import com.heshucheng.graduation.bean.ProvinceInfoData

import com.heshucheng.graduation.bean.detection.LocationBean
import com.heshucheng.graduation.utiles.RxjavaObservable.BaiduLocationObservable
import com.heshucheng.graduation.utiles.RxjavaObservable.BaiduSuggestionbservable
import com.heshucheng.graduation.bean.MarkerBeas
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.json.JSONArray
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


/**
 * Created by heshu on 2018/10/18.
 */

class DetectionModel {
    private val mLocationClient: LocationClient = LocationClient(App.context)
    private val mSuggestionSearch = SuggestionSearch.newInstance()
    //定位初始化
    init {
        val option = LocationClientOption()
        option.isOpenGps = true // 打开gps
        option.setCoorType("bd09ll") // 设置坐标类型
        option.setScanSpan(1000)
        option.setIsNeedAddress(true)
        mLocationClient.locOption = option

    }

    //获取市
    fun loadProvinceData(): Observable<ArrayList<ProvinceInfoData>> {
        //return NetWork.service.getCity(url).io_main()

        return Observable
                .create(ObservableOnSubscribe<ArrayList<ProvinceInfoData>> { e ->
                    val str = getJson("Province.Json", App.context!!) //读取本地数据
                    val detail: ArrayList<ProvinceInfoData> = parseJsonData(str) //解析json890-=
                    Log.d("aaaaa",""+str)
                    Log.d("aaaaa",""+detail)
                    e.onNext(detail)
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())


    }

    //获取定位坐标
    fun loadLocation(): Observable<LocationBean> {
        return BaiduLocationObservable(mLocationClient)  //使用Rxjava对百度回调的接口进行了包装
    }

    //搜索联想
    fun loadSug(sug : String): Observable<SuggestionResult>{


        return BaiduSuggestionbservable(sug) //使用Rxjava对百度sug的接口进行了包装
    }

    //获取标记点
    fun loadMark() :Observable<List<MarkerBeas>>{
        return Observable
                .create({ e ->
                    var marks =  ArrayList<MarkerBeas>();
                    for(i in 0..3){
                        var mark = MarkerBeas(LatLng(34.16096+i*0.1, 108.912437+i*0.1),i%2==0)
                        marks.add(mark)
                    }
                    e.onNext(marks)
                })
    }

    //读取文件中的数据
    private fun getJson(fileName: String, context: Context): String {
        //将json数据变成字符串
        val stringBuilder = StringBuilder()
        try {
            //获取assets资源管理器
            val assetManager = context.getAssets()

            //通过管理器打开文件并读取
            val bf = BufferedReader(InputStreamReader(
                    assetManager.open(fileName)))
            var line = bf.readLine()

            while (line != null) {
                stringBuilder.append(line)
                line = bf.readLine()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return stringBuilder.toString()
    }

    //解析Json数据
    private fun parseJsonData(result: String): ArrayList<ProvinceInfoData> {
        val detail: ArrayList<ProvinceInfoData> = ArrayList<ProvinceInfoData>()

        try {
            var data = JSONArray(result)
            var gson = Gson()

            for (i in 0..(data.length() - 1)) {
                val entity: ProvinceInfoData = gson.fromJson(data.optJSONObject(i).toString(), ProvinceInfoData::class.java)
                detail.add(entity)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return detail
    }

}


