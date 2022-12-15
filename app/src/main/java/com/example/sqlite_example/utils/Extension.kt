package com.example.sqlite_example.utils

import android.app.Activity
import android.text.Editable
import android.widget.EditText
import com.example.sqlite_example.R

fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)

fun Activity.toEndCursor(editText: EditText) = editText.setSelection(editText.length())


fun Activity.selectorBorder(editText1: EditText, activity: Activity) {
    editText1.setOnFocusChangeListener { _, hasFocus ->
        if (!hasFocus) {
            editText1.setBackgroundDrawable(activity?.resources?.getDrawable(R.drawable.selector_edittext))
        } else {
            editText1.setBackgroundDrawable(activity?.resources?.getDrawable(R.drawable.selector_edittext_green))
        }
    }
}

fun Activity.initBorder(editText1: EditText, activity: Activity) {
            editText1.setBackgroundDrawable(activity?.resources?.getDrawable(R.drawable.selector_edittext))
}