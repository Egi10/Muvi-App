package id.buaja.dashboard.di

import id.buaja.dashboard.ui.home.HomeViewModel
import id.buaja.dashboard.ui.popular.PopularViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Julsapargi Nursam on 4/11/21.
 */
 
val dashboardModule = module {
    factory {
        Dispatchers.Main
    }

    viewModel {
        HomeViewModel(get())
    }

    viewModel {
        PopularViewModel(get())
    }
}