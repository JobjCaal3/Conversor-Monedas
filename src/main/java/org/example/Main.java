package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner lectura = new Scanner(System.in);
        PeticionesApi peticionesApi = new PeticionesApi();
        ArchivoJson archivoJson = new ArchivoJson();
        List<DatosMoneda> historial = new ArrayList<>();
        double cantidad;
        String codigoBase, codigoConvertir;
        int opcion = 0;

        do {
            //impresion de datos
            peticionesApi.imprecion();
            System.out.println("\n****************************************************************************");
            System.out.println("Elija uno de los codigos de moneda con las 3 iniciales de esta:");
            System.out.println("Presione 1 si quiere salir");
            System.out.println("****************************************************************************");

            codigoBase = lectura.nextLine();
            peticionesApi.obtenerConversiones(codigoBase);

            System.out.println("Tasas de conversión:");
            peticionesApi.imprimirConversiones(codigoBase);
            System.out.println("\n****************************************************************************");
            System.out.println("Escoja una de las siguientes opciones:\nPresione 1 si quiere salir");
            System.out.println("****************************************************************************");

            codigoConvertir = lectura.nextLine();

            System.out.println("Ingrese el monto a convertir");
            cantidad = lectura.nextDouble();
            DatosMoneda datosMoneda = peticionesApi.conversionMoneda(codigoBase + "/" + codigoConvertir + "/" + cantidad);

            historial.add(datosMoneda);
            System.out.println(datosMoneda);

            System.out.println("\n****************************************************************************");
            System.out.println("Presione 1 si quiere salir\nSi desea salir presione 2");
            opcion =lectura.nextInt();

        }while (opcion!=1);
        archivoJson.guardarHistorial(historial);
        System.out.println("\n\nHistorial de todas las conversiones");
        historial.forEach(System.out::println);
    }
}
