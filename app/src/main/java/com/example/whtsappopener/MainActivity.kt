package com.example.whtsappopener

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import com.example.whtsappopener.databinding.ActivityMainBinding




class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var countryCode:String="91"
        // get Country Code
        binding.ccp.setOnCountryChangeListener {
            countryCode=it.phoneCode.toString()
        }


        binding.btnOpenWhtsapp.setOnClickListener {
            val phNo=binding.phoneNO.text.toString()
            if(phNo.isDigitsOnly()&&phNo.length>=10)
                startWhatsApp(phNo,binding.messageText.text.toString(),countryCode)
            else if(phNo.isEmpty())
                Toast.makeText(this, "Please Enter The Number !!", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this, "Please Check The Number !!", Toast.LENGTH_SHORT).show()
        }

    }

    private fun startWhatsApp(phNo: String,message:String,countryCode:String) {

        val intent=Intent(Intent.ACTION_VIEW)
        intent.setPackage("com.whatsapp")
        //set the number
        val data : String=countryCode+phNo
        //set URL
        intent.data = Uri.parse("https://api.whatsapp.com/send?phone=+$data&text=$message")

        try {
            startActivity(intent)
        }
        catch (e: Exception){
            Toast.makeText(this, "Please Install WhatsApp !!", Toast.LENGTH_SHORT).show()
            val int=Intent(Intent.ACTION_SEND)
            int.data=Uri.parse("https://play.google.com/store/apps/details?id=com.whatsapp")
            startActivity(int)
        }
    }
}