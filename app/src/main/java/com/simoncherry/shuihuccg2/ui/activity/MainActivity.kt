package com.simoncherry.shuihuccg2.ui.activity

import android.os.Bundle
import android.util.Log
import com.simoncherry.shuihuccg2.R
import com.simoncherry.shuihuccg2.ui.custom.BottomBar
import com.simoncherry.shuihuccg2.ui.custom.BottomBarTab
import com.simoncherry.shuihuccg2.ui.fragment.CollectionFragment
import com.simoncherry.shuihuccg2.ui.fragment.MapFragment
import com.simoncherry.shuihuccg2.ui.fragment.NoteFragment
import com.simoncherry.shuihuccg2.ui.fragment.StatusFragment
import me.yokeyword.fragmentation.SupportFragment
import me.yokeyword.fragmentation.anim.FragmentAnimator

class MainActivity : BaseActivity() {
    val TAG = MainActivity::class.java.simpleName!!
    val FIRST = 0
    val SECOND = 1
    val THIRD = 2
    val FOURTH = 3

    private val mFragments = arrayOfNulls<SupportFragment>(4)

    private lateinit var mBottomBar: BottomBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initFragment(savedInstanceState)
        initView()
    }

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun onCreateFragmentAnimator(): FragmentAnimator {
        return super.onCreateFragmentAnimator()
    }

    override fun onBackPressedSupport() {
        // 对于 4个类别的主Fragment内的回退back逻辑,已经在其onBackPressedSupport里各自处理了
        super.onBackPressedSupport()
    }

    private fun initFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            mFragments[FIRST] = MapFragment.newInstance()
            mFragments[SECOND] = StatusFragment.newInstance()
            mFragments[THIRD] = NoteFragment.newInstance()
            mFragments[FOURTH] = CollectionFragment.newInstance()

            loadMultipleRootFragment(R.id.fl_container, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD],
                    mFragments[FOURTH])
        } else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题

            // 这里我们需要拿到mFragments的引用,也可以通过getSupportFragmentManager.getFragments()自行进行判断查找(效率更高些),用下面的方法查找更方便些
            mFragments[FIRST] = findFragment(MapFragment::class.java)
            mFragments[SECOND] = findFragment(StatusFragment::class.java)
            mFragments[THIRD] = findFragment(NoteFragment::class.java)
            mFragments[FOURTH] = findFragment(CollectionFragment::class.java)
        }
    }

    private fun initView() {
        mBottomBar = findViewById(R.id.bottomBar) as BottomBar

        mBottomBar.addItem(BottomBarTab(this, R.drawable.icon_scene_pressed, R.drawable.icon_scene_default))
                .addItem(BottomBarTab(this, R.drawable.icon_status_pressed, R.drawable.icon_status_default))
                .addItem(BottomBarTab(this, R.drawable.icon_note_pressed, R.drawable.icon_note_default))
                .addItem(BottomBarTab(this, R.drawable.icon_collection_pressed, R.drawable.icon_collection_default))

        mBottomBar.setOnTabSelectedListener(object : BottomBar.OnTabSelectedListener {
            override fun onTabSelected(position: Int, prePosition: Int) {
                Log.e(TAG, "position: $position, prePosition: $prePosition")
                showHideFragment(mFragments[position], mFragments[prePosition])
            }

            override fun onTabUnselected(position: Int) {
            }

            override fun onTabReselected(position: Int) {
            }
        })
    }
}
