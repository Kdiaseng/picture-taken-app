package br.personal.project.picturestaken.data.model

import java.io.Serializable

class Picture(
    val id: Long,
    val width: Int,
    val height: Int,
    val url: String,
    val photographer: String,
    val photographer_url: String,
    val photographer_id: Long,
    val avg_color: String,
    val src: Src
) : Serializable

class Src(
    val original: String,
    val large2x: String,
    val large: String,
    val medium: String,
    val small: String,
    val portrait: String,
    val landscape: String,
    val tiny: String
) : Serializable