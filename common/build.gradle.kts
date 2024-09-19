plugins {
    `maven-publish`
    kotlin("jvm") version "2.0.10"
}

dependencies {
//    implementation("com.google.code.gson:gson:2.11.0")
//    implementation("org.apache.commons:commons-lang3:3.17.0")
//    implementation("com.google.guava:guava:33.3.0-jre")
    implementation("org.apache.logging.log4j:log4j-core:2.24.0")
    testImplementation(kotlin("test"))
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

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components.getByName("java"))
            groupId = project.group.toString()
            artifactId = project.name
            version = project.version.toString()
        }
//        register("jar", MavenPublication::class.java) {
//            from(components.getByName("java"))
//            groupId = project.group.toString()
//            artifactId = project.name
//            version = project.version.toString()
//        }
    }
}
