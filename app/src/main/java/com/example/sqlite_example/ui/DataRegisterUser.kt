package com.example.sqlite_example.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sqlite_example.MainActivity
import com.example.sqlite_example.databinding.FragmentDataRegisterUserBinding
import com.example.sqlite_example.db.DbUser

class DataRegisterUser : AppCompatActivity() {
    private lateinit var binding : FragmentDataRegisterUserBinding
    private val userData : DbUser = DbUser(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentDataRegisterUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSaveData.setOnClickListener {
            val name = binding.edtName.text.toString()
            val surname = binding.edtSurname.text.toString()
            val state = binding.edtState.text.toString()
            val country = binding.edtCountry.text.toString()
            val phone = binding.edtPhone.text.toString()
            userData.insertDataUser(name,surname,state,country, phone)

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }


}