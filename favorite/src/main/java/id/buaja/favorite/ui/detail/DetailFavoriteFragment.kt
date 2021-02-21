package id.buaja.favorite.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import coil.load
import id.buaja.core.base.BaseFragment
import id.buaja.core.domain.model.FavoriteModel
import id.buaja.favorite.R
import id.buaja.favorite.databinding.FragmentDetailFavoriteBinding
import id.buaja.games.utils.setHtml
import org.koin.android.viewmodel.ext.android.viewModel

class DetailFavoriteFragment : BaseFragment(R.layout.fragment_detail_favorite) {
    private var _binding: FragmentDetailFavoriteBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<DetailFavoriteViewModel>()

    override fun initObservable() {
        with(viewModel) {
            successDelete.observe(this@DetailFavoriteFragment, {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                back()
            })
        }
    }

    override fun initView(view: View) {
        val favorite = arguments?.getParcelable<FavoriteModel>(FAVORITE)

        binding.apply {
            ivImageBackground.load(favorite?.backgroundImage)
            tvGenre.text = favorite?.genre
            tvNameGame.text = favorite?.nameGame
            tvDescription.text = favorite?.description?.setHtml()

            btnDeleteFavorite.setOnClickListener {
                favorite?.id?.let { it1 -> viewModel.deleteFavoriteId(it1) }
            }

            btnBack.setOnClickListener {
                back()
            }
        }
    }

    override fun createView(inflater: LayoutInflater, container: ViewGroup?): View {
        _binding = FragmentDetailFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun destroyView() {
        _binding = null
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