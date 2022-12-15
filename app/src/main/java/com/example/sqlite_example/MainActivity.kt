package com.example.sqlite_example


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sqlite_example.data.Users
import com.example.sqlite_example.databinding.ActivityMainBinding
import com.example.sqlite_example.db.DbUser
import com.example.sqlite_example.ui.DataRegisterUser
import com.example.sqlite_example.ui.UserAdapter
import com.example.sqlite_example.ui.infoUser.InfoActivity
import com.example.sqlite_example.ui.listeners.OpenUserListener
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var rv: RecyclerView? = null
    var adapter: UserAdapter? = null
    var listUsers: ArrayList<Users>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        rv = findViewById<RecyclerView>(R.id.rv_Users)
        rv!!.layoutManager = LinearLayoutManager(this)

        val dbUsers = DbUser(this@MainActivity)

        adapter = UserAdapter(this,dbUsers.getUsers())
        rv!!.adapter = adapter



        binding.swipe.setOnRefreshListener {
            adapter = UserAdapter(this,dbUsers.getUsers())
            rv!!.adapter = adapter
            swipe.isRefreshing= false
        }

        binding.addInfoUser.setOnClickListener {
            val intent = Intent(this, DataRegisterUser::class.java)
            startActivity(intent)
        }
    }
}