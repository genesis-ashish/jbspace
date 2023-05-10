
// Add your genesis config dependencies here
dependencies {
    implementation(project(":jbspace-dictionary-cache:jbspace-generated-dao"))
    implementation(project(":jbspace-dictionary-cache:jbspace-generated-fields"))
    implementation(project(":jbspace-dictionary-cache:jbspace-generated-hft"))
    implementation(project(":jbspace-dictionary-cache:jbspace-generated-sysdef"))
    implementation(project(":jbspace-dictionary-cache:jbspace-generated-view"))

    implementation("global.genesis:auth-config:${properties["authVersion"]}")
}

description = "jbspace-dictionary-cache"
