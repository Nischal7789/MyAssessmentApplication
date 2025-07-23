plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false

    // ✅ Hilt plugin
    id("com.google.dagger.hilt.android") version "2.48" apply false
}

// ✅ No need for buildscript block or repositories here

