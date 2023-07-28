package be.tftic.web2023.exo02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import be.tftic.web2023.exo02.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USERNAME = "Username"
    }

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Binding du layout
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtenir les données depuis l'intent
        val username : String? = intent.getStringExtra(EXTRA_USERNAME)

        // Welcome message
        Toast.makeText(this, getString(R.string.toast_welcome_msg).format(username), Toast.LENGTH_LONG).show()
    }
}