package com.example.examenfinalsqlite_java;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
public class AlumnosFetcher {

    public interface AlumnosCallback {
        void onSuccess(List<Alumnos> alumnos);
        void onError(String error);
    }

    public static void obtenerAlumnos(Context context, AlumnosCallback callback) {
        String url = "http://nocompila.lat/~AlexisDiaz/ApiRest/API/read.php";

        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        List<Alumnos> alumnosList = new ArrayList<>();
                        JSONArray alumnosArray = response.getJSONArray("records");

                        for (int i = 0; i < alumnosArray.length(); i++) {
                            JSONObject alumnoObj = alumnosArray.getJSONObject(i);
                            int id = alumnoObj.getInt("id");
                            String nombre = alumnoObj.getString("nombre");
                            String usuarioMaquina = alumnoObj.getString("usuarioMaquina");
                            String ip = alumnoObj.getString("ip");
                            String img = alumnoObj.getString("img");

                            Alumnos alumno = new Alumnos(id, nombre, usuarioMaquina, ip, img);
                            alumnosList.add(alumno);
                        }

                        callback.onSuccess(alumnosList);
                    } catch (Exception e) {
                        callback.onError(e.toString());
                    }
                },
                error -> {
                    callback.onError(error.toString());
                    Log.e("VolleyError", error.toString());
                });

        queue.add(request);
    }
}
