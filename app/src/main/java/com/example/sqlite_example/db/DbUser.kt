package com.example.sqlite_example.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.sqlite_example.data.Users
import com.example.sqlite_example.db.UserDataReaderContract.UserEntry.TABLE_NAME

class DbUser( context : Context)  {
    private val dbHelper = UserDataReaderDbHelper(context)
    private var id : Long = 0
    lateinit var user: Users
    var correct = false

    lateinit var context:Context
    init {
        this.context = context
    }

    fun insertDataUser (name:String, surname: String, state: String, country: String, phone: String): Long {
        try {
            val db = dbHelper.writableDatabase

            val values = ContentValues().apply {
                put(UserDataReaderContract.UserEntry.COLUMN_NAME, name)
                put(UserDataReaderContract.UserEntry.COLUMN_SURNAME, surname)
                put(UserDataReaderContract.UserEntry.COLUMN_STATE, state)
                put(UserDataReaderContract.UserEntry.COLUMN_COUNTRY, country)
                put(UserDataReaderContract.UserEntry.COLUMN_PHONE, phone)
        }
            id = db.insert(TABLE_NAME, null, values)

        } catch (ex: Exception) {
            ex.toString()
        }
        return id
    }

    fun getUsers() : ArrayList<Users>{
        val dbHelper = UserDataReaderDbHelper(context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        val listUser: java.util.ArrayList<Users> = java.util.ArrayList<Users>()

        val cursorUser: Cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null )
        if (cursorUser.moveToFirst()) {
            do {
                user = Users(
                    id = cursorUser.getInt(0),
                    name = cursorUser.getString(1),
                    surname = cursorUser.getString(2),
                    state = cursorUser.getString(3),
                    country = cursorUser.getString(4),
                    phone = cursorUser.getString(5))
                listUser.add(user)
            } while (cursorUser.moveToNext())
        }
        cursorUser.close()
        return listUser
    }


    fun getUser(id: Int) : Users {
        val dbHelper = UserDataReaderDbHelper(context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        val cursorUser: Cursor = db.rawQuery("SELECT * FROM $TABLE_NAME WHERE _id = $id LIMIT 1 ", null )
        if (cursorUser.moveToFirst()) {

            user = Users(
                id = cursorUser.getInt(0),
                name = cursorUser.getString(1),
                surname = cursorUser.getString(2),
                state = cursorUser.getString(3),
                country = cursorUser.getString(4),
                phone = cursorUser.getString(5))
        }
        cursorUser.close()
        return user
    }

    fun editDataUser (id : Int,name:String, surname: String, state: String, country: String, phone: String): Boolean {
        val db = dbHelper.writableDatabase
        correct = try {
            db.execSQL("UPDATE $TABLE_NAME SET name = '$name', surname = '$surname', state = '$state' , country = '$country' , phone = '$phone' WHERE _id='$id' ")
            true

        } catch (ex: Exception) {
            ex.toString()
            false
        } finally {
            db.close()
        }
        return correct
    }

    fun delateUser(id: Int): Boolean {
        val db = dbHelper.writableDatabase
        correct = try {
            db.execSQL("DELETE FROM $TABLE_NAME WHERE _id = '$id'")
            true
        } catch (ex: java.lang.Exception) {
            ex.toString()
            false
        } finally {
            db.close()
        }
        return correct
    }
}



