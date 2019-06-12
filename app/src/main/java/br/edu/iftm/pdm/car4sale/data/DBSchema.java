package br.edu.iftm.pdm.car4sale.data;

import android.provider.BaseColumns;

public class DBSchema {

    public static final class TCar {

        public static final String TABLE_NAME = "car";
        public static final String ID = "c_id";
        public static final String MODEL = "c_model";
        public static final String BRAND = "c_brand";
        public static final String CHASSIS = "c_chassis";
        public static final String LICENSE_PLATE = "c_license_plate";
        public static final String PRICE = "c_price";
        public static final String PCT_DISCOUNT = "c_pct_discount";
        public static final String IS_ON_SALE = "c_is_on_sale";

        public static String getCreateTableQuery() {
            // implemente o método que permite criar a tabela Car no banco
            // Os campos acima correspondem aos campos que deverão ter na tabela
            return "CREATE TABLE" + TABLE_NAME + " (" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    MODEL + " TEXT NOT NULL, " +
                    BRAND + " TEXT NOT NULL, " +
                    CHASSIS + " TEXT NOT NULL, " +
                    LICENSE_PLATE + " TEXT NOT NULL, " +
                    PRICE + " REAL NOT NULL, " +
                    PCT_DISCOUNT + " INTEGER NOT NULL, " +
                    IS_ON_SALE + " INTEGER NOT NULL " +
                    ");";
        }

    }

    public static final class TPicture {

        public static final String TABLE_NAME = "picture";
        public static final String ID = "p_id";
        public static final String PATH = "p_path";
        public static final String CAR = "p_car";

        public static String getCreateTableQuery() {
            // implemente o método que permite criar a tabela Picture no banco
            // Os campos acima correspondem aos campos que deverão ter na tabela
            // LEMBRE-SE DE QUE A RELAÇÃO É DE 1 CAR PARA N PICTURES
            return "CREATE TABLE" + TABLE_NAME + " (" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    PATH + "TEXT NOT NULL, " +
                    "FOREIGN KEY("+ CAR +") REFERENCES " + TCar.TABLE_NAME + "(" + TCar.ID +")" +
                    ");";
        }
    }
}
