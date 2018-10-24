package com.heshucheng.graduation.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.baidu.location.d.j.v
import com.baidu.mapapi.map.*
import com.baidu.mapapi.map.BitmapDescriptorFactory.fromResource
import com.baidu.mapapi.model.LatLng
import com.baidu.mapapi.search.sug.SuggestionResult
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.listener.OnOptionsSelectListener
import com.bigkoo.pickerview.view.OptionsPickerView
import com.heshucheng.graduation.utiles.App
import com.heshucheng.graduation.R
import com.heshucheng.graduation.R.id.*
import com.heshucheng.graduation.adapter.SeekAdapter
import com.heshucheng.graduation.bean.MarkerBeas
import com.heshucheng.graduation.bean.ProvinceInfoData
import com.heshucheng.graduation.bean.detection.LocationBean
import com.heshucheng.graduation.mvp.contract.DetectionContract
import com.heshucheng.graduation.mvp.presenter.DetectionPresenter
import kotlinx.android.synthetic.main.fragment_detection.*


import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_detection_seek.*
import java.util.*
import java.util.concurrent.TimeUnit


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


    private var recyclerView: RecyclerView? = null
    private val recyclerList = ArrayList<String>()
    private val seekAdapter = SeekAdapter(recyclerList)
    private var Res: SuggestionResult? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater!!.inflate(R.layout.fragment_detection, container, false)
        presenter.requestLocation()

        if (options1Items.size == 0 || options1Items == null) {
            presenter.requestProvinceData()
        }
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    private fun initView(view: View) {
        mMapView = view.findViewById<View>(R.id.bmapView) as MapView
        mBaiduMap = mMapView!!.map


        RxView.clicks(cvLocation)
                .throttleFirst(1, TimeUnit.SECONDS) //功能防抖，即2s内只发送第1次点击按钮的事件
                .subscribe({ e ->
                    presenter.requestLocation()
                })

        iv_select.setOnClickListener({ v ->
            pvOptions?.show() //打开省市区选择器
        })

        iv_seek.setOnClickListener({ v ->
            showSeekDialog(v)
        })

        mBaiduMap?.setOnMarkerClickListener(object : BaiduMap.OnMarkerClickListener {
            override fun onMarkerClick(p0: Marker?): Boolean {
                Log.d("1111","1111")
                showDialog()
                return false
            }
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

    //获得定位
    override fun acquireLocation(locationBean: LocationBean) {
        showLocation(locationBean.radius, locationBean.latitude, locationBean.longitude)
    }

    //显示定位
    private fun showLocation(radius: Float, latitude: Double, longitude: Double) {

        mBaiduMap!!.setMyLocationEnabled(true)

        val locData = MyLocationData.Builder().accuracy(radius)
                .direction(0f).latitude(latitude)
                .longitude(longitude).build()

        Log.d("定位点", "" + locData.latitude + "  " + locData.longitude)
        mBaiduMap!!.setMyLocationData(locData)
        val config = MyLocationConfiguration(MyLocationConfiguration.LocationMode.FOLLOWING, false, null)
        mBaiduMap!!.setMyLocationConfiguration(config)

        presenter.requestMaker() //获取点标记
    }

    //显示数据错误
    override fun showFault() {
        //Toast.makeText(App.context, "数据请求失败", Toast.LENGTH_SHORT).show()
    }

    //加载省市区的数据
    override fun showProvinceData(ProvinceInfoDataItem: ArrayList<ProvinceInfoData>) {
        for (p in ProvinceInfoDataItem) {
            options1Items.add(p.name)
            var clist = ArrayList<String>()
            var cdlist = ArrayList<ArrayList<String>>()
            for (c in p.city!!) {
                clist.add(c.name)

                var dList = ArrayList<String>()
                for (d in c.area) {
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

        }).setTitleText("城市选择")
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

        pvOptions?.setPicker(options1Items, options2Items as List<List<String>>?, options3Items as List<List<List<String>>>?)//二级选择器
    }

    //搜索弹窗
    @SuppressLint("CheckResult")
    private fun showSeekDialog(parent: View) {
        val layoutInflater = LayoutInflater.from(this.context)
        val contentView = layoutInflater.inflate(R.layout.fragment_detection_seek, null)

        val popupWindow = PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT)
        popupWindow.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        popupWindow.setTouchable(true)
        popupWindow.setFocusable(true);
        popupWindow.showAsDropDown(parent, 0, 0)


        val searchBackImage = contentView?.findViewById<ImageView>(R.id.searchBackImage)
        searchBackImage?.setOnClickListener({ v ->
            popupWindow.dismiss()
        })

        val searchKeyWord = contentView?.findViewById<EditText>(R.id.searchKeyWord)
        RxTextView.textChanges(searchKeyWord as TextView)
                .debounce(1, TimeUnit.SECONDS).skip(1) //只发送1秒内改变的值 功能防抖
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ e ->
                    presenter.requestSug(e.toString())
                    Log.d("发送给服务器的值", e.toString())
                })



        recyclerView = contentView?.findViewById<RecyclerView>(R.id.searchRecyclerView)
        val layoutManager = LinearLayoutManager(this.context)
        recyclerView?.setLayoutManager(layoutManager)
        recyclerView?.setAdapter(seekAdapter)
        recyclerList.clear()

        seekAdapter.setItemClickListener(object : SeekAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                showLocation(0f, Res?.allSuggestions?.get(position)?.pt?.latitude!!, Res?.allSuggestions?.get(position)?.pt?.longitude!!)
                popupWindow.dismiss()
            }
        })
    }


    //标记点弹窗
    private fun showDialog() {
        val layoutInflater = LayoutInflater.from(this.context)
        val contentView = layoutInflater.inflate(R.layout.fragment_detection_dialog, null)

        val popupWindow = PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
        popupWindow.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        popupWindow.setTouchable(true)
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(activity.getWindow().getDecorView(), Gravity.CENTER, 0, 0);

    }
    //显示搜索结果
    override fun showSug(res: SuggestionResult) {
        Res = res
        recyclerList.clear()
        for (re in res.allSuggestions) {
            recyclerList.add(re.key)
            Log.d("数据", re.key)
        }

        seekAdapter.notifyDataSetChanged()
    }

    override fun showMarker(marks: List<MarkerBeas>) {
        var bdN = BitmapDescriptorFactory.fromResource(R.mipmap.ic_point_normal)
        var bdW = BitmapDescriptorFactory.fromResource(R.mipmap.ic_point_warn)
        var options = ArrayList<OverlayOptions>();
        for (mark in marks) {
            var option = MarkerOptions()
                    .position(mark.latLne)
                    .icon(if (mark.normal) bdN else bdW);
            options.add(option)
        }
        mBaiduMap?.addOverlays(options)

    }
}
