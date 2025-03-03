package es.unizar.eina.vv6f.practica3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Programa Java que, al iniciar su ejecución, solicita al usuario el nombre de un fichero de texto.
 * A continuación, si el fichero existe y se puede leer, muestra en la salida estándar una lista de
 * las letras del alfabeto español y el número de veces que dicha letra aparece en el fichero
 * introducido. En caso contrario, escribe en la salida estándar un mensaje de error de la forma
 * «El fichero 'f' no existe.», donde 'f' es el nombre de fichero introducido por el usuario.
 * 
 * No se distingue entre mayúsculas y minúsculas. La letra Ñ es una letra en español. El resto de
 * apariciones de letras voladas y caracteres con diacríticos (acentos agudos, graves, diéresis y
 * cedillas), se consideran como ocurrencias de la letra correspondiente sin diacríticos.
 */
public class Main {

    private static final String FORMATO_SALIDA_FRECUENCIAS = "%c: %7d%n";

    /**
     * Método que, al iniciar su ejecución, solicita al usuario el nombre de un fichero de texto.
     * A continuación, si el fichero existe y se puede leer, muestra en la salida estándar una lista
     * de las letras del alfabeto español y el número de veces que dicha letra aparece en el fichero
     * introducido. En caso contrario, escribe en la salida estándar un mensaje de error de la forma
     * «El fichero 'f' no existe.», donde 'f' es el nombre de fichero introducido por el usuario.
     *
     * No se distingue entre mayúsculas y minúsculas. La letra Ñ es una letra en español. El resto
     * de apariciones de letras voladas y caracteres con diacríticos (acentos agudos, graves,
     * diéresis y cedillas), se consideran como ocurrencias de la letra correspondiente sin
     * diacríticos.
     *
     * @param args
     *            no utilizado.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce el nombre del fichero: ");
        String nombreFichero = scanner.nextLine();
        scanner.close();

        File fichero = new File(nombreFichero);
        if (!fichero.exists() || !fichero.canRead()) {
            System.out.printf("El fichero '%s' no existe o no se puede leer.%n", nombreFichero);
            return;
        }

        try {
            ContadorDeLetras contador = new ContadorDeLetras(fichero);
            int[] frecuencias = contador.frecuencias();
            for (int i = 0; i < 26; i++) {
                System.out.printf(FORMATO_SALIDA_FRECUENCIAS, (char) ('A' + i), frecuencias[i]);
            }
            System.out.printf(FORMATO_SALIDA_FRECUENCIAS, 'Ñ', frecuencias[26]);
        } catch (FileNotFoundException e) {
            System.out.println("Error: No se pudo abrir el fichero.");
        }
    }
}
