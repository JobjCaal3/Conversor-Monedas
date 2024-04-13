package org.example;

public record DatosMoneda(String base_code, String target_code,
                          double conversion_rate,double conversion_result ) {
    @Override
    public String toString() {
        return "\nMoneda Base = " + base_code +
                "\nMoneda a convertir = " + target_code +
                "\nValor 1 a 1 = " + conversion_rate +
                "\nResultado de la conversión = " + conversion_result+"\n\n";
    }
}
