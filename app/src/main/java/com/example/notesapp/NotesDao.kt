package com.example.notesapp

import android.os.Bundle
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class NotesDao {

    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()



        val Notecollection = db.collection("Notes")


        fun addNote(titlenote: String, textnote: String) {

            val currentuser = auth.currentUser
            val cuid = currentuser?.uid.toString()

            val note = Note(titlenote, textnote, cuid)
            Notecollection.document().set(note)
        }


    }

