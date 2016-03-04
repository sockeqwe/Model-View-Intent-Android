package com.hannesdorfmann.mvi

import com.hannesdorfmann.mvi.github.GithubBackend
import rx.Observable

/**
 *
 *
 * @author Hannes Dorfmann
 */
class SearchEngine(private val githubBackend: GithubBackend) {

  fun search(query: String): Observable<SearchModel> =
      if (query.isEmpty()) {
        Observable.just(SearchModel("", emptyList()))
      } else {
        githubBackend.getRepositories(query).map { SearchModel(query, it.items) }
      }

}