package id.buaja.games.ui.detail

import android.os.Build
import android.text.Html
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.navigation.fragment.findNavController
import coil.load
import id.buaja.core.base.BaseFragment
import id.buaja.games.R
import id.buaja.games.databinding.FragmentDetailGamesBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailGamesFragment : BaseFragment(R.layout.fragment_detail_games) {
    private val binding by viewBinding<FragmentDetailGamesBinding>()
    private val viewModel by viewModel<DetailGamesViewModel>()

    override fun initObservable() {
        with(viewModel) {
            success.observe(this@DetailGamesFragment, {
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
                when(it) {
                    true -> {

                    }

                    false -> {

                    }
                }
            })
        }
    }

    override fun initView(view: View) {
        viewModel.getGamesDetail(arguments?.getInt(ID_GAMES))

        binding.apply {
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