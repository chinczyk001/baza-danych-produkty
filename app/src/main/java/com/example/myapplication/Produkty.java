package com.example.myapplication;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "produkty")
public class Produkty {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String nazwa;

    public String dataWaznosci;

    public int cena;

    public boolean czyNapoj;

    public Produkty(String nazwa, String dataWaznosci, int cena, boolean czyNapoj) {
        this.nazwa = nazwa;
        this.dataWaznosci = dataWaznosci;
        this.cena = cena;
        this.czyNapoj = czyNapoj;
    }

    public Produkty() {
    }
}
