package com.example.kharchenko.di

object ComponentHolder {

    val appComponent: AppComponent = DaggerAppComponent.create()
}