package com.heshucheng.graduation.mvp.model

import com.baidu.location.*
import com.baidu.mapapi.search.sug.SuggestionSearch
import io.reactivex.Observable
import com.heshucheng.graduation.utiles.App

import com.heshucheng.graduation.bean.detection.LocationBean
import com.heshucheng.graduation.utiles.RxjavaObservable.BaiduLocationObservable
import com.heshucheng.graduation.bean.gson.area.Area
import com.heshucheng.graduation.utiles.io_main
import com.heshucheng.graduation.utiles.net.NetWork


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

//    //获取市
//    fun loadProvinceData(): Observable<ArrayList<ProvinceInfoData>> {
//        //return NetWork.service.getCity(url).io_main()
//
//        return Observable
//                .create(ObservableOnSubscribe<ArrayList<ProvinceInfoData>> { e ->
//                    val str = getJson("Province.Json", App.context!!) //读取本地数据
//                    val detail: ArrayList<ProvinceInfoData> = parseJsonData(str) //解析json890-=
//                    e.onNext(detail)
//                })
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//
//
//    }

    //    //读取文件中的数据
//    private fun getJson(fileName: String, context: Context): String {
//        //将json数据变成字符串
//        val stringBuilder = StringBuilder()
//        try {
//            //获取assets资源管理器
//            val assetManager = context.getAssets()
//
//            //通过管理器打开文件并读取
//            val bf = BufferedReader(InputStreamReader(
//                    assetManager.open(fileName)))
//            var line = bf.readLine()
//
//            while (line != null) {
//                stringBuilder.append(line)
//                line = bf.readLine()
//            }
//        } catch (e: IOException) {
//            e.printStackTrace()
//        }
//
//        return stringBuilder.toString()
//    }
//
//    //解析Json数据
//    private fun parseJsonData(result: String): ArrayList<ProvinceInfoData> {
//        val detail: ArrayList<ProvinceInfoData> = ArrayList<ProvinceInfoData>()
//
//        try {
//            var data = JSONArray(result)
//            var gson = Gson()
//
//            for (i in 0..(data.length() - 1)) {
//                val entity: ProvinceInfoData = gson.fromJson(data.optJSONObject(i).toString(), ProvinceInfoData::class.java)
//                detail.add(entity)
//            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//        return detail
//    }

    //获取定位坐标
    fun loadLocation(): Observable<LocationBean> {
        return BaiduLocationObservable(mLocationClient)  //使用Rxjava对百度回调的接口进行了包装
    }

//    //搜索联想
//    fun loadSug(sug : String): Observable<SuggestionResult>{
//        return BaiduSuggestionbservable(sug) //使用Rxjava对百度sug的接口进行了包装
//    }

    fun getArea(apiKay:String?):Observable<Area>{
        val url="devices"

        return  NetWork.service.getArea(url,apiKay).io_main()
    }

    fun getEquipment(apiKay:String?,tag:String):Observable<Area>{
        val url="devices?tag="+tag
        return NetWork.service.getEquipment(url,apiKay).io_main()
    }


}


