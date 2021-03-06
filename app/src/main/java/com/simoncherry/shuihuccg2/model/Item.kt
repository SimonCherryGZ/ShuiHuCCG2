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
@PoKo open class Item(
        @PrimaryKey var id: Int,
        var type: Int,
        var value: Int,
        var count: Int
) : RealmObject()