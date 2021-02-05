package id.buaja.games.ui.detail

import android.os.Build
import android.text.Html
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import coil.load
import id.buaja.core.base.BaseFragment
import id.buaja.core.domain.model.FavoriteModel
import id.buaja.core.domain.model.GamesDetailModel
import id.buaja.games.R
import id.buaja.games.databinding.FragmentDetailGamesBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailGamesFragment : BaseFragment(R.layout.fragment_detail_games) {
    private val binding by viewBinding<FragmentDetailGamesBinding>()
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
                    tvDescription.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        Html.fromHtml(it.description, Html.FROM_HTML_MODE_COMPACT)
                    } else {
                        Html.fromHtml(it.description)
                    }
                }
            })

            error.observe(this@DetailGamesFragment, {

            })

            loading.observe(this@DetailGamesFragment, {
                when (it) {
                    true -> {
                        binding.apply {
                            shimmerDetail.startShimmerAnimation()
                            shimmerDetail.visibility = View.VISIBLE
                        }
                    }

                    false -> {
                        binding.apply {
                            shimmerDetail.stopShimmerAnimation()
                            shimmerDetail.visibility = View.GONE
                        }
                    }
                }
            })
        }
    }

    override fun initView(view: View) {
        viewModel.getGamesDetail(arguments?.getInt(ID_GAMES))

        binding.apply {
            btnFavorite.setOnClickListener {
                val favoriteModel = GamesDetailModel(
                    id = id,
                    backgroundImage = backgroundImage,
                    nameGame = nameGame,
                    genre = genre,
                    description = description
                )
                viewModel.insertFavorite(favoriteModel)

                Toast.makeText(requireContext(), "Data Ditambahkan Ke Favorite", Toast.LENGTH_SHORT).show()
            }

            btnBack.setOnClickListener {
                back()
            }
        }
    }

    override fun onBackPressed() {
        back()
    }

    private fun back() {
        findNavController().popBackStack(R.id.homeFragment, false)
    }

    companion object {
        const val ID_GAMES = "id_games"
    }
}