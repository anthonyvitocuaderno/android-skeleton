package com.vitocuaderno.skeleton.usecases

abstract class UseCase<Q : UseCase.RequestValues?, P : UseCase.ResponseValue?> {
    var requestValues: Q? = null
        private set
    var useCaseCallback: UseCaseCallback<P>? = null

    fun setRequestValues(requestValues: Q) {
        this.requestValues = requestValues
    }

    fun run() {
        executeUseCase(requestValues)
    }

    protected abstract fun executeUseCase(requestValues: Q?)

    /**
     * Data passed to a request.
     */
    interface RequestValues

    /**
     * Data received from a request.
     */
    interface ResponseValue
    interface UseCaseCallback<R> {
        fun onSuccess(response: R)
        fun onError()
    }
}
