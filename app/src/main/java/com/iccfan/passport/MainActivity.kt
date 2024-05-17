package com.iccfan.passport

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.insomnia.fanpassport.EntryPoint
import com.insomnia.fanpassport.IccFanPassportActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        IccFanPassportActivity.Builder(this).accessToken(
            "eyJhbGciOiJSUzI1NiIsImtpZCI6Imlia2V5MTIzNDUiLCJ0eXAiOiJKV1QifQ.eyJzdWIiOiJmYjk4MDE5NS03MDAyLTRjNTItYWFlZC1hMTA2ZTE4NWY3NWYiLCJwcm9maWxlX2lkIjoiZmI5ODAxOTUtNzAwMi00YzUyLWFhZWQtYTEwNmUxODVmNzVmIiwiaWRwX3N1YiI6ImMxYzc3NWFiLWI0MTktNGMyNi1hZDg1LTI1OGEyNmE2ODkzMSIsInRlbmFudF9pZCI6ImljY2RldiIsImp0aSI6ImIwMmRjZWI2LTllYTctNDRhYy1iODQ3LTBmM2ExMTY2NDg2ZCIsInNpZCI6ImIwMmRjZWI2LTllYTctNDRhYy1iODQ3LTBmM2ExMTY2NDg2ZCIsImRldmljZV9pZCI6IjgzZWU0OTc2LWU1MGYtNDRiZC05NzFiLTY2NTdkYjdhYWZkNSIsImRldmljZV90eXBlIjoid2ViX2Jyb3dzZXIiLCJyb2xlIjoidXNlciIsInNjb3BlIjoiY2F0YWxvZ3VlLnJlYWQiLCJ0eXAiOiJhY2Nlc3MiLCJhenAiOiI3ZDE4Y2I2Ni01YmFmLTQ5NzktODVjMy05YTZjYzM3ZTYyMmUiLCJpZHBfYmFnIjp7InByb3ZpZGVyIjoiQURCMkMiLCJ0ZnAiOiJCMkNfMUFfU0lHTklOU0lHTlVQIn0sImF1ZCI6ImRldi1zY2FsZS1mcmFua2Z1cnQtYXdzIiwiZXhwIjoxNzE1MTg1MzY3LCJpc3MiOiJodHRwczovL2RlbHRhdHJlLmNvbS9pc3N1ZXIiLCJpYXQiOjE3MTUxODM1NjcsIm5iZiI6MTcxNTE4MzU2N30.iLSFecS1_b_vJvVV1KgGdqGdCk28-Esxnep-1sKQe2NEMmccmU_AsiiIMoAmtDj155db4xD-z45hx6KWksw5sVQNvVAjPI11FDUHeHaAENYNeF1h_1m3YCse4JIX_fzR5NikPiVvJ7k-BTlI9__7XqdWINK-okOIID5hxBgWmLJY0xAYju--yG8TEHjKQMSYMX1_zue-6tgKgWUT24T7ibgvybLPiDaLFIwV3hLhkRb00TiaPf-X4KzpGIhgz4XKO1qyLZsPrOGMjhnCrTX3S_v7VsM_hGeVG6k0tkbQhvgJaXkmid8fj51_H1FFJ3Nx1iEocc9kmZQgmQCNeVIZBQ"
        ).email("iyanuoluwa@insomnialabs.io")
            .name("Iyanu Falaye")
            .entryPoint(EntryPoint.PROFILE.path)
            .onNavigateBack {
                this.startActivity(Intent(this, AnotherActivity::class.java))
            }.build()
    }
}