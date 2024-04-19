package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Inicio {
    public static void main(String[] args) throws IOException {
        Scanner lectura = new Scanner(System.in);
        PeticionesApi peticionesApi = new PeticionesApi();
        ArchivoJson archivoJson = new ArchivoJson();
        List<DatosMoneda> historial = new ArrayList<>();
        String cantidad;
        String codigoBase, codigoConvertir;
        int opcion = 0;

        do {
            //impresion de datos
            peticionesApi.imprecionCodigoSoportado();
            System.out.println("\n****************************************************************************");
            System.out.println("Elija uno de los codigos de moneda con las 3 iniciales de esta:");
            System.out.println("****************************************************************************");

            codigoBase = lectura.next();
            //comprobacion
            if(codigoBase.matches("[a-zA-Z]+")) {
                codigoBase.toUpperCase();
                peticionesApi.obtenerConversiones(codigoBase);
                System.out.println("Tasas de conversión:");
                peticionesApi.imprimirConversiones(codigoBase);
            }else{
                System.out.println("Solo se permite letras no caracteres especiales");
            }
            System.out.println("\n****************************************************************************");
            System.out.println("Escoja una de las siguientes opciones:");
            System.out.println("****************************************************************************");

            codigoConvertir = lectura.next();
            if (codigoConvertir.matches("[a-zA-Z]+")){
                System.out.println("Ingrese el monto a convertir");
                cantidad = lectura.next();
                if(cantidad.matches("\\d+(\\.\\d+)?")) {
                    DatosMoneda datosMoneda = peticionesApi.conversionMoneda(codigoBase + "/" + codigoConvertir + "/" + cantidad);

                    historial.add(datosMoneda);
                    System.out.println(datosMoneda);
                }else{
                    System.out.println("Solo se permite numeros enteros o decimales no caracteres especiales");
                }
            }else{
                System.out.println("Solo se permite letras no caracteres especiales");
            }
            System.out.println("\n****************************************************************************");
            System.out.println("Presione 1 si quiere salir\nSi desea hacer otra conversion presione 2");
            opcion = lectura.nextInt();
        }while (opcion!=1);
        archivoJson.guardarHistorial(historial);
        System.out.println("\n\nHistorial de todas las conversiones");
        historial.forEach(System.out::println);
    }
}

