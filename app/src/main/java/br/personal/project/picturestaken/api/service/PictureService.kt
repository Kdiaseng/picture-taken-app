package br.personal.project.picturestaken.api.service

import br.personal.project.picturestaken.data.model.ResponsePicture
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface PictureService {

    @Headers("Authorization:563492ad6f9170000100000114b5e4e1f9d34599ae2294562006338f")
    @GET("search")
    suspend fun getPictures(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("color") color: String? = null
    ): Response<ResponsePicture>

    @Headers("Authorization:563492ad6f9170000100000114b5e4e1f9d34599ae2294562006338f")
    @GET("curated")
    suspend fun getPicturesCurated(
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ): Response<ResponsePicture>
}