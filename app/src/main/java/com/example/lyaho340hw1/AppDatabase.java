package com.example.lyaho340hw1;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {UserSettings.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserSettingsDao userSettingsDao();
}
