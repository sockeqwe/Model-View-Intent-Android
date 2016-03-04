package com.hannesdorfmann.mvi.dagger

import com.hannesdorfmann.mvi.SearchEngine
import com.hannesdorfmann.mvi.SearchModel
import com.hannesdorfmann.mvi.github.GithubBackend
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import rx.Observable
import javax.inject.Singleton

/**
 *
 *
 * @author Hannes Dorfmann
 */
@Module
class ApplicationModule {

  @Provides @Singleton
  fun providesSearchEngine(): SearchEngine {
    val retrofit = Retrofit.Builder().baseUrl("https://api.github.com").addConverterFactory(
        MoshiConverterFactory.create()).addCallAdapterFactory(
        RxJavaCallAdapterFactory.create()).build()

    return SearchEngine(retrofit.create(GithubBackend::class.java))
  }

  @Provides @Singleton
  fun providesModelFunc(
      searchEngine: SearchEngine): Function1<@JvmWildcard Observable<String>, @JvmWildcard Observable<SearchModel>>
      =
      { stringObservable ->
        stringObservable.startWith("").flatMap { queryString -> searchEngine.search(queryString) }
      }
}