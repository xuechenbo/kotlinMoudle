package com.monebac.common_base.network


class LoginRepository {
    suspend fun login(map: MutableMap<String, String>) = NetworkService.apiService.getResult(map)
}