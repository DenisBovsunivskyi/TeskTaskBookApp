package com.books.app.data.models.books


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Books(
    @SerializedName("author")
    val author: String,
    @SerializedName("cover_url")
    val coverUrl: String,
    @SerializedName("genre")
    val genre: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("likes")
    val likes: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("quotes")
    val quotes: String,
    @SerializedName("summary")
    val summary: String,
    @SerializedName("views")
    val views: String
) : Parcelable