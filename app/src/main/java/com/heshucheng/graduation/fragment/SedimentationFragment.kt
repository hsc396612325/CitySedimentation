package com.heshucheng.graduation.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.data.Entry
import com.heshucheng.graduation.R
import com.heshucheng.graduation.View.ChartUtils
import com.heshucheng.graduation.mvp.contract.SedimentationContract
import com.heshucheng.graduation.mvp.presenter.SedimentationPresenter
import kotlinx.android.synthetic.main.fragment_sedimentation.*
import java.util.ArrayList

/**
 * Created by heshu on 2018/10/18.
 */
class SedimentationFragment:Fragment(),SedimentationContract.View{
    private val presenter :SedimentationPresenter = SedimentationPresenter(this);

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_sedimentation,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ChartUtils.initChart(chart)
        ChartUtils.notifyDataSetChanged(chart, getData(), ChartUtils.dayValue)
    }

    private fun getData(): List<Entry> {
        val values = ArrayList<Entry>()
        values.add(Entry(0F, 15F))
        values.add(Entry(1F, 15F))
        values.add(Entry(2F, 15F))
        values.add(Entry(3F, 20F))
        values.add(Entry(4F, 8F))
        values.add(Entry(5F, 20F))
        values.add(Entry(6F, 20F))
        return values
    }
}