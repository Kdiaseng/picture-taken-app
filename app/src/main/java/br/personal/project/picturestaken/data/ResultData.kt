package br.personal.project.picturestaken.data

import java.lang.Exception

sealed class ResultData<out T : Any> {
    data class Success<out T : Any>(val data: T) : ResultData<T>()
    data class Error(val message: String, val cause: Exception? = null) : ResultData<Nothing>()
}
