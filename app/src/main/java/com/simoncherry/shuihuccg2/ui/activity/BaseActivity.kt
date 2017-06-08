package com.simoncherry.shuihuccg2.ui.activity

import android.os.Bundle
import me.yokeyword.fragmentation.SupportActivity

/**
 * <pre>
 *     author : Donald
 *     e-mail : xxx@xx
 *     time   : 2017/06/08
 *     desc   :
 *     version: 1.0
 * </pre>
 */
open abstract class BaseActivity : SupportActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
    }

    protected abstract fun getLayout(): Int
}