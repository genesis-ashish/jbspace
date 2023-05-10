dependencies {
    implementation("global.genesis:genesis-pal-execution")
    implementation("global.genesis:genesis-eventhandler")
    implementation(project(":jbspace-messages"))
    api("global.genesis:genesis-db")
    compileOnly(project(":jbspace-config"))
    compileOnly(project(path = ":jbspace-dictionary-cache", configuration = "codeGen"))
    testImplementation("global.genesis:genesis-dbtest")
    testImplementation("global.genesis:genesis-testsupport")
    testImplementation(project(path = ":jbspace-dictionary-cache", configuration = "codeGen"))
}

description = "jbspace-eventhandler"