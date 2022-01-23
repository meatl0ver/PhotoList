package raul.imashev.photolist.api

import io.reactivex.rxjava3.core.Single
import raul.imashev.photolist.URL_VALUE
import retrofit2.http.GET

interface ApiService {
    @GET(URL_VALUE)
    fun getPhotos(): Single<List<String>>
}