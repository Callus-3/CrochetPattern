package edu.vt.mobiledev.crochetmaker

import android.content.Context
import androidx.room.Room
import edu.vt.mobiledev.crochetmaker.database.StitchDatabase
import kotlinx.coroutines.flow.Flow


private const val DATABASE_NAME = "stitch-database"

class StitchRepository private constructor(context: Context) {

    private val database: StitchDatabase = Room
        .databaseBuilder(
            context.applicationContext,
            StitchDatabase::class.java,
            DATABASE_NAME
        )
        .createFromAsset(DATABASE_NAME)
        .build()

    fun getStitches(): Flow<List<Stitch>> = database.stitchDao().getStitches()

    companion object{
        private var INSTANCE: StitchRepository? = null

        fun initialize(context: Context){
            if (INSTANCE == null){
                INSTANCE = StitchRepository(context)
            }
        }

        fun get(): StitchRepository{
            return INSTANCE?:
            throw IllegalStateException("Stitch repository must be initialized")
        }
    }
}