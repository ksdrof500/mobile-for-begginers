package com.coronaintheworld.data.di

import com.coronaintheworld.data.BuildConfig
import com.coronaintheworld.data.remote.CountryDataSource
import com.coronaintheworld.data.remote.CountrySource
import com.coronaintheworld.data.remote.CovidApi
import com.coronaintheworld.data.repository.AuthDataRepository
import com.coronaintheworld.data.repository.CountryDataRepository
import com.coronaintheworld.domain.repository.AuthRepository
import com.coronaintheworld.domain.repository.CountryRepository
import com.google.firebase.auth.FirebaseAuth
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val dataModules = module {
    single { setupFirebaseAuth() }
}

val repositoryModules = module {

    factory<AuthRepository> {
        AuthDataRepository(
            firebaseAuth = get()
        )
    }

    factory<CountryRepository> {
        CountryDataRepository(
            countrySource = get()
        )
    }

    factory<CountrySource> {
        CountryDataSource(
            covidApi = get()
        )
    }
}

val remoteModule = module {
    factory { createOkHttpClient() }

    single {
        createWebService<CovidApi>(
            okHttpClient = get(),
            url = BuildConfig.BASE_URL
        )
    }
}

private fun createOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .apply {
            if (BuildConfig.DEBUG)
                connectTimeout(40, TimeUnit.SECONDS)
                    .readTimeout(40, TimeUnit.SECONDS)
                    .writeTimeout(40, TimeUnit.SECONDS)
        }
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .writeTimeout(20, TimeUnit.SECONDS)

        .build()
}

private inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(T::class.java)
}

private fun setupFirebaseAuth() = FirebaseAuth.getInstance()
