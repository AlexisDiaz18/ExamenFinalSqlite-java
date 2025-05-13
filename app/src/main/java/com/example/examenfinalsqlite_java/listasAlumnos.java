package com.example.examenfinalsqlite_java;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class listasAlumnos extends AppCompatActivity {

    private DBHelper dbHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_alumno);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dbHelper = new DBHelper(this);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        obtenerAlumnos();

    }

    private void obtenerAlumnos() {
        String url = "http://nocompila.lat/~AlexisDiaz/ApiRest/API/read.php";
        RequestQueue queue = Volley.newRequestQueue(this);

        // Solicitud para obtener los datos en formato JSON
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    List<Alumnos> alumnosList = new ArrayList<>();
                    try {
                        // Procesar el JSON recibido
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject alumnoObject = response.getJSONObject(i);
                            int idPersona = alumnoObject.getInt("idPersona");
                            String nombre = alumnoObject.getString("nombre");
                            String usuarioMaquina = alumnoObject.getString("usuarioMaquina");
                            String ip = alumnoObject.getString("ip");
                            String img = alumnoObject.getString("img");

                            // Crear objeto Alumno
                            Alumnos alumno = new Alumnos(idPersona, nombre, usuarioMaquina, ip, img);
                            alumnosList.add(alumno);
                        }

                        // Configurar el adaptador para el RecyclerView
                        AlumnoAdapter adapter = new AlumnoAdapter(alumnosList);
                        RecyclerView recyclerView = findViewById(R.id.recyclerView);
                        recyclerView.setAdapter(adapter);

                    } catch (Exception e) {
                        Toast.makeText(listasAlumnos.this, "Error al procesar los datos: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                },
                error -> Toast.makeText(listasAlumnos.this, "Error al obtener datos: " + error.toString(), Toast.LENGTH_LONG).show()
        );

        // Agregar la solicitud a la cola de Volley
        queue.add(jsonArrayRequest);
    }
}
