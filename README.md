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

where `x.x.x` is the latest version `1.0.8`

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

1. When a user is not authenticated.

```
       IccFanPassportActivity.launch(context = this, onAuthenticate = onAuthenticate)
```

2. When a user is authenticated.

```
                IccFanPassportActivity.launch(this, user, null)
```

**To Create Wallet after Deeplink.**

```
        IccFanPassportActivity.connectWallet(context = this, publicKey = publicKey, accountId = accountId)
```

**To Delegate Sign in to ICC after Signin has been clicked on the SDK**

```
        val onAuthenticate = object : OnAuthenticate {
            override fun signIn()  {
                val param = SdkParam(user)
                IccFanPassportActivity.launch(this@MainActivity, param, null)
            }
        }

```

**To Logout**

```
        IccFanPassportActivity.logOut(this)
```


**SdkParams**


This is an object that helps set the SDK. It  accepts optional arguments that include;

1. user of type **User**,
2. entryPoint of type **String**, which can be gotten from the enum EntryPoint,
3. public key of type **String** (required for wallet creation),
4. accountId of type **String** (required for wallet creation),
5. environment of enum type **Environment**

**EntryPoint**

**DEFAULT** -> default web URL

**CREATE_AVATAR**  -> Create avatar page

**ONBOARDING**  -> Onboarding page

**PROFILE**  -> Profile page

**CHALLENGES**  -> Challenges page

**REWARDS**  -> Rewards page


**Wallet Creation Flow**

To create a wallet on a fan passport, click Create Wallet in the fan passport module, and an in-app browser is launched. After creating, when connect is clicked, it opens the ICC app, where D3 get the **accountId** and **publicKey** and then sends it into the SDK by calling the build method with the **publicKey** and **accountId** as shown below the deep link must be implemented to add **accountId** and **publicKey** to the builder method to complete the wallet creation flow.

1. Add this to the manifest under the activity that will resolve the deep link; `MainActivity` in this case.
2. Click on Create Wallet in the fan passport module, and an in-app browser is launched.
4. after creating, when connect is clicked, it opens the ICC app, where D3 get the **accountId** and **publicKey** and then sends it into the SDK by calling the initialization method with the **publicKey** and **accountId** as shown below.

```
  <activity
            android:name=".MainActivity"
            android:exported="true">
            <intent-filter android:autoVerify="true">
                <action android:name="android.intent.action.VIEW" />
                <category android:name="android.intent.category.DEFAULT" />
                <category android:name="android.intent.category.BROWSABLE" />
                <data android:scheme="icc" />
                <data android:host="mintbase.xyz" />
            </intent-filter>
        </activity>
```
2. In the receiving activity, retrieve the **accountId** and **publicKey**, 3. Then, call this function

```
        IccFanPassportActivity.connectWallet(context = this, publicKey = publicKey, accountId = accountId)
``` 

3. Wallet created.


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


