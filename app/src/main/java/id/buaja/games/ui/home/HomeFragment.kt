package id.buaja.games.ui.home

import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import id.buaja.core.base.BaseFragment
import id.buaja.core.domain.model.DevelopersGameModel
import id.buaja.core.domain.model.GamesModel
import id.buaja.games.R
import id.buaja.games.databinding.FragmentHomeBinding
import id.buaja.games.ui.home.adapter.DevelopersGamesAdapter
import id.buaja.games.ui.home.adapter.GamesAdapter
import id.buaja.games.utils.PeekingLinearLayoutManager
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class HomeFragment : BaseFragment(R.layout.fragment_home) {
    private val viewModel by viewModel<HomeViewModel>()

    private val binding by viewBinding<FragmentHomeBinding>()

    private lateinit var adapterDevelopers: DevelopersGamesAdapter
    private val listDevelopers: MutableList<DevelopersGameModel> = mutableListOf()

    private lateinit var gamesAdapter: GamesAdapter
    private val listGames: MutableList<GamesModel> = mutableListOf()

    override fun initObservable() {
        with(viewModel) {
            loading.observe(this@HomeFragment, {

            })

            developersGame.observe(this@HomeFragment, {
                listDevelopers.clear()
                listDevelopers.addAll(it)
                adapterDevelopers.notifyDataSetChanged()
            })

            games.observe(this@HomeFragment, {
                listGames.clear()
                listGames.addAll(it)
                gamesAdapter.notifyDataSetChanged()
            })

            error.observe(this@HomeFragment, {
                Timber.d(it)
            })
        }
    }

    override fun initView(view: View) {
        viewModel.apply {
            getDevelopers()
            getGames()
        }

        // Set Adapter
        adapterDevelopers = DevelopersGamesAdapter(listDevelopers) {

        }

        // Set Adapter Games
        gamesAdapter = GamesAdapter(listGames) {

        }

        with(binding) {
            rvDevelopersGame.layoutManager =
                PeekingLinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            rvDevelopersGame.adapter = adapterDevelopers
            rvDevelopersGame.setHasFixedSize(false)
            rvDevelopersGame.isNestedScrollingEnabled = false

            // Set Recyclerview Games
            rvGames.layoutManager = GridLayoutManager(requireContext(), 2)
            rvGames.adapter = gamesAdapter
            rvGames.setHasFixedSize(false)
            rvGames.isNestedScrollingEnabled = false
        }
    }
}