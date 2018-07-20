package app.concertor.injection

import app.concertor.BuildConfig
import app.concertor.SongkickApi
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    companion object {
        private const val API_ENDPOINT = "https://api.songkick.com"

        private const val CLIENT_CONNECT_TIMEOUT_SECONDS = 30L
        private const val CLIENT_READ_TIMEOUT_SECONDS = 30L
        private const val CLIENT_WRITE_TIMEOUT_SECONDS = 10L
    }

    @Provides
    @Singleton
    fun provideOkHttpInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {

        val clientBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            clientBuilder.addNetworkInterceptor(httpLoggingInterceptor)
        }
        clientBuilder
                .readTimeout(CLIENT_READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(CLIENT_WRITE_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .connectTimeout(CLIENT_CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS)

        return clientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideApiService(okHttpClient: OkHttpClient, moshi: Moshi): SongkickApi {
        return Retrofit.Builder()
                .baseUrl(API_ENDPOINT)
                .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(okHttpClient)
                .build()
                .create(SongkickApi::class.java)
    }
}