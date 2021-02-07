package com.challenge.turoapp

import android.app.Activity
import android.app.Application
import com.challenge.turoapp.dagger.DaggerTuroComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class TuroApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): DispatchingAndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()

        DaggerTuroComponent.builder().
        application(this).
        build().
        inject(this)
    }
}