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
            "eyJhbGciOiJSUzI1NiIsImtpZCI6Imlia2V5MTIzNDUiLCJ0eXAiOiJKV1QifQ.eyJzdWIiOiIxODA5YzQzZS0wOTJmLTRjY2ItOWU2Zi1kYjFkYzk2NTQ2ZWQiLCJwcm9maWxlX2lkIjoiMTgwOWM0M2UtMDkyZi00Y2NiLTllNmYtZGIxZGM5NjU0NmVkIiwiaWRwX3N1YiI6IjZlNWY5YTU4LTJiYTctNDU4NS04NmE2LWFlNTk4NmFmMWNjYyIsInRlbmFudF9pZCI6ImljY2RldiIsImp0aSI6IjVhYzY0YjRkLWRlZDMtNGQxZi04NzJkLWU2YTM3OTE3ZWFjOSIsInNpZCI6IjVhYzY0YjRkLWRlZDMtNGQxZi04NzJkLWU2YTM3OTE3ZWFjOSIsImRldmljZV9pZCI6IjMwNDQzODZiLTNiYjgtNDViMy1hYWE0LWRlNTZiY2IxNTY5ZCIsImRldmljZV90eXBlIjoid2ViX2Jyb3dzZXIiLCJyb2xlIjoidXNlciIsInNjb3BlIjoiY2F0YWxvZ3VlLnJlYWQiLCJ0eXAiOiJhY2Nlc3MiLCJhenAiOiI3ZDE4Y2I2Ni01YmFmLTQ5NzktODVjMy05YTZjYzM3ZTYyMmUiLCJpZHBfYmFnIjp7InByb3ZpZGVyIjoiQURCMkMiLCJ0ZnAiOiJCMkNfMUFfU0lHTklOU0lHTlVQIn0sImF1ZCI6ImRldi1zY2FsZS1mcmFua2Z1cnQtYXdzIiwiZXhwIjoxNzE1NzcxMTcyLCJpc3MiOiJodHRwczovL2RlbHRhdHJlLmNvbS9pc3N1ZXIiLCJpYXQiOjE3MTU3NjkzNzIsIm5iZiI6MTcxNTc2OTM3Mn0.cT3dge4YvnOZ5pIqaDWgWILTdEHVoy2K8kx8CZ1EE6TDh3avHJs0RlD2y1HYkmG5CDXRBs34At6vH2PDmHITrSUp8hfytsRkt2-hTxO_Lxb1LFjmSnv1_ScK9ByUbAo_ZapacLJb_DqgKkQ0xi3mhg0Kl9Grf6woHoWiEObk-lRiqA3NzNTcvvfR3J_B6st-D5KY3g3iJowMKI5skUbwI90wN5kiafvG0dqraS6hoWzWMt_1b9G3q6ILobiqsH47Dg5FPQdQRVC5RY_9AthpqUc5VRfaUssVjwOQa254iB9EkZQqBSq2LLRE0uAYLCZOlneq4qu2bNd60NSIhB_lHw"        )
            .name("John Doe")
            .email("iyanuc.falaye@google.com")
            .entryPoint(EntryPoint.PROFILE.path)
            .accountId(accountId)
            .publicKey(publicKey)
            .onNavigateBack {
                this.startActivity(Intent(this, AnotherActivity::class.java))
            }.build()
    }
}