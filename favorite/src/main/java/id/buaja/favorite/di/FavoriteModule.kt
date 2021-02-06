package id.buaja.favorite.di

import id.buaja.favorite.ui.FavoriteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Julsapargi Nursam on 2/6/21.
 */
 
val favoriteModule = module {
    viewModel {
        FavoriteViewModel(get())
    }
}