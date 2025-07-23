package com.example.myassssmentapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myassssmentapplication.databinding.ActivityDetailsBinding
import com.example.myassssmentapplication.model.Entity

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val entity = intent.getParcelableExtra<Entity>("entity")

        entity?.let {
            binding.tvProp1.text = it.property1
            binding.tvProp2.text = it.property2
            binding.tvDescription.text = it.description
        }
    }
}

