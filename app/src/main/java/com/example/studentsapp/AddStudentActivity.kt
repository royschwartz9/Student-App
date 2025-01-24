package com.example.studentsapp

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.student_app.R
import com.example.studentsapp.model.Model
import com.example.studentsapp.model.Student


class AddStudentActivity : AppCompatActivity() {
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_student)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val saveButton: Button = findViewById(R.id.add_student_save_button)
        val cancelButton: Button = findViewById(R.id.student_details_edit_button)
        val nameEditText: EditText = findViewById(R.id.edit_student_activity_name_edit_text)
        val idEditText: EditText = findViewById(R.id.edit_student_activity_id_edit_text)
        val phoneEditText: EditText = findViewById(R.id.edit_student_activity_phone_edit_text)
        val addressEditText: EditText = findViewById(R.id.edit_student_activity_address_edit_text)
        val checkBox:CheckBox = findViewById(R.id.edit_student_checkbox)
        val savedMassageTextview: TextView = findViewById(R.id.add_student_activity_save_massage_textview)
        cancelButton.setOnClickListener {
            finish()
        }
        saveButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val id = idEditText.text.toString()
            val phone = phoneEditText.text.toString()
            val address = addressEditText.text.toString()
            val avatarUrl = "app/src/main/res/drawable/avatar.png"
            val checkBox: CheckBox = findViewById(R.id.edit_student_checkbox)
            val newStudent = Student(name, id, phone, address, avatarUrl, checkBox.isChecked)
            savedMassageTextview.text = "Name:${nameEditText.text} ID:${idEditText.text} saved"

            Model.shared.students.add(newStudent)
            finish()
        }
    }
}