package com.simoncherry.shuihuccg2.ui.fragment

import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import com.simoncherry.shuihuccg2.R
import com.simoncherry.shuihuccg2.model.Player
import com.simoncherry.shuihuccg2.ui.adapter.CardDebugAdapter
import com.simoncherry.shuihuccg2.util.GridSpacingItemDecoration
import com.simoncherry.shuihuccg2.util.getDrawableResId
import com.simoncherry.shuihuccg2.util.setSampledBitmap
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.fragment_player_db.*

/**
 * <pre>
 *     author : Donald
 *     e-mail : xxx@xx
 *     time   : 2017/06/12
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class PlayerDBFragment : BaseFragment() {
    private val TAG = PlayerDBFragment::class.java.simpleName

    private var mPlayerIndex = 0

    private lateinit var debugAdapter: CardDebugAdapter
    private lateinit var realm: Realm
    private lateinit var realmResults: RealmResults<Player>

    companion object {
        fun newInstance(index: Int): PlayerDBFragment {
            val fragment = PlayerDBFragment()
            val args = Bundle()
            args.putInt("index", index)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mPlayerIndex = arguments.getInt("index", 0)
        }
    }

    override fun getLayout(): Int {
        return R.layout.fragment_player_db
    }

    override fun onDestroy() {
        super.onDestroy()
        rvCard?.adapter = null
        realmResults.removeAllChangeListeners()
        realm.close()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }
    
    override fun init() {
        initRealm()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        debugAdapter = CardDebugAdapter(mContext,
                realm.where(Player::class.java).equalTo("id", mPlayerIndex).findFirst().cardList)
        rvCard.layoutManager = StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL)
        //rvCard.addItemDecoration(GridSpacingItemDecoration(4, 30, false))
        rvCard.adapter = debugAdapter
    }
    
    private fun initRealm() {
        realm = Realm.getDefaultInstance()
        realmResults = realm.where(Player::class.java)
                .equalTo("id", mPlayerIndex)
                .findAllAsync()
        realmResults.addChangeListener { element ->
            handleRealmResult(element)
        }
    }

    private fun handleRealmResult(element: RealmResults<Player>) {
        Log.e(TAG, "player id: " + mPlayerIndex)
        val player = element.first()
        // 注意，原来下面几个控件没有加"?"。  在测试页面把玩家卡片清空后，上面的监听调用到这里，下面控件全部null了
        ivAvatar?.setSampledBitmap(getDrawableResId(mContext, "chara_", mPlayerIndex), 100, 120)
        tvId?.text = player.id.toString()
        tvName?.text = player.name
        tvStatus?.text = player.getStatus()
    }
}