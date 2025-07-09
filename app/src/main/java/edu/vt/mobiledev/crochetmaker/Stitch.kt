package edu.vt.mobiledev.crochetmaker

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Stitch(
    @PrimaryKey val abbreviation: String,
    val title: String
)