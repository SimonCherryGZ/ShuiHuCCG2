package com.simoncherry.shuihuccg2.model

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * <pre>
 *     author : Donald
 *     e-mail : xxx@xx
 *     time   : 2017/06/12
 *     desc   :
 *     version: 1.0
 * </pre>
 */
@PoKo open class Player(
        @PrimaryKey var id: Long,
        var name: String,
        var income: Int,
        var money: Int,
        var study: Int,
        var mood: Int,
        var cardList: RealmList<Card>,
        var itemList: RealmList<Item>,
        var featureList: RealmList<Feature>
) : RealmObject()