package hu.danlett.otpmobil.network.picture.model

import com.google.gson.annotations.SerializedName

data class NetworkPhoto(
    @SerializedName("id") val id: String?,
    @SerializedName("owner") val owner: String?,
    @SerializedName("secret") val secret: String?,
    @SerializedName("server") val server: String?,
    @SerializedName("farm") val farm: Int?,
    @SerializedName("title") val title: String?,
    @SerializedName("ispublic") val isPublic: Int?,
    @SerializedName("isfriend") val isFriend: Int?,
    @SerializedName("isfamily") val isFamily: Int?
)