package com.example.xmlformatassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.xmlformatassignment.databinding.ActivityMainBinding
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var students: List<Student>
    lateinit var studentRecyclerView: RecyclerView
    lateinit var studentAdapter: StudentAdapter
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fetchData()
        setupRecyclerView()
    }

    private fun fetchData() {
        try {
            val parser = MyXMLPullParserHolder()
            val iStream = assets.open("student_details.xml")
            students = parser.parse(iStream)
            Log.d("Main", "Data: $students")
        } catch (e: IOException) {
            println("Error: $e")
        }
    }

    private fun setupRecyclerView() {
        studentRecyclerView = binding.rvStudents
        studentAdapter = StudentAdapter(students)
        studentRecyclerView.adapter = studentAdapter
        studentRecyclerView.layoutManager = LinearLayoutManager(this)
    }
}