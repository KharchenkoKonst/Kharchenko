package com.example.kharchenko.di

import android.app.Application

object ComponentHolder {

    val appComponent: AppComponent = DaggerAppComponent.create()
}