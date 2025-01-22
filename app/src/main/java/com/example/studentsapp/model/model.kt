package com.example.studentsapp.model

class Model private constructor() {

    val students: MutableList<Student> = ArrayList()

    companion object {
        val shared = Model()
    }
    init{
        for(i in 0..20){
           val student = Student(
               name="Student $i"
                ,id="ID $i"
                ,avatarUrl="https://picsum.photos/200/300"
                ,isChecked=false
           )
            students.add(student)
        }
    }
}