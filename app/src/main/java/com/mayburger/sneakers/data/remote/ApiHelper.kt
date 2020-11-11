package com.mayburger.sneakers.data.remote

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.mayburger.sneakers.BuildConfig
import com.mayburger.sneakers.models.Sneaker
import com.mayburger.sneakers.models.SneakerResponseModel
import com.mayburger.sneakers.ui.base.BaseApplication
import com.readystatesoftware.chuck.ChuckInterceptor
import io.reactivex.Observable

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiHelper {


    companion object Factory {
        fun create(): ApiHelper {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            val httpClient = OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)

            if (BuildConfig.DEBUG) {
                httpClient.addInterceptor(logging)
                    .addInterceptor(
                        ChuckInterceptor(BaseApplication.getInstance()).showNotification(
                            true
                        )
                    )
            }

            val client = httpClient.build()
            val gson = GsonBuilder().disableHtmlEscaping().create()
            return Retrofit.Builder()
                .baseUrl("https://api.thesneakerdatabase.com/v1/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
                .create(ApiHelper::class.java)
        }
    }

    @GET("sneakers")
    suspend fun getSneakers(
        @Query("limit") limit: Int,
        @Query("brand") brand: String
    ): SneakerResponseModel
}