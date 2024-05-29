package com.iccfan.passport

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
            authToken = "eyJhbGciOiJSUzI1NiIsImtpZCI6Imlia2V5MTIzNDUiLCJ0eXAiOiJKV1QifQ.eyJzdWIiOiJkYjUyMjM0YS0wM2RhLTRiZTUtYjRjNy1hMTVlNDliY2EwMDUiLCJwcm9maWxlX2lkIjoiZGI1MjIzNGEtMDNkYS00YmU1LWI0YzctYTE1ZTQ5YmNhMDA1IiwiaWRwX3N1YiI6ImViMjljNjU2LTVmMTYtNGEyOS04ZGJiLTI3NjFlNjI4ZDZhMiIsInRlbmFudF9pZCI6ImljY2RldiIsImp0aSI6IjkxNGY2M2ZlLTlmZmQtNDQwNC05NDg2LTMyM2I3OTIzZjRkMiIsInNpZCI6IjkxNGY2M2ZlLTlmZmQtNDQwNC05NDg2LTMyM2I3OTIzZjRkMiIsImRldmljZV9pZCI6IjdlN2Y5YjFjLWJjMDQtNGM3YS04MjQ3LTEwMDgyNTMyMzc1YSIsImRldmljZV90eXBlIjoid2ViX2Jyb3dzZXIiLCJyb2xlIjoidXNlciIsInNjb3BlIjoiY2F0YWxvZ3VlLnJlYWQiLCJ0eXAiOiJhY2Nlc3MiLCJhenAiOiI3ZDE4Y2I2Ni01YmFmLTQ5NzktODVjMy05YTZjYzM3ZTYyMmUiLCJpZHBfYmFnIjp7InByb3ZpZGVyIjoiQURCMkMiLCJ0ZnAiOiJCMkNfMUFfU0lHTklOU0lHTlVQIn0sImF1ZCI6ImRldi1zY2FsZS1mcmFua2Z1cnQtYXdzIiwiZXhwIjoxNzE3MDEyMzQwLCJpc3MiOiJodHRwczovL2RlbHRhdHJlLmNvbS9pc3N1ZXIiLCJpYXQiOjE3MTcwMTA1NDAsIm5iZiI6MTcxNzAxMDU0MH0.DMKo6755FnUZ46lvd3sSGVlf0TMMX94e1dSI2wfk_kz9Z_72mvEiXm7Rk5n4J-AiFoYpTop7t4S_WJHnH19rQhHFcQDOAaO9Z7WIfaaOGcDX8n2d7p0vPqDhzcv9GMwB1-VKQxJPBGhyaDBviB29TqEhWpSuYWX88yv-OuBeVXmUrt-C_B8FRZTN1eli5OPCpTadPjTBaQzAP36cKEr1NPcIkH8AUrzLwN6YfXN4mIaRKKjRjEIfQNwxO1bXZ4X1v76m-nXP6jVtHasZAjIQzgH1XfgjFgS-n3AE2t3e7RwBmAIYnWUJ6sbZpMp8wpwGQVPgnZp-gUuFGYGSjQltbA",
            name = "Iyanu falaye",
            email = "falaycornelius+07@gmail.com"
        )

        val onAuthenticate = object : OnAuthenticate {
            override fun signIn() {
                IccFanPassportActivity.launch(context = this@MainActivity, user = user, onAuthenticate = this, entryPoint = EntryPoint.DEFAULT.path)
            }

            override fun onNavigateBack() {

            }
        }

        IccFanPassportActivity.launch(context = this, user, onAuthenticate = onAuthenticate, entryPoint = EntryPoint.DEFAULT.path)




//        val onAuthenticate = object : OnAuthenticate {
//            override fun signIn() {
//                IccFanPassportActivity.launch(this@MainActivity, user, onAuthenticate = null)
//            }
//        }

//        IccFanPassportActivity.launch(context = this, user, onAuthenticate = onAuthenticate)

//        IccFanPassportActivity.logOut(this)
    }


}