package br.personal.project.picturestaken.ui.homePictures

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.personal.project.picturestaken.repository.PictureRepository

@Suppress("UNCHECKED_CAST")
class HomePicturesViewModelFactory(private val repository: PictureRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomePictureViewModel(repository) as T
    }
}