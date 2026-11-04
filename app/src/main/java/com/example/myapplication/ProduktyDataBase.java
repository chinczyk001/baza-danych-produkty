package com.example.myapplication;

import androidx.room.Database;

@Database(entities = {Produkty.class},version = 1)
public abstract class ProduktyDataBase {
    public abstract ProduktyDAO zwrocProduktyDao();
}
