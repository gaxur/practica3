package es.unizar.eina.vv6f.practica3;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Clase para el análisis de la frecuencia de aparición de letras del alfabeto español en un
 * fichero de texto. Los objetos de esta clase se construyen utilizando como argumento un objeto de
 * la clase File que representa el fichero de texto que se quiere analizar. La primera invocación al
 * metodo frecuencias() analiza el contenido del fichero de texto y, si se ha podido procesar,
 * devuelve un vector de siempre 27 componentes de tipo entero. Las primeras 26 componentes
 * almacenan el número de apariciones de las 26 letras del alfabeto inglés. La última componente
 * almacena el número de apariciones de la letra Ñ.
 *
 * No se distingue entre mayúsculas y minúsculas. En español, la letra Ñ es una letra distinta a la
 * N. El resto de apariciones de letras voladas y caracteres con diacríticos (acentos agudos,
 * graves, diéresis, cedillas), se consideran como ocurrencias de la letra correspondiente sin
 * diacríticos.
 *
 */
public class ContadorDeLetras {
    private File fichero;
    private int[] frecuencias;


    /**
     * Construye un ContadorDeLetras para frecuencias la frecuencia en las que aparecen las letras
     * del fichero «fichero».
     * @param fichero
     *            fichero de texto cuyo contenido será analizado.
     */
    public ContadorDeLetras(File fichero) {
        this.fichero = fichero;
        this.frecuencias = null;
    }

    /**
     * La primera vez que este metodo es invocado, analiza el contenido del fichero de texto asociado a este
     * objeto en el constructor. Devuelve un vector de 27 componentes con las frecuencias
     * absolutas de aparición de cada letra del alfabeto español en el fichero.
     *
     * @return vector de 27 componentes de tipo entero. Las primeras 26 componentes almacenan el
     *         número de apariciones de las 26 letras del alfabeto inglés: la componente indexada
     *         por 0 almacena el número de apariciones de la letra A, la componente indexada por 1,
     *         el de la letra B y así sucesivamente. La última componente, almacena el número de
     *         apariciones de la letra Ñ.
     * @throws FileNotFoundException
     *             si el fichero de texto que se especificó al construir este objeto no existe o no
     *             puede abrirse.
     */
    public int[] frecuencias() throws FileNotFoundException {
        if (frecuencias == null) {
            frecuencias = new int[27];
            Scanner scanner = new Scanner(fichero, "UTF-8");

            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine().toUpperCase(); // Convertimos a mayúsculas primero

                // Extraemos todas las Ñ antes de la normalización
                StringBuilder soloN = new StringBuilder(); // Las Ñ no se normalizan
                StringBuilder restoTexto = new StringBuilder();

                for (char c : linea.toCharArray()) {
                    if (c == 'Ñ') {
                        soloN.append(c);  // Guardamos las Ñ en su sitio
                    } else {
                        restoTexto.append(c);  // Guardamos el resto de caracteres
                    }
                }

                // Normalizamos el resto del texto sin tocar la Ñ
                String textoNormalizado = Normalizer.normalize(restoTexto.toString(), Normalizer.Form.NFD);
                textoNormalizado = textoNormalizado.replaceAll("\\p{InCombiningDiacriticalMarks}+", ""); // Quitamos diacríticos


                textoNormalizado = textoNormalizado.replaceAll("º", "O");
                textoNormalizado = textoNormalizado.replaceAll("ᵃ", "A");
                textoNormalizado = textoNormalizado.replaceAll("ᵉ", "E");
                textoNormalizado = textoNormalizado.replaceAll("ᶦ", "I");
                textoNormalizado = textoNormalizado.replaceAll("ᵒ", "O");
                textoNormalizado = textoNormalizado.replaceAll("ᵘ", "U");
                textoNormalizado = textoNormalizado.replaceAll("[¹²³⁴⁵⁶⁷⁸⁹⁰]", "");
                
                // Concatenamos la parte normalizada con la parte de Ñ intacta
                String textoFinal = soloN + textoNormalizado;

                // Contamos las letras
                for (char c : textoFinal.toCharArray()) {
                    if (c >= 'A' && c <= 'Z') {
                        frecuencias[c - 'A']++; // Letras A-Z
                    } else if (c == 'Ñ') {
                        frecuencias[26]++; // Contador para la Ñ
                    }
                }
            }
            scanner.close();
        }
        return frecuencias; // Ira mas rapido cuando sea != null
    }
}