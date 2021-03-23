package br.personal.project.picturestaken.ui.dataBinding

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import br.personal.project.picturestaken.R
import com.google.android.material.card.MaterialCardView
import com.squareup.picasso.Picasso


@BindingAdapter("url", "error")
fun ImageView.setUrl(url: String, error: Drawable) {
    Picasso.get()
        .load(url)
        .placeholder(R.drawable.ic_camera_64)
        .error(error)
        .into(this)
}

@BindingAdapter("app:cardForegroundColor")
fun MaterialCardView.setCardForegroundColor(color: String) {
//    val color = if (colorString.isNotEmpty()) Color.parseColor(colorString) else Color.TRANSPARENT
    this.setCardBackgroundColor(Color.RED)
}
