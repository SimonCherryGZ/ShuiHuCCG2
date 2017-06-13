package com.simoncherry.shuihuccg2.model

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
@PoKo open class Card(
        @PrimaryKey var id: Int,
        var playerId: Int,
        var index: Int,
        var count: Int
) : RealmObject() {

    override fun toString(): String {
        return "Card(id=$id, playerId=$playerId, index=$index, count=$count)"
    }
}