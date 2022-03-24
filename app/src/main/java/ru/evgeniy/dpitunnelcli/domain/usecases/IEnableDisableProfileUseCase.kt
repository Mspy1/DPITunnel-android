package ru.evgeniy.dpitunnelcli.domain.usecases

interface IEnableDisableProfileUseCase {
    suspend fun enable(id: Long)
    suspend fun disable(id: Long)
}