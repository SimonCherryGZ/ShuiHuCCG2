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
class NoteFragment : BaseFragment() {

    companion object {
        fun newInstance(): NoteFragment {
            val fragment = NoteFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getLayout(): Int {
        return R.layout.fragment_note
    }

    override fun init() {
    }
}