package com.vitocuaderno.skeleton.features.main.users

import com.vitocuaderno.skeleton.data.local.models.User

sealed class UsersState {
    object Idle : UsersState()
    object Busy : UsersState()
    object EmptyList : UsersState()
    data class List(val users: ArrayList<User>) : UsersState()
}
