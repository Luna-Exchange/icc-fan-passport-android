An Android SDK that provides a simple way to launch a web view in android applications.


1. Add the dependency to your build.gradle.

**Kotlin** 

`implementation("com.github.Luna-Exchange:icc-fan-passport-android:x.x.x")`

**Groovy**

`implementation 'com.github.Luna-Exchange:icc-fan-passport-android:x.x.x`

where `x.x.x` is the latest version `1.0.3`

2. In settings.gradle

**Kotlin**

```
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

```
        
        
**Groovy**

```
dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}
	}
```

3. Sync the project.

4. Start the SDK with
``` 
IccFanPassportActivity.Builder(this)
            .accessToken("")
            .email("")
            .name("")
	    .onNavigateBack{ //TODO: enter an action you want to take when back is pressed }
            .build()
```
Where accessToken, email, onNavigateBack() and name are passed from the ICC app.

onNavigateBack() >> Where you want to navigate to after the SDK has been closed on when Back to ICC is pressed on the web
