package id.buaja.games.di

import id.buaja.core.domain.usescase.GameUseCaseImpl
import id.buaja.core.domain.usescase.GamesUseCase
import id.buaja.games.ui.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Julsapargi Nursam on 12/18/20.
 */

val useCaseModule = module {
    factory<GamesUseCase> {
        GameUseCaseImpl(get())
    }
}

val viewModelModule = module {
    viewModel {
        HomeViewModel(get())
    }
}