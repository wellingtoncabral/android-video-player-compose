package com.wcabral.core.data.network.di

import com.wcabral.core.data.network.BuildConfig
import com.wcabral.core.data.network.GamesNetworkDataSource
import com.wcabral.core.data.network.retrofit.ApiConstants
import com.wcabral.core.data.network.retrofit.Endpoints
import com.wcabral.core.data.network.retrofit.RetrofitApi
import com.wcabral.core.data.network.retrofit.RetrofitDataSource
import com.wcabral.core.data.network.retrofit.RetrofitInterceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

val networkModule = module {
    // Retrofit and network injections
    single { GsonConverterFactory.create() }

    single { RetrofitInterceptor() }

    // Cache
    single {
        val cacheSize = (10 * 1024 * 1024).toLong() //10 MB
        val httpCacheDirectory = File(androidContext().cacheDir, "http-cache")
        Cache(httpCacheDirectory, cacheSize)
    }

    single {
        OkHttpClient.Builder().run {
            if (BuildConfig.DEBUG) {
                val loggingInterceptor = HttpLoggingInterceptor()
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                addNetworkInterceptor(loggingInterceptor)
            }
            addInterceptor(get<RetrofitInterceptor>())
            cache(get())
            writeTimeout(ApiConstants.TIMEOUT, TimeUnit.SECONDS)
            readTimeout(ApiConstants.TIMEOUT, TimeUnit.SECONDS)
            connectTimeout(ApiConstants.TIMEOUT, TimeUnit.SECONDS)
            build()
        }
    }

    single {
        Retrofit.Builder()
            .baseUrl(Endpoints.BASE_URL)
            .addConverterFactory(get<GsonConverterFactory>())
            .client(get())
            .build()
    }

    single {
        get<Retrofit>().create(RetrofitApi::class.java)
    }

    // Data sources
    factory<GamesNetworkDataSource> { RetrofitDataSource(get()) }
}