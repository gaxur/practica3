import es.unizar.eina.vv6f.practica3.ContadorDeLetras;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;
import org.junit.Test;

public class ContadorDeLetrasTest {

    @Test
    public void test1() throws IOException {
        // Ruta de los archivos
        File entrada = new File("src/test/res/minusculas_basico.txt");
        File salidaEsperada = new File("src/test/res/salida-minusculas_basico.txt");

        // Verificar que los archivos existen antes de proceder
        assertTrue("El archivo no existe.", entrada.exists());
        assertTrue("El archivo no existe.", salidaEsperada.exists());

        // Crear un objeto ContadorDeLetras y obtener las frecuencias
        ContadorDeLetras contador = new ContadorDeLetras(entrada);
        int[] frecuenciasGeneradas = contador.frecuencias();

        // Leer la salida esperada y convertirla en un array de enteros
        List<String> lineas = Files.readAllLines(Paths.get(salidaEsperada.getPath()));
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

        // Comparar ambos arrays de frecuencias
        assertArrayEquals("Las frecuencias no coinciden.", frecuenciasEsperadas, frecuenciasGeneradasReordenadas);
    }

    @Test
    public void test2() throws IOException {
        // Ruta de los archivos
        File entrada = new File("src/test/res/minusculas_con_todo.txt");
        File salidaEsperada = new File("src/test/res/salida-minusculas_con_todo.txt");

        // Verificar que los archivos existen antes de proceder
        assertTrue("El archivo no existe.", entrada.exists());
        assertTrue("El archivo no existe.", salidaEsperada.exists());

        // Crear un objeto ContadorDeLetras y obtener las frecuencias
        ContadorDeLetras contador = new ContadorDeLetras(entrada);
        int[] frecuenciasGeneradas = contador.frecuencias();

        // Leer la salida esperada y convertirla en un array de enteros
        List<String> lineas = Files.readAllLines(Paths.get(salidaEsperada.getPath()));
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

        // Comparar ambos arrays de frecuencias
        assertArrayEquals("Las frecuencias no coinciden.", frecuenciasEsperadas, frecuenciasGeneradasReordenadas);
    }


    @Test
    public void test3() throws IOException {
        // Ruta de los archivos
        File entrada = new File("src/test/res/mayusculas_basico.txt");
        File salidaEsperada = new File("src/test/res/salida-mayusculas_basico.txt");

        // Verificar que los archivos existen antes de proceder
        assertTrue("El archivo no existe.", entrada.exists());
        assertTrue("El archivo no existe.", salidaEsperada.exists());

        // Crear un objeto ContadorDeLetras y obtener las frecuencias
        ContadorDeLetras contador = new ContadorDeLetras(entrada);
        int[] frecuenciasGeneradas = contador.frecuencias();

        // Leer la salida esperada y convertirla en un array de enteros
        List<String> lineas = Files.readAllLines(Paths.get(salidaEsperada.getPath()));
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

        // Comparar ambos arrays de frecuencias
        assertArrayEquals("Las frecuencias no coinciden.", frecuenciasEsperadas, frecuenciasGeneradasReordenadas);
    }


    @Test
    public void test4() throws IOException {
        // Ruta de los archivos
        File entrada = new File("src/test/res/mayusculas_con_todo.txt");
        File salidaEsperada = new File("src/test/res/salida-mayusculas_con_todo.txt");

        // Verificar que los archivos existen antes de proceder
        assertTrue("El archivo no existe.", entrada.exists());
        assertTrue("El archivo no existe.", salidaEsperada.exists());

        // Crear un objeto ContadorDeLetras y obtener las frecuencias
        ContadorDeLetras contador = new ContadorDeLetras(entrada);
        int[] frecuenciasGeneradas = contador.frecuencias();

        // Leer la salida esperada y convertirla en un array de enteros
        List<String> lineas = Files.readAllLines(Paths.get(salidaEsperada.getPath()));
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

        // Comparar ambos arrays de frecuencias
        assertArrayEquals("Las frecuencias no coinciden.", frecuenciasEsperadas, frecuenciasGeneradasReordenadas);
    }

    @Test
    public void test5() throws IOException {
        // Ruta de los archivos
        File entrada = new File("src/test/res/caracteres_especiales.txt");
        File salidaEsperada = new File("src/test/res/salida-caracteres_especiales.txt");

        // Verificar que los archivos existen antes de proceder
        assertTrue("El archivo no existe.", entrada.exists());
        assertTrue("El archivo no existe.", salidaEsperada.exists());

        // Crear un objeto ContadorDeLetras y obtener las frecuencias
        ContadorDeLetras contador = new ContadorDeLetras(entrada);
        int[] frecuenciasGeneradas = contador.frecuencias();

        // Leer la salida esperada y convertirla en un array de enteros
        List<String> lineas = Files.readAllLines(Paths.get(salidaEsperada.getPath()));
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

        // Comparar ambos arrays de frecuencias
        assertArrayEquals("Las frecuencias no coinciden.", frecuenciasEsperadas, frecuenciasGeneradasReordenadas);
    }


