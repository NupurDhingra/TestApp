import org.gradle.api.initialization.resolve.RepositoriesMode.FAIL_ON_PROJECT_REPOS

pluginManagement {
    repositories {
        @Suppress("DEPRECATION")
        //noinspection JcenterRepositoryObsolete
        jcenter()
        google()
        mavenCentral()
        gradlePluginPortal()
        maven { url = uri("https://jitpack.io") }
        maven { url = uri("https://maven.google.com") }
    }
}
dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositoriesMode.set(FAIL_ON_PROJECT_REPOS)

    @Suppress("UnstableApiUsage")
    repositories(fun RepositoryHandler.() {
        @Suppress("DEPRECATION")
        //noinspection JcenterRepositoryObsolete
        jcenter()
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
        maven { url = uri("https://maven.google.com") }
    })

    versionCatalogs {
        create("libs") {
            files("./gradle/libs.version.toml")
        }
    }
}

rootProject.name = "TestApp"
include(":app")
