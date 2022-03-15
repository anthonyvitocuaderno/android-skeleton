package com.vitocuaderno.skeleton.features.common

interface BasePresenter<T> {
    fun start()

    fun setView(view: T)

    fun unsetView()
}
