package com.heshucheng.graduation.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import com.baidu.location.d.j.v
import com.heshucheng.graduation.R

/**
 * Created by heshu on 2018/10/22.
 */
class SeekAdapter(list:List<String>): RecyclerView.Adapter<SeekAdapter.SeekHolder>(){

    private var strList = list
    private var mItemClickListener: OnItemClickListener? = null
    class SeekHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        var textView: TextView

        init {
            textView = mView.findViewById<TextView>(R.id.tv_data)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SeekHolder {
        val view = LayoutInflater.from(parent?.getContext()).inflate(R.layout.fragment_dataction_seek_item, parent, false)
        view.setOnClickListener({ v ->
            mItemClickListener?.onItemClick(v,viewType)
        })
        Log.d("sss","s"+viewType)
        return SeekHolder(view)
    }

    override fun onBindViewHolder(holder: SeekHolder?, position: Int) {
        holder?.textView?.setText(strList.get(position))
    }

    override fun getItemCount(): Int {
       return strList.size
    }

    interface OnItemClickListener {
        fun onItemClick(view: View,position :Int)
    }

    fun setItemClickListener(itemClickListener: OnItemClickListener) {
        mItemClickListener = itemClickListener
    }
}