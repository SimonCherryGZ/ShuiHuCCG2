package com.simoncherry.shuihuccg2.ui.fragment

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager.VERTICAL
import com.simoncherry.shuihuccg2.R
import com.simoncherry.shuihuccg2.model.CollectionBean
import com.simoncherry.shuihuccg2.ui.adapter.CollectionAdapter
import kotlinx.android.synthetic.main.fragment_collection.*
import java.util.ArrayList

/**
 * <pre>
 *     author : Donald
 *     e-mail : xxx@xx
 *     time   : 2017/06/08
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class CollectionFragment : BaseFragment() {

    private lateinit var mAdapter: CollectionAdapter
    private lateinit var mData: MutableList<CollectionBean>
    private var mCurrentPage = 1

    companion object {
        fun newInstance(): CollectionFragment {
            val fragment = CollectionFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getLayout(): Int {
        return R.layout.fragment_collection
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    private fun init() {
        initView()
        initRecyclerView()
    }

    private fun initView() {
        setPagePointer(mCurrentPage)

        arrowLeft.setOnClickListener {
            if (mCurrentPage >= 2) {
                mCurrentPage--
            } else {
                mCurrentPage = 18
            }
            setPagePointer(mCurrentPage)
            showCardByPage(mCurrentPage)
        }

        arrowRight.setOnClickListener {
            if (mCurrentPage <= 17) {
                mCurrentPage++
            } else {
                mCurrentPage = 1
            }
            setPagePointer(mCurrentPage)
            showCardByPage(mCurrentPage)
        }
    }

    private fun initRecyclerView() {
        mData = ArrayList<CollectionBean>()
        mAdapter = CollectionAdapter(mContext, mData)
        //rvCollection.layoutManager = GridLayoutManager(mContext, 2)
        rvCollection.layoutManager = StaggeredGridLayoutManager(2, VERTICAL)
        rvCollection.adapter = mAdapter

        showCardByPage(mCurrentPage)
    }

    private fun setPagePointer(page: Int) {
        val pagePointer = page.toString() + " / 18"
        tvPagePointer.text = pagePointer
    }

    private fun showCardByPage(page: Int) {
        mData.clear()
        for (i in 1..6) {
            val number = (page - 1) * 6 + i
            val count = 99

            val name: String
            if (count > 0) {
                name = "front" + number.toString()
            } else {
                name = "front0"
            }
            val resId = resources.getIdentifier(name, "drawable", this.activity.packageName)

            val bean = CollectionBean(resId, number, count)
            mData.add(bean)
        }
        mAdapter.notifyDataSetChanged()
    }
}