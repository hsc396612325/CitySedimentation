package com.heshucheng.graduation.utiles

import com.heshucheng.graduation.R
import com.heshucheng.graduation.fragment.DetectionFragment
import com.heshucheng.graduation.fragment.MineFragment
import com.heshucheng.graduation.fragment.SedimentationFragment

/**
 * Created by heshu on 2018/10/18.
 */
object MainData {
    val mainFragmentList = arrayOf(DetectionFragment(),SedimentationFragment(),MineFragment())
    val mainTabRes = listOf(R.mipmap.ic_tab_home, R.mipmap.ic_tab_find,
            R.mipmap.ic_tab_follow)
    val mainTabResPressed = listOf(R.mipmap.ic_tab_home_selected, R.mipmap.ic_tab_find_selected,
            R.mipmap.ic_tab_follow_selected)
    val mainTabStr = listOf("检测", "信息", "我的")
}