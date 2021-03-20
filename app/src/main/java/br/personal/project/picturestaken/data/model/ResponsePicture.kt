package br.personal.project.picturestaken.data.model

class ResponsePicture(
    val page : Int,
    val per_page: Int,
    val photos: ArrayList<Picture>,
    val total_result: Int,
    val next_page: String? = null
)




