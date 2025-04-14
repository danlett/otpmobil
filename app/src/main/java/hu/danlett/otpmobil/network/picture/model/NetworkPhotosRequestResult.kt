package hu.danlett.otpmobil.network.picture.model

import hu.danlett.otpmobil.domain.picture.model.Photo

sealed interface NetworkPhotosRequestResult
data class NetworkPhotosRequestSuccess(val photos: List<Photo>) : NetworkPhotosRequestResult
object NetworkPhotosRequestError : NetworkPhotosRequestResult
object NetworkPhotosRequestEmptyResult : NetworkPhotosRequestResult