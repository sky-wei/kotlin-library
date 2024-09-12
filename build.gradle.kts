plugins {
    kotlin("jvm") version "2.0.10"
}

allprojects {
    group = "com.github.jingcai-wei.jvm-library"
    version = "1.0-SNAPSHOT"

    repositories {
        maven { url=uri("https://maven.aliyun.com/repository/public") }
        maven { url=uri("https://maven.aliyun.com/repository/central") }
        maven { url=uri("https://jitpack.io") }
        mavenCentral()
    }


}

subprojects {


}

java {
    withSourcesJar()
    withJavadocJar()
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(11)
}
