/*
 *
 *  * Copyright (C) 2017-19 Rohit Sahebrao Surwase.
 *  *
 *  *    Licensed under the Apache License, Version 2.0 (the "License");
 *  *    you may not use this file except in compliance with the License.
 *  *    You may obtain a copy of the License at
 *  *
 *  *        http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *    Unless required by applicable law or agreed to in writing, software
 *  *    distributed under the License is distributed on an "AS IS" BASIS,
 *  *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *    See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

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