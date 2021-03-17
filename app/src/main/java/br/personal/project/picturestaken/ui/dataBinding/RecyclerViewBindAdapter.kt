package br.personal.project.picturestaken.ui.dataBinding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import br.personal.project.picturestaken.ui.homePictures.HomePictureAdapter

@BindingAdapter("adapter")
fun RecyclerView.loadList(
    adapterPhoto: RecyclerView.Adapter<*>,
) {
    adapter = adapterPhoto
    layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
}