package com.example.studentsapp.model

class Model private constructor() {

    val students: MutableList<Student> = ArrayList()

    companion object {
        val shared = Model()
    }

    init {
        for (i in 0..students.size+5) {
            val student = Student(
                name = "Student $i",
                id = "ID $i",
                phone = "Phone $i",
                address = "Address $i",
                isChecked = false,
                avatarUrl = "app/src/main/res/drawable/avatar.png"
            )
            students.add(student)
        }
    }

    fun updateStudent(updatedStudent: Student) {
        students.find { it.id == updatedStudent.id }?.apply {
            name = updatedStudent.name
            phone = updatedStudent.phone
            address = updatedStudent.address
            avatarUrl = updatedStudent.avatarUrl
            isChecked = updatedStudent.isChecked
        }
    }
}