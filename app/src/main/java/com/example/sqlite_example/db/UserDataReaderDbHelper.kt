package com.example.sqlite_example.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.sqlite_example.db.UserDataReaderContract.SQL_CREATE_ENTRIES
import com.example.sqlite_example.db.UserDataReaderContract.SQL_DELETE_ENTRIES

class UserDataReaderDbHelper(context: Context) : SQLiteOpenHelper(context,DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
     db!!.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
      // Esta base de datos es solo un caché para datos en línea, por lo que su política de actualización es simplemente descartar los datos y comenzar de nuevo.
        db!!.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    companion object {
        // Si cambia el esquema de la base de datos, debe incrementar la versión de la base de datos.
        const val DATABASE_VERSION = 7
        const val DATABASE_NAME = "User.db"
    }

}

