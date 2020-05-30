package com.example.lyaho340hw1;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Match.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MatchDao matchDao();
}
