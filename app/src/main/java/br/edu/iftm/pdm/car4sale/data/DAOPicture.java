package br.edu.iftm.pdm.car4sale.data;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import br.edu.iftm.pdm.car4sale.model.Picture;

class DAOPicture {

    protected static void insert(DBHelper dbHelper, long carId, ArrayList<Picture> pictures) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        for (Picture picture: pictures) {
            // para cada picture que você tiver no array você deve inserir no banco.
            ContentValues cv = new ContentValues();
            cv.put(DBSchema.TPicture.PATH, picture.getPath());
            cv.put(DBSchema.TPicture.CAR, carId);
            db.insert(DBSchema.TPicture.TABLE_NAME, null, cv);
        }
        db.close();
    }
}
