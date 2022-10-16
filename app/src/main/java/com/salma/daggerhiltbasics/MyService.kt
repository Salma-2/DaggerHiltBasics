package com.salma.daggerhiltbasics

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.salma.daggerhiltbasics.data.repository.MyRepositoryImpl
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MyService : Service() {

    @Inject
    lateinit var repositoryImpl: MyRepositoryImpl

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }
}