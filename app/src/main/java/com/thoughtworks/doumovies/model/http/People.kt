package com.thoughtworks.doumovies.model.http

data class People(
    val alt: String,
    val avatars: Images,
    val id: String,
    val name: String,
    val name_en: String,
    var role: String? = null
)