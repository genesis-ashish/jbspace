dependencies {
    implementation("global.genesis:genesis-messages")
    compileOnly(project(path = ":jbspace-dictionary-cache", configuration = "codeGen"))
}

description = "jbspace-messages"