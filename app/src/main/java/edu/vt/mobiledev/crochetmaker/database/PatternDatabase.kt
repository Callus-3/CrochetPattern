package edu.vt.mobiledev.crochetmaker.database

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.vt.mobiledev.crochetmaker.Pattern

@Database(entities = [Pattern::class], version=1)
abstract class PatternDatabase:RoomDatabase(){
    abstract fun patternDao(): PatternDao
}