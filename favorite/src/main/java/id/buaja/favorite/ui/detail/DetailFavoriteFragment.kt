package id.buaja.favorite.ui.detail

import android.os.Build
import android.text.Html
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.navigation.fragment.findNavController
import coil.load
import id.buaja.core.base.BaseFragment
import id.buaja.core.domain.model.FavoriteModel
import id.buaja.favorite.R
import id.buaja.favorite.databinding.FragmentDetailFavoriteBinding

class DetailFavoriteFragment : BaseFragment(R.layout.fragment_detail_favorite) {
    private val binding by viewBinding<FragmentDetailFavoriteBinding>()

    override fun initView(view: View) {
        val favorite = arguments?.getParcelable<FavoriteModel>(FAVORITE)

        binding.apply {
            ivImageBackground.load(favorite?.backgroundImage)
            tvGenre.text = favorite?.genre
            tvNameGame.text = favorite?.nameGame
            tvDescription.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(favorite?.description, Html.FROM_HTML_MODE_COMPACT)
            } else {
                Html.fromHtml(favorite?.description)
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
        findNavController().popBackStack()
    }

    companion object {
        const val FAVORITE = "favorite"
    }
}