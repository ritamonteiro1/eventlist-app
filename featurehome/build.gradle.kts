plugins {
    id(BuildPlugins.ANDROID_LIBRARY)
    id(BuildPlugins.KOTLIN_ANDROID)
    id(BuildPlugins.KOTLIN_PARCELIZE)
    id(BuildPlugins.NAV_SAFE_ARGS)
}

android {
    compileSdk = Android.COMPILE
    buildToolsVersion = AppConfig.BUILD_TOOLS_VERSION

    defaultConfig {
        minSdk = Android.MIN
        targetSdk = Android.TARGET
        testInstrumentationRunner = Android.TEST_INSTRUMENTATION_RUNNER
        multiDexEnabled = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = AndroidCompileOptions.SOURCE_COMPATIBILITY
        targetCompatibility = AndroidCompileOptions.TARGET_COMPATIBILITY
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = Android.JVM_TARGET
        }
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    androidx()
    koin()
    retrofit()
    navigation()
    coroutines()
    lifecycle()
    androidTestImplementation(Libs.JUNIT_EXT)
    androidTestImplementation(Libs.ESPRESSO)
    testImplementation(Libs.ARCH_CORE_TESTING)
    testImplementation(Libs.JUNIT4)
    testImplementation(Libs.MOCKK)
    implementation(Libs.GLIDE)
    implementation(project(path = ":core"))
    implementation(project(path = ":datalocal"))
    implementation(project(path = ":navigation"))
}