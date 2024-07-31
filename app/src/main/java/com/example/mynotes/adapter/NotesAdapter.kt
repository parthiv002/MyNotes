package com.example.mynotes.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotes.R
import com.example.mynotes.activity.NoteDetailsActivity
import com.example.mynotes.data.NoteModel

class NotesAdapter(private val context: Context, private val notes: MutableList<NoteModel>) :
    RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.note_layout, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.bind(note)
        holder.itemView.setOnClickListener {
            val intent = Intent(context, NoteDetailsActivity::class.java).apply {
                putExtra("title", note.title)
                putExtra("description", note.description)
                putExtra("position", position)
            }
            context.startActivity(intent)
        }

        holder.editButton.setOnClickListener {
            val intent = Intent(context, NoteDetailsActivity::class.java).apply {
                putExtra("title", note.title)
                putExtra("description", note.description)
                putExtra("position", position)
                putExtra("isEdit", true)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = notes.size

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.note_title)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.note_description)
        val editButton: ImageButton = itemView.findViewById(R.id.edit_button)

        fun bind(note: NoteModel) {
            titleTextView.text = note.title
            descriptionTextView.text = note.description
        }
    }
}
