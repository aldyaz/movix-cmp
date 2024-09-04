package com.aldyaz.movix

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform