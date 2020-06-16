package com.globant.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.globant.domain.util.Constants.EMPTY_STRING

@Entity(tableName = "character_table")
data class CharacterRoom(
    @PrimaryKey val id: String = EMPTY_STRING,
    val name: String = EMPTY_STRING,
    val description: String = EMPTY_STRING,
    val image: String = EMPTY_STRING
)