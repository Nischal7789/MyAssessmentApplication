package com.example.myassssmentapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myassssmentapplication.model.Entity

class DashboardAdapter(
    private val entities: List<Entity>,
    private val onItemClick: (Entity) -> Unit
) : RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val prop1: TextView = view.findViewById(R.id.tvProperty1)
        val prop2: TextView = view.findViewById(R.id.tvProperty2)

        init {
            view.setOnClickListener {
                onItemClick(entities[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_entity, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val entity = entities[position]
        holder.prop1.text = entity.property1
        holder.prop2.text = entity.property2
    }

    override fun getItemCount() = entities.size
}

