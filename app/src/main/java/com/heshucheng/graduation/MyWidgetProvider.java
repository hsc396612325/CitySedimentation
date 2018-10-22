package com.heshucheng.graduation;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.SystemClock;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

/**
 * Created by heshu on 2018/10/22.
 */

public class MyWidgetProvider extends AppWidgetProvider {
    public static final String CLICK_ACTION = "com.heshucheng.remoteviews.click_action";
    private static final String TAG = "MyWidgetProvider";

    public MyWidgetProvider() {
        super();
    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        super.onReceive(context, intent);
        Log.d(TAG, "onReceive: " + intent.getAction());

        //这里判断是自己的action，做自己的事情，比如小部件被点击了要干什么

        if (intent.getAction().equals(CLICK_ACTION)) {
            Toast.makeText(context, "click it", Toast.LENGTH_SHORT).show();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    Bitmap srcbBitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_action_search);
                    AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
                    for (int i = 0; i < 37; i++){
                        float degree = (i*10)%360;
                        RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.widget);
                        remoteViews.setImageViewBitmap(R.id.image, rotateBitmap(context,srcbBitmap,degree));
                        Intent intentClick = new Intent();
                        intentClick.setAction(CLICK_ACTION);
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,0,intentClick,0);
                        remoteViews.setOnClickPendingIntent(R.id.image,pendingIntent);
                        appWidgetManager.updateAppWidget(new ComponentName(context,MyWidgetProvider.class),remoteViews);
                        SystemClock.sleep(30);
                    }
                }
            }).start();
        }
    }

    //每次更新都会调用
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Log.i(TAG, "onUpdate");
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
        Intent intentClick = new Intent(CLICK_ACTION);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intentClick, 0);
        remoteViews.setOnClickPendingIntent(R.id.image, pendingIntent);
        appWidgetManager.updateAppWidget(new ComponentName(context, MyWidgetProvider.class), remoteViews);

    }
    //动画
    private Bitmap rotateBitmap(Context context, Bitmap srcbitmap, float degree ) {
        Matrix matrix = new Matrix();
        matrix.reset();
        matrix.setRotate(degree);
        Bitmap temBitmap = Bitmap.createBitmap(srcbitmap,0,0,srcbitmap.getWidth(),srcbitmap.getHeight(),matrix,true);
        return temBitmap;
    }
}
