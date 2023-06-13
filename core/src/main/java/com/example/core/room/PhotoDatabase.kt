package com.example.core.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.core.entity.PhotoEntity

@Dao
interface PhotoDatabase {

    @Query("SELECT * FROM photos_table")
    fun getPhotos(): List<PhotoEntity>

    @Query("SELECT * FROM photos_table WHERE title LIKE '%' ||:param ||'%' ")
    fun getPhotos(param: String): List<PhotoEntity>

    @Query("DELETE FROM photos_table")
    fun deleteAllRows();

    @Insert
    fun putPhoto(entity: PhotoEntity)

    @Insert
    fun putPhoto(entity: List<PhotoEntity>)

}