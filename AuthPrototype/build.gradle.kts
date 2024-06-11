// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false

    // NOTE imp to keep in sync with app/build.gradle.kts
    val hiltVer = "2.51.1"

    //    AX_SH ADD HILT
    id("com.google.dagger.hilt.android") version hiltVer apply false
}