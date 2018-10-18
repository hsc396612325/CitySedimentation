package com.heshucheng.graduation.fragment


import android.os.Bundle
import android.support.v4.app.Fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.heshucheng.graduation.R
import com.heshucheng.graduation.mvp.contract.DetectionContract
import com.heshucheng.graduation.mvp.presenter.DetectionPresenter

/**
 * Created by heshu on 2018/10/18.
 */
class DetectionFragment:Fragment(),DetectionContract.View{
    private val presenter :DetectionPresenter = DetectionPresenter(this)

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_detection,container,false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}