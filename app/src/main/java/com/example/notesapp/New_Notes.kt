package com.example.notesapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.Navigation
import com.example.notesapp.databinding.FragmentNewNotesBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton


class New_Notes : Fragment() {

    lateinit var binding:FragmentNewNotesBinding
    private lateinit var notesDao: NotesDao


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewNotesBinding.inflate(layoutInflater,container,false)
        binding.saveBttn.setOnClickListener(){

            val titleedittext = binding.TitleEditTextBox.text.toString()
            val notesedittext = binding.NotesEditTextBox.text.toString()
            notesDao= NotesDao()

            if(titleedittext.isNotEmpty() && notesedittext.isNotEmpty()) {

                notesDao.addNote(titleedittext,notesedittext)
                Navigation.findNavController(it).navigate(R.id.action_new_Notes_to_homeFragment)

            }
        }

        return binding.root
    }


}