    @Test
    public void test6() throws IOException {
        // Ruta de los archivos
        File entrada = new File("src/test/res/vocales_voladas.txt");
        File salidaEsperada = new File("src/test/res/salida-vocales_voladas.txt");

        // Verificar que los archivos existen antes de proceder
        assertTrue("El archivo no existe.", entrada.exists());
        assertTrue("El archivo no existe.", salidaEsperada.exists());

        // Crear un objeto ContadorDeLetras y obtener las frecuencias
        ContadorDeLetras contador = new ContadorDeLetras(entrada);
        int[] frecuenciasGeneradas = contador.frecuencias();

        // Leer la salida esperada y convertirla en un array de enteros
        List<String> lineas = Files.readAllLines(Paths.get(salidaEsperada.getPath()));
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

        // Comparar ambos arrays de frecuencias
        assertArrayEquals("Las frecuencias no coinciden.", frecuenciasEsperadas, frecuenciasGeneradasReordenadas);
    }

    @Test
    public void test7() throws IOException {
        // Ruta de los archivos
        File entrada = new File("src/test/res/digitos.txt");
        File salidaEsperada = new File("src/test/res/salida-digitos.txt");

        // Verificar que los archivos existen antes de proceder
        assertTrue("El archivo no existe.", entrada.exists());
        assertTrue("El archivo no existe.", salidaEsperada.exists());

        // Crear un objeto ContadorDeLetras y obtener las frecuencias
        ContadorDeLetras contador = new ContadorDeLetras(entrada);
        int[] frecuenciasGeneradas = contador.frecuencias();

        // Leer la salida esperada y convertirla en un array de enteros
        List<String> lineas = Files.readAllLines(Paths.get(salidaEsperada.getPath()));
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

        // Comparar ambos arrays de frecuencias
        assertArrayEquals("Las frecuencias no coinciden.", frecuenciasEsperadas, frecuenciasGeneradasReordenadas);
    }

    @Test
    public void test8() throws IOException {
        // Ruta de los archivos
        File entrada = new File("src/test/res/vacio.txt");
        File salidaEsperada = new File("src/test/res/salida-vacio.txt");

        // Verificar que los archivos existen antes de proceder
        assertTrue("El archivo no existe.", entrada.exists());
        assertTrue("El archivo no existe.", salidaEsperada.exists());

        // Crear un objeto ContadorDeLetras y obtener las frecuencias
        ContadorDeLetras contador = new ContadorDeLetras(entrada);
        int[] frecuenciasGeneradas = contador.frecuencias();

        // Leer la salida esperada y convertirla en un array de enteros
        List<String> lineas = Files.readAllLines(Paths.get(salidaEsperada.getPath()));
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

        // Comparar ambos arrays de frecuencias
        assertArrayEquals("Las frecuencias no coinciden.", frecuenciasEsperadas, frecuenciasGeneradasReordenadas);
    }


    @Test
    public void test9() throws IOException {
        // Ruta de los archivos
        File entrada = new File("src/main/res/quijote.txt");
        File salidaEsperada = new File("src/test/res/salida-quijote.txt");

        // Verificar que los archivos existen antes de proceder
        assertTrue("El archivo quijote.txt no existe.", entrada.exists());
        assertTrue("El archivo salida-quijote.txt no existe.", salidaEsperada.exists());

        // Crear un objeto ContadorDeLetras y obtener las frecuencias
        ContadorDeLetras contador = new ContadorDeLetras(entrada);
        int[] frecuenciasGeneradas = contador.frecuencias();

        // Leer la salida esperada y convertirla en un array de enteros
        List<String> lineas = Files.readAllLines(Paths.get(salidaEsperada.getPath()));
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

        // Comparar ambos arrays de frecuencias
        assertArrayEquals("Las frecuencias no coinciden.", frecuenciasEsperadas, frecuenciasGeneradasReordenadas);
    }

    @Test
    public void test10() throws IOException {
        // Ruta de los archivos
        File entrada = new File("src/main/res/hamlet.txt");
        File salidaEsperada = new File("src/test/res/salida-hamlet.txt");

        // Verificar que los archivos existen antes de proceder
        assertTrue("El archivo hamlet.txt no existe.", entrada.exists());
        assertTrue("El archivo salida-hamlet.txt no existe.", salidaEsperada.exists());

        // Crear un objeto ContadorDeLetras y obtener las frecuencias
        ContadorDeLetras contador = new ContadorDeLetras(entrada);
        int[] frecuenciasGeneradas = contador.frecuencias();

        // Leer la salida esperada y convertirla en un array de enteros
        List<String> lineas = Files.readAllLines(Paths.get(salidaEsperada.getPath()));
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

        // Comparar ambos arrays de frecuencias
        assertArrayEquals("Las frecuencias no coinciden.", frecuenciasEsperadas, frecuenciasGeneradasReordenadas);
    }

    @Test
    public void test11() throws IOException {
        // Ruta de los archivos
        File entrada = new File("src/main/res/regenta.txt");
        File salidaEsperada = new File("src/test/res/salida-regenta.txt");

        // Verificar que los archivos existen antes de proceder
        assertTrue("El archivo regenta.txt no existe.", entrada.exists());
        assertTrue("El archivo salida-regenta.txt no existe.", salidaEsperada.exists());

        // Crear un objeto ContadorDeLetras y obtener las frecuencias
        ContadorDeLetras contador = new ContadorDeLetras(entrada);
        int[] frecuenciasGeneradas = contador.frecuencias();

        // Leer la salida esperada y convertirla en un array de enteros
        List<String> lineas = Files.readAllLines(Paths.get(salidaEsperada.getPath()));
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

        // Comparar ambos arrays de frecuencias
        assertArrayEquals("Las frecuencias no coinciden.", frecuenciasEsperadas, frecuenciasGeneradasReordenadas);
    }
}
