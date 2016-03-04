package com.hannesdorfmann.mvi

import com.hannesdorfmann.mosby.mvp.MvpView
import rx.Observable

/**
 *
 *
 * @author Hannes Dorfmann
 */
interface SearchView : MvpView {

  fun searchIntent(): Observable<String>

  fun showLoading(): (Any) -> Unit

  fun showData(): (SearchModel) -> Unit

  fun showError(): (Throwable) -> Unit

}