package com.example.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    lateinit var navController:NavController

    lateinit var mGoogleSignInClient: GoogleSignInClient



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        val email = intent.getStringExtra("email")
        val displayName = intent.getStringExtra("name")

        findViewById<TextView>(R.id.details).text="Welcome "+ displayName+ "\n" + email
        val gso=GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build()
        mGoogleSignInClient=GoogleSignIn.getClient(this,gso)

        findViewById<FloatingActionButton>(R.id.Sign_out_button).setOnClickListener {
            mGoogleSignInClient.signOut().addOnCompleteListener {
                val intent=Intent(this,google_Login::class.java)
                startActivity(intent)
                finish()
            }
        }

/*        findViewById<FloatingActionButton>(R.id.Sign_out_button).setOnClickListener {
            auth.signOut()

            startActivity(Intent(this, google_Login::class.java))
        }
*/
        navController = findNavController(R.id.fragmentContainer)

    }

    override fun onNavigateUp(): Boolean {
        return navController.navigateUp() || super.onNavigateUp()
    }
}