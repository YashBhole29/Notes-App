package com.example.notesapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class MyAdapter(options: FirestoreRecyclerOptions<Note>) :FirestoreRecyclerAdapter<Note,MyAdapter.RVViewHolder>(
    options
){
    fun deleteNote(position:Int){
        snapshots.getSnapshot(position).reference.delete()
    }



    class RVViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val noteText : TextView = itemView.findViewById(R.id.notecontent)
        val noteTitle: TextView=itemView.findViewById(R.id.notetitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVViewHolder {
       return RVViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false))
    }

    override fun onBindViewHolder(holder: RVViewHolder, position: Int, model: Note) {
        holder.noteText.text=model.text
        holder.noteTitle.text=model.title
    }
}
