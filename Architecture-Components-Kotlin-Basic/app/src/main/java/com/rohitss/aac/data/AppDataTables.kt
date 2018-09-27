/*
 *
 *  * Copyright (C) 2017 Rohit Sahebrao Surwase.
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

package com.rohitss.aac.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

/**
 * Created by Rohit Surwase on 07/08/18.
 */
@Entity(tableName = "articles_table", indices = [Index("title", unique = true)])
data class ArticlesItem(
        @PrimaryKey(autoGenerate = true) @NonNull var id: Int,
        var author: String,
        var description: String,
        var title: String
)