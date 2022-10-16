## Adding dependencies

add the `hilt-android-gradle-plugin` plugin to project's root `build.gradle` file

```kotlin

plugins {
  ...
  id("com.google.dagger.hilt.android") version "2.44" apply false
}

```
apply the Gradle plugin, add these dependencies, and enable Java 8 in `app/build.gradle` file

```kotlin
plugins {
  id "kotlin-kapt"
  id "com.google.dagger.hilt.android"
}

// Hilt uses Java 8 features
android {
  ...
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
}

dependencies {
  implementation("com.google.dagger:hilt-android:2.44")
  kapt("com.google.dagger:hilt-android-compiler:2.44")
}

// Allow references to generated code
kapt {
  correctErrorTypes = true
}

```

## Application Class
Create application class that's annotated with `@HiltAndroidApp` that serves as the application-level dependency container

```kotlin
@HiltAndroidApp
class MyApplication : Application()
```
register application at manifest
```xml
<application 
  ...
  android:name=".MyApplication"
  ...
</application>
```
