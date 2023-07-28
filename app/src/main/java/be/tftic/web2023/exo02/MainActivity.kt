package be.tftic.web2023.exo02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import be.tftic.web2023.exo02.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        // Permet de rendre accessible des valeurs depuis le nom de la classe
        const val EXTRA_USERNAME = "Username"
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var username: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Binding du layout
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtenir les données depuis l'intent
        username = intent.getStringExtra(EXTRA_USERNAME) ?: getString(R.string.unknown_username)

        // Welcome message
        Toast.makeText(
            this,
            getString(R.string.toast_welcome_msg).format(username),
            Toast.LENGTH_LONG
        ).show()

        // Listener des boutons
        binding.btnMainInfo.setOnClickListener(this)
        binding.btnMainCall.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
            when (v?.id) {
                R.id.btn_main_info -> openActivity(InfoActivity::class.java)
                R.id.btn_main_call -> openActivity(CallActivity::class.java)
            }
    }

    private fun openActivity(cls: Class<*>) {
        // Le parametre "cls" permet de récuperer l'activité à lancer
        // Le type de donnée "Class<*>" represente n'importe quelle classe Java (Générique)

        val intent : Intent = Intent(this, cls)
        startActivity(intent)
    }
}