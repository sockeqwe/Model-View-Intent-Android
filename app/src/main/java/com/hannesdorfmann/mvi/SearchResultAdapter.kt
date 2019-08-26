package com.hannesdorfmann.mvi

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import kotterknife.bindView
import com.hannesdorfmann.mvi.github.GithubRepo
import com.squareup.picasso.Picasso

/**
 *
 *
 * @author Hannes Dorfmann
 */

class SearchResultViewHolder(view: View) : RecyclerView.ViewHolder(view) {

  val repoName: TextView by bindView(R.id.repoName)
  val ownerName: TextView by bindView(R.id.ownerName)
  val avatar: ImageView by bindView(R.id.avatar)

}

class SearchResultAdapter(private val layoutInflater: LayoutInflater) : RecyclerView.Adapter<SearchResultViewHolder>() {
  var items: List<GithubRepo>? = null

  override fun onBindViewHolder(vh: SearchResultViewHolder, pos: Int) {
    val repo = items!![pos]
    vh.repoName.text = repo.name
    vh.ownerName.text = repo.owner.name
    Picasso.with(vh.itemView.context).load(repo.owner.avatar).placeholder(
        R.color.image_placeholder).fit().into(vh.avatar)
  }

  override fun onCreateViewHolder(parent: ViewGroup,
      viewType: Int): SearchResultViewHolder = SearchResultViewHolder(
      layoutInflater.inflate(R.layout.item_search_result, parent, false))

  override fun getItemId(position: Int) = items!![position].id

  override fun getItemCount(): Int = if (items == null) 0 else items!!.size
}