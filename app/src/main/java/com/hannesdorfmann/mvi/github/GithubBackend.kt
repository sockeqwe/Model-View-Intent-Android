package com.hannesdorfmann.mvi.github

import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/**
 *
 *
 * @author Hannes Dorfmann
 */
interface GithubBackend {

  @GET("search/repositories")
  fun getRepositories(@Query("q") query: String): Observable<GithubRepoResponse>

}