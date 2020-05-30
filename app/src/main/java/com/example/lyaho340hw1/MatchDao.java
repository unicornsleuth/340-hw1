package com.example.lyaho340hw1;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MatchDao {
    @Query("SELECT * FROM match")
    List<Match> getAll();

    @Query("SELECT * FROM match WHERE uid IN (:userIds)")
    List<Match> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM match WHERE name LIKE :name LIMIT 1")
    Match findByName(String name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Match... matches);

    @Update
    void updateMatches(Match... matches);

    @Delete
    void delete(Match match);
}
