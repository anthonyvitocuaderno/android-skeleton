package com.vitocuaderno.skeleton.data.remote.models

import com.google.gson.annotations.SerializedName

class Session(
    @field:SerializedName("token") val token: String,
)
