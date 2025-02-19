package ru.evgeniy.dpitunnelcli.utils

import ru.evgeniy.dpitunnelcli.domain.entities.DesyncFirstAttack
import ru.evgeniy.dpitunnelcli.domain.entities.DesyncZeroAttack
import ru.evgeniy.dpitunnelcli.domain.entities.Profile

object Constants {
    val TTL_VALUE_RANGE = 1..255
    val TCP_WINDOW_SIZE_VALUE_RANGE = 1..65535
    val TCP_WINDOW_SCALE_FACTOR_VALUE_RANGE = 0..14
    val SPLIT_POSITION_VALUE_RANGE = 1..65535
    val SERVER_PORT_RANGE = 1..65535
    const val DPITUNNEL_DEFAULT_PORT = 8080
    const val DPITUNNEL_BINARY_NAME = "libdpitunnel-cli.so"
    const val DPITUNNEL_DAEMON_PID_FILE = "/dev/dpitunnel-cli-daemon.pid"
    const val INBUILT_CA_BUNDLE_FILE_NAME = "ca.bundle"
    const val USER_CA_BUNDLE_FILE_NAME = "ca.bundle_user"
    val DEFAULT_PROFILE = Profile(
        id = null,
        enabled = false,
        name = "",
        title = null,
        bufferSize = null,
        splitPosition = 3,
        splitAtSni = true,
        wrongSeq = false,
        autoTtl = true,
        fakePacketsTtl = null,
        windowSize = 1,
        windowScaleFactor = 6,
        inBuiltDNS = true,
        inBuiltDNSIP = "8.8.8.8",
        inBuiltDNSPort = null,
        doh = true,
        dohServer = "https://dns.google/dns-query",
        desyncAttacks = true,
        desyncZeroAttack = DesyncZeroAttack.DESYNC_ZERO_FAKE,
        desyncFirstAttack = DesyncFirstAttack.DESYNC_FIRST_DISORDER_FAKE
    )
}