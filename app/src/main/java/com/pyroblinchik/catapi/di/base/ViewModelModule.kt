package com.pyroblinchik.catapi.di.base

import androidx.lifecycle.ViewModel
import com.pyroblinchik.catapi.presentation.menu.MenuViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MenuViewModel::class)
    fun bindMenuViewModel(viewModel: MenuViewModel): ViewModel

}
