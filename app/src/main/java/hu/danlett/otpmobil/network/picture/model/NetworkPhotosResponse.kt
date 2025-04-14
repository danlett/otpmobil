package hu.danlett.otpmobil.network.picture.model

import com.google.gson.annotations.SerializedName

data class NetworkPhotosResponse(
    @SerializedName("photos") val data: NetworkPhotos?
)