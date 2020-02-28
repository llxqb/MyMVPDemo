package com.freemud.app.mvp.demo.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.freemud.app.mvp.demo.gen.DaoMaster;

import javax.inject.Inject;

/**
 * Created by li.liu on 2018/4/10.
 */

public class DbHelper extends DaoMaster.OpenHelper{
    private static final String DB_NAME="fm_db";

    @Inject
    public DbHelper(Context context) {
        super(context, DB_NAME, null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
    }
}
