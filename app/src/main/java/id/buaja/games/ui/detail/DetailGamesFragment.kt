package id.buaja.games.ui.detail

import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.navigation.fragment.findNavController
import id.buaja.core.base.BaseFragment
import id.buaja.games.R
import id.buaja.games.databinding.FragmentDetailGamesBinding

class DetailGamesFragment : BaseFragment(R.layout.fragment_detail_games) {
    private val binding by viewBinding<FragmentDetailGamesBinding>()

    override fun initObservable() {

    }

    override fun initView(view: View) {

    }

    override fun onBackPressed() {
        back()
    }

    private fun back() {
        findNavController().popBackStack()
    }
}