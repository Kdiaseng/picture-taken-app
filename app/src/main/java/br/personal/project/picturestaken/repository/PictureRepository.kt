package br.personal.project.picturestaken.repository

import br.personal.project.picturestaken.api.RetrofitConfig
import br.personal.project.picturestaken.api.service.PictureService
import br.personal.project.picturestaken.data.ResultData
import java.lang.Exception

class PictureRepository(private val pictureService: PictureService = RetrofitConfig.retrofitService) {

    suspend fun findPictureByName(name: String, page: Int, color: String?=null) =
        try {
            val response = pictureService.getPictures(name, page, color)
            if (response.isSuccessful && response.body() != null) {
                ResultData.Success(response.body()!!)
            } else {
                ResultData.Error("FAIL GET PICTURES")
            }
        } catch (ex: Exception) {
            ResultData.Error("FAIL", ex)
        }

    suspend fun getPictureCurated(perPage: Int, page: Int = 1) =
        try {
            val response = pictureService.getPicturesCurated(perPage, page)
            if(response.isSuccessful && response.body() != null){
                ResultData.Success(response.body()!!)
            }else{
                ResultData.Error("FAIL GET PICTURES")
            }

        }catch (ex: Exception){
            ResultData.Error("FAIL")
        }
}
