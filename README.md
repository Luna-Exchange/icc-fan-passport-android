An Android SDK that provides a simple way to launch a web view in android applications.


1. Add the dependency to your build.gradle.

**Kotlin** 

`implementation("com.github.Luna-Exchange:icc-fan-passport-android:x.x.x")`

**Groovy**

`implementation 'com.github.Luna-Exchange:icc-fan-passport-android:x.x.x`

where `x.x.x` is the latest version `1.0.6`

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
            .entryPoint(EntryPoint.DEFAULT)
	    .onNavigateBack{ //TODO: enter an action you want to take when back is pressed }
            .build()
```
Where accessToken(required), email(required), name (required), and onNavigateBack() are passed from the ICC app, and entryPoint takes an enum;

**DEFAULT** -> default web URL

**CREATE_AVATAR**  -> Create avatar page

**ONBOARDING**  -> Onboarding page

**PROFILE**  -> Profile page

**CHALLENGES**  -> Challenges page

**REWARDS**  -> Rewards page

onNavigateBack() >> Where you want to navigate to after the SDK has been closed when Back to ICC is pressed on the web

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
2. In the receiving activity, retrieve the **accountId** and **publicKey**, 3. Then, the builder sends the **publicKey** and **accountId** to the fan passport SDK.

``` 

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val pbText = findViewById<TextView>(R.id.public_key)
        val aidText = findViewById<TextView>(R.id.account_id)


        val intent = intent
        val data = intent.data
        val accountId = data?.getQueryParameter("account_id") ?: ""
        val publicKey = data?.getQueryParameter("public_key") ?: ""

	IccFanPassportActivity.Builder(this)
            .accessToken("")
            .email("")
            .name("")
            .entryPoint(EntryPoint.DEFAULT)
            .accountId(accountId)
            .publicKey(publicKey)
	    .onNavigateBack{ //TODO: enter an action you want to take when back is pressed }
            .build()
	}

}

```
PS:  **accountId** and **publicKey** are optional.

5. Wallet created.
