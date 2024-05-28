package com.iccfan.passport

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.insomnia.fanpassport.IccFanPassportActivity
import com.insomnia.fanpassport.OnAuthenticate
import com.insomnia.fanpassport.User

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val user = User(
            authToken = "eyJhbGciOiJSUzI1NiIsImtpZCI6Imlia2V5MTIzNDUiLCJ0eXAiOiJKV1QifQ.eyJzdWIiOiJkYjUyMjM0YS0wM2RhLTRiZTUtYjRjNy1hMTVlNDliY2EwMDUiLCJwcm9maWxlX2lkIjoiZGI1MjIzNGEtMDNkYS00YmU1LWI0YzctYTE1ZTQ5YmNhMDA1IiwiaWRwX3N1YiI6ImViMjljNjU2LTVmMTYtNGEyOS04ZGJiLTI3NjFlNjI4ZDZhMiIsInRlbmFudF9pZCI6ImljY2RldiIsImp0aSI6IjYxYmRhNjMyLTZiM2EtNDE4OC04ZTgxLTgzZmQ5MzY4NDUyMCIsInNpZCI6IjYxYmRhNjMyLTZiM2EtNDE4OC04ZTgxLTgzZmQ5MzY4NDUyMCIsImRldmljZV9pZCI6IjY2ZjdmNDk5LTE2ZGEtNDc1ZS04OTlmLWE2ZTRjNjViZDJlMCIsImRldmljZV90eXBlIjoid2ViX2Jyb3dzZXIiLCJyb2xlIjoidXNlciIsInNjb3BlIjoiY2F0YWxvZ3VlLnJlYWQiLCJ0eXAiOiJhY2Nlc3MiLCJhenAiOiI3ZDE4Y2I2Ni01YmFmLTQ5NzktODVjMy05YTZjYzM3ZTYyMmUiLCJpZHBfYmFnIjp7InByb3ZpZGVyIjoiQURCMkMiLCJ0ZnAiOiJCMkNfMUFfU0lHTklOU0lHTlVQIn0sImF1ZCI6ImRldi1zY2FsZS1mcmFua2Z1cnQtYXdzIiwiZXhwIjoxNzE2OTI2OTIyLCJpc3MiOiJodHRwczovL2RlbHRhdHJlLmNvbS9pc3N1ZXIiLCJpYXQiOjE3MTY5MjUxMjIsIm5iZiI6MTcxNjkyNTEyMn0.lgywkE4UV_L-F1koVIbd4RrdbOrPuKgTn1pM29xjMSON3GhJHiZZUaRzlglzuGZ4N-S_RP9tUK7JQKVUmN_XJdAs2D0fATfdVh0Sr3XrEwWNIdY77W0dM356uO6CCpyEA1GkdslmfWTYLU5LydLqS2kiEIWUud5wPErGxwoPrspliex9S1a1iLcd_8tdUAel3PTH5eUpk5n2Vt9t44BWC3ygt1Tnwap-69yWtbjhrowD2u-mL8ieZItx979D74BX7MYbsWLLEt_c2zU0HFYdSVKW1qTsbIRlK9Cpw5zGEbz4e39_1QsuEGUipfxKhdXTN3WiNJ8dq-yiko6jqBkLLA",
            name = "Iyanu falaye",
            email = "falaycornelius+07@gmail.com"
        )

        val onAuthenticate = object : OnAuthenticate {
            override fun signIn() {
                IccFanPassportActivity.launch(this@MainActivity, user, onAuthenticate = null)
            }
        }

        IccFanPassportActivity.launch(context = this, user = user, onAuthenticate = onAuthenticate)




//        val onAuthenticate = object : OnAuthenticate {
//            override fun signIn() {
//                IccFanPassportActivity.launch(this@MainActivity, user, onAuthenticate = null)
//            }
//        }

//        IccFanPassportActivity.launch(context = this, user, onAuthenticate = onAuthenticate)
//        IccFanPassportActivity.launch(context = this, user, onAuthenticate = onAuthenticate)

//        IccFanPassportActivity.logOut(this)
    }


}