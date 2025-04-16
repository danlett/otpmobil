package hu.danlett.otpmobil.network.picture.gateway

import hu.danlett.otpmobil.domain.Constants.FLICKR_IMAGE_BASE_URL
import hu.danlett.otpmobil.domain.picture.model.Photo
import hu.danlett.otpmobil.network.api.ApiFlickr
import hu.danlett.otpmobil.network.picture.model.NetworkPhoto
import hu.danlett.otpmobil.network.picture.model.NetworkPhotos
import hu.danlett.otpmobil.network.picture.model.NetworkPhotosRequestEmptyResult
import hu.danlett.otpmobil.network.picture.model.NetworkPhotosRequestError
import hu.danlett.otpmobil.network.picture.model.NetworkPhotosRequestSuccess
import hu.danlett.otpmobil.network.picture.model.NetworkPhotosResponse
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.exceptions.base.MockitoException
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class NetworkPictureGatewayImplTest {
	private val flickrApi = mock<ApiFlickr>()
	private val gateway = NetworkPictureGatewayImpl(flickrApi)

	@Test
	fun `given photos available for the query when fetching photos then return success state`() {
		runBlocking {
			givenPhotosAvailableForTheQuery()

			val result = gateway.getPhotos(SEARCH_QUERY, 1)
			assert(result is NetworkPhotosRequestSuccess)
			assert((result as NetworkPhotosRequestSuccess).photos.size == 3)
		}
	}

	@Test
	fun `given no photos available for the query when fetching photos then return empty state`() {
		runBlocking {
			givenNoPhotosAvailableForTheQuery()

			val result = gateway.getPhotos(SEARCH_QUERY, 1)
			assert(result is NetworkPhotosRequestEmptyResult)
		}
	}

	@Test
	fun `given error happens for the query when fetching photos then return error state`() {
		runBlocking {
			givenErrorHappensForTheQuery()

			val result = gateway.getPhotos(SEARCH_QUERY, 1)
			assert(result is NetworkPhotosRequestError)
		}
	}

	@Test
	fun `given response malformed for the query when fetching photos then return error state`() {
		runBlocking {
			givenMalformedResponseForTheQuery()

			val result = gateway.getPhotos(SEARCH_QUERY, 1)
			assert(result is NetworkPhotosRequestError)
		}
	}

	@Test
	fun `given photos available for the query when fetching photos then photos mapped correctly`() {
		runBlocking {
			givenPhotosAvailableForTheQuery()

			val result = gateway.getPhotos(SEARCH_QUERY, 1)
			assert((result as NetworkPhotosRequestSuccess).photos[0] == PHOTO_1)
		}
	}


	private suspend fun givenPhotosAvailableForTheQuery() {
		whenever(
			flickrApi.getPhotos(
				any(),
				any(),
				eq(SEARCH_QUERY),
				any(),
				any(),
				any(),
				any()
			)
		).thenReturn(NETWORK_PHOTOS_AVAILABLE_RESPONSE)
	}

	private suspend fun givenNoPhotosAvailableForTheQuery() {
		whenever(
			flickrApi.getPhotos(
				any(),
				any(),
				eq(SEARCH_QUERY),
				any(),
				any(),
				any(),
				any()
			)
		).thenReturn(NETWORK_PHOTOS_EMPTY_RESPONSE)
	}

	private suspend fun givenErrorHappensForTheQuery() {
		whenever(
			flickrApi.getPhotos(
				any(),
				any(),
				eq(SEARCH_QUERY),
				any(),
				any(),
				any(),
				any()
			)
		).thenThrow(
			MockitoException("test exception")
		)
	}

	private suspend fun givenMalformedResponseForTheQuery() {
		whenever(
			flickrApi.getPhotos(
				any(),
				any(),
				eq(SEARCH_QUERY),
				any(),
				any(),
				any(),
				any()
			)
		).thenReturn(NETWORK_PHOTOS_MALFORMED_RESPONSE)
	}

	companion object {
		private val NETWORK_PHOTO_1 = NetworkPhoto(
			id = "id1", secret = "secret1", server = "server1", title = "title1",
			owner = "owner1",
			farm = 1,
			isPublic = 1,
			isFriend = 0,
			isFamily = 0
		)
		private val PHOTO_1 = Photo(
			id = "id1",
			imageUrl = "${FLICKR_IMAGE_BASE_URL}/server1/id1_secret1_b.jpg",
			thumbUrl = "${FLICKR_IMAGE_BASE_URL}/server1/id1_secret1_n.jpg",
			title = "title1"
		)
		private val NETWORK_PHOTO_2 = NetworkPhoto(
			id = "id2", secret = "secret2", server = "server2", title = "title2",
			owner = "owner2",
			farm = 1,
			isPublic = 1,
			isFriend = 0,
			isFamily = 0
		)
		private val NETWORK_PHOTO_3 = NetworkPhoto(
			id = "id3", secret = "secret3", server = "server3", title = "title3",
			owner = "owner3",
			farm = 1,
			isPublic = 1,
			isFriend = 0,
			isFamily = 0
		)
		private val NETWORK_PHOTO_LIST = listOf(NETWORK_PHOTO_1, NETWORK_PHOTO_2, NETWORK_PHOTO_3)
		private val NETWORK_PHOTOS_AVAILABLE = NetworkPhotos(NETWORK_PHOTO_LIST, 3)
		private val NETWORK_PHOTOS_EMPTY = NetworkPhotos(listOf(), 0)
		private val NETWORK_PHOTOS_AVAILABLE_RESPONSE =
			NetworkPhotosResponse(NETWORK_PHOTOS_AVAILABLE)
		private val NETWORK_PHOTOS_EMPTY_RESPONSE = NetworkPhotosResponse(NETWORK_PHOTOS_EMPTY)
		private val NETWORK_PHOTOS_MALFORMED_RESPONSE = NetworkPhotosResponse(null)
		private const val SEARCH_QUERY = "query"
	}
}