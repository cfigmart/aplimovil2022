package edu.unicauca.main.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import edu.unicauca.main.activities.adaptadores.AdaptadorUsuarios;
import edu.unicauca.main.activities.modelo.Usuario;

public class MainActivity extends AppCompatActivity {
    ArrayList<Usuario> listaUsuarios;
    RecyclerView recyclerUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listaUsuarios = new ArrayList<>();
        setContentView(R.layout.activity_main);

        recyclerUsuarios = findViewById(R.id.idRecyclerView);
        recyclerUsuarios.setLayoutManager(new LinearLayoutManager(this));

        llenarUsuarios();

        AdaptadorUsuarios adaptadorUsuarios = new AdaptadorUsuarios(listaUsuarios);
        recyclerUsuarios.setAdapter(adaptadorUsuarios);
    }

    private void llenarUsuarios(){
        listaUsuarios.add(new Usuario("Maria", "Estudiante FIET", R.drawable.img1));
        listaUsuarios.add(new Usuario("Pedro", "Estudiante SENA CTPI", R.drawable.img2));
        listaUsuarios.add(new Usuario("José", "Estudiante FUP", R.drawable.img3));
        listaUsuarios.add(new Usuario("Nairo", "Ciclista Arkea", R.drawable.img4));
        listaUsuarios.add(new Usuario("Josefina", "Estudiante Unicomfacauca", R.drawable.img5));
        listaUsuarios.add(new Usuario("Laura", "Estudiante FIET", R.drawable.img6));
        listaUsuarios.add(new Usuario("Martha", "Ciclista Amateur", R.drawable.img7));
        listaUsuarios.add(new Usuario("Carolina", "Médico del Hospital San José", R.drawable.img8));
        listaUsuarios.add(new Usuario("Andrés", "Geek apasionado de Ubuntu", R.drawable.img9));
        listaUsuarios.add(new Usuario("Nicolás", "Estudiante FIET", R.drawable.img4));
        listaUsuarios.add(new Usuario("Carlos", "Candidato a la presidencia de la república", R.drawable.img2));
    }

}