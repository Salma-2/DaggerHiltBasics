package com.salma.daggerhiltbasics.domain.repository

interface MyRepository {
    suspend fun doNetworkCall()
}