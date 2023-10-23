plugins {
    kotlin("multiplatform")
}

kotlin {
    jvm()
    js {
        nodejs()
    }

    sourceSets {
        val commonMain by getting
        val commonTest by getting

        val jsMain by getting
        val jsTest by getting

        val jvmMain by getting
        val jvmTest by getting

    }
}