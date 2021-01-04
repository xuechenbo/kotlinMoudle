package com.monebac.common_base.network

class ApiException(var code: Int, override var message: String) : RuntimeException()