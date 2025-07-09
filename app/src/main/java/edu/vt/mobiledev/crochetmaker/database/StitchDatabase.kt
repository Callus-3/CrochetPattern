package edu.vt.mobiledev.crochetmaker.database

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.vt.mobiledev.crochetmaker.Stitch

@Database(entities = [Stitch::class], version=1)
abstract class StitchDatabase: RoomDatabase(){
    abstract fun stitchDao(): StitchDao
}