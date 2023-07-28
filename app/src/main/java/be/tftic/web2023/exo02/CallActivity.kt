package be.tftic.web2023.exo02

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import be.tftic.web2023.exo02.databinding.ActivityCallBinding

class CallActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCallBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Binding
        binding = ActivityCallBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Listener
        binding.btnCallAction.setOnClickListener { call() }
    }

    private fun call() {
        if(!checkCallPermission()) {
            return;
        }

        // Récuperation du numéro de tel (format Uri)
        val phoneNumber = binding.etCallNumber.text.toString()
        val uri : Uri = Uri.parse(getString(R.string.uri_tel).format(phoneNumber))

        // Création d'un intent pour demander à Android d'utiliser l'app "Telephone"
        val intent : Intent = Intent(Intent.ACTION_CALL, uri)

        // Envoi de l'intent à Android
        startActivity(intent)
    }

    val callPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted : Boolean ->
        if(isGranted) {
            call()
        }
        else {
            Toast.makeText(this, R.string.toast_no_call_permission, Toast.LENGTH_LONG).show()
        }
    }

    private fun checkCallPermission(): Boolean {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED)  {
            return true
        }

        callPermissionLauncher.launch(Manifest.permission.CALL_PHONE)
        return false
    }
}
