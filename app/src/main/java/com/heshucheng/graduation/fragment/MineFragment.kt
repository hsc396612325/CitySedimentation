package com.heshucheng.graduation.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.heshucheng.graduation.R
import com.heshucheng.graduation.mvp.contract.MineContract
import com.heshucheng.graduation.mvp.presenter.MinePresenter
/**
 * Created by heshu on 2018/10/18.
 */
class MineFragment : Fragment(), MineContract.View {
    private val presenter : MinePresenter = MinePresenter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_mine,container,false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}