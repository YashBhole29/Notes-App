package com.example.notesapp

import android.app.DownloadManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.databinding.FragmentHomeBinding
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.Query
import com.google.firebase.ktx.Firebase


class HomeFragment : Fragment() {

    private lateinit var binding:FragmentHomeBinding
    private lateinit var recyclerview:RecyclerView
    private lateinit var notesDao: NotesDao
    private val auth = FirebaseAuth.getInstance()
    private lateinit var adapter: MyAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        notesDao = NotesDao()

        binding=FragmentHomeBinding.inflate(layoutInflater,container,false)
        recyclerview=binding.recyclerviewid

        binding.addnotesbtn.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_new_Notes)
        }

        setUpRecyclerView()


        return binding.root
    }

    private fun setUpRecyclerView() {
        val noteCollection = notesDao.Notecollection
        val currentuser = auth.currentUser
        val cuid = currentuser?.uid.toString()
        val query = noteCollection.whereEqualTo("uid",cuid).orderBy("title",Query.Direction.ASCENDING)

        val recyclerViewOption = FirestoreRecyclerOptions.Builder<Note>().setQuery(query,Note::class.java).build()
        val layoutManager = LinearLayoutManager(context)
        adapter= MyAdapter(recyclerViewOption)
        recyclerview.layoutManager= layoutManager
        recyclerview.adapter = adapter

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position =viewHolder.absoluteAdapterPosition
                adapter.deleteNote(position)
            }

        }).attachToRecyclerView(recyclerview)

    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }
    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }


}