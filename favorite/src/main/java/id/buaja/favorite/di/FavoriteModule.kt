package id.buaja.favorite.di

import id.buaja.favorite.ui.detail.DetailFavoriteViewModel
import id.buaja.favorite.ui.home.FavoriteViewModel
import id.buaja.games.ui.detail.DetailGamesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Julsapargi Nursam on 2/6/21.
 */
 
val favoriteModule = module {
    viewModel {
        FavoriteViewModel(get())
    }
    viewModel {
        DetailFavoriteViewModel(get())
    }
}