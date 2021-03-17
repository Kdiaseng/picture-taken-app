package br.personal.project.picturestaken.ui.dataBinding

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import br.personal.project.picturestaken.data.model.Picture
import br.personal.project.picturestaken.ui.homePictures.HomePictureAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("url", "error")
fun ImageView.setUrl(url: String, error: Drawable) {
    Picasso.get()
        .load(url)
        .error(error)
        .into(this)
}


