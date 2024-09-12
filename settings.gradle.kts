//pluginManagement {
//    repositories {
//        maven { url=uri("https://maven.aliyun.com/repository/public") }
//        maven { url=uri("https://maven.aliyun.com/repository/central") }
//        maven { url=uri("https://jitpack.io") }
//        mavenCentral()
//    }
//}
//dependencyResolutionManagement {
//    repositories {
//        maven { url=uri("https://maven.aliyun.com/repository/public") }
//        maven { url=uri("https://maven.aliyun.com/repository/central") }
//        maven { url=uri("https://jitpack.io") }
//        mavenCentral()
//    }
//}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

rootProject.name = "kotlin-library"

include("common")
