/*
Carlos Alejos Fumanal (872342)
Mario Caudevilla Ruiz (870421)
Marcos Galán Carrillo (874095)
*/

import es.unizar.eina.vv6f.practica3.ContadorDeLetras;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ContadorDeLetrasTest {

    @Test
    public void test1() throws IOException {
        testContadorLetras("src/test/res/minusculas_basico.txt","src/test/res/salida-minusculas_basico.txt", false);
    }

    @Test
    public void test2() throws IOException {
        testContadorLetras("src/test/res/minusculas_con_todo.txt","src/test/res/salida-minusculas_con_todo.txt", false);
    }


    @Test
    public void test3() throws IOException {
        testContadorLetras("src/test/res/mayusculas_basico.txt","src/test/res/salida-mayusculas_basico.txt", false);
    }


    @Test
    public void test4() throws IOException {
        testContadorLetras("src/test/res/mayusculas_con_todo.txt","src/test/res/salida-mayusculas_con_todo.txt", false);
    }

    @Test
    public void test5() throws IOException {
        testContadorLetras("src/test/res/caracteres_especiales.txt","src/test/res/salida-caracteres_especiales.txt", false);
    }


    @Test
    public void test6() throws IOException {
        testContadorLetras("src/test/res/vocales_voladas.txt","src/test/res/salida-vocales_voladas.txt", false);
    }

    @Test
    public void test7() throws IOException {
        testContadorLetras("src/test/res/digitos.txt","src/test/res/salida-digitos.txt", false);
    }

    @Test
    public void test8() throws IOException {
        testContadorLetras("src/test/res/vacio.txt","src/test/res/salida-vacio.txt", false);
    }


    @Test
    public void test9() throws IOException {
        testContadorLetras("src/main/res/quijote.txt","src/test/res/salida-quijote.txt", false);
    }

    @Test
    public void test10() throws IOException {
        testContadorLetras("src/main/res/hamlet.txt","src/test/res/salida-hamlet.txt", false);
    }

    @Test
    public void test11() throws IOException {
        testContadorLetras("src/main/res/regenta.txt","src/test/res/salida-regenta.txt", false);
    }

    @Test
    public void test12() throws IOException {
        testContadorLetras("src/main/res/quijote.txt","src/test/res/salida-quijote.txt", true); // sera al instante al recalcular
    }


    @Test
    public void test13() {
        // Archivo que no existe
        File entrada = new File("src/main/res/prueba");

        // Asegurar que el archivo no existe antes de la prueba
        assertFalse(entrada.exists(), "El archivo debería NO existir para esta prueba.");

        // Verificar que al invocar el metodo de frecuencias lanza FileNotFoundException
        assertThrows(FileNotFoundException.class, () -> {
            ContadorDeLetras contador = new ContadorDeLetras(entrada);
            contador.frecuencias(); // Se lanza excepción
        });
    }

    @Test
    public void test14() {
        // Verifica que se lanza NullPointerException cuando la entrada es null
        assertThrows(NullPointerException.class, () -> {
            // Intentar crear un ContadorDeLetras con un archivo nulo
            ContadorDeLetras contador = new ContadorDeLetras(null);
            contador.frecuencias(); // Se lanza excepción
        });
    }

    public void testContadorLetras (String entrada, String salida, Boolean notNull) throws IOException {
        // Ruta de los archivos
        File entradaTest = new File(entrada);
        File salidaTest = new File(salida);

        // Verificar que los archivos existen antes de proceder
        assertTrue(entradaTest.exists(), "El archivo no existe.");
        assertTrue(salidaTest.exists(), "El archivo no existe.");

        // Crear un objeto ContadorDeLetras y obtener las frecuencias
        ContadorDeLetras contador = new ContadorDeLetras(entradaTest);
        int[] frecuenciasGeneradas = contador.frecuencias();

        // Leer la salida esperada y convertirla en un array de enteros
        List<String> lineas = Files.readAllLines(Paths.get(salidaTest.getPath()));
        int[] frecuenciasEsperadas = new int[27];

        // Procesar cada línea de salida-esperada.txt para extraer las frecuencias
        for (String linea : lineas) {
            if (linea.matches("[A-ZÑ]:\\s+\\d+")) { // Formato esperado "A: 12345"
                char letra = linea.charAt(0); // Obtener la letra
                int valor = Integer.parseInt(linea.split(":")[1].trim());

                // Mapear la letra a la posición correcta en el array
                if (letra == 'Ñ') {
                    frecuenciasEsperadas[14] = valor; // La Ñ debe estar después de la N (posición 14)
                } else {
                    int indice = letra - 'A'; // Posición normal
                    if (letra > 'N') {
                        indice++; // Ajustar el índice para saltar la Ñ
                    }
                    frecuenciasEsperadas[indice] = valor;
                }
            }
        }

        // Reordenar la Ñ en la salida generada antes de comparar
        int[] frecuenciasGeneradasReordenadas = new int[27];
        System.arraycopy(frecuenciasGeneradas, 0, frecuenciasGeneradasReordenadas, 0, 14);
        frecuenciasGeneradasReordenadas[14] = frecuenciasGeneradas[26]; // Colocar la Ñ en la posición correcta
        System.arraycopy(frecuenciasGeneradas, 14, frecuenciasGeneradasReordenadas, 15, 12);

        // Simular el caso cuando frecuencias != null sin recalcular
        if (notNull){
            int[] frecuenciasGeneradasRapida = contador.frecuencias();// Se reutiliza
            // Comparar ambos arrays de frecuencias
            assertArrayEquals(frecuenciasGeneradas, frecuenciasGeneradasRapida, "Las frecuencias no coinciden.");
        }
        // Comparar ambos arrays de frecuencias
        else {
            assertArrayEquals(frecuenciasEsperadas, frecuenciasGeneradasReordenadas, "Las frecuencias no coinciden.");
        }
    }
}
