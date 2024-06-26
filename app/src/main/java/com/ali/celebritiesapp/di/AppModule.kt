package com.ali.celebritiesapp.di

import com.ali.celebritiesapp.data.remote.CelebritiesAPI
import com.ali.celebritiesapp.data.repository.CelebritiesRepositoryImpl
import com.ali.celebritiesapp.domain.repository.CelebritiesRepository
import com.ali.celebritiesapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideCelebritiesRepository(api: CelebritiesAPI): CelebritiesRepository {
        return CelebritiesRepositoryImpl(api)
    }

    @Singleton
    @Provides
    fun provideCelebritiesAPI(): CelebritiesAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CelebritiesAPI::class.java)
    }
}