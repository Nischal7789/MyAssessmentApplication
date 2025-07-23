package com.example.myassssmentapplication.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Entity(
    val property1: String,
    val property2: String,
    val description: String
) : Parcelable

data class DashboardResponse(
    val entities: List<Entity>,
    val entityTotal: Int
)


