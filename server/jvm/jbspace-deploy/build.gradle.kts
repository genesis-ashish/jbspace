plugins {
    id("global.genesis.deploy")
}

description = "jbspace-deploy"

dependencies {
    genesisServer(
        group = "global.genesis",
        name = "genesis-distribution",
        version = properties["genesisVersion"].toString(),
        classifier = "bin",
        ext = "zip"
    )
    genesisServer(
        group = "global.genesis",
        name = "auth-distribution",
        version = properties["authVersion"].toString(),
        classifier = "bin",
        ext = "zip"
    )

    genesisServer(project(":jbspace-distribution", "distribution"))
    genesisServer(project(":jbspace-site-specific", "distribution"))
    genesisWeb("client:web")

    api(project(":jbspace-eventhandler"))
    api(project(":jbspace-messages"))
    // Add additional dependencies on other external distributions here
}
tasks {
    copyDependencies {
        from(configurations.getByName("genesisServer"))
    }
}
