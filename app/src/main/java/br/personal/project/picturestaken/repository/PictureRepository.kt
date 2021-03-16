package br.personal.project.picturestaken.repository

import br.personal.project.picturestaken.api.RetrofitConfig
import br.personal.project.picturestaken.api.service.PictureService
import java.lang.Exception

class PictureRepository(private val pictureService: PictureService = RetrofitConfig.retrofitService) {

    suspend fun findPictureByName(name: String) {
        try {
            val response = pictureService.getPictures(name)
            if (response.isSuccessful) {

            }
        } catch (ex: Exception) {

        }
    }
}