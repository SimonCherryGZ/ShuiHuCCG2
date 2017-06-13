package com.simoncherry.shuihuccg2.ui.fragment

import android.os.Bundle
import com.simoncherry.shuihuccg2.R
import com.simoncherry.shuihuccg2.model.Card
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.fragment_debug.*
import java.util.*

/**
 * <pre>
 *     author : Donald
 *     e-mail : xxx@xx
 *     time   : 2017/06/13
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class DebugFragment : BaseFragment() {

    private lateinit var realm: Realm
    private lateinit var realmResults: RealmResults<Card>

    companion object {
        fun newInstance(): DebugFragment {
            val fragment = DebugFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getLayout(): Int {
        return R.layout.fragment_debug
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    override fun init() {
        initRealm()
        initView()
    }

    private fun initRealm() {
        realm = Realm.getDefaultInstance()
        realmResults = realm.where(Card::class.java).equalTo("playerId", 0).findAllAsync()
    }

    private fun initView() {
        btnClearCard.setOnClickListener {
            realm.executeTransaction {
                for (card in realmResults) {
                    card.count = 0
                }
            }
        }

        btnRandomCard.setOnClickListener {
            realm.executeTransaction {
                val random = Random(System.nanoTime())
                for (card in realmResults) {
                    card.count = random.nextInt(2)
                }
            }
        }
    }
}