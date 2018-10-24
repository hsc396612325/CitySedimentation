package com.heshucheng.graduation.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.heshucheng.graduation.R
import com.heshucheng.graduation.activity.LoginActivity
import com.heshucheng.graduation.mvp.contract.MineContract
import com.heshucheng.graduation.mvp.presenter.MinePresenter
import kotlinx.android.synthetic.main.fragment_mine.*

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
        initView(view)
    }

    private fun initView(view: View) {
        iv_head.setOnClickListener({ _ ->
            val intent = Intent(view.context, LoginActivity::class.java)
            this.context.startActivity(intent)  })

    }
}