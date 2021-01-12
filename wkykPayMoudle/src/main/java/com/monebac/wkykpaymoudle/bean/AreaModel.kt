package com.monebac.wkykpaymoudle.bean

import java.io.Serializable

data class AreaModel(
        val areaCode: String,
        val divisionCode: String,
        val divisionName: String,
        val id: String
) : Serializable