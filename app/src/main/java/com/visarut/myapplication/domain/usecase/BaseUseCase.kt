package com.visarut.myapplication.domain.usecase

abstract class BaseUseCase<INPUT, OUTPUT> {
    protected abstract suspend fun create(input: INPUT): OUTPUT

    suspend fun execute(input: INPUT): Result<OUTPUT> {
        return try {
            Result.success(create(input))
        } catch (error: Throwable) {
            Result.failure(error)
        }
    }
}