import org.gradle.kotlin.dsl.DependencyHandlerScope

object ClasspathDependenciesLibs {
    const val ANDROID_GRADLE_PLUGIN =
        "com.android.tools.build:gradle:${Versions.ANDROID_GRADLE_PLUGIN_VERSION}"
    const val KOTLIN_GRADLE_PLUGIN =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN_VERSION}"
    const val NAV_SAFE_ARGS =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.NAV_SAFE_ARGS_VERSION}"
}

object Libs {
    const val JUNIT4 =
        "junit:junit:${Versions.JUNIT4_VERSION}"
    const val MOCKK =
        "io.mockk:mockk:${Versions.MOCKK_VERSION}"
    const val JUNIT_EXT =
        "androidx.test.ext:junit:${Versions.JUNIT_EXT_VERSION}"
    const val ESPRESSO =
        "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_VERSION}"
    const val HTTP_INTERCEPTOR =
        "com.squareup.okhttp3:logging-interceptor:${Versions.HTTP_INTERCEPTOR_VERSION}"
    const val GLIDE =
        "com.github.bumptech.glide:glide:${Versions.GLIDE_VERSION}"
}

fun DependencyHandlerScope.androidx() {
    "implementation"("androidx.appcompat:appcompat:${Versions.APP_COMPAT_VERSION}")
    "implementation"("androidx.core:core-ktx:${Versions.CORE_KTX_VERSION}")
    "implementation"("com.google.android.material:material:${Versions.MATERIAL_DESIGN_VERSION}")
    "implementation"("androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT_VERSION}")
}

fun DependencyHandlerScope.retrofit() {
    "implementation"("com.squareup.retrofit2:retrofit:${Versions.RETROFIT_VERSION}")
    "implementation"("com.squareup.retrofit2:converter-gson:${Versions.GSON_VERSION}")
}

fun DependencyHandlerScope.coroutines() {
    "implementation"("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINES_VERSION}")
    "testImplementation"("org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.COROUTINES_VERSION}")
}

fun DependencyHandlerScope.lifecycle() {
    "implementation"("androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE_VERSION}")
    "implementation"("androidx.lifecycle:lifecycle-livedata-ktx:${Versions.LIFECYCLE_VERSION}")
}

fun DependencyHandlerScope.koin() {
    "implementation"("io.insert-koin:koin-core:${Versions.KOIN_VERSION}")
    "implementation"("io.insert-koin:koin-android:${Versions.KOIN_VERSION}")
}

fun DependencyHandlerScope.navigation() {
    "implementation"("androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION_VERSION}")
    "implementation"("androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION_VERSION}")
}

fun DependencyHandlerScope.room() {
    "implementation"("androidx.room:room-runtime:${Versions.ROOM_VERSION}")
    "implementation"("androidx.room:room-ktx:${Versions.ROOM_VERSION}")
}