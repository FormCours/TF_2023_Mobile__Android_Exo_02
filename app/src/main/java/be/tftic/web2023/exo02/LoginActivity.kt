package be.tftic.web2023.exo02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import be.tftic.web2023.exo02.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    // Pour utiliser le "ViewBinding", ajouter dans le gradle de l'app la ligne suivante :
    // buildFeatures { viewBinding = true }
    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialiser le binding avec le layout (-> Inflate)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        // Définir le visuel avec le résultat du binding
        setContentView(binding.root)

        // Listener sur l'action click du bouton
        binding.btnLogin.setOnClickListener { login() }
    }

    private fun login() : Unit {
        val username : String = binding.etLoginUsername.text.toString()
        val password : String = binding.etLoginPassword.text.toString()

        if(username.lowercase() != "della" || password != "Test1234=") {
            binding.etLoginUsername.text.clear()
            binding.etLoginPassword.text.clear()
            binding.etLoginUsername.requestFocus()
            Toast.makeText(this, R.string.toast_error_login, Toast.LENGTH_SHORT).show()
            return
        }

        openMainActivity()
    }

    private fun openMainActivity() {
        // Récuperation du username
        val username = binding.etLoginUsername.text.toString()

        // Intent pour ouvrir l'activité main
        val mainIntent : Intent = Intent(this, MainActivity::class.java).apply {
            // Ajouter des données à l'intent
            putExtra(MainActivity.EXTRA_USERNAME, username)
        }

        // Ajouter des données à l'intent (Alternative)
        // mainIntent.putExtra(MainActivity.EXTRA_USERNAME, username)

        // Envoi à Android de l'intent
        startActivity(mainIntent)

        // On cloture l'activité
        finish()
    }
}