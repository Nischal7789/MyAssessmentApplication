pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS) // ✅ Prefer instead of FAIL
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "MyAssessmentApplication" // ✅ Remove extra 's' if it's a typo
include(":app")

 