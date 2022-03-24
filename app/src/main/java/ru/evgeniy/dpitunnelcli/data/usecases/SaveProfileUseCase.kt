package ru.evgeniy.dpitunnelcli.data.usecases

import android.content.Context
import ru.evgeniy.dpitunnelcli.database.AppDatabase
import ru.evgeniy.dpitunnelcli.domain.entities.Profile
import ru.evgeniy.dpitunnelcli.domain.usecases.ISaveProfileUseCase
import ru.evgeniy.dpitunnelcli.preferences.AppPreferences

class SaveProfileUseCase(private val context: Context): ISaveProfileUseCase {

    private val profileDao = AppDatabase.getInstance(context).profileDao()
    private val appPreferences = AppPreferences.getInstance(context)

    override suspend fun save(profile: Profile) {
        val insertedId = profileDao.insertOrUpdate(profile = ru.evgeniy.dpitunnelcli.database.Profile(
            id = profile.id,
            enabled = profile.enabled,
            name = profile.name,
            title = profile.title,
            bufferSize = profile.bufferSize,
            splitPosition = profile.splitPosition,
            splitAtSni = profile.splitAtSni,
            wrongSeq = profile.wrongSeq,
            autoTtl = profile.autoTtl,
            fakePacketsTtl = profile.fakePacketsTtl,
            windowSize = profile.windowSize,
            windowScaleFactor = profile.windowScaleFactor,
            inBuiltDNS = profile.inBuiltDNS,
            inBuiltDNSIP = profile.inBuiltDNSIP,
            inBuiltDNSPort = profile.inBuiltDNSPort,
            doh = profile.doh,
            dohServer = profile.dohServer,
            desyncAttacks = profile.desyncAttacks,
            desyncZeroAttack = profile.desyncZeroAttack?.ordinal?.let { ru.evgeniy.dpitunnelcli.database.DesyncZeroAttack.values()[it] },
            desyncFirstAttack = profile.desyncFirstAttack?.ordinal?.let { ru.evgeniy.dpitunnelcli.database.DesyncFirstAttack.values()[it] }
        ))
        if (profile.default)
            appPreferences.defaultProfileId = insertedId
    }
}