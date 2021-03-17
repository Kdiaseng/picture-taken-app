package br.personal.project.picturestaken.ui.homePictures

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.personal.project.picturestaken.data.ResultData
import br.personal.project.picturestaken.data.model.Picture
import br.personal.project.picturestaken.data.model.ResponsePicture
import br.personal.project.picturestaken.repository.PictureRepository
import kotlinx.coroutines.launch

class HomePictureViewModel(private val repository: PictureRepository) : ViewModel() {

    private val _showProgressLiveData = MutableLiveData(false)
    val showProgressLiveData: LiveData<Boolean> = _showProgressLiveData

    private val _photosLiveData = MutableLiveData<MutableList<Picture>>()
    val photosLiveData: LiveData<MutableList<Picture>> = _photosLiveData

    fun findPictureByName(name: String) {
        viewModelScope.launch {
            _showProgressLiveData.value = true
            when (val response = repository.findPictureByName(name)) {
                is ResultData.Success -> {
                    _showProgressLiveData.value = false
                    _photosLiveData.value = response.data.photos
                }
                is ResultData.Error -> {
                    _showProgressLiveData.value = false
                }
            }
        }
    }
}