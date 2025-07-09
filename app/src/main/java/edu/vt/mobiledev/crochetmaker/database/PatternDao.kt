package edu.vt.mobiledev.crochetmaker.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import edu.vt.mobiledev.crochetmaker.Pattern
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface PatternDao{

    @Query("SELECT * FROM pattern")
    fun getPatterns(): Flow<List<Pattern>>
    @Query("SELECT * FROM pattern WHERE id=(:id)")
    fun getPattern(id: UUID): Pattern
    @Insert
    fun addPattern(pattern: Pattern)
}