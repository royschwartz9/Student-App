package com.example.studentsapp.model

import android.location.Address

data class Student(
    var name: String,
    var id: String,
    var address: String,
    var phone: String,
    var avatarUrl: String,
    var isChecked: Boolean
)