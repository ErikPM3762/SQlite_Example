package com.example.sqlite_example.ui

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.createDeviceProtectedStorageContext
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.sqlite_example.R
import com.example.sqlite_example.data.Users
import com.example.sqlite_example.ui.infoUser.InfoActivity
import java.util.*

class UserAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var listUsers: ArrayList<Users>? = null
    var context: Context? = null

    constructor(context: Context, listUsers: ArrayList<Users>) : this() {
        this.listUsers = listUsers
        this.context = context
    }


    private var TYPE_USER: Int= 0

    private fun getInflatedView(parent: ViewGroup, resourceLayout: Int): View? {
        return LayoutInflater
            .from(parent.context)
            .inflate(resourceLayout, parent, false)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View? = getInflatedView(parent, R.layout.list_user_item)
        return   UserHolder(view!!)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


        when(getItemViewType(position)) {

            TYPE_USER -> {
                val rnd = Random()
                val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
                val n: UserHolder = holder as UserHolder
                n.cardView.setCardBackgroundColor(color)
                n.cardView.radius = 50f
                n.name.text = (listUsers!![position].name)
                n.root_element.setOnClickListener {
                    val id = listUsers!![position].id
                    val intent = Intent(context, InfoActivity::class.java)
                    intent.putExtra("ID",id)
                    context?.startActivity(intent)
                }
            }
        }
    }

    override fun getItemCount(): Int {
      return listUsers!!.size
    }



    class UserHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit  var name: TextView
        lateinit  var root_element: LinearLayout
        lateinit  var cardView: CardView

        init {
            root_element = itemView.findViewById(R.id.root_element)
            name = itemView.findViewById(R.id.txt_name)
            cardView = itemView.findViewById(R.id.card_view_lead)
        }
    }
}