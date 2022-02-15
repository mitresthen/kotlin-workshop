enableFeaturePreview("VERSION_CATALOGS")
rootProject.name = "kotlin-workshop"

pluginManagement {

    repositories {
        gradlePluginPortal()
        mavenLocal()
        mavenCentral()
    }
    plugins {
        kotlin("jvm") version "1.6.10"
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    versionCatalogs {
        fileTree("versions").filter { it.isFile }.forEach {
            create(it.name.split(".").first()) {
                from(files(it))
            }
        }
    }
}
