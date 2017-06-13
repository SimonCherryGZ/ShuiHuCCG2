package com.simoncherry.shuihuccg2.ui.fragment

import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager.VERTICAL
import android.view.View
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import com.simoncherry.shuihuccg2.R
import com.simoncherry.shuihuccg2.model.Card
import com.simoncherry.shuihuccg2.model.CollectionBean
import com.simoncherry.shuihuccg2.ui.adapter.CollectionAdapter
import com.simoncherry.shuihuccg2.util.Rotate3d
import com.simoncherry.shuihuccg2.util.getDrawableResId
import com.simoncherry.shuihuccg2.util.setSampledBitmap
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.fragment_collection.*
import java.util.*


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

    private lateinit var realm: Realm
    private lateinit var realmResults: RealmResults<Card>
    private lateinit var mAdapter: CollectionAdapter
    private lateinit var mData: MutableList<CollectionBean>
    private var mCurrentPage = 1
    private var mCurrentIndex = 0
    private var mIsFrontFace = true

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

    override fun onDestroy() {
        super.onDestroy()
        realmResults.removeAllChangeListeners()
        realm.close()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    private fun init() {
        initView()
        initRecyclerView()
        initRealm()
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

        ivMask.setOnClickListener {
            ivDetail.setImageResource(0)
            ivDetail.visibility = View.GONE
            ivMask.visibility = View.GONE
            ivMask.isClickable = false
            layoutDetail.visibility = View.GONE
            mIsFrontFace = true
        }

        ivDetail.setOnClickListener {
            if(mIsFrontFace){
                mIsFrontFace = false
                applyRotateToBack(250, mCurrentIndex)
            }else{
                mIsFrontFace = true
                applyRotateToFront(250, mCurrentIndex)
            }
        }
    }

    private fun initRealm() {
        realm = Realm.getDefaultInstance()
        realmResults = realm.where(Card::class.java).equalTo("playerId", 0).findAllAsync()
        realmResults.addChangeListener { _ ->
            showCardByPage(mCurrentPage)
        }
    }

    private fun initRecyclerView() {
        mData = ArrayList<CollectionBean>()
        mAdapter = CollectionAdapter(mContext, mData)
        mAdapter.setOnItemClickListener(object : CollectionAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                if (mData.size > position) {
                    val count = mData[position].count
                    if (count > 0) {
                        mCurrentIndex = (mCurrentPage - 1) * 6 + position + 1
                        ivDetail.setSampledBitmap(mData[position].resId, 200, 300)
                        ivDetail.visibility = View.VISIBLE
                        ivMask.visibility = View.VISIBLE
                        ivMask.isClickable = true
                        layoutDetail.visibility = View.VISIBLE
                    }
                }
            }
        })
        rvCollection.layoutManager = StaggeredGridLayoutManager(2, VERTICAL)
        rvCollection.adapter = mAdapter

        //showCardByPage(mCurrentPage)
    }

    private fun setPagePointer(page: Int) {
        val pagePointer = page.toString() + " / 18"
        tvPagePointer.text = pagePointer
    }

    private fun showCardByPage(page: Int) {
        mData.clear()
        for (i in 1..6) {
            val number = (page - 1) * 6 + i
            val count = realmResults[number-1].count

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

    private fun applyRotateToBack(duration: Int, index: Int) {
        val centerX = ivDetail.width / 2.0f
        val centerY = ivDetail.height / 2.0f

        val rotateTo180 = Rotate3d(270f, 360f, centerX, centerY, 0f, true)
        rotateTo180.duration = duration.toLong()
        rotateTo180.fillAfter = true

        val rotateTo90 = Rotate3d(0f, 90f, centerX, centerY, 0f, true)
        rotateTo90.duration = duration.toLong()
        rotateTo90.setAnimationListener(object : AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                ivDetail.setSampledBitmap(getDrawableResId(mContext, "back", index), 200 ,300)
                layoutDetail.startAnimation(rotateTo180)
            }
            override fun onAnimationRepeat(animation: Animation) {}
        })

        layoutDetail.startAnimation(rotateTo90)
    }

    private fun applyRotateToFront(duration: Int, index: Int) {
        val centerX = ivDetail.width / 2.0f
        val centerY = ivDetail.height / 2.0f

        val rotateTo180 = Rotate3d(-270f, -360f, centerX, centerY, 0f, true)
        rotateTo180.duration = duration.toLong()
        rotateTo180.fillAfter = true

        val rotateTo90 = Rotate3d(0f, -90f, centerX, centerY, 0f, true)
        rotateTo90.duration = duration.toLong()
        rotateTo90.setAnimationListener(object : AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                ivDetail.setSampledBitmap(getDrawableResId(mContext, "front", index), 200 ,300)
                layoutDetail.startAnimation(rotateTo180)
            }
            override fun onAnimationRepeat(animation: Animation) {}
        })

        layoutDetail.startAnimation(rotateTo90)
    }
}