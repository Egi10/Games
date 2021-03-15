package id.buaja.core.data

/**
 * Created by Julsapargi Nursam on 12/17/20.
 */
sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null,
    val loading: Boolean? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T>(loading: Boolean?, data: T? = null) :
        Resource<T>(data = data, loading = loading)

    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
}