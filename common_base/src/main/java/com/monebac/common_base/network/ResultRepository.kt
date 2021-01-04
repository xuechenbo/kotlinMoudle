package com.monebac.common_base.network

class ResultRepository {
    suspend fun getResult(map: MutableMap<String, String>) = NetworkService.apiService.getResult(map)
}