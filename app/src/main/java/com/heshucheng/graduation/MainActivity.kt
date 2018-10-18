package com.heshucheng.graduation

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.tabview.view.*
import kotlinx.android.synthetic.main.toolbar.*


/**
 * 主页面
 * 主要实现底部导航栏
 * 将toolbar放在主页面上
 */
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbar()
        initView()
    }
    private fun initToolbar() {
        toolbarTitle.text = "open eyes"
        toolbar.inflateMenu(R.menu.toolbar_item)
        toolbar.setBackgroundColor(Color.WHITE)
        toolbar.setTitleTextColor(Color.BLACK)
        toolbarTitle.setTextColor(Color.BLACK)
        toolbarTitle.typeface = Typeface.createFromAsset(this.assets, "fonts/Lobster-1.4.otf")
        toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
            // R.id.app_bar_search -> startActivity<SearchActivity>()
            }
            true
        }
    }

    private fun onToolbarSelected(position: Int){  //改变toolbar
        when(position){
            0 ->{toolbar.menu.getItem(0).setIcon(R.mipmap.ic_action_search)
                toolbar.background.alpha = 0
                toolbarTitle.text = "detection"
                toolbar.title = "检测区域"
                toolbar.setOnClickListener {
                    // toast("6666")
                }
            }
            1 ->{ toolbar.menu.getItem(0).setIcon(R.mipmap.ic_action_search)
                toolbar.background.alpha = 255
                toolbarTitle.text = "sedimentation"
                toolbar.title = "沉降信息"
                toolbar.setOnClickListener {
                    //startActivity<CategoryActivity>()
                }
            }
            2 ->{toolbar.menu.getItem(0).setIcon(R.mipmap.ic_action_search)
                toolbar.background.alpha = 255
                toolbarTitle.text = "My"
                toolbar.title = "我的信息"
                toolbar.setOnClickListener {
                    // startActivity<PgcsAllActivity>()
                }
            }
        }
    }
    private fun initView(){

        mainTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                onTabItemSelected(tab.position)  //加载fragment
                // Tab 选中之后，改变各个Tab的状态
                for (i in 0 until mainTabLayout.tabCount ) {
                    val view = mainTabLayout.getTabAt(i)!!.customView

                    if (i == tab.position) { // 选中状态
                        view?.tabContentImage?.setImageResource(MainData.mainTabResPressed[i])
                        view?.tabContentText?.setTextColor(Color.BLACK)

                    } else {// 未选中状态
                        view?.tabContentImage?.setImageResource(MainData.mainTabRes[i])
                        view?.tabContentText?.setTextColor(Color.DKGRAY)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

        for(i in 0..2) {
            mainTabLayout.addTab(mainTabLayout.newTab().setCustomView(getTabView(this, i)))
        }
    }

    private fun getTabView(context: Context, position: Int): View {
        val view = LayoutInflater.from(context).inflate(R.layout.tabview, null)
        view.tabContentImage.setImageResource(MainData.mainTabRes[position])
        view.tabContentText.text = MainData.mainTabStr[position]
        return view
    }
    fun onTabItemSelected(position: Int){
//        val transaction = fragmentManager.beginTransaction()
        val transaction = supportFragmentManager.beginTransaction()//v4 使用supportFragmentManager
        /*when(position){
            0 -> transaction.replace(R.id.main_container, HomeFragment()).commit()
            1 -> transaction.replace(R.id.main_container, DiscoverFragment()).commit()
            2 -> transaction.replace(R.id.main_container, FollowFragment()).commit()
            3 -> transaction.replace(R.id.main_container, MineFragment()).commit()
        }*/
        transaction.replace(R.id.mainContainer, MainData.mainFragmentList[position] as Fragment?).commit()
        onToolbarSelected(position)
    }

    private var exitTime: Long = 0

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - exitTime > 2000) {
                Toast.makeText(applicationContext, "再按一次退出程序", Toast.LENGTH_SHORT).show()
                exitTime = System.currentTimeMillis()
            } else {
                finish()
                System.exit(0)
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}