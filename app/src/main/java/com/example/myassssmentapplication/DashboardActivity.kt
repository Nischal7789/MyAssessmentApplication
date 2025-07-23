package com.example.myassssmentapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myassssmentapplication.databinding.ActivityDashboardBinding
import com.example.myassssmentapplication.model.Entity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding

    @Inject
    lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val keypass = intent.getStringExtra("keypass") ?: return

        lifecycleScope.launch {
            try {
                val response = apiService.getDashboard(keypass)
                if (response.isSuccessful) {
                    val dashboard = response.body()
                    dashboard?.let {
                        val adapter = DashboardAdapter(it.entities) { entity ->
                            val intent = Intent(this@DashboardActivity, DetailsActivity::class.java)
                            intent.putExtra("entity", entity)
                            startActivity(intent)
                        }
                        binding.recyclerDashboard.layoutManager = LinearLayoutManager(this@DashboardActivity)
                        binding.recyclerDashboard.adapter = adapter
                    }
                } else {
                    Toast.makeText(this@DashboardActivity, "Failed to load dashboard", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@DashboardActivity, "Error: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}

