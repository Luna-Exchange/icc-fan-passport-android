package com.iccfan.passport

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.insomnia.fanpassport.EntryPoint
import com.insomnia.fanpassport.Environment
import com.insomnia.fanpassport.IccFanPassportActivity
import com.insomnia.fanpassport.OnAuthenticate
import com.insomnia.fanpassport.User

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val user = User(
            authToken = "eyJhbGciOiJSUzI1NiIsImtpZCI6IjQ5ZmY3M2MwYjUiLCJ0eXAiOiJKV1QifQ.eyJzdWIiOiJkMTBhZTkyZC1hNjg4LTRhMTQtYmFmYi0wM2JiYzdjYjc1Y2UiLCJwcm9maWxlX2lkIjoiZDEwYWU5MmQtYTY4OC00YTE0LWJhZmItMDNiYmM3Y2I3NWNlIiwiaWRwX3N1YiI6ImRiZTE5OGNiLTA3YmMtNGU3My04YmQ5LTBiNGFiZTQ2NmEzZSIsInRlbmFudF9pZCI6ImljYyIsImp0aSI6ImZjOGFhNDAwLTVhZTItNDIxNy04NGRiLTJmMDYyOTRkNTBiZSIsInNpZCI6ImZjOGFhNDAwLTVhZTItNDIxNy04NGRiLTJmMDYyOTRkNTBiZSIsImRldmljZV9pZCI6IjRmNzI5NzQ4LWY4MmUtNGEyNS04MzhkLWI3NmFiMDM1NTAxYSIsImRldmljZV90eXBlIjoid2ViX2Jyb3dzZXIiLCJyb2xlIjoidXNlciIsInNjb3BlIjoiY2F0YWxvZ3VlLnJlYWQiLCJ0eXAiOiJhY2Nlc3MiLCJhenAiOiIzNmRhNjA1NC04NTUyLTQwMTUtYTZiMi1iN2I2OTA2ZmQ0YWIiLCJpZHBfYmFnIjp7InByb3ZpZGVyIjoiQURCMkMiLCJ0ZnAiOiJCMkNfMUFfU0lHTklOU0lHTlVQIn0sImF1ZCI6InByb2Qtc2NhbGUtaWNjLWF3cyIsImV4cCI6MTcxNzEwNDYwNCwiaXNzIjoiaHR0cHM6Ly9kZWx0YXRyZS5jb20vaXNzdWVyIiwiaWF0IjoxNzE3MDkwMjA0LCJuYmYiOjE3MTcwOTAyMDR9.lypPonFSEoW1vNkHy2KSR-2l5n9LaWqo3NqY_5H5asmZLdv32Gct8XPTsE2r-k2M9ruPcBdVQpg1nH3SPIisQ2R9k2N_MAXYp_bDtAqW-kkY5Px8gGpNSfDAF2E4uiovmFypYJY6icNgvFjtbP8m-Bi-cPnGB-jRZd87hqIWgNxUuUAsVPXOC7gQ0Tf3D2R0OOmGX8s_d_xvl7BTNLjz5yYe374eeHFdro5NVB-m3pSbzAtIbekQKPQnV7srWudnn_5DJ4ZjCCuVZEKJFEXXUjYZ46S01tiYJbnGBHfjx6yTiwcV214s3vIxTHNgFHN8CAaj08pOKL7AepATgMQKrA",
            name = "Iyanu Falaye",
            email = "iyanuoluwa@insomnialabs.io"
        )

        val onAuthenticate = object : OnAuthenticate {
            override fun signIn() {
                IccFanPassportActivity.launch(context = this@MainActivity, user = user, onAuthenticate = this, entryPoint = EntryPoint.DEFAULT.path, environment = Environment.DEVELOPMENT)
            }

            override fun onNavigateBack() {
                val intent = Intent(this@MainActivity, AnotherActivity::class.java)
                startActivity(intent)

            }
        }

        IccFanPassportActivity.launch(context = this,   onAuthenticate = onAuthenticate, environment = Environment.DEVELOPMENT, entryPoint = EntryPoint.DEFAULT.path, fantasyUri = "halllo", predictorUri = "hfgyttt")




//        val onAuthenticate = object : OnAuthenticate {
//            override fun signIn() {
//                IccFanPassportActivity.launch(this@MainActivity, user, onAuthenticate = null)
//            }
//        }

//        IccFanPassportActivity.launch(context = this, user, onAuthenticate = onAuthenticate)

//        IccFanPassportActivity.logOut(this)
    }


}