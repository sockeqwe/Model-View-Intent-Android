package com.hannesdorfmann.mvi

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter
import rx.Observable
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import javax.inject.Inject

/**
 *
 *
 * @author Hannes Dorfmann
 */
class SearchPresenter @Inject constructor(private val modelFunc: (Observable<String>) -> @JvmWildcard Observable<SearchModel>) : MvpBasePresenter<SearchView>() {

  lateinit var subscription: Subscription

  override fun attachView(view: SearchView) {
    super.attachView(view)

    subscription =
        modelFunc(view.searchIntent().doOnNext(view.showLoading()))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(view.showData(), view.showError())
  }

  override fun detachView(retainInstance: Boolean) {
    super.detachView(retainInstance)
    subscription.unsubscribe()
  }

}