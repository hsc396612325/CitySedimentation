package com.heshucheng.graduation.fragment

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.bigkoo.pickerview.listener.OnTimeSelectListener
import com.bigkoo.pickerview.view.TimePickerView
import com.heshucheng.graduation.R
import com.heshucheng.graduation.View.ChartUtils
import com.heshucheng.graduation.mvp.contract.SedimentationContract
import com.heshucheng.graduation.bean.gson.device_data.DeviceData
import com.heshucheng.graduation.mvp.presenter.SedimentationPresenter
import com.heshucheng.graduation.utiles.App
import com.heshucheng.graduation.utiles.MainData.ApiKey
import com.heshucheng.graduation.utiles.MainData.markRecord
import com.heshucheng.graduation.utiles.MainData.marks
import kotlinx.android.synthetic.main.fragment_sedimentation.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by heshu on 2018/10/18.
 */
class SedimentationFragment : Fragment(), SedimentationContract.View {
    private val presenter: SedimentationPresenter = SedimentationPresenter(this);
    private var timeStartView: TimePickerView? = null
    private var timeEndView: TimePickerView? = null

    private var timeStartStr: String? = null
    private var timeEndStr: String? = null
    private var limitStr: String? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_sedimentation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ChartUtils.initChart(chart)
        ChartUtils.initChart(chart2)
        ChartUtils.initChart(chart3)
        initTimePicker()

        initView(view)
    }

    private fun initView(view: View) {
        start_date.setOnClickListener({ v ->
            timeStartView?.show()
        })

        end_date.setOnClickListener({ v ->
            timeEndView?.show()
        })

        limit_et.addTextChangedListener( object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {
               limitStr = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })

        limit_et.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {
                var str = limit_et.getText().toString()
                if (str != null) {
                    limitStr = str
                }
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }

        bt.setOnClickListener({ v ->
            Log.d("sss", "111111111")
            if (timeEndStr != null && timeEndStr != null && limitStr != null) {
                presenter.requestDeviceData(ApiKey, marks.get(markRecord).id, timeStartStr, timeEndStr, limitStr)
            } else {
                Toast.makeText(App.context, "设置请求数据", Toast.LENGTH_SHORT).show()
            }
        })
    }


    private fun initTimePicker() {
        val startDate = Calendar.getInstance()
        val endDate = Calendar.getInstance()
        endDate.add(Calendar.DATE, -1)
        startDate.set(2017, 0, 1)
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val sdf2 = SimpleDateFormat("HH:MM:SS")

        timeStartView = TimePickerBuilder(this.context, OnTimeSelectListener { date, v ->

            timeStartStr = sdf.format(date) + "T" + sdf2.format(date)
            start_tv.setText(sdf.format(date) + " " + sdf2.format(date))

        }).setType(booleanArrayOf(true, true, true, true, true, true))// 默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确定")//确认按钮文字
                .setTitleSize(20)//标题文字大小
                .setTitleText("Title")//标题文字
                .setOutSideCancelable(false)//点击屏幕，点在控件外部范围时，是否取消显示
                .setBgColor(Color.BLACK)
                .setTitleBgColor(Color.DKGRAY)
                .setTitleColor(Color.LTGRAY)
                .setCancelColor(Color.WHITE)
                .setSubmitColor(Color.WHITE)
                .setTextColorCenter(Color.LTGRAY)
                .setRangDate(startDate, endDate)//起始终止年月日设定
                .setLabel("年", "月", "日", "时", "分", "秒")//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的 label 文字，false 则每项 item 全部都带有 label。
                .isDialog(true)//是否显示为对话框样式
                .build()

        timeEndView = TimePickerBuilder(this.context, OnTimeSelectListener { date, v ->
            timeEndStr = sdf.format(date) + "T" + sdf2.format(date)
            end_tv.setText(sdf.format(date) + " " + sdf2.format(date))

        }).setType(booleanArrayOf(true, true, true, true, true, true))// 默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确定")//确认按钮文字
                .setTitleSize(20)//标题文字大小
                .setTitleText("Title")//标题文字
                .setOutSideCancelable(false)//点击屏幕，点在控件外部范围时，是否取消显示
                .setBgColor(Color.BLACK)
                .setTitleBgColor(Color.DKGRAY)
                .setTitleColor(Color.LTGRAY)
                .setCancelColor(Color.WHITE)
                .setSubmitColor(Color.WHITE)
                .setTextColorCenter(Color.LTGRAY)
                .setRangDate(startDate, endDate)//起始终止年月日设定
                .setLabel("年", "月", "日", "时", "分", "秒")//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的 label 文字，false 则每项 item 全部都带有 label。
                .isDialog(true)//是否显示为对话框样式
                .build()
    }

    override fun showDeviceData(deviceDate: DeviceData) {
        val values = ArrayList<Coordinate>()
        val values2 = ArrayList<Coordinate>()
        val values3 = ArrayList<Coordinate>()

        for (data in deviceDate.data.datastreams[0].datapoints) {

            values.add(Coordinate(data.value.lat, data.at))
            values2.add(Coordinate(data.value.lon, data.at))
            values3.add(Coordinate(data.value.ele, data.at))
        }


        ChartUtils.showLineChart(values, Color.CYAN, "纬度变化", chart)
        ChartUtils.showLineChart(values2, Color.CYAN, "经度变化", chart2)
        ChartUtils.showLineChart(values3, Color.CYAN, "高度变化", chart3)
    }

    override fun showFault() {
        Toast.makeText(App.context, "数据请求失败", Toast.LENGTH_SHORT).show()
    }



}

data class Coordinate(var y: Double, var x: String)