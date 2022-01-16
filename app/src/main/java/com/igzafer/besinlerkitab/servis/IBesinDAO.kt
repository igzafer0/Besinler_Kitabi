package com.igzafer.besinlerkitab.servis

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.igzafer.besinlerkitab.model.BesinModel

@Dao
interface IBesinDAO {

    @Insert
    suspend fun insertAll(vararg besin: BesinModel): List<Long>

    @Query("select * from BesinModel")
    suspend fun getAllBesin(): List<BesinModel>

    @Query("select * from BesinModel where uuid = :besinId")
    suspend fun getSingleBesin(besinId:Int): BesinModel

    @Query("delete from BesinModel")
    suspend fun deleteAllBesin()

}