package com.heshucheng.graduation.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.baidu.location.d.j.v
import com.heshucheng.graduation.R

import com.heshucheng.graduation.mvp.contract.MineContract
import com.heshucheng.graduation.mvp.presenter.MinePresenter
import com.heshucheng.graduation.utiles.MainData
import kotlinx.android.synthetic.main.fragment_setting.*

/**
 * 关于界面
 */
class SettingFragment : Fragment(), MineContract.View {
    private val presenter: MinePresenter = MinePresenter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_setting, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    private fun initView(view: View) {
        button.setOnClickListener({ v ->
            var str = api_key.text.toString()
            if (str == null || str.equals("")) {
                Toast.makeText(context, "apikey不能为空", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "设置apikey为=" + str, Toast.LENGTH_SHORT).show()
                MainData.ApiKey = str
            }

        })
    }
}