package ru.evgeniy.dpitunnelcli.data.usecases

import android.content.Context
import ru.evgeniy.dpitunnelcli.database.AppDatabase
import ru.evgeniy.dpitunnelcli.domain.usecases.IEnableDisableProfileUseCase

class EnableDisableProfileUseCase(private val context: Context): IEnableDisableProfileUseCase {

    private val profileDao = AppDatabase.getInstance(context).profileDao()

    override suspend fun enable(id: Long) {
        profileDao.setEnable(id, true)
    }

    override suspend fun disable(id: Long) {
        profileDao.setEnable(id, false)
    }
}