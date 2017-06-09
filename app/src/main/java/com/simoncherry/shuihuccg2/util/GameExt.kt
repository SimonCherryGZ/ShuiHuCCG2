package com.simoncherry.shuihuccg2.util

import android.content.Context

/**
 * <pre>
 *     author : Donald
 *     e-mail : xxx@xx
 *     time   : 2017/06/09
 *     desc   :
 *     version: 1.0
 * </pre>
 */

fun getDrawableResId(context: Context, prefix: String, index: Int): Int {
    val resName = prefix + index.toString()
    val resId = context.resources.getIdentifier(resName, "drawable", context.packageName)
    return resId
}