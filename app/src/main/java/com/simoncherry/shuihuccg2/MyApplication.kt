package com.simoncherry.shuihuccg2

import android.app.Application
import android.widget.Toast
import com.simoncherry.shuihuccg2.model.*
import com.simoncherry.shuihuccg2.util.getStringRes
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmResults
import io.realm.RealmList



/**
 * <pre>
 *     author : Donald
 *     e-mail : xxx@xx
 *     time   : 2017/06/12
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class MyApplication : Application() {

    private val DB_NAME = "shuihuccg"

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val realmConfiguration = RealmConfiguration.Builder()
                .name(DB_NAME)
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.setDefaultConfiguration(realmConfiguration)

        checkIsInit()
    }

    private fun checkIsInit() {
        val realm: Realm = Realm.getDefaultInstance()
        val results: RealmResults<Global> = realm.where(Global::class.java).findAll()
        val isFirstRun = results.size == 0
        if (isFirstRun) {
            Toast.makeText(this, "首次运行", Toast.LENGTH_SHORT).show()
            realm.executeTransaction {
                val global = Global()
                realm.copyToRealm(global)
            }
        }

        val global = results.first()
        val isInit = global.isInit
        if (!isInit) {
            initData(realm)
        }
        realm.close()
    }

    private fun initData(realm: Realm) {
        for (i in 0..11) {
            val cardList: RealmList<Card> = RealmList()
            val itemList: RealmList<Item> = RealmList()
            val feature: RealmList<Feature> = RealmList()
            val player: Player = Player(i, getStringRes(this, "chara_name_", i), 5, 500, 50, 100, cardList, itemList, feature)
            realm.executeTransaction {
                realm.copyToRealm(player)
            }
        }
    }
}