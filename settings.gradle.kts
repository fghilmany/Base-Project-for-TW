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
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "BaseProject"
include(":app")
include(":core:configs:rest:retrofit")
include(":core:configs:sqlite:room")
include(":core:shared_models:room_entity")
include(":core:shared-resource")
include(":utilities")
include(":feature:movielist:domain")
include(":feature:movielist:api")
include(":feature:movielist:apiinfra")
include(":feature:movielist:presentation")
include(":feature:movielist:ui")
include(":feature:moviedetail:domain")
include(":feature:moviedetail:api")
