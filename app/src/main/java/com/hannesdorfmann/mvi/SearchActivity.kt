package com.hannesdorfmann.mvi

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Toast
import butterknife.bindView
import com.hannesdorfmann.mosby.mvp.MvpActivity
import com.jakewharton.rxbinding.widget.RxSearchView
import rx.Observable
import java.util.concurrent.TimeUnit

class SearchActivity : SearchView, MvpActivity<SearchView, SearchPresenter>() {

  private val editSearch: android.widget.SearchView by bindView(R.id.searchView)
  private val recyclerView: RecyclerView  by bindView(R.id.recyclerView)
  private val loadingView: View by bindView(R.id.loadingView)
  private val toolbar: Toolbar by bindView(R.id.toolbar)
  private lateinit var adapter: SearchResultAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    setContentView(R.layout.activity_search)
    super.onCreate(savedInstanceState)

    adapter = SearchResultAdapter(layoutInflater)
    adapter.setHasStableIds(true)

    recyclerView.adapter = adapter
    recyclerView.layoutManager = LinearLayoutManager(this)
  }

  override fun searchIntent(): Observable<String> =
      Observable.defer {
        RxSearchView.queryTextChangeEvents(editSearch)
            .filter { it.queryText().count() >= 3 || it.queryText().isEmpty() }
            .debounce(500, TimeUnit.MILLISECONDS)
            .map { it.queryText().toString() } // convert CharSequence to String
      }

  override fun createPresenter(): SearchPresenter = App.getComponent(this).searchPresenter()

  override fun showLoading(): (Any) -> Unit = {
    runOnUiThread {
      loadingView.visibility = View.VISIBLE
    }
  }

  override fun showData(): (SearchModel) -> Unit = {
    loadingView.visibility = View.GONE
    adapter.items = it.results
    adapter.notifyDataSetChanged()
  }

  override fun showError(): (Throwable) -> Unit = {
    loadingView.visibility = View.GONE
    Toast.makeText(this, "An Error has occurred", Toast.LENGTH_SHORT).show()
    it.printStackTrace()
  }
}
