package com.iccfan.passport

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.insomnia.fanpassport.IccFanPassportActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        IccFanPassportActivity.Builder(this)
            .accessToken(
                "Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6Imlia2V5MTIzNDUiLCJ0eXAiOiJKV1QifQ.eyJzdWIiOiJmYjk4MDE5NS03MDAyLTRjNTItYWFlZC1hMTA2ZTE4NWY3NWYiLCJwcm9maWxlX2lkIjoiZmI5ODAxOTUtNzAwMi00YzUyLWFhZWQtYTEwNmUxODVmNzVmIiwiaWRwX3N1YiI6ImMxYzc3NWFiLWI0MTktNGMyNi1hZDg1LTI1OGEyNmE2ODkzMSIsInRlbmFudF9pZCI6ImljY2RldiIsImp0aSI6IjI0ZWJmOWZjLTM1MmMtNDY5ZC05NmU2LWZhMWVlMTZjYjcyNSIsInNpZCI6IjI0ZWJmOWZjLTM1MmMtNDY5ZC05NmU2LWZhMWVlMTZjYjcyNSIsImRldmljZV9pZCI6ImVhOGFmN2M2LTNjMTQtNGMyZi1hYzJiLTJhOWFmM2JmMWJjMiIsImRldmljZV90eXBlIjoid2ViX2Jyb3dzZXIiLCJyb2xlIjoidXNlciIsInNjb3BlIjoiY2F0YWxvZ3VlLnJlYWQiLCJ0eXAiOiJhY2Nlc3MiLCJhenAiOiI3ZDE4Y2I2Ni01YmFmLTQ5NzktODVjMy05YTZjYzM3ZTYyMmUiLCJpZHBfYmFnIjp7InByb3ZpZGVyIjoiQURCMkMiLCJ0ZnAiOiJCMkNfMUFfU0lHTklOU0lHTlVQIn0sImF1ZCI6ImRldi1zY2FsZS1mcmFua2Z1cnQtYXdzIiwiZXhwIjoxNzEzOTE0MzA2LCJpc3MiOiJodHRwczovL2RlbHRhdHJlLmNvbS9pc3N1ZXIiLCJpYXQiOjE3MTM5MTQwMDYsIm5iZiI6MTcxMzkxNDAwNn0.vAq3DP9MhbOPvusmr7fN35eGXOqWFSWTvmXy_Vww07qUaL9RVymkfdF_CTb1ClL6TBlN1ACyWDR3VdbcnyPsnspBgCcHsvBu2D_W1nTR8hLP5EiEbcyAYn1jKXhyv0M-Cv5HmVnrBrGB39lRDNS5YjHEg2kOZg1tIFoCSeFPZdXIyaOl4Sq_Qzb952y0EhvRDyAGPRAzN-PvNFMUwrT8z33pPr8Dv8MSS52b1p63yGpYHa3bOCsG1woIQyrbjvUybNP_lHrztwl9K1tipJd1plBeCZXqQm8KVCKD-4aCiq0gjEDbwgscJ-ef0KKlLkJuIIgxjPkxByyl_d7PAZ93lQ"
            )
            .email("iyanuoluwa@insomnialabs.io")
            .name("Iyanu Falaye")
            .userName("Iyanu Falaye")
            .build()
    }
}