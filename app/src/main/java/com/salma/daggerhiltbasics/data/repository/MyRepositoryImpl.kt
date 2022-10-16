package com.salma.daggerhiltbasics.data.repository

import android.app.Application
import com.salma.daggerhiltbasics.R
import com.salma.daggerhiltbasics.data.remote.MyApi
import com.salma.daggerhiltbasics.domain.repository.MyRepository
import javax.inject.Inject

class MyRepositoryImpl @Inject constructor(
    private val api: MyApi,
    private val appContext: Application,
) : MyRepository {

    init {
        val appName = appContext.getString(R.string.app_name)
        println("Hello from repo, the app name is $appName")
    }

    override suspend fun doNetworkCall() {

    }
}