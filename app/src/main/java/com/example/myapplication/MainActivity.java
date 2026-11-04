package com.example.myapplication;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;
import java.util.ArrayList;
import java.util.Arrays;
import android.widget.AdapterView;
import android.widget.EditText;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import java.util.List;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {


    private ProduktyDataBase produktyDataBase;
    private EditText editTextNazwa, editTextData, editTextCena;
    private CheckBox checkBoxCzyNapoj;
    private Button buttonDodaj, buttonNapoje, buttonAktualizuj;

    private List<Produkty> listaProduktow, listaNapojow;
    private ArrayAdapter<Produkty> adapter;
    private ListView listView;
    private int temp =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        buttonDodaj = findViewById(R.id.buttonDodaj);
        editTextNazwa = findViewById(R.id.editTextNazwa);
        editTextCena = findViewById(R.id.editTextNumberCena);
        editTextData = findViewById(R.id.editTextDate);
        checkBoxCzyNapoj = findViewById(R.id.checkBox);
        buttonNapoje = findViewById(R.id.buttonWyswietlNapoje);

        produktyDataBase = Room.databaseBuilder(
                getApplicationContext(),
                ProduktyDataBase.class,
                "produkty_db"
        )
                .allowMainThreadQueries()
                .build();

        buttonDodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Produkty produkty = new Produkty(
                        editTextNazwa.getText().toString(),
                        editTextData.getText().toString(),
                        Integer.parseInt(editTextCena.getText().toString()),
                        checkBoxCzyNapoj.isChecked()
                );
                produktyDataBase.zwrocProduktyDao().wstawDoBazy(produkty);
                listaProduktow.add(produkty);
                adapter.notifyDataSetChanged();

            }
        });
        listView=findViewById(R.id.listView);
        listaProduktow = produktyDataBase.zwrocProduktyDao().zwrocWszystkieProdukty();
        listaNapojow = produktyDataBase.zwrocProduktyDao().zwrocNapoje();
        adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, listaProduktow);
        listView.setAdapter(adapter);
        buttonNapoje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (temp == 0){
                    adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, listaNapojow);
                    temp = 1;
                    buttonNapoje.setText("Wyświetl wszystko");
                }else{
                    adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, listaProduktow);
                    temp = 0;
                    buttonNapoje.setText("Wyświetl napoje");
                }
                listView.setAdapter(adapter);
            }
        });


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                produktyDataBase.zwrocProduktyDao().usunZBazy(listaProduktow.get(i));
                listaProduktow.remove(i);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
    }
}