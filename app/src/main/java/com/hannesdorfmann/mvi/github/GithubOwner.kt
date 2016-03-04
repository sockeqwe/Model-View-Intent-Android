package com.hannesdorfmann.mvi.github

import com.squareup.moshi.Json

/**
 *
 *
 * @author Hannes Dorfmann
 */
data class GithubOwner(@Json(name = "avatar_url") val avatar: String, @Json(name = "login") val name: String)