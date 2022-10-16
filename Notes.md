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

## Inject dependencies into Android classes
Hilt can provide dependencies to other Android classes that have the `@AndroidEntryPoint`
*Hilt supports (Application `@HiltAndroidApp`, ViewModel `@HiltViewModel`, Activity, Fragment, View, Service, BroadcastReceiver*

```kotlin
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
...
}
```

## Hilt Bindings
### Constructor Injection
```kotlin
@HiltViewModel
class MyViewModel @Inject constructor(private val repository: Lazy<MyRepository>) : ViewModel() {
}
```
### Field Injection
```kotlin

@Inject
lateinit var repositoryImpl: MyRepositoryImpl

```


## Hilt Modules
A Hilt module is a class that is annotated with `@Module`.
you must annotate Hilt modules with `@InstallIn` to tell Hilt which Android class each module will be used or installed in.
```kotlin
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
  ...
}
```

### Inject interface instances with @Binds
*a class that has an `@Inject constructor`, doesn't need `@Provides()`*
```kotlin

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindMyRepository(
        myRepositoryImpl: MyRepositoryImpl
    ): MyRepository
}

class MyRepositoryImpl @Inject constructor(
    private val api: MyApi,
    private val appContext: Application,
) : MyRepository {}
```


### Inject instances with @Provides
*you cannot construcor inject an interface or a class from external library (eg. retrofit, room, etc.)*

```kotlin
object AppModule {
 
 ...
 
 @Provides
 @Singleton
 fun provideMyApi(): MyApi {
     return Retrofit.Builder()
            .baseUrl("https://test.com")
            .build()
            .create(MyApi::class.java)
  }
  
  ...
}
  
class MyRepositoryImpl(private val api: MyApi) : MyRepository {}
```

