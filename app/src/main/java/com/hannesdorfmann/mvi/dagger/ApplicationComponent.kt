package com.hannesdorfmann.mvi.dagger

import com.hannesdorfmann.mvi.SearchPresenter
import dagger.Component
import javax.inject.Singleton

/**
 *
 *
 * @author Hannes Dorfmann
 */
@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {
  fun searchPresenter(): SearchPresenter
}