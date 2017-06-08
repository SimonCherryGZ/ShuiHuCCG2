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
class StatusFragment : BaseFragment() {

    companion object {
        fun newInstance(): StatusFragment {
            val fragment = StatusFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getLayout(): Int {
        return R.layout.fragment_status
    }
}