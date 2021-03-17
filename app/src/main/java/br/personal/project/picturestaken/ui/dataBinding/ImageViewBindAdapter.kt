package br.personal.project.picturestaken.ui.dataBinding

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import br.personal.project.picturestaken.R
import com.squareup.picasso.Picasso


@BindingAdapter("url", "error")
fun ImageView.setUrl(url: String, error: Drawable) {
    Picasso.get()
        .load(url)
        .placeholder(R.drawable.ic_camera_64)
        .error(error)
        .into(this)
}
