package com.example.studentsapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.student_app.R
import com.example.studentsapp.model.Model
import com.example.studentsapp.model.Student

class studentrcyclerviewActivity : AppCompatActivity() {

    lateinit var adapter: StudentsRecyclerAdapter
    lateinit var students: MutableList<Student>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_studentrcyclerview)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        students = Model.shared.students
        val recyclerView: RecyclerView = findViewById(R.id.students_recycler_view)
        recyclerView.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        adapter = StudentsRecyclerAdapter(students)
        recyclerView.adapter = adapter

        val addStudentButton: Button = findViewById(R.id.add_student_button)
        addStudentButton.setOnClickListener {
            val intent = Intent(this, com.example.studentsapp.AddStudentActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onResume() {
        super.onResume()
        adapter?.notifyDataSetChanged()
    }


    class StudentsviewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var nameTextView: TextView? = null
        private var idTextView: TextView? = null
        private var checkBox: CheckBox? = null
        private var avatarImageView: ImageView? = null
        private var student: Student? = null

        init {
           nameTextView = itemView.findViewById(R.id.student_row_name_view)
           idTextView = itemView.findViewById(R.id.student_row_id_text_view)
           checkBox = itemView.findViewById(R.id.student_row_checkbox)
           avatarImageView = itemView.findViewById(R.id.student_row_imageview)
            checkBox?.apply {
                setOnClickListener { view -> (tag as? Int)?.let { tag ->
                        student?.isChecked = (view as? CheckBox)?.isChecked ?: false
                       }
                   }
                }
                itemView.setOnClickListener {
                   adapterPosition
                }
                itemView.setOnClickListener {
                student?.let {
                    val intent = Intent(itemView.context, studentdetailsActivity::class.java).apply {
                        putExtra("student_name", it.name)
                        putExtra("student_id", it.id)
                        putExtra("student_phone", it.phone)
                        putExtra("student_address", it.address)
                    }
                    itemView.context.startActivity(intent)
                    }
                }
             }
        fun bind(student: Student?, position: Int) {
            this.student = student
            nameTextView?.text = student?.name
            idTextView?.text = student?.id
            checkBox?.apply {
                isChecked = student?.isChecked ?: false
                tag = position
                }
                avatarImageView?.setImageResource(R.drawable.avatar)
            }
         }
    class StudentsRecyclerAdapter(private val stundents: List<Student>?) : RecyclerView.Adapter<StudentsviewHolder>() {

        override fun getItemCount(): Int = stundents?.size ?: 0

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentsviewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val view = inflater.inflate(R.layout.student_list_row, parent, false)
            return StudentsviewHolder(view)
        }
        override fun onBindViewHolder(holder: StudentsviewHolder, position: Int) {

            holder.bind(stundents?.get(position), position)

        }
    }
}