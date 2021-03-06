package id.buaja.games.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import coil.load
import id.buaja.core.base.BaseFragment
import id.buaja.core.domain.model.GamesDetailModel
import id.buaja.games.R
import id.buaja.games.databinding.FragmentDetailGamesBinding
import id.buaja.games.utils.setHtml
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class DetailGamesFragment : BaseFragment(R.layout.fragment_detail_games) {
    private var _binding: FragmentDetailGamesBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<DetailGamesViewModel>()

    private var id: Int? = null
    private var backgroundImage: String? = null
    private var genre: String? = null
    private var nameGame: String? = null
    private var description: String? = null

    override fun initObservable() {
        with(viewModel) {
            success.observe(this@DetailGamesFragment, {
                id = it.id
                backgroundImage = it.backgroundImage
                genre = it.genre
                nameGame = it.nameGame
                description = it.description

                binding.apply {
                    ivImageBackground.load(it.backgroundImage)
                    tvGenre.text = it.genre
                    tvNameGame.text = it.nameGame
                    tvDescription.text = it.description?.setHtml()
                }
            })

            isFavorite.observe(this@DetailGamesFragment, {
                binding.btnFavorite.text = if (it == true) {
                    "Delete Favorite"
                } else {
                    "Favorite"
                }
            })

            successDelete.observe(this@DetailGamesFragment, {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            })

            error.observe(this@DetailGamesFragment, {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            })

            loading.observe(this@DetailGamesFragment, {
                Timber.d("$it")
                when (it) {
                    true -> {
                        binding.apply {
                            shimmerDetail.startShimmer()
                            shimmerDetail.visibility = View.VISIBLE
                        }
                    }

                    false -> {
                        binding.apply {
                            shimmerDetail.stopShimmer()
                            shimmerDetail.visibility = View.GONE
                        }
                    }
                }
            })
        }
    }

    override fun createView(inflater: LayoutInflater, container: ViewGroup?): View {
        _binding = FragmentDetailGamesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initView(view: View) {
        viewModel.getGamesDetail(arguments?.getInt(ID_GAMES))
        viewModel.checkFavorite(arguments?.getInt(ID_GAMES))

        binding.apply {
            btnFavorite.setOnClickListener {
                checkFavorite()
            }

            btnBack.setOnClickListener {
                back()
            }
        }
    }

    override fun destroyView() {
        _binding = null
    }

    override fun onBackPressed() {
        back()
    }

    private fun back() {
        findNavController().popBackStack(R.id.homeFragment, false)
    }

    private fun checkFavorite() {
        if (binding.btnFavorite.text == "Favorite") {
            val favoriteModel = GamesDetailModel(
                id = id,
                backgroundImage = backgroundImage,
                nameGame = nameGame,
                genre = genre,
                description = description
            )
            viewModel.insertFavorite(favoriteModel)

            Toast.makeText(requireContext(), "Data Ditambahkan Ke Favorite", Toast.LENGTH_SHORT)
                .show()
            binding.btnFavorite.text = getString(R.string.delete_favorite)
        } else {
            id?.let { viewModel.deleteFavoriteId(it) }
            binding.btnFavorite.text = getString(R.string.favorite)
        }
    }

    companion object {
        const val ID_GAMES = "id_games"
    }
}