package com.pyroblinchik.catapi.di.base

import android.app.Application
import com.pyroblinchik.catapi.CatApplication
import com.pyroblinchik.catapi.di.DataModule
import com.pyroblinchik.catapi.presentation.menu.FavoritesFragment
import com.pyroblinchik.catapi.presentation.menu.FeedFragment
import com.pyroblinchik.catapi.presentation.menu.MenuActivity
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun inject(fragment: FeedFragment)

    fun inject(fragment: FavoritesFragment)

    fun inject(activity: MenuActivity)
    fun inject(application: CatApplication)

//    fun inject(application: CatApplication)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}
