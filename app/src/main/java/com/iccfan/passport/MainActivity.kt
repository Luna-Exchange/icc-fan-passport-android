package com.iccfan.passport

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.insomnia.fanpassport.EntryPoint
import com.insomnia.fanpassport.Environment
import com.insomnia.fanpassport.IccFanPassportActivity
import com.insomnia.fanpassport.OnJavScriptInterface

class MainActivity : AppCompatActivity(), OnJavScriptInterface {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = intent
        val data = intent.data
        val accountId = data?.getQueryParameter("account_id") ?: ""
        val publicKey = data?.getQueryParameter("public_key") ?: ""

        IccFanPassportActivity.Builder(this)
            .accessToken("eyJhbGciOiJSUzI1NiIsImtpZCI6Imlia2V5MTIzNDUiLCJ0eXAiOiJKV1QifQ.eyJzdWIiOiIxODA5YzQzZS0wOTJmLTRjY2ItOWU2Zi1kYjFkYzk2NTQ2ZWQiLCJwcm9maWxlX2lkIjoiMTgwOWM0M2UtMDkyZi00Y2NiLTllNmYtZGIxZGM5NjU0NmVkIiwiaWRwX3N1YiI6IjZlNWY5YTU4LTJiYTctNDU4NS04NmE2LWFlNTk4NmFmMWNjYyIsInRlbmFudF9pZCI6ImljY2RldiIsImp0aSI6ImUwNDJiNmEyLTVkYzYtNGZjZi04ZmRjLTY5YTk1NjE2NzM4MyIsInNpZCI6ImUwNDJiNmEyLTVkYzYtNGZjZi04ZmRjLTY5YTk1NjE2NzM4MyIsImRldmljZV9pZCI6ImZmNTU2YjhiLTE0YzktNGQ4MC04MDMxLTk0YTVlNzFiODk5NSIsImRldmljZV90eXBlIjoid2ViX2Jyb3dzZXIiLCJyb2xlIjoidXNlciIsInNjb3BlIjoiY2F0YWxvZ3VlLnJlYWQiLCJ0eXAiOiJhY2Nlc3MiLCJhenAiOiI3ZDE4Y2I2Ni01YmFmLTQ5NzktODVjMy05YTZjYzM3ZTYyMmUiLCJpZHBfYmFnIjp7InByb3ZpZGVyIjoiQURCMkMiLCJ0ZnAiOiJCMkNfMUFfU0lHTklOU0lHTlVQIn0sImF1ZCI6ImRldi1zY2FsZS1mcmFua2Z1cnQtYXdzIiwiZXhwIjoxNzE2NTMwODQxLCJpc3MiOiJodHRwczovL2RlbHRhdHJlLmNvbS9pc3N1ZXIiLCJpYXQiOjE3MTY1MjkwNDEsIm5iZiI6MTcxNjUyOTA0MX0.fNd66jMpfLEtcHGyYqoZHF59NYke4KP6Zu8amM_AQTzNKRZ_hqxG8pCq-ZnhRjEVuXUbeiuiiU9joMTO_PcojiuHG6rMYbJFvwUePjIlhahTKxGJ4AC2mni3Sa5Au4aizxK1PmR1WOshzY96DqT-tmjP_YoI0Vktu8dhG4vF25Yl_DACRarGq49iGLE6qb-eb6p0npo2PCLldU51waLdISTvclyv0k6_FMUHVxTjE_Q2iwT_zJiaEbVcW2ifLlwHlKG53NQfcpnN0WiFTfC9L5v6AIyUmUtb6w9qQtvsFIHDh2pezvAIZ9PvvcYfgozM0MaPK21dig-5RuiPYHZ5wg")
            .email("iyanuc.falaye@gmail.com")
            .name("John Doe")
            .environment(Environment.DEVELOPMENT)
            .onNavigateBack {
                this.startActivity(Intent(this, AnotherActivity::class.java))
            }.build()    }

    override fun onAuthenticateWithIcc() {
        IccFanPassportActivity.Builder(this)
            .accessToken(
                "eyJhbGciOiJSUzI1NiIsImtpZCI6Imlia2V5MTIzNDUiLCJ0eXAiOiJKV1QifQ.eyJzdWIiOiIxODA5YzQzZS0wOTJmLTRjY2ItOWU2Zi1kYjFkYzk2NTQ2ZWQiLCJwcm9maWxlX2lkIjoiMTgwOWM0M2UtMDkyZi00Y2NiLTllNmYtZGIxZGM5NjU0NmVkIiwiaWRwX3N1YiI6IjZlNWY5YTU4LTJiYTctNDU4NS04NmE2LWFlNTk4NmFmMWNjYyIsInRlbmFudF9pZCI6ImljY2RldiIsImp0aSI6IjY4ZjY4YzZmLWZjZjMtNDNmMy05MjAyLWQ3Njg5N2ZiYmJlYSIsInNpZCI6IjY4ZjY4YzZmLWZjZjMtNDNmMy05MjAyLWQ3Njg5N2ZiYmJlYSIsImRldmljZV9pZCI6IjRmZTlkMGUzLWRhOGQtNGViNS1hNTFjLTk2YzE0ZDVjMGU1ZCIsImRldmljZV90eXBlIjoid2ViX2Jyb3dzZXIiLCJyb2xlIjoidXNlciIsInNjb3BlIjoiY2F0YWxvZ3VlLnJlYWQiLCJ0eXAiOiJhY2Nlc3MiLCJhenAiOiI3ZDE4Y2I2Ni01YmFmLTQ5NzktODVjMy05YTZjYzM3ZTYyMmUiLCJpZHBfYmFnIjp7InByb3ZpZGVyIjoiQURCMkMiLCJ0ZnAiOiJCMkNfMUFfU0lHTklOU0lHTlVQIn0sImF1ZCI6ImRldi1zY2FsZS1mcmFua2Z1cnQtYXdzIiwiZXhwIjoxNzE2NDc3OTA0LCJpc3MiOiJodHRwczovL2RlbHRhdHJlLmNvbS9pc3N1ZXIiLCJpYXQiOjE3MTY0NzYxMDQsIm5iZiI6MTcxNjQ3NjEwNH0.gsegNOJE-yLz1ly3DzEqZg9K_eEojQrXr2Eu_0ugAqGwKQ4YnJ9KD0Jr7gLx-0gEbgOvBoPw3yuamz-2gwy2wUScX295xD9PdhsYjx48OgXzFm7GU8kLZbiLy2kwd1p6qFsS9P5XZqFy7BCR9FvIG_mUT6sPDLBu1qCNdnAVDZ9FdXOy4ULEA0Q3UO-6YAxAF-qnOurflslSVbcwtJOhJ29pMfPNb4qT87Un_agFPn0JFkWQu1jrbjG2pCx_oWNBmebC19X6Mrbvot3k9ojKbMnlfimGneRxSh_3be-uvXVq7Z1eucFDl5oIT14ejp27QWed4oFXCuU1r13xo1BehA")
            .email("iyanuc.falaye@gmail.com")
            .name("John Doe")
            .entryPoint(EntryPoint.PROFILE.path)
            .environment(Environment.PRODUCTION)
            .onNavigateBack {
                this.startActivity(Intent(this, AnotherActivity::class.java))
            }.build()
    }

}