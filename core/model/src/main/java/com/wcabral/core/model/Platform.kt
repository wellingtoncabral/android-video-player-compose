package com.wcabral.core.model

enum class PlatformType {
    UNKNOWN,
    PC,
    LINUX,
    APPLE,
    XBOX,
    PLAYSTATION,
    NINTENDO,
}

data class Platform(val id: Int, val name: String, val platformType: PlatformType)

val previewPlatformUnknown = Platform(
    id = -1,
    name = "",
    platformType = PlatformType.UNKNOWN
)

val previewPlatformPC = Platform(
    id = 1,
    name = "PC",
    platformType = PlatformType.PC
)

val previewPlatformLinux = Platform(
    id = 2,
    name = "Nintendo",
    platformType = PlatformType.NINTENDO
)

val previewPlatformApple = Platform(
    id = 4,
    name = "Apple",
    platformType = PlatformType.APPLE
)

val previewPlatformXbox = Platform(
    id = 5,
    name = "XBox",
    platformType = PlatformType.XBOX
)

val previewPlatformPlaystation = Platform(
    id = 6,
    name = "Playstation",
    platformType = PlatformType.PLAYSTATION
)

val previewPlatformNintendo = Platform(
    id = 7,
    name = "Nintendo",
    platformType = PlatformType.NINTENDO
)

val previewPlatforms = listOf(
    previewPlatformPC,
    previewPlatformLinux,
    previewPlatformApple,
    previewPlatformXbox,
    previewPlatformPlaystation,
    previewPlatformNintendo,
)
