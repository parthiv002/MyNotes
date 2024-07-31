package com.example.mynotes.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.mynotes.R
import com.google.android.material.textfield.TextInputEditText

class NoteDetailsActivity : AppCompatActivity() {
    private lateinit var titleEditText: TextInputEditText
    private lateinit var descriptionEditText: TextInputEditText
    private var isEdit = false
    private var position = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewnotes)

        titleEditText = findViewById(R.id.details_title)
        descriptionEditText = findViewById(R.id.details_description)
        val backButton: ImageButton = findViewById(R.id.backButton)
        val saveButton: ImageButton = findViewById(R.id.button_save)

        val title = intent.getStringExtra("title")
        val description = intent.getStringExtra("description")
        isEdit = intent.getBooleanExtra("isEdit", false)
        position = intent.getIntExtra("position", -1)

        titleEditText.setText(title)
        descriptionEditText.setText(description)

        backButton.setOnClickListener {
            finish()
        }

        saveButton.setOnClickListener {
            val resultIntent = Intent().apply {
                putExtra("title", titleEditText.text.toString())
                putExtra("description", descriptionEditText.text.toString())
                if (isEdit) putExtra("position", position)
            }
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}

