rootProject.name = "genesisproduct-jbspace"

pluginManagement {
    pluginManagement {
        val genesisVersion: String by settings
        val deployPluginVersion: String by settings
        plugins {
            id("global.genesis.build") version genesisVersion
            id("global.genesis.deploy") version deployPluginVersion
        }
    }
    repositories {
        mavenLocal {
            // VERY IMPORTANT!!! EXCLUDE AGRONA AS IT IS A POM DEPENDENCY AND DOES NOT PLAY NICELY WITH MAVEN LOCAL!
            content {
                excludeGroup("org.agrona")
            }
        }
        mavenCentral()
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



include("jbspace-config")
include("jbspace-messages")
include("jbspace-eventhandler")
include("jbspace-script-config")
include("jbspace-distribution")
include("jbspace-dictionary-cache")
include("jbspace-dictionary-cache:jbspace-generated-sysdef")
include("jbspace-dictionary-cache:jbspace-generated-fields")
include("jbspace-dictionary-cache:jbspace-generated-dao")
include("jbspace-dictionary-cache:jbspace-generated-hft")
include("jbspace-dictionary-cache:jbspace-generated-view")
include("jbspace-deploy")
include("jbspace-site-specific")

includeBuild("../../client") {
    dependencySubstitution {
        substitute(module("client:web"))
            .using(project(":web"))
    }
}
