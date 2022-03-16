package com.vitocuaderno.skeleton.usecases

import com.vitocuaderno.skeleton.data.local.models.Task
import com.vitocuaderno.skeleton.data.repository.task.TaskRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class GetTasks @Inject constructor(
    private val tasksRepository: TaskRepository,
) : UseCase<GetTasks.RequestValues, GetTasks.ResponseValue>() {

    override fun executeUseCase(values: RequestValues?) {
        GlobalScope.launch {

            val responseValue = ResponseValue(tasksRepository.getTasks())
            useCaseCallback?.onSuccess(responseValue)
//            useCaseCallback?.onError()
        }
    }

    class RequestValues() : UseCase.RequestValues
    class ResponseValue(val tasks: List<Task>) : UseCase.ResponseValue

}
