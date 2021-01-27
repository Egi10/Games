package id.buaja.games.ui.home

import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.recyclerview.widget.LinearLayoutManager
import id.buaja.core.base.BaseFragment
import id.buaja.core.domain.model.DevelopersEntity
import id.buaja.games.R
import id.buaja.games.databinding.FragmentHomeBinding
import id.buaja.games.utils.PeekingLinearLayoutManager
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class HomeFragment : BaseFragment(R.layout.fragment_home) {
    private val viewModel by viewModel<HomeViewModel>()

    private val binding by viewBinding<FragmentHomeBinding>()

    private lateinit var adapterDevelopers: DevelopersGamesAdapter
    private val listDevelopers: MutableList<DevelopersEntity> = mutableListOf()

    override fun initObservable() {
        with(viewModel) {
            loading.observe(this@HomeFragment, {

            })

            developersGame.observe(this@HomeFragment, {
                listDevelopers.clear()
                listDevelopers.addAll(it)
                adapterDevelopers.notifyDataSetChanged()
            })

            error.observe(this@HomeFragment, {
                Timber.d(it)
            })
        }
    }

    override fun initView(view: View) {
        viewModel.getDevelopers()

        // Set Adapter
        adapterDevelopers = DevelopersGamesAdapter(listDevelopers) {

        }

        with(binding) {
            rvDevelopersGame.layoutManager =
                PeekingLinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            rvDevelopersGame.adapter = adapterDevelopers
        }
    }
}