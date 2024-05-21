package com.iccfan.passport

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.insomnia.fanpassport.EntryPoint
import com.insomnia.fanpassport.Environment
import com.insomnia.fanpassport.IccFanPassportActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = intent
        val data = intent.data
        val accountId = data?.getQueryParameter("account_id") ?: ""
        val publicKey = data?.getQueryParameter("public_key") ?: ""

        IccFanPassportActivity.Builder(this).accessToken("eyJhbGciOiJSUzI1NiIsImtpZCI6Imlia2V5MTIzNDUiLCJ0eXAiOiJKV1QifQ.eyJzdWIiOiJmYjk4MDE5NS03MDAyLTRjNTItYWFlZC1hMTA2ZTE4NWY3NWYiLCJwcm9maWxlX2lkIjoiZmI5ODAxOTUtNzAwMi00YzUyLWFhZWQtYTEwNmUxODVmNzVmIiwiaWRwX3N1YiI6ImMxYzc3NWFiLWI0MTktNGMyNi1hZDg1LTI1OGEyNmE2ODkzMSIsInRlbmFudF9pZCI6ImljY2RldiIsImp0aSI6ImI3ZjcwZGNlLTMyOGEtNDc3ZS04YzhhLWUwNWE3Mzk0MmFhNSIsInNpZCI6Ijg2NDVlN2M3LTg4MjAtNGU1MC04OTZiLTA1NDE2NjViYjEyNiIsImRldmljZV9pZCI6IjZlNjZiMzdjLTM2ZWYtNDM4NS04NTA2LTFiZTJhMjdjYTM5MyIsImRldmljZV90eXBlIjoid2ViX2Jyb3dzZXIiLCJyb2xlIjoidXNlciIsInNjb3BlIjoiY2F0YWxvZ3VlLnJlYWQiLCJ0eXAiOiJhY2Nlc3MiLCJhenAiOiI3ZDE4Y2I2Ni01YmFmLTQ5NzktODVjMy05YTZjYzM3ZTYyMmUiLCJpZHBfYmFnIjp7InByb3ZpZGVyIjoiQURCMkMiLCJ0ZnAiOiJCMkNfMUFfU0lHTklOU0lHTlVQIn0sImF1ZCI6ImRldi1zY2FsZS1mcmFua2Z1cnQtYXdzIiwiZXhwIjoxNzE2MjUzMTcxLCJpc3MiOiJodHRwczovL2RlbHRhdHJlLmNvbS9pc3N1ZXIiLCJpYXQiOjE3MTYyNTEzNzEsIm5iZiI6MTcxNjI1MTM3MX0.TPGThXvtjr2nlxSlck5zIDAQpuiz-x3xGQwFdTT60B_iA3GxRq7e5ZCd0AXnAuootrW93cGOSJTX1RFB1nsQ1OFeBe4RjnC5cEl5qabvhzDwubGHhmhEgzzgFjEziwEiP8h3VEdm-GVJzSkqZtyM6F3WMhYSo9I61tK3PoFuhz7OLxCPwbkAzOEL5Kak62TMWNUj0wGSeFJPsYCWH_LzN90-vlU1V8-h0Qr-7V6hWH2jBVuEhxEKsP71HIaWjzoFRxcykno7PmGcBEQl3umXvQzDMP65Erx2s6SwK3vPfEUj-7rs-RnfSZNHcl-OnLqXMf9wrUAQeKqdy9QrWzGR5A").environment(Environment.DEVELOPMENT)
            .email("iyanuoluwa@insomnialabs.io")
            .name("Iyanu Falaye")
            .entryPoint(EntryPoint.PROFILE.path)
            .accountId(accountId)
            .publicKey(publicKey)
            .environment(Environment.PRODUCTION)
            .onNavigateBack {
                this.startActivity(Intent(this, AnotherActivity::class.java))
            }.build()
    }
}