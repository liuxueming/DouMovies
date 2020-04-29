package com.thoughtworks.doumovies.model.http

import com.thoughtworks.doumovies.model.http.Images

data class People(
    val alt: String,
    val avatars: Images,
    val id: String,
    val name: String,
    val name_en: String
)