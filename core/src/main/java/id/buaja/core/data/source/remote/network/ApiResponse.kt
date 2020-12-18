package id.buaja.core.data.source.remote.network

/**
 * Created by Julsapargi Nursam on 12/17/20.
 */
sealed class ApiResponse<out R> {
    data class Success<out T>(val data: T) : ApiResponse<T>()
    data class Error(val errorMessage: String) : ApiResponse<Nothing>()
    object Empty : ApiResponse<Nothing>()
}