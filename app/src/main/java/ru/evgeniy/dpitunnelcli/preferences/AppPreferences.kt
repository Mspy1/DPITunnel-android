package ru.evgeniy.dpitunnelcli.preferences

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import androidx.preference.PreferenceManager
import ru.evgeniy.dpitunnelcli.R

class AppPreferences private  constructor() {
    private lateinit var sharedPreferences: SharedPreferences

    val startOnBoot: Boolean
        get() = sharedPreferences.getBoolean(START_ON_BOOT_PROPERTY_NAME, false)

    var caBundlePath: String?
        set(value) {
            with(sharedPreferences.edit()){
                putString(CA_BUNDLE_PROPERTY_NAME, value)
                apply()
            }
        }
        get() = sharedPreferences.getString(CA_BUNDLE_PROPERTY_NAME, "")?.ifEmpty { null }

    val systemWide: Boolean
        get() = sharedPreferences.getBoolean(SYSTEM_WIDE_PROXY_PROPERTY_NAME, false)

    val ip: String?
        get() = sharedPreferences.getString(IP_PROPERTY_NAME, "")?.ifEmpty { null }

    val port: Int?
        get() = sharedPreferences.getString(PORT_PROPERTY_NAME, "")?.ifEmpty { null }?.toIntOrNull()

    val firstRun: Boolean
        get() {
            val first = sharedPreferences.getBoolean(FIRST_RUN_PROPERTY_NAME, true)
            if (first) with(sharedPreferences.edit()){
                putBoolean(FIRST_RUN_PROPERTY_NAME, false)
                apply()
            }
            return first
        }

    var defaultProfileId: Long?
        set(value) {
            with(sharedPreferences.edit()){
                putLong(DEFAULT_PROFILE_ID_PROPERTY_NAME, value ?: 0L)
                apply()
            }
        }
        get() = sharedPreferences.getLong(DEFAULT_PROFILE_ID_PROPERTY_NAME, 0L).let { if (it == 0L) null else it }

    companion object {
        const val SETTINGS_STORAGE_NAME = "PERSISTENT_SETTINGS"
        private const val START_ON_BOOT_PROPERTY_NAME = "preference_start_on_boot"
        private const val CA_BUNDLE_PROPERTY_NAME = "preference_ca_bundle_path"
        private const val SYSTEM_WIDE_PROXY_PROPERTY_NAME = "preference_proxy_system_wide"
        private const val IP_PROPERTY_NAME = "preference_proxy_ip"
        private const val PORT_PROPERTY_NAME = "preference_proxy_port"
        private const val FIRST_RUN_PROPERTY_NAME = "first_run"
        private const val DEFAULT_PROFILE_ID_PROPERTY_NAME = "default_profile_id"

        fun setDefaults(context: Context) {
            PreferenceManager.setDefaultValues(context, SETTINGS_STORAGE_NAME, Context.MODE_PRIVATE, R.xml.root_preferences, false);
        }

        fun getInstance(context: Context): AppPreferences {
            val instance = AppPreferences()
            instance.sharedPreferences = context.getSharedPreferences(SETTINGS_STORAGE_NAME, Context.MODE_PRIVATE)
            return instance
        }
    }
}