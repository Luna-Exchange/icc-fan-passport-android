An Android SDK that provides a simple way to launch a web view in android applications.


1. Add the dependency to your build.gradle.

**Kotlin** 

`implementation("com.insomnia:fanpassport:x.x.x")`

**Groovy**

`implementation 'com.insomnia:fanpassport:x.x.x'`

where `x.x.x` is the latest version `1.0.8`

2. In settings.gradle

**Kotlin**

```

    maven {
            url = uri("https://maven.pkg.github.com/Luna-Exchange/icc-fan-passport-android")
            credentials {
                username = property("gpr.user")
                 password = property("gpr.token")
                                }
                   }

```
        
        
**Groovy**


```
  maven {
            url 'https://maven.pkg.github.com/Luna-Exchange/icc-fan-passport-android'
            credentials {
                username = property("gpr.user")
                password = property("gpr.token")
            }
        }
```

                
 Where the GitHub user and token is saved in a `github.properties` file     

3. Sync the project.

4. Start the SDK with
``` 
IccFanPassportActivity.Builder(this)
            .accessToken("")
            .email("")
            .name("")
            .userName("")
            .build()
```
Where accessToken, email, name and username are passed from the ICC app.
