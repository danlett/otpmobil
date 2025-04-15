package hu.danlett.otpmobil.network.picture.gateway

import hu.danlett.otpmobil.domain.Constants.FLICKR_API_KEY
import hu.danlett.otpmobil.domain.Constants.FLICKR_IMAGE_BASE_URL
import hu.danlett.otpmobil.domain.picture.gateway.NetworkPictureGateway
import hu.danlett.otpmobil.domain.picture.model.Photo
import hu.danlett.otpmobil.network.api.ApiFlickr
import hu.danlett.otpmobil.network.picture.model.NetworkPhoto
import hu.danlett.otpmobil.network.picture.model.NetworkPhotosRequestEmptyResult
import hu.danlett.otpmobil.network.picture.model.NetworkPhotosRequestError
import hu.danlett.otpmobil.network.picture.model.NetworkPhotosRequestResult
import hu.danlett.otpmobil.network.picture.model.NetworkPhotosRequestSuccess
import timber.log.Timber
import javax.inject.Inject

class NetworkPictureGatewayImpl @Inject constructor(
    private val apiFlickr: ApiFlickr
) : NetworkPictureGateway {
    override suspend fun getPhotos(
        searchQuery: String?,
        page: Int
    ): NetworkPhotosRequestResult {
        try {
            val response = apiFlickr.getPhotos(
                method = PHOTO_SEARCH_METHOD,
                apiKey = FLICKR_API_KEY,
                searchQuery = searchQuery,
                perPage = PER_PAGE,
                page = page,
                format = FLICKR_API_FORMAT,
                noJsonCallback = FLICKR_API_NO_JSON_CALLBACK
            )

            if (response.data?.photos?.isEmpty() == true) {
                return NetworkPhotosRequestEmptyResult
            }

            val photos = response.data?.photos?.mapNotNull {
                mapPhoto(it)
            }
            val pages = response.data?.pages

            return if (photos != null && pages != null) {
                NetworkPhotosRequestSuccess(
                    photos = photos,
                    page = page,
                    hasMore = page < pages
                )
            } else {
                NetworkPhotosRequestError
            }
        } catch (e: Exception) {
            Timber.d(e)
            return NetworkPhotosRequestError
        }
    }

    private fun mapPhoto(networkPhoto: NetworkPhoto): Photo? {
        networkPhoto.let {
            if (it.server == null || it.secret == null || it.id == null) {
                return null
            }
            return Photo(
                id = it.id,
                imageUrl = "${FLICKR_IMAGE_BASE_URL}/${it.server}/${it.id}_${it.secret}_b.jpg",
                thumbUrl = "${FLICKR_IMAGE_BASE_URL}/${it.server}/${it.id}_${it.secret}_n.jpg",
                title = it.title ?: ""
            )
        }
    }

    companion object {
        private const val PHOTO_SEARCH_METHOD = "flickr.photos.search"
        private const val FLICKR_API_FORMAT = "json"
        private const val FLICKR_API_NO_JSON_CALLBACK = 1
        private const val PER_PAGE = 20
    }
}