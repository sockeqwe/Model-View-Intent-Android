package com.hannesdorfmann.mvi

import android.app.Application
import android.content.Context
import com.hannesdorfmann.mvi.dagger.ApplicationComponent
import com.hannesdorfmann.mvi.dagger.DaggerApplicationComponent

/**
 *
 *
 * @author Hannes Dorfmann
 */
class App : Application() {

  private lateinit var component: ApplicationComponent

  override fun onCreate() {
    super.onCreate()
    component = DaggerApplicationComponent.create()
  }

  companion object {
    fun getComponent(c: Context) =
        (c.applicationContext as App).component
    
  }
}