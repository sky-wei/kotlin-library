allprojects {
    group = "com.github.sky-wei.jvm-library"
    version = "1.0.1"

    repositories {
        maven { url=uri("https://maven.aliyun.com/repository/public") }
        maven { url=uri("https://maven.aliyun.com/repository/central") }
        maven { url=uri("https://jitpack.io") }
        mavenCentral()
    }
}