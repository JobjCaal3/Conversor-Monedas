package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ArchivoJson {
    private Gson gson;
    /*
    este metodo sirve sin embargo en el siguiente metodo se usa para poder gestionar el posible error de que no se cierre
    correctamente el fileWrite
    public void guardarHistorial(List<DatosMoneda> datosMoneda) throws IOException {
        gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter escritura = new FileWriter( "Historial de conversion");
        escritura.write(gson.toJson(datosMoneda));
        escritura.close();
    }*/

    public void guardarHistorial(List<DatosMoneda> datosMoneda) throws IOException {
        gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter escritura = new FileWriter( "Historial de conversion")) {
            escritura.write(gson.toJson(datosMoneda));
        }
    }
}