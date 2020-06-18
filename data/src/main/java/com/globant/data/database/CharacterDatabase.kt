package com.globant.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.globant.domain.entity.CharacterRoom

@Database(entities = [CharacterRoom::class], version = 1, exportSchema = false)
abstract class CharacterDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao
}
