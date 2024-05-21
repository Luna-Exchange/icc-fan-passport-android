package com.insomnia.fanpassport

class EnvConfig(private val environment: Environment = Environment.DEVELOPMENT) {

    val iccUi : String
        get() = if (environment == Environment.DEVELOPMENT) "https://icc-fan-passport-staging.vercel.app" else "https://fanpassport.icc-cricket.com"

    val iccApi : String
        get() = if (environment == Environment.DEVELOPMENT) "https://icc-fan-passport-stg-api.insomnialabs.xyz" else "https://fanpassport-api.icc-cricket.com"

    val mintBaseUrl : String
        get() = if (environment == Environment.DEVELOPMENT) "https://testnet.wallet.mintbase.xyz" else "https://wallet.mintbase.xyz/?theme=icc"

    val scheme : String
        get() = if (environment == Environment.DEVELOPMENT) "iccdev" else "icc"

}


enum class Environment(val key : String) {
    DEVELOPMENT("development"),
    PRODUCTION("production")
}