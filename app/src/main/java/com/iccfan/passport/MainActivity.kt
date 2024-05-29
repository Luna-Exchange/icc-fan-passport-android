package com.iccfan.passport

import android.os.Bundle
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
            authToken = "eyJhbGciOiJSUzI1NiIsImtpZCI6Imlia2V5MTIzNDUiLCJ0eXAiOiJKV1QifQ.eyJzdWIiOiJkYjUyMjM0YS0wM2RhLTRiZTUtYjRjNy1hMTVlNDliY2EwMDUiLCJwcm9maWxlX2lkIjoiZGI1MjIzNGEtMDNkYS00YmU1LWI0YzctYTE1ZTQ5YmNhMDA1IiwiaWRwX3N1YiI6ImViMjljNjU2LTVmMTYtNGEyOS04ZGJiLTI3NjFlNjI4ZDZhMiIsInRlbmFudF9pZCI6ImljY2RldiIsImp0aSI6IjQ1YzMxZTY4LTgxOWUtNDZmZC1iNDllLWRlY2ExZWNiMmJjNCIsInNpZCI6IjQ1YzMxZTY4LTgxOWUtNDZmZC1iNDllLWRlY2ExZWNiMmJjNCIsImRldmljZV9pZCI6ImI5MWI0YWQ4LWNiNDAtNDZiNi04NTIwLWZlYmM2MThkMmY0MyIsImRldmljZV90eXBlIjoid2ViX2Jyb3dzZXIiLCJyb2xlIjoidXNlciIsInNjb3BlIjoiY2F0YWxvZ3VlLnJlYWQiLCJ0eXAiOiJhY2Nlc3MiLCJhenAiOiI3ZDE4Y2I2Ni01YmFmLTQ5NzktODVjMy05YTZjYzM3ZTYyMmUiLCJpZHBfYmFnIjp7InByb3ZpZGVyIjoiQURCMkMiLCJ0ZnAiOiJCMkNfMUFfU0lHTklOU0lHTlVQIn0sImF1ZCI6ImRldi1zY2FsZS1mcmFua2Z1cnQtYXdzIiwiZXhwIjoxNzE2OTg2OTQwLCJpc3MiOiJodHRwczovL2RlbHRhdHJlLmNvbS9pc3N1ZXIiLCJpYXQiOjE3MTY5ODUxNDAsIm5iZiI6MTcxNjk4NTE0MH0.TV1ALbHgUTFF2wbkn8ZGLK4lToe8h4jRWHUPbyKg3K210Kf_j0eoK_Rwc9GUNBHLfy8I_V26Yvl2IrAvnxX8MKnye9RhUYJyE4BIp0nGVlT-i_7Bo6m-kDvuwIAF8uMisj3x_rTbHhjaBU-HUgqjpvDSXT2wvhezENc3zLMptbTrNMIyWJELV1-opC6A2u6WzyPQZXJbIA5o_Eidlvns96H-FlRcjdFrRQs42X0ZtSIPIrPF91vpoSiYk7ODPwXhstPGlkVrJxR2OpmEVwMDd5GwOFNJrslCfHcpoETNfdJIKPXlC2p5EaOx8JgO34P7zBMk2Ta7_vO0pQi26LmY3g",
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

        IccFanPassportActivity.launch(context = this, onAuthenticate = onAuthenticate)




//        val onAuthenticate = object : OnAuthenticate {
//            override fun signIn() {
//                IccFanPassportActivity.launch(this@MainActivity, user, onAuthenticate = null)
//            }
//        }

//        IccFanPassportActivity.launch(context = this, user, onAuthenticate = onAuthenticate)

//        IccFanPassportActivity.logOut(this)
    }


}