package com.yang.demo.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.yang.demo.dao.WordDao;
import com.yang.demo.entity.Word;

/**
 * Created by yang on 2019/08/13.
 */
@Database(entities = {Word.class}, version = 1)
public abstract class WordRoomDatasBase extends RoomDatabase {
    public abstract WordDao wordDao();

    private static WordRoomDatasBase INSTANCE;

    public static WordRoomDatasBase getDataBase(Context context) {
        if (INSTANCE == null) {
            synchronized (WordRoomDatasBase.class) {
                if (INSTANCE == null) {
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(),WordRoomDatasBase.class,"word_database").addCallback(sRoomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
//            new PopulateDbAsync(INSTANCE).execute();
        }
    };


    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final WordDao mDao;

        PopulateDbAsync(WordRoomDatasBase db) {
            mDao = db.wordDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mDao.deleteAll();
            Word word = new Word("Hello");
            mDao.insert(word);
            word = new Word("World");
            mDao.insert(word);
            return null;
        }
    }

}
