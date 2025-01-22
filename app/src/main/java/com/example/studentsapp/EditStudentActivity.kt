package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.studentsapp.model.Model
import com.example.studentsapp.model.Student

class EditStudentActivity : AppCompatActivity() {
    private var student: Student? = null
    private var studentIndex: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit_student)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        studentIndex = intent.getIntExtra("student_index", -1)
        student = Model.shared.students?.get(studentIndex)

        val nameEditText: EditText = findViewById(R.id.edit_student_activity_name_edit_text)
        val idEditText: EditText = findViewById(R.id.edit_student_activity_id_edit_text)
        val phoneEditText: EditText = findViewById(R.id.edit_student_activity_phone_edit_text)
        val addressEditText: EditText = findViewById(R.id.edit_student_activity_address_edit_text)

        student?.let {
            nameEditText.setText(it.name)
            idEditText.setText(it.id)
            phoneEditText.setText(it.phone)
            addressEditText.setText(it.address)
        }

        val cancelButton: Button = findViewById(R.id.edit_student_cancel_button)
        cancelButton.setOnClickListener {
            finish()
        }

        val deleteButton: Button = findViewById(R.id.edit_studnt_delete_button)
        deleteButton.setOnClickListener {
            Model.shared.students?.removeAt(studentIndex)
            finish()
        }

        val saveButton: Button = findViewById(R.id.edit_student_save_button)
        saveButton.setOnClickListener {
            student?.let {
                it.name = nameEditText.text.toString()
                it.id = idEditText.text.toString()
                it.phone = phoneEditText.text.toString()
                it.address = addressEditText.text.toString()
                Model.shared.students?.set(studentIndex, it)
            }
            finish()
        }
    }
}