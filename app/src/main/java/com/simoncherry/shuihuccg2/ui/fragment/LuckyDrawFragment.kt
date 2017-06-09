package com.simoncherry.shuihuccg2.ui.fragment

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.ScaleAnimation
import android.view.animation.TranslateAnimation
import com.simoncherry.shuihuccg2.R
import com.simoncherry.shuihuccg2.util.getDrawableResId
import com.simoncherry.shuihuccg2.util.getStringRes
import com.simoncherry.shuihuccg2.util.setSampledBitmap
import kotlinx.android.synthetic.main.fragment_lucky_draw.*
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
class LuckyDrawFragment : BaseFragment() {

    private lateinit var mBagScaleAnim: Animation
    private lateinit var mDrawCardAnim: Animation
    private lateinit var mResetAnim: Animation
    private lateinit var mNewLogoAnim: Animation

    companion object {
        fun newInstance(): LuckyDrawFragment {
            val fragment = LuckyDrawFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getLayout(): Int {
        return R.layout.fragment_lucky_draw
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    private fun init() {
        initView()
        initAnimation()
    }

    private fun initView() {
        val bagCount = "x99"
        tvBagCount.text =  bagCount

        ivBag.setOnClickListener {
            ivCard.clearAnimation()
            ivCard.invalidate()
            ivCard.visibility = View.INVISIBLE
            layoutCardDetail.visibility = View.INVISIBLE
            ivBag.startAnimation(mBagScaleAnim)
        }
    }

    private fun initAnimation() {
        mBagScaleAnim = ScaleAnimation(1.0f, 1.5f, 1.0f, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f)
        mBagScaleAnim.repeatCount = 3
        mBagScaleAnim.repeatMode = Animation.REVERSE
        mBagScaleAnim.duration = 300
        mBagScaleAnim.setAnimationListener(object : AnimationListener {
            override fun onAnimationEnd(animation: Animation) {
                LoadNewCard()
            }
            override fun onAnimationRepeat(animation: Animation) {}
            override fun onAnimationStart(animation: Animation) {}
        })

        mDrawCardAnim = TranslateAnimation(0f, 0f, 0f, -270f)
        mDrawCardAnim.duration = 1000
        mDrawCardAnim.fillAfter = true
        mDrawCardAnim.setAnimationListener(object : AnimationListener {
            override fun onAnimationEnd(animation: Animation) {
                layoutCardDetail.visibility = View.VISIBLE
//                if (isExistCard === false) {
//                    isExistCard = true
//                    ivNewLogo.visibility = View.VISIBLE
//                    ivNewLogo.startAnimation(mNewLogoAnim)
//                } else {
//                    ivNewLogo.visibility = View.INVISIBLE
//                }
                ivNewLogo.visibility = View.VISIBLE
                ivNewLogo.startAnimation(mNewLogoAnim)
            }
            override fun onAnimationRepeat(animation: Animation) {}
            override fun onAnimationStart(animation: Animation) {}
        })

        mResetAnim = TranslateAnimation(0f, 0f, 0f, 0f)
        mResetAnim.duration = 0
        mResetAnim.fillAfter = true

        mNewLogoAnim = ScaleAnimation(2.0f, 1.0f, 2.0f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f)
        mNewLogoAnim.duration = 500
    }

    private fun LoadNewCard() {
        val random = Random(System.currentTimeMillis())
        val index = random.nextInt(108) + 1

        val cardIndex = getString(R.string.hero_index) + index.toString()
        tvCardIndex.text = cardIndex
        val cardStar = getString(R.string.hero_star) + getStringRes(mContext, "star", index)
        tvCardStar.text = cardStar
        val cardEpithet = getString(R.string.hero_epithet) + getStringRes(mContext, "epithet", index)
        tvCardEpithet.text = cardEpithet
        val cardName = getString(R.string.hero_name) + getStringRes(mContext, "name", index)
        tvCardName.text = cardName

        //val cardName = "front" + index.toString()
        //val imgResId = resources.getIdentifier(cardName, "drawable", this.activity.packageName)
        val imgResId = getDrawableResId(mContext, "front", index)

        ivCard.visibility = View.VISIBLE
        ivCard.setSampledBitmap(imgResId, 70, 105)
        ivCard.startAnimation(mDrawCardAnim)
        ivCardDetail.setSampledBitmap(imgResId, 120, 170)
    }
}