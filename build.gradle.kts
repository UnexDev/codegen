//plugins {
//    kotlin("multiplatform") version "1.9.0"
//}
//
//group = "dev.unex"
//version = "0.0.1"
//
//repositories {
//    mavenCentral()
//}
//
//kotlin {
//    jvm {
//        jvmToolchain(17)
//        withJava()
//        testRuns.named("test") {
//            executionTask.configure {
//                useJUnitPlatform()
//            }
//        }
//    }
//
//    js {
//        browser {
//            commonWebpackConfig {
//                cssSupport {
//                    enabled.set(true)
//                }
//            }
//        }
//    }
//
//
//
//    sourceSets {
//        val commonMain by getting
//        val commonTest by getting {
//            dependencies {
//                implementation(kotlin("test"))
//            }
//        }
//        val jvmMain by getting
//        val jvmTest by getting
//        val jsMain by getting
//        val jsTest by getting
//    }
//}

buildscript {
    extra["kotlin_plugin_id"] = "dev.unex.codegen.plugin"
}

plugins {
    kotlin("jvm") version "1.9.10" apply false
    id("org.jetbrains.dokka") version "1.9.10" apply false
    id("com.gradle.plugin-publish") version "1.2.1" apply false
    id("com.github.gmazzo.buildconfig") version "4.1.2" apply false
}


allprojects {
    group = "dev.unex.codegen"
    version = "0.0.1"
}

subprojects {
    repositories {
        mavenCentral()
    }
}
