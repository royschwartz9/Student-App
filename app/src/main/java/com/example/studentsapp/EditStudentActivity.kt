package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.student_app.R
import com.example.studentsapp.model.Model
import com.example.studentsapp.model.Student

class EditStudentActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit_student)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val studentName = intent.getStringExtra("student_name")
        val studentId = intent.getStringExtra("student_id")
        val studentPhone = intent.getStringExtra("student_phone")
        val studentAddress = intent.getStringExtra("student_address")

        val nameEditText: EditText = findViewById(R.id.edit_student_activity_name_edit_text)
        val idEditText: EditText = findViewById(R.id.edit_student_activity_id_edit_text)
        val phoneEditText: EditText = findViewById(R.id.edit_student_activity_phone_edit_text)
        val addressEditText: EditText = findViewById(R.id.edit_student_activity_address_edit_text)


        nameEditText.setText(studentName)
        idEditText.setText(studentId)
        phoneEditText.setText(studentPhone)
        addressEditText.setText(studentAddress)



        val cancelButton: Button = findViewById(R.id.edit_student_cancel_button)
        cancelButton.setOnClickListener {
            finish()
        }

        val deleteButton: Button = findViewById(R.id.edit_studnt_delete_button)
        deleteButton.setOnClickListener { val position = Model.shared.students.indexOfFirst { it.id == studentId }
            if (position != -1) {
                Model.shared.students.removeAt(position)
            }
            setResult(RESULT_OK)
            finish()
        }


        val saveButton: Button = findViewById(R.id.edit_student_save_button)
        saveButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val id = idEditText.text.toString()
            val phone = phoneEditText.text.toString()
            val address = addressEditText.text.toString()
            val avatarUrl = R.drawable.avatar.toString()
            val checkBox: CheckBox = findViewById(R.id.edit_student_checkbox)
            val updatedStudent = Student(name, id, phone, address, avatarUrl, checkBox.isChecked)

            val position = Model.shared.students.indexOfFirst { it.id == id }
            if (position != -1) {
                Model.shared.students[position] = updatedStudent
            }
            setResult(RESULT_OK)
            finish()

        }
    }
}