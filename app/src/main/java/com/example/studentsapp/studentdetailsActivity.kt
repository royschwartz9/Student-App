package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.student_app.R


class studentdetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_studentdetails)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val name = intent.getStringExtra("student_name")
        val id = intent.getStringExtra("student_id")
        val address = intent.getStringExtra("student_address")
        val phone = intent.getStringExtra("student_phone")
        val isChecked = intent.getBooleanExtra("student_checkbox", false)

        findViewById<TextView>(R.id.student_name_textview).text = name
        findViewById<TextView>(R.id.student_ID_textview).text = id
        findViewById<TextView>(R.id.student_address_textview).text = address
        findViewById<TextView>(R.id.student_phone_textview).text = phone
        findViewById<CheckBox>(R.id.student_detail_checkbox).apply {
            this.isChecked = isChecked
            this.isEnabled = false
        }


        val EditStudentButton: Button = findViewById(R.id.student_details_edit_button)
        EditStudentButton.setOnClickListener {
                val intent = Intent(this, EditStudentActivity::class.java).apply {
                    putExtra("student_name", name)
                    putExtra("student_id", id)
                    putExtra("student_address", address)
                    putExtra("student_phone", phone)
                    putExtra("student_checkbox", isChecked)
                }
                startActivity(intent)
                finish()
            }

        val BackButton: Button = findViewById(R.id.student_details_back_button)
        BackButton.setOnClickListener {
            finish()
        }

    }
}