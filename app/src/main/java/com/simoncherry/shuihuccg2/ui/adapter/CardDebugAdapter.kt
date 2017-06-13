package com.simoncherry.shuihuccg2.ui.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.simoncherry.shuihuccg2.R
import com.simoncherry.shuihuccg2.model.Card
import io.realm.RealmList
import io.realm.RealmRecyclerViewAdapter
import kotlinx.android.synthetic.main.item_card_debug.view.*

/**
 * <pre>
 *     author : Donald
 *     e-mail : xxx@xx
 *     time   : 2017/06/12
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class CardDebugAdapter(
        var mContext: Context,
        var mData: RealmList<Card>
) : RealmRecyclerViewAdapter<Card, CardDebugAdapter.MyViewHolder>(mData, true) {

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(mContext.applicationContext)
                .inflate(R.layout.item_card_debug, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val card = mData[position]
        val count = card.count
        if (count > 0) {
            holder.layoutRoot.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorAccent))
        } else {
            holder.layoutRoot.setBackgroundColor(0)
        }
        holder.tvIndex.text = card.index.toString()
        holder.tvCount.text = card.count.toString()
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val layoutRoot = view.layoutRoot!!
        val tvIndex = view.tvIndex!!
        val tvCount = view.tvCount!!
    }
}