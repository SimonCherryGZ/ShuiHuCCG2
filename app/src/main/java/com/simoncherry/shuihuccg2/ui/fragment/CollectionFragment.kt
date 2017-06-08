package com.simoncherry.shuihuccg2.ui.fragment

import android.os.Bundle
import com.simoncherry.shuihuccg2.R

/**
 * <pre>
 *     author : Donald
 *     e-mail : xxx@xx
 *     time   : 2017/06/08
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class CollectionFragment : BaseFragment() {

    companion object {
        fun newInstance(): CollectionFragment {
            val fragment = CollectionFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getLayout(): Int {
        return R.layout.fragment_collection
    }
}