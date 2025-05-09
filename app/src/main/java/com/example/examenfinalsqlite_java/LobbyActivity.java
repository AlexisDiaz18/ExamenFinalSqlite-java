package com.example.examenfinalsqlite_java;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LobbyActivity extends AppCompatActivity {
    private Button btnAgregar = null;
    private Button btnMostrar = null;
    private Intent intent = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.lobby_activity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        btnAgregar = findViewById(R.id.btnAgregar);
        btnMostrar = findViewById(R.id.btnMostrarLista);

        btnAgregar.setOnClickListener( v ->{
            intent = new Intent(this, agregarAlumno.class);
            startActivity(intent);
        });

        btnMostrar.setOnClickListener(v -> {
            intent = new Intent(this, listasAlumnos.class);
            startActivity(intent);
        });

    }
}