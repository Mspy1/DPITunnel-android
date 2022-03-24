package ru.evgeniy.dpitunnelcli.domain.usecases

interface IRenameProfileUseCase {
    suspend fun rename(id: Long, newTitle: String)
}