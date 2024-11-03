# Movix (Compose Multiplatform)

Movies & TV Show app built by Compose Multiplatform, targeting Android and iOS.

## Stack
- [Compose Multiplatform](https://www.jetbrains.com/compose-multiplatform/) - Modern, declarative sharing UI across multiple platform with Kotlin
- [kotlinx.serialization](https://github.com/Kotlin/kotlinx.serialization) - Multi-format serialization for Kotlin Multiplatform
- [kotlinx.datetime](https://github.com/Kotlin/kotlinx-datetime) - A multiplatform Kotlin library for working with date and time
- [Ktor](https://ktor.io/) - Asynchronous HTTP Client networking
- [Koin](https://insert-koin.io/) - Pragmatic lightweight DI framework for Kotlin Multiplatform
- [SQLDelight](https://sqldelight.github.io/sqldelight) - Local databases, it is typesafe Kotlin-generated API from your SQL statements
- [Circuit Navigation](https://slackhq.github.io/circuit/navigation/) - For handling content navigation
- [BuildConfig Plugin](https://github.com/gmazzo/gradle-buildconfig-plugin) - A gradle plugin for generating BuildConfig .kt files
- [Kamel](https://github.com/Kamel-Media/Kamel) - Asynchronous image loader for Compose

## General Project Structure (by CMP Wizard)
* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.
* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.

