package com.wcabral.core.data.mapper

import com.wcabral.core.data.network.model.PlatformResponse
import com.wcabral.core.model.Platform
import com.wcabral.core.model.PlatformType

fun List<PlatformResponse>?.toModel() = this?.map { platformResponse ->
    Platform(
        id = platformResponse.platform.id,
        name = platformResponse.platform.name,
        platformType = when (platformResponse.platform.id) {
            1, 14, 186 -> PlatformType.XBOX
            4 -> PlatformType.PC
            5 -> PlatformType.APPLE
            6 -> PlatformType.LINUX
            7 -> PlatformType.NINTENDO
            16, 18, 187 -> PlatformType.PLAYSTATION
            else -> PlatformType.UNKNOWN
        }
    )
}?.distinctBy { it.platformType }