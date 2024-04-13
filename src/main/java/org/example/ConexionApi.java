package org.example;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConexionApi {
    public DatosMoneda conversionMoneda(String mensaje, double cantidad){
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/4d1346251aa0a4967d5645a0/pair/" + mensaje + cantidad);
        HttpClient client =HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(direccion).build();
        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), DatosMoneda.class);
        }catch (Exception e){
            throw  new RuntimeException("hubo un problema al convertir la moneda: ");
        }
    }
}
