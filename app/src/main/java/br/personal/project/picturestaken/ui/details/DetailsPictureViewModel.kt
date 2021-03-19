package br.personal.project.picturestaken.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.personal.project.picturestaken.data.model.Picture

class DetailsPictureViewModel : ViewModel() {

    private val _pictureSelected = MutableLiveData<Picture>()
    val pictureSelected: LiveData<Picture> = _pictureSelected

    private val _showProgress = MutableLiveData(false)
    val showProcess: LiveData<Boolean> = _showProgress

    fun setPicture(picture: Picture) {
        _pictureSelected.value = picture
    }

    fun setShowProgress(isVisible: Boolean) {
        _showProgress.value = isVisible
    }

}