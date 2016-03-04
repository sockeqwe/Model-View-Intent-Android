package com.hannesdorfmann.mvi

import com.hannesdorfmann.mvi.github.GithubRepo

/**
 *
 *
 * @author Hannes Dorfmann
 */
data class SearchModel(val searchTerm: String, val results: List<GithubRepo>)