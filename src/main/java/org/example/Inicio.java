package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Inicio {
    public static void main(String[] args) throws IOException {
        Scanner lectura = new Scanner(System.in);
        ConexionApi conexionApi = new ConexionApi();
        ArchivoJson archivoJson = new ArchivoJson();
        List<DatosMoneda> historial = new ArrayList<>();
        String mensaje="";
        int opcion = 0;
        String Menu = """
                Menu
                1. Dolar --> Peso Argentino
                2. Peso Argentino --> Dolar
                3. Dolar --> Real Brasileño
                4. Real Brasileño --> Dolar
                5. Dolar --> Pezo Mexicano 
                6. Peso Mexicano --> Dolar
                7. Dolar --> Quetzal
                8. Quetzal --> Dolar
                9. Salir
                \nElija una opcion valida:
                """;

        do{
            System.out.print(Menu);
            opcion = lectura.nextInt();
            if (opcion==9){
                break;
            }
            mensaje = getMensaje(opcion);
            if (mensaje.equals("")){
                System.out.println("Ingrese una opcion valida");
                continue;
            }
            System.out.print("Ingrese la cantidad a convertir: ");
            Double cantidad = lectura.nextDouble();
            DatosMoneda datosMoneda = conexionApi.conversionMoneda(mensaje,cantidad);
            historial.add(datosMoneda);
            System.out.println(datosMoneda);
        }while(opcion!=9);
        archivoJson.guardarHistorial(historial);
        System.out.println("\n\nHistorial de todas las conversiones");
        historial.forEach(System.out::println);
    }

    private static String getMensaje(int opcion) {
        return switch (opcion) {
            case 1 -> "USD/ARS/";
            case 2 -> "ARS/USD/";
            case 3 -> "USD/BRL/";
            case 4 -> "BRL/USD/";
            case 5 -> "USD/MXN/";
            case 6 -> "MXN/USD/";
            case 7 -> "USD/GTQ/";
            case 8 -> "GTQ/USD/";
            default -> "";
        };
    }
}
