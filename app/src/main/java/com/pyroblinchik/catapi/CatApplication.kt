package com.pyroblinchik.catapi

import android.app.Application
import android.content.Context
import com.pyroblinchik.catapi.di.base.DaggerApplicationComponent
import timber.log.Timber

class CatApplication : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun onCreate() {
        component.inject(this)
        initLogger()
        instance = this
        super.onCreate()
    }
    fun getContext(): Context? {
        return applicationContext
    }
    private fun initLogger() = Timber.plant(Timber.DebugTree())

    companion object {
        lateinit var instance: CatApplication
    }
}
