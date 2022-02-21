

val kotlinVersion = "1.6.0"

plugins {
    id("idea")
    `kotlin-dsl`

}

repositories {
    mavenCentral()
}


dependencies {
    implementation(ktor.bundles.ktorBundle)
    implementation(db.bundles.exposedWithH2)// https://mvnrepository.com/artifact/com.google.code.gson/gson
    implementation("com.google.code.gson:gson:2.7")

    testImplementation ("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly ("org.junit.jupiter:junit-jupiter-engine:5.8.2")
}