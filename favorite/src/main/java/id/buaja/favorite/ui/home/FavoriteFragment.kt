package id.buaja.favorite.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import id.buaja.core.base.BaseFragment
import id.buaja.core.domain.model.FavoriteModel
import id.buaja.favorite.R
import id.buaja.favorite.databinding.FragmentFavoriteBinding
import id.buaja.favorite.ui.detail.DetailFavoriteFragment
import id.buaja.games.ui.MainActivity
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteFragment : BaseFragment(R.layout.fragment_favorite) {
    private val binding by viewBinding<FragmentFavoriteBinding>()
    private val viewModel by viewModel<FavoriteViewModel>()

    private lateinit var adapter: FavoriteAdapter
    private val list: MutableList<FavoriteModel> = mutableListOf()

    override fun initObservable() {
        with(viewModel) {
            favorite.observe(this@FavoriteFragment, {
                if (it.isEmpty()) {
                    binding.viewEmpty.apply {
                        viewEmpty.visibility = View.VISIBLE
                    }
                } else {
                    binding.viewEmpty.apply {
                        viewEmpty.visibility = View.GONE
                    }
                }

                list.clear()
                list.addAll(it)
                adapter.notifyDataSetChanged()
            })
        }
    }

    override fun initView(view: View) {
        viewModel.getFavorite()

        binding.apply {
            adapter = FavoriteAdapter(list) {
                val bundle = Bundle()
                bundle.putParcelable(DetailFavoriteFragment.FAVORITE, it)
                findNavController().navigate(
                    R.id.action_favoriteFragment_to_detailFavoriteFragment,
                    bundle
                )
            }
            recyclerview.layoutManager = LinearLayoutManager(requireContext())
            recyclerview.adapter = adapter
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(requireContext(), MainActivity::class.java))
    }
}