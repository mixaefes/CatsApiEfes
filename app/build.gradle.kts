plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("org.jlleitschuh.gradle.ktlint") version ("10.2.0")
    id("io.gitlab.arturbosch.detekt") version ("1.18.1")
}
subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    ktlint {
        debug.set(false)
    }
}
detekt {
    toolVersion = "1.18.1"
    config = files("config/detekt/default-detekt-config.yml")
    buildUponDefaultConfig = true
    source = files("app/src/main/java", "app/src/main/kotlin")
}

android {
    compileSdk = (30)

    defaultConfig {
        applicationId = ("com.example.thecatsapi")
        minSdk = 21
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = ("androidx.test.runner.AndroidJUnitRunner")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.6.0")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.0")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    testImplementation("junit:junit:4.+")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    // Moshi
    implementation("com.squareup.moshi:moshi:1.12.0")
    kapt("com.squareup.moshi:moshi-kotlin-codegen:1.12.0")
    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.0")
    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.1.0-beta01")
    implementation("androidx.activity:activity-ktx:1.3.1")
    implementation("androidx.fragment:fragment-ktx:1.3.6")
    // RecyclerView
    implementation("androidx.recyclerview:recyclerview:1.2.1")
    // Coil
    implementation("io.coil-kt:coil:0.11.0")

    // Pagination
    val paging_version = "3.0.0" // current version at the time

    implementation("androidx.paging:paging-runtime:$paging_version")

    // alternatively - without Android dependencies for tests
    testImplementation("androidx.paging:paging-common:$paging_version")

    // optional - RxJava2 support
    implementation("androidx.paging:paging-rxjava2:$paging_version")

    // optional - RxJava3 support
    implementation("androidx.paging:paging-rxjava3:$paging_version")

    // optional - Guava ListenableFuture support
    implementation("androidx.paging:paging-guava:$paging_version")

    // Jetpack Compose Integration
    implementation("androidx.paging:paging-compose:1.0.0-alpha12")
}
