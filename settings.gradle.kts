rootProject.name = "jbspace"

// servers
includeBuild("server/jvm") {
    name = "genesisproduct-jbspace"
}

// clients
includeBuild("client")

pluginManagement {
    repositories {
        gradlePluginPortal()
        maven {
            url = uri("https://genesisglobal.jfrog.io/genesisglobal/dev-repo")
            credentials {
                username = extra.properties["genesisArtifactoryUser"].toString()
                password = extra.properties["genesisArtifactoryPassword"].toString()
            }
        }
    }
}
