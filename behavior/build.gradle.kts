@file:Suppress("SpellCheckingInspection")

//buildscript {
//
//    repositories {
//        jcenter()
//        mavenCentral()
//    }
//
//    dependencies {
//
//        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.20")
//
//    }
//
//}

plugins {
    id("java-library")
    id("kotlin")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

sourceSets.getByName("main") {
    java.srcDir("src/main/java")
    java.srcDir("src/main/kotlin")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.4.20")
    implementation("org.junit.jupiter:junit-jupiter:5.7.0")

}