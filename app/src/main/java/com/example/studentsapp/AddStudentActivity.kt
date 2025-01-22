package com.example.studentsapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class AddStudentActivity : AppCompatActivity() {
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
        val savedMassageTextview: TextView = findViewById(R.id.add_student_activity_save_massage_textview)
        cancelButton.setOnClickListener {
            finish()
        }
        saveButton.setOnClickListener {
            savedMassageTextview.text = "Name:${nameEditText.text} ID:${idEditText.text} saved"


        }
    }
}