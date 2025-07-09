package edu.vt.mobiledev.crochetmaker

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "pattern")
data class Pattern(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    val title: String,
    val description: String,
    val category: Category,
    val gauge: String,
    val skillLevel: Skill
)

enum class Category {
    Accessory,
    Clothing,
    Amigurumi,
    Household
}

enum class Skill {
    Beginner,
    Intermediate,
    Advanced
}
