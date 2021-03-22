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
    private val _totalPage = MutableLiveData(0)

    private val _queryLiveData = MutableLiveData<String?>(null)

    private val _visibleLoading = MutableLiveData(false)
    val visibleLoading: LiveData<Boolean> = _visibleLoading

    private val _photosLiveData = MutableLiveData<MutableList<Picture>>()
    val photosLiveData: LiveData<MutableList<Picture>> = _photosLiveData

    fun findPictureByName(page: Int = 1, isRefresh: Boolean = false) {
        _queryLiveData.value?.let { query ->
            viewModelScope.launch {
                _visibleLoading.value = true
                when (val response = repository.findPictureByName(query, page)) {
                    is ResultData.Success -> {
                        _visibleLoading.value = false
                        _responsePicture.value = response.data
                        if (isRefresh) {
                            _photosLiveData.value?.addAll(response.data.photos)
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


    fun refreshPictures() {
        _responsePicture.value?.let { response ->
            response.next_page?.let {
                val nextPage = response.page + 1
                findPictureByName(nextPage,true)
            }
        }
    }


    fun setQuery(queryString: String?) {
        _queryLiveData.value = queryString
    }

    fun getPicturesCurated(perPage: Int = 15) {
        viewModelScope.launch {
            _visibleLoading.value = true
            when (val response = repository.getPictureCurated(perPage)) {
                is ResultData.Success -> {
                    _visibleLoading.value = false
                    _photosLiveData.value = response.data.photos
                }
                is ResultData.Error -> {
                    _visibleLoading.value = false
                }
            }
        }
    }
}