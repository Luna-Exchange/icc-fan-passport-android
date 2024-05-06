package com.insomnia.fanpassport

enum class EntryPoint( val path : String) {
    DEFAULT("/"),
    CREATE_AVATAR("/create-avatar"),
    ONBOARDING("/onboarding"),
    PROFILE("/profile"),
    CHALLENGES("/challenges"),
    REWARDS("/rewards");
}