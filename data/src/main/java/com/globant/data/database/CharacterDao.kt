package com.globant.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.globant.domain.entity.CharacterRoom

@Dao
interface CharacterDao {

    @Query("SELECT * FROM character_table")
    fun getAllCharacters(): List<CharacterRoom>

    @Query("SELECT * FROM character_table WHERE id = :character_id")
    fun getSpecificCharacter(character_id: String): CharacterRoom

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCharacter(character: CharacterRoom)
}