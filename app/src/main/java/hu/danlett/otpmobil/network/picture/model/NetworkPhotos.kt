package hu.danlett.otpmobil.network.picture.model

import com.google.gson.annotations.SerializedName

data class NetworkPhotos(
    @SerializedName("photo") val photos: List<NetworkPhoto>?,
    @SerializedName("pages") val pages: Int?
)