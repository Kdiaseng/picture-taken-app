package br.personal.project.picturestaken.api

import br.personal.project.picturestaken.api.service.PictureService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitConfig {
    private const val URL = "https://api.pexels.com/v1/"


    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val retrofitService: PictureService by lazy {
        retrofit.create(PictureService::class.java)
    }

}