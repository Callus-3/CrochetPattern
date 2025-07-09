package edu.vt.mobiledev.crochetmaker

import android.content.Context
import androidx.room.Room
import edu.vt.mobiledev.crochetmaker.database.PatternDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.util.UUID

private const val DATABASE_NAME = "patterntest.db"

class PatternRepository private constructor(context:Context){

    private val database: PatternDatabase = Room
        .databaseBuilder(
            context.applicationContext,
            PatternDatabase::class.java,
            DATABASE_NAME
        )
        .createFromAsset(DATABASE_NAME)
        .build()

    suspend fun getPatterns():Flow<List<Pattern>>{
        return withContext(Dispatchers.IO){
            database.patternDao().getPatterns()
        }

    }

    suspend fun getPattern(id: UUID):Pattern{
        return withContext(Dispatchers.IO)
        {
            database.patternDao().getPattern(id)
        }

    }

    suspend fun addPattern(pattern: Pattern){
        return withContext(Dispatchers.IO)
        {
            database.patternDao().addPattern(pattern)
        }

    }

    companion object{
        private var INSTANCE: PatternRepository? = null

        fun initialize(context: Context){
            if (INSTANCE == null){
                INSTANCE = PatternRepository(context)
            }
        }

        fun get(): PatternRepository{
            return INSTANCE?:
            throw IllegalStateException("Pattern repository must be initialized")
        }
    }

}