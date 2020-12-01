package com.coronaintheworld.data

import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CountryDataSourceTest {


    private lateinit var server: MockWebServer
    private lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun beforeEachTest() {
        server = MockWebServer()

        val rbsApi = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(server.url("/").toString())
            .build()
            .create(RbsApi::class.java)

        remoteDataSource =
            RemoteDataSource(rbsApi)
    }

    @After
    fun afterEachTest() {
        server.shutdown()
    }

    @Test
    fun `should retrieve config correctly`() {
        server.mock("config_correct")

        remoteDataSource.fetchConfig()
            .test()
            .assertNoErrors()
            .assertComplete()
    }
}