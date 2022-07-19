package com.tan.first_app.Models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "member")
data class Member (
    @PrimaryKey(autoGenerate = true) var id: Long,
    var name: String,
    var age: Int
)

