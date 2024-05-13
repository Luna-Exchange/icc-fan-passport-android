package com.iccfan.passport

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.insomnia.fanpassport.EntryPoint
import com.insomnia.fanpassport.IccFanPassportActivity


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

        IccFanPassportActivity.Builder(this).accessToken(
            "eyJhbGciOiJSUzI1NiIsImtpZCI6Imlia2V5MTIzNDUiLCJ0eXAiOiJKV1QifQ.eyJzdWIiOiIxODA5YzQzZS0wOTJmLTRjY2ItOWU2Zi1kYjFkYzk2NTQ2ZWQiLCJwcm9maWxlX2lkIjoiMTgwOWM0M2UtMDkyZi00Y2NiLTllNmYtZGIxZGM5NjU0NmVkIiwiaWRwX3N1YiI6IjZlNWY5YTU4LTJiYTctNDU4NS04NmE2LWFlNTk4NmFmMWNjYyIsInRlbmFudF9pZCI6ImljY2RldiIsImp0aSI6ImMzYmYwMTdiLTNjNDctNGU2ZS1hMjc3LWMwZTkxMDA1NDA2MCIsInNpZCI6ImMzYmYwMTdiLTNjNDctNGU2ZS1hMjc3LWMwZTkxMDA1NDA2MCIsImRldmljZV9pZCI6ImYyZTFkNTI2LTM1ZDgtNGVhYi04ZWIwLWE2NDgzMTRmNDU0MCIsImRldmljZV90eXBlIjoid2ViX2Jyb3dzZXIiLCJyb2xlIjoidXNlciIsInNjb3BlIjoiY2F0YWxvZ3VlLnJlYWQiLCJ0eXAiOiJhY2Nlc3MiLCJhenAiOiI3ZDE4Y2I2Ni01YmFmLTQ5NzktODVjMy05YTZjYzM3ZTYyMmUiLCJpZHBfYmFnIjp7InByb3ZpZGVyIjoiQURCMkMiLCJ0ZnAiOiJCMkNfMUFfU0lHTklOU0lHTlVQIn0sImF1ZCI6ImRldi1zY2FsZS1mcmFua2Z1cnQtYXdzIiwiZXhwIjoxNzE1NTY0MDE5LCJpc3MiOiJodHRwczovL2RlbHRhdHJlLmNvbS9pc3N1ZXIiLCJpYXQiOjE3MTU1NjIyMTksIm5iZiI6MTcxNTU2MjIxOX0.qu7l78JGcJwlWKmwaGgY5vEh_EIjOi1apc0mqDjDbG2Q6tIChkEi04wA_SHlHXOw2ULxyksOXWhEpOexQAMsdYVZJsQC3GFsfZEILQXe2p2XG9dLJfdlQi6J88wiYq4Fjtowc4BAv3UsXuWoXXhmrh92aSiaULeeScCJQhKUIlooPVOlX2rTgiYW3CEOAwxTzjA7LHrWLgUEa5WnImSt_rHGDpan7SjcdRnevaUQEYNuvxom-6ahbj0OFowVXaTizXpW6D6ASGuHqTDrS6Bh5t9gF3lNJQ1XGu0ATQ4ccPN3QcDAqWh33lBfQWEMb2ACm-IcizlQsMusC8RfIJjRsA").email("iyanuc.falaye@gmail.com")
            .name("John Doe")
            .entryPoint(EntryPoint.PROFILE)
            .accountId(accountId)
            .publicKey(publicKey)
            .onNavigateBack {
                this.startActivity(Intent(this, AnotherActivity::class.java))
            }.build()
    }
}