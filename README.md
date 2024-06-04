An Android SDK that provides a simple way to launch a web view in Android applications.

Contents
1. Steps to Install
2. Wallet Creation Flow
3. Authentication Flow


Steps to Install
1. Add the dependency to your build.gradle.

**Kotlin** 

`implementation("com.github.Luna-Exchange:icc-fan-passport-android:x.x.x")`

**Groovy**

`implementation 'com.github.Luna-Exchange:icc-fan-passport-android:x.x.x`

where `x.x.x` is the latest version `1.0.9`

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


**Launch this SDK **

1. When a user is authenticated.

```
     IccFanPassportActivity.launch(this, user, onAuthenticate)
```

2.  When a user is not authenticated.

```
     IccFanPassportActivity.launch(context = this, onAuthenticate = onAuthenticate)
```

**To Delegate Sign in to ICC after Signin has been clicked on the SDK**

```
        val onAuthenticate = object : OnAuthenticate {
            override fun signIn()  {
                val user = User(email, authToken, name)
                IccFanPassportActivity.launch(this@MainActivity, user, onAuthenticate)
            }
        }

```

**To Logout**

```
    IccFanPassportActivity.logOut(this)
```


**Launch Arguments**


This is a function that helps launch the SDK. It  accepts optional arguments that include;

1. context.
2. user of type **User**.
3. entryPoint of type **String**can be obtained from the enum EntryPoint.
4. environment of enum type **Environment**: To declare the environment.
5. fantasyUri: URI to deep link into fantasy games. in the form `scheme:\\url`
6. predictorUri: URI to deep link into predictor games. in the form `scheme:\\url`
7. an interface helps with sign-in delegation when the user attempts to sign in to fanpassport.

**EntryPoint**

**DEFAULT** -> default web URL

**CREATE_AVATAR**  -> Create avatar page

**ONBOARDING**  -> Onboarding page

**PROFILE**  -> Profile page

**CHALLENGES**  -> Challenges page

**REWARDS**  -> Rewards page


**Environment**
DEVELOPMENT, PRODUCTION  


**Wallet Creation Flow**

To create a wallet on a fan passport, click Create Wallet in the fan passport module, and an in-app browser is launched. After creating, when connect is clicked, it deep links back to the SDK, where the wallet creation flow IS complete.

1. Add this to the host app's manifest.

```
  <activity
            android:name="com.insomnia.fanpassport.IccFanPassportActivity"
            android:launchMode="singleTask"
            android:exported="true">
        </activity>
```	


2. Click on Create Wallet in the fan passport module, and an in-app browser is launched.
3. after creating, when connect is clicked, the browser deep links into the SDK and the flow is completed.


**Authentication Flow**

This flow caters to users who use fan passports without getting authenticated via the ICC app. The expectation is that when calling the Sdk, an interface should be passed as an argument. e.g 

```
  val onAuthenticate = object : OnAuthenticate {
            override fun signIn()  {
		//handles authentication
                val param = SdkParam(user)
                IccFanPassportActivity.launch(this@MainActivity, param, null)
            }
        }
```


In this interface, a **signIn()** function handles authentication and then calls the SDK with the user object, as shown above. Therefore, this flow is executed when sign-in is clicked on the WebView, and the user is authenticated on a fan passport.

**Log Out**

When log out is clicked on the ICC App, the function below is called, which ensures that the user will be logged out when the fan passport is opened.

```
    IccFanPassportActivity.logOut(this)
```
