package hu.danlett.otpmobil.domain.picture.gateway

import hu.danlett.otpmobil.network.picture.model.NetworkPhotosRequestResult

interface NetworkPictureGateway {
    suspend fun getPhotos(searchQuery: String?, page: Int): NetworkPhotosRequestResult
}