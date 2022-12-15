package com.example.sqlite_example.ui.infoUser


import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.sqlite_example.MainActivity
import com.example.sqlite_example.data.Users
import com.example.sqlite_example.databinding.ActivityInfoBinding
import com.example.sqlite_example.db.DbUser
import com.example.sqlite_example.utils.initBorder
import com.example.sqlite_example.utils.selectorBorder
import com.example.sqlite_example.utils.toEditable
import com.example.sqlite_example.utils.toEndCursor
import kotlinx.android.synthetic.main.activity_info.*

class InfoActivity : AppCompatActivity() {

    var user: Users? = null
    var id = 0
    private lateinit var binding: ActivityInfoBinding


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)




        id = if (savedInstanceState == null) {
            val extras = intent.extras
            extras?.getInt("ID") ?: null!!.toInt()
        } else {
            savedInstanceState.getSerializable("ID") as Int
        }

        val dbContactos = DbUser(this)
        user = dbContactos.getUser(id)



        if (user != null) {
            binding.name.text = user!!.name.toEditable()
            binding.surname.text = user!!.surname.toEditable()
            binding.phone.text = user!!.phone.toEditable()
            binding.state.text = user!!.state.toEditable()
            binding.country.text = user!!.country.toEditable()
        }

        binding.cardViewEdit.setOnClickListener{
            moveCursorEnd()
            binding.cardViewEdit.visibility= View.GONE
            binding.cardViewSave.visibility= View.VISIBLE
            binding.cardViewDelate.visibility = View.GONE
            binding.name.isEnabled = true
            binding.surname.isEnabled = true
            binding.state.isEnabled = true
            binding.country.isEnabled = true
            binding.phone.isEnabled = true
        }

        binding.cardViewSave.setOnClickListener {
            dbContactos.editDataUser(
            id,
            name.text.toString(),
            surname.text.toString(),
            phone.text.toString(),
            state.text.toString(),
            country.text.toString(),
            )
            Toast.makeText(this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        cardView_delate.setOnClickListener(View.OnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setMessage("¿Desea eliminar este contacto?")
                .setPositiveButton(
                    "SI"
                ) { dialogInterface, i ->
                    if (dbContactos.delateUser(id)) {
                        navigateMain()
                    }
                }
                .setNegativeButton(
                    "NO"
                ) { dialogInterface, i ->
                }.show()
        })

    }

    private fun moveCursorEnd(){
        selectorBorder(name,this)
        selectorBorder(surname,this)
        selectorBorder(phone,this)
        selectorBorder(state,this)
        selectorBorder(country,this)
        initBorder(name,this)
        initBorder(surname,this)
        initBorder(phone,this)
        initBorder(state,this)
        initBorder(country,this)
        toEndCursor(name)
        toEndCursor(surname)
        toEndCursor(phone)
        toEndCursor(state)
        toEndCursor(country)
    }


    private fun navigateMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }




}