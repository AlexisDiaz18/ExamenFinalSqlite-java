package com.example.examenfinalsqlite_java;

import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

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
        DBHelper dbHelper = new DBHelper(this);


        btnGuardar.setOnClickListener(v ->{

            String nombre = editNombre.getText().toString();
            String usuario = editUsuario.getText().toString();
            String ip = editIp.getText().toString();

            if(!nombre.isEmpty() && !usuario.isEmpty() && !ip.isEmpty()){

                int selectedId = rdGroup.getCheckedRadioButtonId();

                if(selectedId != -1){
                    rdButton = findViewById(selectedId);
                    String selected = rdButton.getText().toString();

                    if (selected != null && !selected.trim().isEmpty()) {

                        boolean exito = dbHelper.insertarAlumno(nombre,usuario,ip,selected);

                        if(exito){
                            Toast.makeText(this, "Alumno guardado", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(this, "Error al guardar", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(this, "Tipo de máquina inválido", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(this, "Por favor de elegir una maquina", Toast.LENGTH_LONG).show();
                }

            }else{
                Toast.makeText(this, "Por favor de rellenar los datos", Toast.LENGTH_LONG).show();
            }
        });

        btnSalir.setOnClickListener(v ->{
            finish();
        });
    }
}