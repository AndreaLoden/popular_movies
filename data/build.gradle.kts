import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    id("kotlinx-serialization")}

kotlin {
    //select iOS target platform depending on the Xcode environment variables
    val iOSTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
    if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true)
    ::iosArm64
    else
    ::iosX64

    iOSTarget("ios") {
        binaries {
            framework {
                baseName = "presentation"
            }
        }
    }

    jvm("android")

    sourceSets["commonMain"].dependencies {
        api(project(":domain"))
        implementation("org.jetbrains.kotlin:kotlin-stdlib-common")
        implementation("io.ktor:ktor-client-json:1.2.2")
        implementation("io.ktor:ktor-client-serialization:1.2.2")
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:0.20.0")
    }

    sourceSets["androidMain"].dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib")
        implementation("io.ktor:ktor-client-android:1.2.2")
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:0.20.0")
    }

    sourceSets["iosMain"].dependencies {
        implementation("io.ktor:ktor-client-ios:1.2.2")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-native:1.3.5")
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:0.20.0")
    }
}


val packForXcode by tasks.creating(Sync::class) {
    group = "build"

//selecting the right configuration for the iOS framework depending on the Xcode environment variables
    val mode = System.getenv("CONFIGURATION") ?: "DEBUG"
    val framework = kotlin.targets.getByName<KotlinNativeTarget>("ios").binaries.getFramework(mode)

    inputs.property("mode", mode)
    dependsOn(framework.linkTask)

    val targetDir = File(buildDir, "xcode-frameworks")
    from({ framework.outputDirectory })
    into(targetDir)

    doLast {
        val gradlew = File(targetDir, "gradlew")
        gradlew.writeText("#!/bin/bash\nexport 'JAVA_HOME=${System.getProperty("java.home")}'\ncd '${rootProject.rootDir}'\n./gradlew \$@\n")
        gradlew.setExecutable(true)
    }
}

tasks.getByName("build").dependsOn(packForXcode)