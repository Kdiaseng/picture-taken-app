package br.personal.project.picturestaken.data.model

import android.os.Parcelable
import androidx.annotation.Keep

@Keep
class PictureDetails(
    val photographer: String,
    val photographer_url: String,
    val avg_color: String,
    val urlImageOriginal: String,
)
