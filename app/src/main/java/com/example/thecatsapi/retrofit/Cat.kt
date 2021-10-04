package com.example.thecatsapi.retrofit

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Cat(
    val id: String?,
    val url: String?
)
