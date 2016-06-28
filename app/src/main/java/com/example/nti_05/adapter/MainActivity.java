package com.example.nti_05.adapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Carro> carros;
    CarroAdapter carroAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.list);
        listView.setEmptyView(findViewById(android.R.id.empty));

        //listView = new ListView(this);
        //setContentView(listView);
        carros = new ArrayList<Carro>();
        //Adicionando carros informando o modelo, o ano,
        // a posição no array de logos que identifica o fabricante e o tipo de combustivel.
        carros.add(new Carro("Celta", 2010, 1, true, false));
        carros.add(new Carro("Uno", 2012, 2, true, true));
        carros.add(new Carro("Fiesta", 2009, 3, false, true));
        carros.add(new Carro("Gol", 2014, 0, true, true));

        carroAdapter = new CarroAdapter(this, carros);
        //Exibe os objetos na lista.
        listView.setAdapter(carroAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Carro carro = (Carro) adapterView.getItemAtPosition(position);
                if(carro != null){
                    Toast.makeText(MainActivity.this, carro.modelo + "-" + carro.ano, Toast.LENGTH_SHORT).show();
                    carros.remove(carro);
                    carroAdapter.notifyDataSetChanged();
                }

            }
        });


    }
}
