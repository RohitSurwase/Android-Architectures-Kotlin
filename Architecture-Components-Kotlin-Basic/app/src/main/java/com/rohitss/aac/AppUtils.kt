package com.rohitss.aac

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

/**
 * Created by Rohit Surwase on 31/08/18.
 */

fun inflate(context: Context, viewId: Int, parent: ViewGroup? = null, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(viewId, parent, attachToRoot)
}

fun showToast(context: Context, strError: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, strError, length).show()
}
