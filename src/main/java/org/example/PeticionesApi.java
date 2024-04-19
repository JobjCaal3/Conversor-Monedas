package org.example;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PeticionesApi {

    public JsonObject CodigosSoportados(){
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/4d1346251aa0a4967d5645a0/codes");
        HttpClient client =HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(direccion).build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), JsonObject.class);
        }catch (Exception e){
            throw  new RuntimeException("Hubo un problema conla peticion de la codigos de moneda: " + e);
        }
    }

    public JsonObject obtenerConversiones(String codigo) {
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/4d1346251aa0a4967d5645a0/latest/"+codigo);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(direccion).build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), JsonObject.class);
        } catch (Exception e) {
            throw new RuntimeException("Hubo un problema al obtener las conversiones de moneda: " + e);
        }
    }

    public DatosMoneda conversionMoneda(String mensaje){
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/4d1346251aa0a4967d5645a0/pair/" + mensaje);
        HttpClient client =HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(direccion).build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), DatosMoneda.class);
        }catch (Exception e){
            throw  new RuntimeException("hubo un problema al convertir la moneda: ");
        }
    }

    public void imprimirConversiones(String codigo1) {
        JsonObject datos = obtenerConversiones(codigo1);
        JsonObject conversiones = datos.getAsJsonObject("conversion_rates");
        int columnas=0;
        for (String codigo : conversiones.keySet()) {
            System.out.printf("%-18s", codigo + ": " + conversiones.get(codigo).getAsDouble());
            columnas++;
            if (columnas % 8 == 0) {
                System.out.println();
            }
        }
    }

    public void imprecionCodigoSoportado(){
        JsonObject datos = CodigosSoportados();
        int columnas=0;
        for (JsonElement codigo : datos.get("supported_codes").getAsJsonArray()) {
            JsonArray detalleCodigo = codigo.getAsJsonArray();
            System.out.printf("%-42s", detalleCodigo.get(0) + ": " + detalleCodigo.get(1));
                columnas++;
                if (columnas % 4 == 0) {
                    System.out.println();
                }
        }
    }
}
