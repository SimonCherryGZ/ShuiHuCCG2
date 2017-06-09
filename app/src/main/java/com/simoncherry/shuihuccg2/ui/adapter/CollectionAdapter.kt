package com.simoncherry.shuihuccg2.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.simoncherry.shuihuccg2.R
import com.simoncherry.shuihuccg2.model.CollectionBean
import kotlinx.android.synthetic.main.item_collection.view.*

/**
 * <pre>
 *     author : Donald
 *     e-mail : xxx@xx
 *     time   : 2017/06/08
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class CollectionAdapter(
        var mContext: Context,
        var mData: List<CollectionBean>) : RecyclerView.Adapter<CollectionAdapter.MyViewHolder>() {


    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(mContext.applicationContext)
                .inflate(R.layout.item_collection, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val bean = mData[position]
        val number = "No." + bean.number
        holder.tvNumber.text = number
        holder.ivCard.setImageResource(bean.resId)
        holder.tvCount.text = bean.count.toString()

        holder.ivCard.setOnClickListener {
            onItemClickListener?.onItemClick(position)
        }
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNumber: TextView = view.tv_number
        val ivCard: ImageView = view.iv_card
        val tvCount: TextView = view.tv_count
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }
}