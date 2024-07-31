package com.example.mynotes

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotes.activity.NoteDetailsActivity
import com.example.mynotes.adapter.NotesAdapter
import com.example.mynotes.data.NoteModel
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    private val notes = mutableListOf<NoteModel>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NotesAdapter
    private lateinit var searchEditText: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rev_note)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        adapter = NotesAdapter(this, notes)
        recyclerView.adapter = adapter

        searchEditText = findViewById(R.id.search)
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.filter(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        findViewById<ImageButton>(R.id.add).setOnClickListener {
            startActivityForResult(Intent(this, NoteDetailsActivity::class.java), ADD_NOTE_REQUEST)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK) {
            val title = data?.getStringExtra("title")
            val description = data?.getStringExtra("description")
            val position = data?.getIntExtra("position", -1)

            title?.let {
                if (position != null && position >= 0) {
                    val note = notes[position]
                    note.title = it
                    note.description = description ?: ""
                    adapter.notifyItemChanged(position)
                } else {
                    val note = NoteModel(it, description ?: "")
                    notes.add(note)
                    adapter.notifyItemInserted(notes.size - 1)
                }
            }
        }
    }

    companion object {
        const val ADD_NOTE_REQUEST = 1
    }
}

