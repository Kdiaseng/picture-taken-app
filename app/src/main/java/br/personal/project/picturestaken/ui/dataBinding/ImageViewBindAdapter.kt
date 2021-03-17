package br.personal.project.picturestaken.ui.dataBinding

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso


@BindingAdapter("url", "error")
fun ImageView.setUrl(url: String, error: Drawable) {
    Picasso.get()
        .load(url)
        .error(error)
        .into(this)
}

//@BindingAdapter("android:visibility")
//fun View.setVisibility(value: Boolean) {
//    this.visibility = if (value) View.VISIBLE else View.GONE
//}


