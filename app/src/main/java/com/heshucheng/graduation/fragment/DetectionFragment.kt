package com.heshucheng.graduation.fragment

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface.NORMAL
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.baidu.location.d.j.P
import com.baidu.location.d.j.v
import com.baidu.mapapi.map.BaiduMap
import com.baidu.mapapi.map.MapView
import com.baidu.mapapi.map.MyLocationConfiguration
import com.baidu.mapapi.map.MyLocationData
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener
import com.bigkoo.pickerview.listener.OnOptionsSelectListener
import com.bigkoo.pickerview.view.OptionsPickerView
import com.heshucheng.graduation.App
import com.heshucheng.graduation.R
import com.heshucheng.graduation.bean.ProvinceInfoData
import com.heshucheng.graduation.bean.detection.LocationBean
import com.heshucheng.graduation.mvp.contract.DetectionContract
import com.heshucheng.graduation.mvp.presenter.DetectionPresenter
import kotlinx.android.synthetic.main.fragment_detection.*


import com.heshucheng.graduation.bean.db.Province
import com.yanzhenjie.alertdialog.AlertDialog.build
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.*


/**
 * Created by heshu on 2018/10/18.
 */

class DetectionFragment : Fragment(), DetectionContract.View {

    internal var mMapView: MapView? = null
    internal var mBaiduMap: BaiduMap? = null

    private var pvOptions: OptionsPickerView<String>? = null
    private val presenter: DetectionPresenter = DetectionPresenter(this)
    private val options1Items = ArrayList<String>()
    private val options2Items = ArrayList<ArrayList<String>>()
    private val options3Items = ArrayList<ArrayList<ArrayList<String>>>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater!!.inflate(R.layout.fragment_detection, container, false)
        presenter.requestLocation()
        presenter.requestProvinceData()
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    private fun initView(view: View) {
        mMapView = view.findViewById<View>(R.id.bmapView) as MapView
        mBaiduMap = mMapView!!.map


        cvLocation.setOnClickListener({ v ->
            presenter.requestLocation() //定位
            pvOptions?.show()
        })

    }

    override fun onResume() {
        super.onResume()
        //在Fragment执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView!!.onResume()
    }

    override fun onPause() {
        super.onPause()
        //在<span style="font-family: 微软雅黑, 'Microsoft YaHei', sans-serif;">Fragment</span>执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView!!.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        //在Fragment执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView!!.onDestroy()
    }

    //显示定位
    override fun showLocation(locationBean: LocationBean) {

        mBaiduMap!!.setMyLocationEnabled(true)

        val locData = MyLocationData.Builder().accuracy(locationBean.radius)
                .direction(0f).latitude(locationBean.latitude)
                .longitude(locationBean.longitude).build()

        Log.d("sssss", "" + locData.latitude + "  " + locData.longitude)
        mBaiduMap!!.setMyLocationData(locData)
        val config = MyLocationConfiguration(MyLocationConfiguration.LocationMode.FOLLOWING, false, null)
        mBaiduMap!!.setMyLocationConfiguration(config)


    }

    //显示数据错误
    override fun showFault() {
        Toast.makeText(App.context, "数据请求失败", Toast.LENGTH_SHORT).show()
    }

    override fun showProvinceData(ProvinceInfoDataItem: ArrayList<ProvinceInfoData>) {
        for (p in ProvinceInfoDataItem){
            options1Items.add(p.name)
            var clist = ArrayList<String>()
            var cdlist = ArrayList<ArrayList<String>>()
            for ( c in p.city!! ){
                clist.add(c.name)

                var dList = ArrayList<String>()
                for(d in c.area){
                    dList.add(d)
                }
                cdlist.add(dList)
            }
            options2Items.add(clist)
            options3Items.add(cdlist)
        }

        initOptionPicker()
    }

    //城市选择器初始化
    private fun initOptionPicker() {

        pvOptions = OptionsPickerBuilder(this.context, OnOptionsSelectListener { options1, options2, options3, v ->
            //返回的分别是三个级别的选中位置
            val tx = options1Items[options1] + options2Items[options1][options2]/* + options3Items.get(options1).get(options2).get(options3).getPickerViewText()*/

        })
                .setTitleText("城市选择")
                .setContentTextSize(20)//设置滚轮文字大小
                .setDividerColor(Color.LTGRAY)//设置分割线的颜色
                .setSelectOptions(0, 1)//默认选中项
                .setBgColor(Color.BLACK)
                .setTitleBgColor(Color.DKGRAY)
                .setTitleColor(Color.LTGRAY)
                .setCancelColor(Color.WHITE)
                .setSubmitColor(Color.WHITE)
                .setTextColorCenter(Color.LTGRAY)
                .isRestoreItem(true)//切换时是否还原，设置默认选中第一项。
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .build()

        pvOptions?.setPicker(options1Items, options2Items as List<List<String>>?,options3Items as List<List<List<String>>>? )//二级选择器
    }

    //数据初始化
   // private fun getOptionData() {
//
//        /**
//         * 注意：如果是添加JavaBean实体数据，则实体类需要实现 IPickerViewData 接口，
//         * PickerView会通过getPickerViewText方法获取字符串显示出来。
//         */
//
//
//        //选项1
//        options1Items.add(" s")
//        options1Items.add(" s")
//        options1Items.add("  s")
//
//        //选项2
//        val options2Items_01 = ArrayList<String>()
//        options2Items_01.add("广州")
//        options2Items_01.add("佛山")
//        options2Items_01.add("东莞")
//        options2Items_01.add("珠海")
//        val options2Items_02 = ArrayList<String>()
//        options2Items_02.add("长沙")
//        options2Items_02.add("岳阳")
//        options2Items_02.add("株洲")
//        options2Items_02.add("衡阳")
//        val options2Items_03 = ArrayList<String>()
//        options2Items_03.add("桂林")
//        options2Items_03.add("玉林")
//        options2Items.add(options2Items_01)
//        options2Items.add(options2Items_02)
//        options2Items.add(options2Items_03)

        /*--------数据源添加完毕---------*/
//    }

    fun getJson(fileName: String, context: Context): String {
        //将json数据变成字符串
        val stringBuilder = StringBuilder()
        try {
            //获取assets资源管理器
            val assetManager = context.getAssets()

            //通过管理器打开文件并读取
            val bf = BufferedReader(InputStreamReader(
                    assetManager.open(fileName)))
            var line: String = ""
            Log.d("wenj",""+bf)
            do{
                line = bf.readLine()
                stringBuilder.append(line)
            } while (line!= null)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return stringBuilder.toString()
    }

}
