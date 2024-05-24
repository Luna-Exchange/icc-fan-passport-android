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
            .accessToken("eyJhbGciOiJSUzI1NiIsImtpZCI6Imlia2V5MTIzNDUiLCJ0eXAiOiJKV1QifQ.eyJzdWIiOiJhNTk3OTkzNi01ZTYyLTQyMjItYmJlZi01NzliYzgwZmNlYmIiLCJwcm9maWxlX2lkIjoiYTU5Nzk5MzYtNWU2Mi00MjIyLWJiZWYtNTc5YmM4MGZjZWJiIiwiaWRwX3N1YiI6IjVlZmZiNDgzLTc1NGMtNGY0Yy04NTdjLTViZTY3OWQxMGZlMCIsInRlbmFudF9pZCI6ImljY2RldiIsImp0aSI6IjQzMzM4NzdhLWU1OGYtNGI0YS05ZTRjLWYwMDg1MjdiNzZmYyIsInNpZCI6IjQzMzM4NzdhLWU1OGYtNGI0YS05ZTRjLWYwMDg1MjdiNzZmYyIsImRldmljZV9pZCI6ImNjZTc5NTNhLWJlMTAtNGEzNy1iYjgyLTEyNGZiOGM1MDA5YyIsImRldmljZV90eXBlIjoid2ViX2Jyb3dzZXIiLCJyb2xlIjoidXNlciIsInNjb3BlIjoiY2F0YWxvZ3VlLnJlYWQiLCJ0eXAiOiJhY2Nlc3MiLCJhenAiOiI3ZDE4Y2I2Ni01YmFmLTQ5NzktODVjMy05YTZjYzM3ZTYyMmUiLCJpZHBfYmFnIjp7InByb3ZpZGVyIjoiQURCMkMiLCJ0ZnAiOiJCMkNfMUFfU0lHTklOU0lHTlVQIn0sImF1ZCI6ImRldi1zY2FsZS1mcmFua2Z1cnQtYXdzIiwiZXhwIjoxNzE2NTUyMjI1LCJpc3MiOiJodHRwczovL2RlbHRhdHJlLmNvbS9pc3N1ZXIiLCJpYXQiOjE3MTY1NTA0MjUsIm5iZiI6MTcxNjU1MDQyNX0.Wkl3lo6HvsWTP0B8mvSYSW1j4i2A2rSWJaFHivoKNDlWJszUodaQDfgF1Mb2f0yCDNbVZjWcOqIwqnmAtA2gp3nCZ76riMcrxVd_jjCOOEnyxpTaSs2rSGGKCYp3m5ea-o86M_s0LtzyOLmRpSp0IQ44MpGLSgCUVfNxQEV5q2mZ6BbBf_3Ur8l78F0Ldvd2ZpMjNGAdaHTZ7RxT-k5MP_JJ4vGO1B1pY9jBpa1nXubejPR04dU0Mtpis9N1gkv2yowWl6MHH9h8TVxuy_W0xoOEgsm56HzJXz2XOiHSypEB1pJWCZyoZ0CUOpdi3qNcTRRuLoQeH3XMuV-blbWTWg")
            .email("falaycornelius@gmail.com")
            .name("Iyanu Falaye")
            .entryPoint(EntryPoint.ONBOARDING.path)
            .publicKey(publicKey)
            .accountId(accountId)
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