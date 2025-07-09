package edu.vt.mobiledev.crochetmaker.database

import androidx.room.Dao
import androidx.room.Query
import edu.vt.mobiledev.crochetmaker.Stitch
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface StitchDao {
    @Query("SELECT * FROM stitch")
    fun getStitches(): Flow<List<Stitch>>

}