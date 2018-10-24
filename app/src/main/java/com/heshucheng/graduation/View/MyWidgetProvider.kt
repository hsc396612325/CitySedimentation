package com.heshucheng.graduation.View

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.SystemClock
import android.util.Log
import android.widget.RemoteViews
import android.widget.Toast

import com.heshucheng.graduation.R

/**
 * Created by heshu on 2018/10/22.
 */

class MyWidgetProvider : AppWidgetProvider() {

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        Log.d(TAG, "onReceive: " + intent.action!!)

        //这里判断是自己的action，做自己的事情，比如小部件被点击了要干什么

        if (intent.action == CLICK_ACTION) {
            Toast.makeText(context, "click it", Toast.LENGTH_SHORT).show()

            Thread(Runnable {
                val manager = AppWidgetManager.getInstance(context)
                val remoteViews = RemoteViews(context.packageName, R.layout.widget)

                for (i in 0..99) { //界面更新
                    remoteViews.setTextViewText(R.id.tv, i.toString() + "")// 设置数字
                    // 调用updateAppWidget去更新RemoteViews
                    manager.updateAppWidget(ComponentName(context, MyWidgetProvider::class.java), remoteViews)
                    SystemClock.sleep(18)
                }

                remoteViews.setTextViewText(R.id.tv, "警报")
                remoteViews.setTextColor(R.id.tv, Color.parseColor("#ff0a00"))

                // 调用updateAppWidget去更新RemoteViews
                manager.updateAppWidget(ComponentName(context, MyWidgetProvider::class.java), remoteViews)
            }).start()

        }
    }

    //每次更新都会调用
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager,
                          appWidgetIds: IntArray) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        val count = appWidgetIds.size
        Log.d(TAG, "onUpdate: " + count)

        for (appWidgetId in appWidgetIds) {
            onWidgetUpdate(context, appWidgetManager, appWidgetId)
        }

    }
    //动画

    private fun onWidgetUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
        Log.d(TAG, "onWidgetUpdate: " + appWidgetId)
        val remoteViews = RemoteViews(context.packageName, R.layout.widget)
        val intent = Intent(context, MyWidgetProvider::class.java)
        intent.action = CLICK_ACTION
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)

        // 保持Button设置点击事件,触发一个Intent,这里是发送广播
        remoteViews.setOnClickPendingIntent(R.id.im, pendingIntent)
        appWidgetManager.updateAppWidget(appWidgetId, remoteViews)
        //        PendingIntent i = PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), 0);
        //        remoteViews.setOnClickPendingIntent(R.id.im,i);
        //        //启动activity
        //        appWidgetManager.updateAppWidget(appWidgetId, remoteViews);

    }

    companion object {
        val CLICK_ACTION = "com.heshucheng.graduation.click_action"
        private val TAG = "MyWidgetProvider"
    }


}
