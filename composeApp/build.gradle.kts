import org.jetbrains.kotlin.daemon.common.trimQuotes
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
}

val secretFolder = "$projectDir/build/generatedSecret"

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    sourceSets.commonMain.configure {
        kotlin.srcDirs(secretFolder)
    }

    sourceSets {

        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
        }

        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)

            implementation(libs.kotlinx.datetime)
        }
    }
}

android {
    namespace = "com.aldyaz.movix"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources", secretFolder)

    defaultConfig {
        applicationId = "com.aldyaz.movix"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        compose = true
    }

    dependencies {
        debugImplementation(compose.uiTooling)
    }
}

tasks.register("generateSecret") {
    doLast {
        generateSecretProp("secret.properties")
    }
}

fun generateSecretProp(path: String) {
    val propContent = file("$rootDir/$path").readText()
    val propData = parseProperties(propContent)
    var ktContent = "package ${android.namespace}\n\nobject SecretConfig {\n"
    propData.forEach { (key, value) ->
        ktContent += "    const val $key = $value\n"
    }
    ktContent += "}"
    val folder = file(secretFolder)
    if (!folder.exists()) {
        folder.mkdirs()
    } else {
        val fileSecret = file("$secretFolder/SecretConfig.kt")
        if (!fileSecret.exists()) {
            fileSecret.createNewFile()
        }
        fileSecret.writeText(ktContent)
    }
}

fun parseProperties(content: String): Map<String, Any> {
    val props = mutableMapOf<String, Any>()
    content.lines()
        .filter { it.isNotBlank() }
        .forEach { line ->
            val key = line.substringBefore("=")
            val rawValue = line.substringAfter("=")
            val value: Any = when {
                rawValue == "true" || rawValue == "false" -> rawValue.toBoolean()
                rawValue.toIntOrNull() != null -> rawValue.toInt()
                rawValue.toLongOrNull() != null -> rawValue.toLong()
                else -> "\"$rawValue\""
            }
            props[key] = value
        }
    return props
}
