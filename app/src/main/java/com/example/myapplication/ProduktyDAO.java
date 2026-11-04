package com.example.myapplication;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProduktyDAO {
    @Insert
    public void wstawDoBazy(Produkty produkty);

    @Insert
    public void wstawWiecejProduktow(Produkty ... produkties);

    @Delete
    public void usunZBazy(Produkty produkty);

    @Update
    public void aktualizuj(Produkty produkty);

    @Query("SELECT * FROM produkty")
    public List<Produkty> zwrocWszystkieProdukty();

    @Query("SELECT * FROM produkty WHERE czyNapoj = 1")
    public  List<Produkty> zwrocNapoje();
}
