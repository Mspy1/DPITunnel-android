package ru.evgeniy.dpitunnelcli.cli

import com.topjohnwu.superuser.Shell
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.evgeniy.dpitunnelcli.domain.entities.Profile
import ru.evgeniy.dpitunnelcli.domain.usecases.DaemonState

class CliDaemon(private val execPath: String,
                private val pidFilePath: String) {

    private val _daemonState = MutableStateFlow<DaemonState>(DaemonState.Loading)
    val daemonState: StateFlow<DaemonState> = _daemonState

    fun check() {
        return when (val exitCode = Shell.su("[ -f /proc/\$(cat \"$pidFilePath\")/status ]").exec().code) {
            0 -> _daemonState.value = DaemonState.Running
            1 -> _daemonState.value = DaemonState.Stopped
            else -> _daemonState.value = DaemonState.Error(exitCode)
        }
    }

    fun start(persistentOptions: PersistentOptions, profiles: List<Profile>) {
        val argsStr = StringBuilder()
        argsStr.append("$execPath --pid \"$pidFilePath\"")
        argsStr.append(' ')
        argsStr.append(persistentOptions.toString())
        profiles.forEach {
            argsStr.append(' ')
            argsStr.append(it.toString())
        }
        Shell.su(argsStr.toString()).exec()
    }

    fun stop() {
        Shell.su("kill -INT \$(cat \"$pidFilePath\")").exec()
    }

    data class PersistentOptions(
        val caBundlePath: String,
        val ip: String?,
        val port: Int?
    ) {
        override fun toString(): String {
            val stringBuilder = StringBuilder()
            stringBuilder.append("--daemon --ca-bundle-path \"${this.caBundlePath}\"")
            ip?.let { stringBuilder.append(" --ip $it") }
            port?.let { stringBuilder.append(" --port $it") }
            return stringBuilder.toString()
        }
    }
}