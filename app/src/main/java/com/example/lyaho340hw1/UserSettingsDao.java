package com.example.lyaho340hw1;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserSettingsDao {
    @Query("SELECT * FROM user_settings")
    List<UserSettings> getAll();

    @Query("SELECT * FROM user_settings WHERE email IN (:emails)")
    List<UserSettings> loadAllByEMail(int[] emails);

    @Query("SELECT * FROM user_settings WHERE email LIKE :email LIMIT 1")
    UserSettings findByName(String email);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(UserSettings... userSettings);

    @Update
    void updateUserSettings(UserSettings... userSettings);

    @Delete
    void delete(UserSettings userSettings);
}
