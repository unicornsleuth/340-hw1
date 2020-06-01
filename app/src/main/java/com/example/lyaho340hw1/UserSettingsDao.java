package com.example.lyaho340hw1;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserSettingsDao {

    @Query("SELECT * FROM user_settings")
    LiveData<List<UserSettings>> getAll();

    @Query("SELECT * FROM user_settings WHERE email LIKE :email LIMIT 1")
    LiveData<UserSettings> findByEmail(String email);

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void insertAll(UserSettings... userSettings);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserSettings userSettings);

//    @Update
//    void updateUserSettings(UserSettings... userSettings);
//
//    @Delete
//    void delete(UserSettings userSettings);
}
