package com.example.mynotes.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotes.R
import com.example.mynotes.data.NoteModel

class NotesAdapter(
    private val context: Context,
    private var notes: List<NoteModel>
) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    private var filteredNotes: List<NoteModel> = notes

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.note_layout, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = filteredNotes[position]
        holder.titleTextView.text = note.title
        holder.descriptionTextView.text = note.description
    }

    override fun getItemCount(): Int {
        return filteredNotes.size
    }

    fun filter(query: String) {
        filteredNotes = if (query.isEmpty()) {
            notes
        } else {
            notes.filter {
                it.title.contains(query, ignoreCase = true) ||
                        it.description.contains(query, ignoreCase = true)
            }
        }
        notifyDataSetChanged()
    }

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.note_title)
        val descriptionTextView: TextView = itemView.findViewById(R.id.note_description)
    }
}

