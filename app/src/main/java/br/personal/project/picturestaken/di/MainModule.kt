package br.personal.project.picturestaken.di

import br.personal.project.picturestaken.repository.PictureRepository
import br.personal.project.picturestaken.ui.homePictures.HomePictureAdapter
import br.personal.project.picturestaken.ui.homePictures.HomePictureViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {

    factory {
        PictureRepository()
    }
    //single para usar em varias features

    viewModel {
        HomePictureViewModel(
            repository = get()
        )
    }
}


val adapter = module {
    factory {
        HomePictureAdapter()
    }
}