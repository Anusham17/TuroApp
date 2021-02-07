package com.challenge.turoapp.dagger

import com.challenge.turoapp.ui.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class TuroActivityModule {

    @ContributesAndroidInjector()
    abstract fun mainActivity(): MainActivity
}