package com.simoncherry.shuihuccg2.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import com.simoncherry.shuihuccg2.R
import com.simoncherry.shuihuccg2.model.Player
import com.simoncherry.shuihuccg2.ui.adapter.TestAdapter
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.fragment_test.*
import java.util.ArrayList

/**
 * <pre>
 *     author : Donald
 *     e-mail : xxx@xx
 *     time   : 2017/06/12
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class TestFragment : BaseFragment() {

    private val TAG = TestFragment::class.java.simpleName

    private lateinit var testAdapter: TestAdapter
    private lateinit var fragmentList: ArrayList<Fragment>
    private lateinit var titleList: ArrayList<String>

    private lateinit var realm: Realm
    private lateinit var realmResults: RealmResults<Player>


    companion object {
        fun newInstance(): TestFragment {
            val fragment = TestFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getLayout(): Int {
        return R.layout.fragment_test
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
        initViewPager()
        initIndicator()
        initRealm()
    }

    private fun initViewPager() {
        titleList = ArrayList<String>()
        fragmentList = ArrayList<Fragment>()
        testAdapter = TestAdapter(childFragmentManager, fragmentList, titleList)
        viewPager.offscreenPageLimit = 2
        viewPager.adapter = testAdapter
    }

    private fun initIndicator() {
        layoutTab.setupWithViewPager(viewPager)
    }

    private fun initRealm() {
        realm = Realm.getDefaultInstance()
        realmResults = realm.where(Player::class.java)
                .findAllAsync()
        realmResults.addChangeListener { element ->
            if (element.size > 0) {
                handleRealmResult(element)
            }
        }
    }

    private fun handleRealmResult(element: RealmResults<Player>) {
        titleList.clear()
        fragmentList.clear()

        for (i in element.indices) {
            val player = element[i]
            titleList.add(player.name)
            val fragment = PlayerDBFragment.newInstance(player.id)
            fragmentList.add(fragment)
        }

        testAdapter.notifyDataSetChanged()
    }
}