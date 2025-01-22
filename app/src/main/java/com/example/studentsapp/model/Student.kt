package com.example.studentsapp.model


data class Student(
    var name: String,
    var id: String,
    var address: String,
    var phone: String,
    var avatarUrl: String? =null,
    var isChecked: Boolean
    //test
)