package com.salma.daggerhiltbasics.di

import android.app.Application
import com.salma.daggerhiltbasics.MyViewModel
import com.salma.daggerhiltbasics.data.remote.MyApi
import com.salma.daggerhiltbasics.data.repository.MyRepositoryImpl
import com.salma.daggerhiltbasics.domain.repository.MyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMyApi(): MyApi {
        return Retrofit.Builder()
            .baseUrl("https://test.com")
            .build()
            .create(MyApi::class.java)
    }

//    @Provides
//    @Singleton
//    fun provideMyRepository(api: MyApi, appContext: Application): MyRepository {
//        return MyRepositoryImpl(api, appContext)
//    }

}