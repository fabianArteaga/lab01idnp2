package com.example.lab01idnp2;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private static final String FILE_NAME = "inscripciones.txt";
    private EditText editTextNombre, editTextApellido, editTextCorreo, editTextTelefono, editTextGrupoSanguineo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNombre = findViewById(R.id.editTextNombre);
        editTextApellido = findViewById(R.id.editTextApellido);
        editTextCorreo = findViewById(R.id.editTextCorreo);
        editTextTelefono = findViewById(R.id.editTextTelefono);
        editTextGrupoSanguineo = findViewById(R.id.editTextGrupoSanguineo);

        Button buttonRegistrar = findViewById(R.id.buttonRegistrar);
        Button buttonLeer = findViewById(R.id.buttonLeer);

        buttonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarDatos();
            }
        });

        buttonLeer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leerDatos();
            }
        });
    }

    private void registrarDatos() {
        String datos = editTextNombre.getText().toString() + "," +
                editTextApellido.getText().toString() + "," +
                editTextCorreo.getText().toString() + "," +
                editTextTelefono.getText().toString() + "," +
                editTextGrupoSanguineo.getText().toString() + "\n";

        try (FileOutputStream fos = openFileOutput(FILE_NAME, Context.MODE_APPEND);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos))) {
            writer.write(datos);
            Log.d("Inscripción", "Datos registrados: " + datos);
        } catch (Exception e) {
            Log.e("Error", "No se pudo registrar los datos", e);
        }
    }

    private void leerDatos() {
        try (FileInputStream fis = openFileInput(FILE_NAME);
             BufferedReader reader = new BufferedReader(new InputStreamReader(fis))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                Log.d("Inscripción", "Datos leídos: " + linea);
            }
        } catch (Exception e) {
            Log.e("Error", "No se pudo leer los datos", e);
        }
    }
}