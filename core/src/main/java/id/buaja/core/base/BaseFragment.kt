package id.buaja.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment

/**
 * Created by Julsapargi Nursam on 1/19/21.
 */

abstract class BaseFragment(layout: Int) : Fragment(layout) {
    protected open fun initObservable() {}
    abstract fun initView(view: View)
    abstract fun createView(inflater: LayoutInflater, container: ViewGroup?): View
    abstract fun destroyView()
    protected open fun onBackPressed() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObservable()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return createView(inflater, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //OnBack
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object :
            OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onBackPressed()
            }
        })

        initView(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        destroyView()
    }
}