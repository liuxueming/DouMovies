package com.thoughtworks.doumovies.api

import okhttp3.OkHttpClient

object ApiClient {

    val client by lazy(LazyThreadSafetyMode.NONE) { OkHttpClient.Builder().build() }
}