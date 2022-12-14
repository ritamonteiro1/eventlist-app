plugins {
    id(BuildPlugins.ANDROID_APPLICATION)
    id(BuildPlugins.KOTLIN_ANDROID)
}

android {
    compileSdk = Android.COMPILE
    buildToolsVersion = AppConfig.BUILD_TOOLS_VERSION

    defaultConfig {
        applicationId = AppConfig.APPLICATION_ID
        minSdk = Android.MIN
        targetSdk = Android.TARGET
        versionCode = Android.APP_VERSION_CODE
        versionName = Android.APP_VERSION_NAME
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
}

dependencies {
    androidx()
    koin()
    implementation(project(path = ":di"))
    implementation(project(path = ":core"))
    implementation(project(path = ":featureauth"))
    implementation(project(path = ":featurehome"))
    implementation(Libs.PLAY_SERVICES)
}