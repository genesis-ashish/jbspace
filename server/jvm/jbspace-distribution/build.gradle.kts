plugins {
    distribution
}

dependencies {
    implementation(project(":jbspace-config"))
    implementation(project(":jbspace-dictionary-cache"))
    implementation(project(":jbspace-eventhandler"))
    implementation(project(":jbspace-messages"))
    implementation(project(":jbspace-script-config"))
}

description = "jbspace-distribution"

distributions {
    main {
        contents {
            // Octal conversion for file permissions
            val libPermissions = "600".toInt(8)
            val scriptPermissions = "700".toInt(8)
            into("jbspace/bin") {
                from(configurations.runtimeClasspath)
                exclude("jbspace-config*.jar")
                exclude("jbspace-script-config*.jar")
                exclude("jbspace-distribution*.jar")
                include("jbspace-*.jar")
            }
            into("jbspace/lib") {
                from("${project.rootProject.buildDir}/dependencies")
                duplicatesStrategy = DuplicatesStrategy.EXCLUDE

                exclude("genesis-*.jar")
                exclude("jbspace-*.jar")
                exclude("*.zip")

                fileMode = libPermissions
            }
            into("jbspace/cfg") {
                from("${project.rootProject.projectDir}/jbspace-config/src/main/resources/cfg")
                from(project.layout.buildDirectory.dir("generated/product-details"))
                filter(
                    org.apache.tools.ant.filters.FixCrLfFilter::class,
                    "eol" to org.apache.tools.ant.filters.FixCrLfFilter.CrLf.newInstance("lf")
                )
            }
            into("jbspace/scripts") {
                from("${project.rootProject.projectDir}/jbspace-config/src/main/resources/scripts")
                from("${project.rootProject.projectDir}/jbspace-script-config/src/main/resources/scripts")
                filter(
                    org.apache.tools.ant.filters.FixCrLfFilter::class,
                    "eol" to org.apache.tools.ant.filters.FixCrLfFilter.CrLf.newInstance("lf")
                )
                fileMode = scriptPermissions
            }
            // Removes intermediate folder called with the same name as the zip archive.
            into("/")
        }
    }
}

val distribution by configurations.creating {
    isCanBeConsumed = true
    isCanBeResolved = false
}

// To give custom name to the distribution package
tasks {
    distZip {
        archiveBaseName.set("genesisproduct-jbspace")
        archiveClassifier.set("bin")
        archiveExtension.set("zip")
    }
    copyDependencies {
        enabled = false
    }
}

artifacts {
    val distzip = tasks.distZip.get()
    add("distribution", distzip.archiveFile) {
        builtBy(distzip)
    }
}

publishing {
    publications {
        create<MavenPublication>("jbspaceServerDistribution") {
            artifact(tasks.distZip.get())
        }
    }
}

description = "jbspace-distribution"
