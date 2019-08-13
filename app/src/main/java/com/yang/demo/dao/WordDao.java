package com.yang.demo.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.yang.demo.entity.Word;

import java.util.List;

/**
 * Created by yang on 2019/08/13.
 */
@Dao
public interface WordDao {
    @Insert
    void insert(Word word);

    @Query("DELETE FROM word_table")
    void deleteAll();

    @Query("SELECT * from word_table ORDER BY word ASC")
    LiveData<List<Word>> getAllWords();

}
