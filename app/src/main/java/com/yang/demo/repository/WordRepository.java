package com.yang.demo.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.yang.demo.database.WordRoomDatasBase;
import com.yang.demo.dao.WordDao;
import com.yang.demo.entity.Word;

import java.util.List;

/**
 * Created by yang on 2019/08/13.
 */
public class WordRepository {
    private LiveData<List<Word>> mAllWords;
    private WordDao mWordDao;

    public WordRepository(Application application) {
        WordRoomDatasBase db = WordRoomDatasBase.getDataBase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAllWords();
    }

    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    public void insert(Word word) {
        mWordDao.insert(word);
    }
}
