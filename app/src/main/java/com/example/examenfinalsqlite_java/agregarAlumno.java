package com.example.examenfinalsqlite_java;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class agregarAlumno extends AppCompatActivity {
    private EditText editNombre = null;
    private EditText editUsuario = null;
    private EditText editIp = null;
    private RadioGroup rdGroup = null;
    private RadioButton rdButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_agregar_alumno);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editNombre = findViewById(R.id.edTxtNombre);
        editUsuario = findViewById(R.id.edTxtUsuario);
        editIp = findViewById(R.id.edTxtIp);
        rdGroup = findViewById(R.id.rdGroup);
        Button btnGuardar = findViewById(R.id.btnGuardar);
        Button btnSalir = findViewById(R.id.btnSalir);

        btnGuardar.setOnClickListener(v -> {
            String nombre = editNombre.getText().toString();
            String usuario = editUsuario.getText().toString();
            String ip = editIp.getText().toString();

            if (!nombre.isEmpty() && !usuario.isEmpty() && !ip.isEmpty()) {

                int selectedId = rdGroup.getCheckedRadioButtonId();
                if (selectedId != -1) {
                    rdButton = findViewById(selectedId);
                    String selected = rdButton.getText().toString();

                    // URL de la API para agregar el alumno
                    String url = "http://nocompila.lat/~AlexisDiaz/ApiRest/API/create.php";

                    // Crear un objeto JSON con los datos a enviar
                    JSONObject params = new JSONObject();
                    try {
                        params.put("nombre", nombre);
                        params.put("usuarioMaquina", usuario);
                        params.put("ip", ip);
                        params.put("img", selected);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    // Crear la solicitud de tipo POST con JsonObjectRequest
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, params,
                            response -> {
                                Toast.makeText(this, "Alumno guardado en API: " + response.toString(), Toast.LENGTH_SHORT).show();
                            },
                            error -> {
                                Toast.makeText(this, "Error en la API: " + error.toString(), Toast.LENGTH_LONG).show();
                            });

                    // Crea la cola de solicitudes de Volley y envía la solicitud
                    RequestQueue queue = Volley.newRequestQueue(this);
                    queue.add(jsonObjectRequest);

                } else {
                    Toast.makeText(this, "Por favor elija una máquina", Toast.LENGTH_LONG).show();
                }

            } else {
                Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_LONG).show();
            }
        });

        btnSalir.setOnClickListener(v -> finish());
    }
}
