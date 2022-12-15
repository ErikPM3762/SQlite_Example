package com.example.sqlite_example.db

import android.provider.BaseColumns

object UserDataReaderContract {
    //El contenido de la tabla se agrupa en un objeto anónimo.
    object UserEntry : BaseColumns {
        const val TABLE_NAME = "entryUser"
        const val COLUMN_NAME = "name"
        const val COLUMN_SURNAME = "surname"
        const val COLUMN_STATE = "state"
        const val COLUMN_COUNTRY = "country"
        const val COLUMN_PHONE = "phone"
    }

     const val SQL_CREATE_ENTRIES =
        "CREATE TABLE ${UserEntry.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${UserEntry.COLUMN_NAME} TEXT," +
                "${UserEntry.COLUMN_SURNAME} TEXT," +
                "${UserEntry.COLUMN_STATE} TEXT," +
                "${UserEntry.COLUMN_COUNTRY} TEXT," +
                "${UserEntry.COLUMN_PHONE} TEXT)"

     const val SQL_DELETE_ENTRIES = "DROP TABLE ${UserEntry.TABLE_NAME}"
}