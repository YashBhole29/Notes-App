package com.example.notesapp

import android.app.DownloadManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.databinding.FragmentHomeBinding
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
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

        //recyclerview=binding.recyclerviewid
        notesDao = NotesDao()



        binding=FragmentHomeBinding.inflate(layoutInflater,container,false)
        binding.addnotesbtn.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_new_Notes)
        }

        //setUpRecyclerView()


        return binding.root




    }
/*
    private fun setUpRecyclerView() {
        val noteCollection = notesDao.Notecollection
        val currentuser = auth.currentUser
        val cuid = currentuser?.uid
        val query = noteCollection.whereEqualTo("uid",cuid)

        val recyclerViewOption = FirestoreRecyclerOptions.Builder<Note>().setQuery(query,Note::class.java).build()
        adapter= MyAdapter(recyclerViewOption)
        recyclerview.adapter = adapter

    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }
    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }

*/
}