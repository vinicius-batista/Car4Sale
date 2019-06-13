package br.edu.iftm.pdm.car4sale.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import br.edu.iftm.pdm.car4sale.model.Car;
import br.edu.iftm.pdm.car4sale.model.Picture;

public class DAOCar {

    public static long getNextIdValue(DBHelper dbHelper) {
        // Você deverá uma função que retorne o próximo valor de ID do carro.
        // Esta função compõe a lógica fundamental para o método DAOCar.insert(...)
        // Integre com a função "semimplementada" de DAOPicture de insert.

        // Nao ha a necessidade de implementacao pq o metodo insert ja retorna o id da tabela, se
        // for o caso de id autoincrement. Se ocorrer problemas quando for inserir, ele retornara -1,
        // entretanto usei o metodo para lancar uma excessao.
        // fonte: https://stackoverflow.com/questions/5409751/get-generated-id-after-insert

        return 0;
    }

    public static void insert(DBHelper dbHelper, Car car) {
        // Você deverá implementar uma função que insira carros no banco.
        // Nessa função você deverá inserir todos os dados do carro no banco, incluindo suas
        // fotos.
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBSchema.TCar.MODEL, car.getModel());
        cv.put(DBSchema.TCar.CHASSIS, car.getChassis());
        cv.put(DBSchema.TCar.BRAND, car.getBrand());
        cv.put(DBSchema.TCar.LICENSE_PLATE, car.getLicensePlate());
        cv.put(DBSchema.TCar.PRICE, car.getPrice());
        cv.put(DBSchema.TCar.PCT_DISCOUNT, car.getPctDiscount());
        cv.put(DBSchema.TCar.IS_ON_SALE, car.isOnSale());

        try {
            long id = db.insertOrThrow(DBSchema.TCar.TABLE_NAME, null, cv);
            DAOPicture.insert(dbHelper, id, car.getPictures());
        } catch (Exception e) {
            Log.d("Exception", e.getLocalizedMessage());
        }

        db.close();
    }

    public static ArrayList<Car> getAll(DBHelper dbHelper) {
        // Vusssshhhhh... Um furacão passou por aqui e levou todas as linhas de código deste método :'(
        // Você deverá implementar este método de forma que pegue TODAS as informações sobre o carro
        // INCLUSIVE as fotos.
        // Você deverá utilizar JOIN de tabelas para pegar os dados completos sobre o carro.

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<Car> cars = new ArrayList<>();
        ArrayList<Picture> pictures = new ArrayList<>();
        long lastId = 0;

        // SELECT * FROM contact;
        String query = "SELECT * FROM " + DBSchema.TCar.TABLE_NAME +
                " LEFT JOIN " + DBSchema.TPicture.TABLE_NAME +
                " ON " + DBSchema.TPicture.CAR + " = " +
                DBSchema.TCar.ID + ";";

        Cursor cursor = db.rawQuery(query, null, null);
        while(cursor.moveToNext()) {
            Car car = new Car();
            long id = cursor.getLong(cursor.getColumnIndex(DBSchema.TCar.ID));

            if (!(lastId == id)) {
                String model = cursor.getString(cursor.getColumnIndex(DBSchema.TCar.MODEL));
                String brand = cursor.getString(cursor.getColumnIndex(DBSchema.TCar.BRAND));
                String chassis = cursor.getString(cursor.getColumnIndex(DBSchema.TCar.CHASSIS));
                String licensePlate = cursor.getString(cursor.getColumnIndex(DBSchema.TCar.LICENSE_PLATE));
                float price = cursor.getFloat(cursor.getColumnIndex(DBSchema.TCar.PRICE));
                int pctDiscount = cursor.getInt(cursor.getColumnIndex(DBSchema.TCar.PCT_DISCOUNT));
                boolean isOnSale = cursor.getInt(cursor.getColumnIndex(DBSchema.TCar.PCT_DISCOUNT)) == 1;
                car = new Car(id, model, brand, chassis, licensePlate, price, pctDiscount, isOnSale);
                pictures = new ArrayList<>();
                car.setPictures(pictures);

                cars.add(car);
            }

            long pictureId =  cursor.getLong(cursor.getColumnIndex(DBSchema.TPicture.ID));
            String path = cursor.getString(cursor.getColumnIndex(DBSchema.TPicture.PATH));
            Picture picture = new Picture(pictureId, path);
            pictures.add(picture);

            lastId = id;
        }
        cursor.close();
        db.close();
        return cars;
    }

    public static void sellCar(DBHelper dbHelper, Car car) {
        // Inhaaac... um quokka faminto do sul da austrália passou por aqui e comeu todas as linhas de código desse método
        // Você deverá implementar um método que permita alterar o estado de um carro de À Venda para Vendido.
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(DBSchema.TCar.IS_ON_SALE, 0);
        String[] args = new String[]{
                Long.toString(car.getId())
        };
        db.update(DBSchema.TCar.TABLE_NAME, cv, DBSchema.TCar.ID + " = ?", args);
        db.close();
        car.setOnSale(false);
    }

    public static void changeDiscount(DBHelper dbHelper, Car car) {
        // Eita... agora fui eu mesmo que removi essas linhas, sorry. :X
        // Você deverá implementar um método que permita alterar o valor da porcentagem de desconto do automóvel.
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(DBSchema.TCar.PCT_DISCOUNT, car.getPctDiscount());
        String[] args = new String[]{
                Long.toString(car.getId())
        };
        db.update(DBSchema.TCar.TABLE_NAME, cv, DBSchema.TCar.ID + " = ?", args);
        db.close();
    }
}
