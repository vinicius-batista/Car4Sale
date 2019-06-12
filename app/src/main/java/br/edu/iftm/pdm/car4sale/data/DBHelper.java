package br.edu.iftm.pdm.car4sale.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "car4sale.db";
    private static final int VERSION = 1;

    public DBHelper (Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Um furacão passou por aqui e levou as linhas de código que deveriam compor este método;
        sqLiteDatabase.execSQL(DBSchema.TCar.getCreateTableQuery());
        sqLiteDatabase.execSQL(DBSchema.TPicture.getCreateTableQuery());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Esse aqui fica vazio mesmo ;)
    }
}
