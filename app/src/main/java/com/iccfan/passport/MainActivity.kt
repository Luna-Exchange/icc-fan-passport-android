package com.iccfan.passport

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.insomnia.fanpassport.EntryPoint
import com.insomnia.fanpassport.Environment
import com.insomnia.fanpassport.IccFanPassportActivity
import com.insomnia.fanpassport.OnJavScriptInterface

class MainActivity : AppCompatActivity(), OnJavScriptInterface{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = intent
        val data = intent.data
        val accountId = data?.getQueryParameter("account_id") ?: ""
        val publicKey = data?.getQueryParameter("public_key") ?: ""

        IccFanPassportActivity.Builder(this).build()
    }

    override fun onAuthenticateWithIcc() {
        IccFanPassportActivity.Builder(this).accessToken("eyJhbGciOiJSUzI1NiIsImtpZCI6Imlia2V5MTIzNDUiLCJ0eXAiOiJKV1QifQ.eyJzdWIiOiJhNTk3OTkzNi01ZTYyLTQyMjItYmJlZi01NzliYzgwZmNlYmIiLCJwcm9maWxlX2lkIjoiYTU5Nzk5MzYtNWU2Mi00MjIyLWJiZWYtNTc5YmM4MGZjZWJiIiwiaWRwX3N1YiI6IjVlZmZiNDgzLTc1NGMtNGY0Yy04NTdjLTViZTY3OWQxMGZlMCIsInRlbmFudF9pZCI6ImljY2RldiIsImp0aSI6ImI4ZjU4NGQ0LTRjMDEtNGY2OS05NTcyLWVlOWE3MTYxN2NjNiIsInNpZCI6ImI4ZjU4NGQ0LTRjMDEtNGY2OS05NTcyLWVlOWE3MTYxN2NjNiIsImRldmljZV9pZCI6Ijg0ODU4OGYxLTQxNTctNDllNy1iOGFhLWUzZjEyMGZiMzdiZSIsImRldmljZV90eXBlIjoid2ViX2Jyb3dzZXIiLCJyb2xlIjoidXNlciIsInNjb3BlIjoiY2F0YWxvZ3VlLnJlYWQiLCJ0eXAiOiJhY2Nlc3MiLCJhenAiOiI3ZDE4Y2I2Ni01YmFmLTQ5NzktODVjMy05YTZjYzM3ZTYyMmUiLCJpZHBfYmFnIjp7InByb3ZpZGVyIjoiQURCMkMiLCJ0ZnAiOiJCMkNfMUFfU0lHTklOU0lHTlVQIn0sImF1ZCI6ImRldi1zY2FsZS1mcmFua2Z1cnQtYXdzIiwiZXhwIjoxNzE2MjkyMDQ3LCJpc3MiOiJodHRwczovL2RlbHRhdHJlLmNvbS9pc3N1ZXIiLCJpYXQiOjE3MTYyOTAyNDcsIm5iZiI6MTcxNjI5MDI0N30.DSkF_snDsyryISfvvQcK30RJ3cwG4KWNjHMG4QgXniHYzvHQudkfalXfMdYZePOKvdPUb9Yvo5SJfw8druBPZU6zYVlQrm1U1UAqsugMR2gzvJGiwiNiD0kEpP08R0DwCrrp34g0_Ff8pcYM_Ja5L_83xgkMGHMeXet871MXmn59z7oKieS2or1Xelr_SYruoz3u1OyWaPbUqf06EmXHB335jEyuTNlRmwKPfkj_ujgb9RRxQ4MIqH_ZQV_OzCWj_-hQG4-V00jcGHC5AzHNvnJhq7Eu8dgyR1qVD0WBoOVvfhofD8nRNMRglR1oEuzPTlG458JOOqMQv4ALZ3ap5w")
            .email("falaycornelius@insomnialabs.io")
            .name("Iyanu Falaye")
            .entryPoint(EntryPoint.PROFILE.path)
            .environment(Environment.PRODUCTION)
            .onNavigateBack {
                this.startActivity(Intent(this, AnotherActivity::class.java))
            }.build()
    }

}