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

    private val _responsePicture = MutableLiveData<ResponsePicture>()
    private val _isCurated = MutableLiveData(false)

    private val _colorLiveData = MutableLiveData<String?>()
    val colorLiveData: LiveData<String?> = _colorLiveData

    private val _queryLiveData = MutableLiveData<String?>(null)

    private val _visibleLoading = MutableLiveData(false)
    val visibleLoading: LiveData<Boolean> = _visibleLoading

    private val _photosLiveData = MutableLiveData<MutableList<Picture>>()
    val photosLiveData: LiveData<MutableList<Picture>> = _photosLiveData

    private val _photosUpdate = MutableLiveData<MutableList<Picture>>()
    val photosUpdate: LiveData<MutableList<Picture>> = _photosUpdate


    fun setColorSearch(color: String) {
        _colorLiveData.value = if (color.isNotEmpty()) color else null
        findPictureByName()
    }

    fun findPictureByName(page: Int = 1, isUpdate: Boolean = false) {
        _isCurated.value = false
        _queryLiveData.value?.let { query ->
            viewModelScope.launch {
                _visibleLoading.value = true
                val color = _colorLiveData.value
                when (val response = repository.findPictureByName(query, page, color)) {
                    is ResultData.Success -> {
                        _visibleLoading.value = false
                        _responsePicture.value = response.data
                        if (isUpdate) {
                            _photosUpdate.value = response.data.photos
                        } else {
                            _photosLiveData.value = response.data.photos
                        }
                    }
                    is ResultData.Error -> {
                        _visibleLoading.value = false
                    }
                }
            }
        }
    }


    fun nextPictures() {
        _responsePicture.value?.let { response ->
            response.next_page?.let {
                val nextPage = response.page + 1
                _isCurated.value?.let { isCurated ->
                    if (isCurated) {
                        getPicturesCurated(page = nextPage,isUpdate = true)
                    } else {
                        findPictureByName(page = nextPage, true)
                    }
                }
            }
        }
    }


    fun setQuery(queryString: String?) {
        _queryLiveData.value = queryString
    }

    fun getPicturesCurated(perPage: Int = 15, page: Int = 1, isUpdate: Boolean = false) {
        _queryLiveData.value = null
        _isCurated.value = true
        viewModelScope.launch {
            _visibleLoading.value = true
            when (val response = repository.getPictureCurated(perPage, page)) {
                is ResultData.Success -> {
                    _visibleLoading.value = false
                    _responsePicture.value = response.data
                    if (isUpdate) {
                        _photosUpdate.value = response.data.photos
                    } else {
                        _photosLiveData.value = response.data.photos
                    }
                }
                is ResultData.Error -> {
                    _visibleLoading.value = false
                }
            }
        }
    }
}