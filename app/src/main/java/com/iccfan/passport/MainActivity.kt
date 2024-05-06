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
            "Bearer eyJraWQiOiJQNHNtYm03TFo4WW55QlpQSGFLYlowVmlFMzJuNEhzdTFXdTZIQ3ZSc19VIiwidmVyIjoiMS4wIiwiemlwIjoiRGVmbGF0ZSIsInNlciI6IjEuMCJ9.WrhfmwrdQyQpYm_3yr62wuYM18bmznrM8NU6xS47PQHsVAV7dt2ygzzT3RmWPHf-moiDjcv-78g9iRDT4S-x2hNb2fVsMs-F180TPBHgt2eyqbRcqOquPSvXUDg1v9APKBXQitW5bmJHGbV4C0tWsY36hOB82tuBFd_cD-oIXOvCjxOwDasajG_wqJsFTKhSrprOZ9z09iZI1duVAa3vAI3OswzwTMX5Ie8E3qaVq4CjDEMTD39azsJD_jKFD3jFAdgVWJbmBrJh8PJDfN3SOzY6vz_-zRafmJUdjkVDwVDf-FwkJHR-dP-WphCdwtjwPgKgUs5o5YKMX8Wc7cVYoQ.EYQdb3-wTe1r8QG1.exct9cXywL2hP3ypyqdzaGatdYr0j-S4Dj_qqU_17g9UGd1SCT188A9MX_2yY9DxLTZeexrl6ULqnUj23te5Tu1qm-C1FlVAFbU1_FJGjKFm6javGvyFZRqng9rQqh8-o588IrV98hj46H4RmDlJAWb-yK1eJ2trPRRPPiNiunM9e1X5PsIsyReMtDtT5tLxUUO0CxLpU7DZz0mLDeU0Y1DMzL38pmvbmBQicpTdloJao4ITHMRzyH6j6XAlFrRiNtRsfOqkK_xWgkhuGKAecyEuImMJTfvQmeMeElQZlq7XVzo-FGOuEvdnWXIdSDJBKbSZenqI9DhYHdpDwilW5DiSWLrY6aRhqIfiWc98nFUNzCO9YcOa_JsX0FRlKGIFVPA-wUsND3XBCTg1z6H8MpmLn_4Z5y_Xqx9WDKvuRoyY26ez0tFswxjwkTmtdqoxt67Wtc8MoICtVHtPSyBhBhHqVnGtxYFiqdukKmz3ih33V0buDkBtdI_Y8q4C8YhgCyrmGxTcICo_-XHKAceDJo9kkNToYk_WmpNZU92OSZdpzTuv1pbhEdAYkGcLVD6A_p-ICPIOgW9_TloNGiEjiyhOd4_r3FBsID8xal13Ak2GxwXqp7q0-iMD4HsDT86o-HmBOM4P1eMFc0TbhVvj8vqKKLIMIpe_Rp6PW--gVGMrp5h3qt3AIZeLfSte_XwxmFlkzj2Rw0M-uYYh7CS3UXI6hLWozoyaP6v21kE3vdcD9O6gf0dP0igPk-omGQTx15kbF1xe5YkdHJRqYNUokufV6b32xamy6dB8wm0s8TaR-4l3pxeIySavhVamkbfRikOpZX-eF4zlP6xUAJKGsjiHn_jdwcc40FIXwG603hRp_0tkOu8W36e8jdkOW_qGiphcSdyvQ9Gc3UmAuzWydn8nWS9p8k4g5muIiQTHoPGenkRlsyE4GGRqRucxbYOpKsi_tVq3YhVmAfQ.O4p6ADFjJ0pbEdF1j3RIyw"
        ).email("iyanuoluwa@insomnialabs.io")
            .name("Iyanu Falaye")
            .entryPoint(EntryPoint.CHALLENGES)
            .onNavigateBack{
                this.startActivity(Intent(this, AnotherActivity::class.java))
            }.build()
    }
}