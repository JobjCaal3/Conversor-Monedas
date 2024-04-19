package org.example;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class PeticionesApi {

    private final HttpClient client = HttpClient.newHttpClient();
    private final Gson gson = new Gson();

    private JsonObject realizarPeticion(String url) {
        URI direccion = URI.create(url);
        HttpRequest request = HttpRequest.newBuilder().uri(direccion).build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return gson.fromJson(response.body(), JsonObject.class);
        } catch (Exception e) {
            throw new RuntimeException("Hubo un problema con la petición: " + e);
        }
    }

    public JsonObject CodigosSoportados() {
        return realizarPeticion("https://v6.exchangerate-api.com/v6/4d1346251aa0a4967d5645a0/codes");
    }

    public JsonObject obtenerConversiones(String ComplementoURL) {
        return realizarPeticion("https://v6.exchangerate-api.com/v6/4d1346251aa0a4967d5645a0/latest/" + ComplementoURL);
    }

    public DatosMoneda conversionMoneda(String ComplementoURL) {
        JsonObject response = realizarPeticion("https://v6.exchangerate-api.com/v6/4d1346251aa0a4967d5645a0/pair/" + ComplementoURL);
        return gson.fromJson(response.toString(), DatosMoneda.class);
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


}
