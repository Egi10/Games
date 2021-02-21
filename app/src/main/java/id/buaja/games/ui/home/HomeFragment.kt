package id.buaja.games.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import id.buaja.core.base.BaseFragment
import id.buaja.core.domain.model.DevelopersGameModel
import id.buaja.core.domain.model.GamesModel
import id.buaja.games.R
import id.buaja.games.databinding.FragmentHomeBinding
import id.buaja.games.ui.detail.DetailGamesFragment
import id.buaja.games.ui.home.adapter.DevelopersGamesAdapter
import id.buaja.games.ui.home.adapter.GamesAdapter
import id.buaja.games.utils.PeekingLinearLayoutManager
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class HomeFragment : BaseFragment(R.layout.fragment_home) {
    private val viewModel by viewModel<HomeViewModel>()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapterDevelopers: DevelopersGamesAdapter
    private val listDevelopers: MutableList<DevelopersGameModel> = mutableListOf()

    private lateinit var gamesAdapter: GamesAdapter
    private val listGames: MutableList<GamesModel> = mutableListOf()

    override fun initObservable() {
        with(viewModel) {
            loading.observe(this@HomeFragment, {
                when (it) {
                    true -> {
                        binding.apply {
                            shimmerDevelopersGame.startShimmer()
                            shimmerDevelopersGame.visibility = View.VISIBLE
                            groupDevelopers.visibility = View.GONE
                        }
                    }

                    false -> {
                        binding.apply {
                            shimmerDevelopersGame.stopShimmer()
                            shimmerDevelopersGame.visibility = View.INVISIBLE
                            groupDevelopers.visibility = View.VISIBLE
                        }
                    }
                }
            })

            loadingGames.observe(this@HomeFragment, {
                when (it) {
                    true -> {
                        binding.apply {
                            shimmerGames.startShimmer()
                            shimmerGames.visibility = View.VISIBLE
                            groupGames.visibility = View.GONE
                        }
                    }

                    false -> {
                        binding.apply {
                            shimmerGames.stopShimmer()
                            shimmerGames.visibility = View.INVISIBLE
                            groupGames.visibility = View.VISIBLE
                        }
                    }
                }
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
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                Timber.d(it)
            })
        }
    }

    override fun initView(view: View) {
        // Set Adapter
        adapterDevelopers = DevelopersGamesAdapter(listDevelopers)

        // Set Adapter Games
        gamesAdapter = GamesAdapter(listGames) {
            val bundle = Bundle()
            it.id?.let { it1 -> bundle.putInt(DetailGamesFragment.ID_GAMES, it1) }
            findNavController().navigate(
                R.id.action_homeFragment_to_detailDeveloperGamesFragment,
                bundle
            )
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

            // To Favorite
            fabFavorite.setOnClickListener {
                val uri = Uri.parse("gamesapp://favorite")
                startActivity(Intent(Intent.ACTION_VIEW, uri))
            }
        }
    }

    override fun createView(inflater: LayoutInflater, container: ViewGroup?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun destroyView() {
        binding.apply {
            rvDevelopersGame.adapter = null
            rvGames.adapter = null
        }
        _binding = null
    }
}