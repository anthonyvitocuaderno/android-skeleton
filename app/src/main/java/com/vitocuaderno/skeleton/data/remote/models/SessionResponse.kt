package com.vitocuaderno.skeleton.data.remote.models

import com.google.gson.annotations.SerializedName

class SessionResponse(
    @field:SerializedName("token") val token: String,
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("username") val username: String,
)
