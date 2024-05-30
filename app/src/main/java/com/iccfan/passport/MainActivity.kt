package com.iccfan.passport

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.insomnia.fanpassport.EntryPoint
import com.insomnia.fanpassport.IccFanPassportActivity
import com.insomnia.fanpassport.OnAuthenticate
import com.insomnia.fanpassport.User

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val user = User(
            authToken = "eyJhbGciOiJSUzI1NiIsImtpZCI6Imlia2V5MTIzNDUiLCJ0eXAiOiJKV1QifQ.eyJzdWIiOiI4MTYyMmQ5Yi05ZjdkLTQxMzMtYjMxYi1mYTI1M2EyYTUwYjUiLCJwcm9maWxlX2lkIjoiODE2MjJkOWItOWY3ZC00MTMzLWIzMWItZmEyNTNhMmE1MGI1IiwiaWRwX3N1YiI6IjhiZDIxMDE4LTE1OGUtNDQyOC1hZDI5LTJlYjRiZjZhODFkNiIsInRlbmFudF9pZCI6ImljY2RldiIsImp0aSI6IjU2Njg5NGUzLTZmOWEtNGJmYy1hMzdlLWFmY2NkNDY5OGJkMiIsInNpZCI6IjU2Njg5NGUzLTZmOWEtNGJmYy1hMzdlLWFmY2NkNDY5OGJkMiIsImRldmljZV9pZCI6IjZmNmQyZWFkLTFkMmMtNDdhMS1hMmM2LTFkZTgxZmI0YzQxZSIsImRldmljZV90eXBlIjoid2ViX2Jyb3dzZXIiLCJyb2xlIjoidXNlciIsInNjb3BlIjoiY2F0YWxvZ3VlLnJlYWQiLCJ0eXAiOiJhY2Nlc3MiLCJhenAiOiI3ZDE4Y2I2Ni01YmFmLTQ5NzktODVjMy05YTZjYzM3ZTYyMmUiLCJpZHBfYmFnIjp7InByb3ZpZGVyIjoiQURCMkMiLCJ0ZnAiOiJCMkNfMUFfU0lHTklOU0lHTlVQIn0sImF1ZCI6ImRldi1zY2FsZS1mcmFua2Z1cnQtYXdzIiwiZXhwIjoxNzE3MDI4NDE3LCJpc3MiOiJodHRwczovL2RlbHRhdHJlLmNvbS9pc3N1ZXIiLCJpYXQiOjE3MTcwMjY2MTcsIm5iZiI6MTcxNzAyNjYxN30.mx4ZeIMv-OqC5CzUPUPZ4S7zuX-_ss8i5N4qFsDl6eXTXwPaPEohe_PiBEAm5kdkLzYeIYSWZFPeyF8HwFVwchxG-36dM_U_6x0TV-Z7srrqUZWSm8OP84gE4v2E8m1IqrsvuLvslWyuguXcI2tveL2y6RHbHvsiAwtuUGLjmX3ec_tY07tH8PxqzS5ekS1q7SEmAY_eeln7lyM-vyvr6Y2NnzcBgsd92PE5smnX_a3F7me-wcHTqVUxhQHCV7O-8Bgi08eXnZXBDjFBRv1tvdkWYO2c_n288RC78vVBC6GhntWBYGfZNu4pUlTuAmdpIze0RxGsXT6Si_u_sOCqdQ",
            name = "Iyanu Falaye",
            email = "iyanuoluwa+01@insomnialabs.io"
        )

        val onAuthenticate = object : OnAuthenticate {
            override fun signIn() {
                IccFanPassportActivity.launch(context = this@MainActivity, user = user, onAuthenticate = this, entryPoint = EntryPoint.DEFAULT.path)
            }

            override fun onNavigateBack() {
                val intent = Intent(this@MainActivity, AnotherActivity::class.java)
                startActivity(intent)

            }
        }

        IccFanPassportActivity.launch(context = this,  user = user,  onAuthenticate = onAuthenticate, entryPoint = EntryPoint.DEFAULT.path)




//        val onAuthenticate = object : OnAuthenticate {
//            override fun signIn() {
//                IccFanPassportActivity.launch(this@MainActivity, user, onAuthenticate = null)
//            }
//        }

//        IccFanPassportActivity.launch(context = this, user, onAuthenticate = onAuthenticate)

//        IccFanPassportActivity.logOut(this)
    }


}