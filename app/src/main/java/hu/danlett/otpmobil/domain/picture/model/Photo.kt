package hu.danlett.otpmobil.domain.picture.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Photo(
    val id: String,
    val imageUrl: String,
    val title: String
) : Parcelable