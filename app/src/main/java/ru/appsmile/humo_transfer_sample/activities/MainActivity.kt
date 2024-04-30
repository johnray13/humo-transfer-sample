package ru.appsmile.humo_transfer_sample

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import ru.appsmile.humo_transfer_sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var sharedPreferences: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("my_storage", Context.MODE_PRIVATE)

        if (sharedPreferences?.contains("phone_number") == true) {
            startActivity(Intent(this, SecondActivity::class.java))
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        isAgreeAndIsNumberCorrect()
        binding.button1.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
            sharedPreferences?.edit()
                ?.putInt("phone_number", binding.number1.text.toString().toInt())?.apply()
            finish()
        }

        binding.exit1.setOnClickListener {
            finish()
        }
    }

    private fun checkIsNumberCorrect(): Boolean {
        return binding.number1.text.toString().length == 9
    }

    private fun isAgreeAndIsNumberCorrect() {

        binding.number1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                binding.button1.isEnabled = (checkIsNumberCorrect() and binding.checkbox.isChecked)
            }
        })

        binding.checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
            binding.button1.isEnabled = checkIsNumberCorrect() and isChecked
        }
    }
}
