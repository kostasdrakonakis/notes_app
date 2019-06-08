package com.kostasdrakonakis.notes.util

import io.reactivex.CompletableTransformer
import io.reactivex.Single
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import java.io.IOException

object Singles {

    fun <T> setSchedulers(): SingleTransformer<T, T> {
        return SingleTransformer { upstream ->
            upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }
}

object Completables {

    fun setSchedulers(): CompletableTransformer {
        return CompletableTransformer { upstream ->
            upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }
}

object AppTransformers {
    fun <T> unWrapResponseWithErrorOnStream(): SingleTransformer<Response<T>, T> {
        return SingleTransformer { observable ->
            observable.flatMap { response ->
                val body = response.body()
                if (body != null) {
                    Single.just(body)
                }
                val error = convertResponseError(response)
                if (error != null) Single.error<T>(error) else Single.never<T>()
            }
        }
    }

    private fun convertResponseError(@NonNull response: Response<*>): Throwable? {
        if (response.errorBody() != null) {
            return try {
                val errorBody = response.errorBody()
                Exception(errorBody.toString())
            } catch (e: IOException) {
                e
            } catch (e: NullPointerException) {
                e
            }
        }
        return null
    }
}