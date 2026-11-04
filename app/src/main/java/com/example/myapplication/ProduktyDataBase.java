package com.example.myapplication;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Produkty.class},version = 1)
public abstract class ProduktyDataBase extends RoomDatabase {
    public abstract ProduktyDAO zwrocProduktyDao();
}
