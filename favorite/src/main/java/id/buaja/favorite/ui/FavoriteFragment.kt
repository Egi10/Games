package id.buaja.favorite.ui

import android.content.Intent
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import id.buaja.core.base.BaseFragment
import id.buaja.core.domain.model.FavoriteModel
import id.buaja.favorite.R
import id.buaja.favorite.databinding.FragmentFavoriteBinding
import id.buaja.favorite.di.favoriteModule
import id.buaja.games.ui.MainActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteFragment : BaseFragment(R.layout.fragment_favorite) {
    private val binding by viewBinding<FragmentFavoriteBinding>()
    private val viewModel by viewModel<FavoriteViewModel>()

    private lateinit var adapter: FavoriteAdapter
    private val list: MutableList<FavoriteModel> = mutableListOf()

    override fun initObservable() {
        loadKoinModules(favoriteModule)
        with(viewModel) {
            favorite.observe(this@FavoriteFragment, {
                list.clear()
                list.addAll(it)
                adapter.notifyDataSetChanged()
            })

            empty.observe(this@FavoriteFragment, {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            })
        }
    }

    override fun initView(view: View) {
        viewModel.getFavorite()

        binding.apply {
            adapter = FavoriteAdapter(list) {

            }
            recyclerview.layoutManager = LinearLayoutManager(requireContext())
            recyclerview.adapter = adapter
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(requireContext(), MainActivity::class.java))
    }
}