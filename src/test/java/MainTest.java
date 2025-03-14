/*
Carlos Alejos Fumanal (872342)
Mario Caudevilla Ruiz (870421)
Marcos Galán Carrillo (874095)
*/

import es.unizar.eina.vv6f.practica3.Main;

import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


public class MainTest {
    private static final String RUTA_ENTRADA = "src/test/res/";
    private static final String RUTA_ENTRADA_TXT ="src/main/res/";
    private static final String RUTA_RESULTADO = "src/test/res/resultado/";

    private static InputStream sistemaInOriginal;
    private static PrintStream sistemaOutOriginal;
    private ByteArrayOutputStream salidaCapturada;

    @BeforeAll
    static void guardarReferenciaEntradaSalida() {
        // Guardamos la referencia original de System.in y System.out
        sistemaInOriginal = System.in;
        sistemaOutOriginal = System.out;
    }

    @BeforeEach
    void redirigirEntradaSalida() {
        salidaCapturada = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(salidaCapturada);
        System.setOut(printStream);
        System.setErr(printStream); // Redirigir también System.err
    }

    @AfterEach
    void restaurarSalida() {
        // Restaurar System.out después de cada prueba
        System.setOut(sistemaOutOriginal);
    }

    @AfterAll
    static void restaurarEntradaSalidaOriginal() {
        // Restaurar System.in y System.out después de todas las pruebas
        System.setIn(sistemaInOriginal);
        System.setOut(sistemaOutOriginal);
    }

    @Test
    public void testMainMinusculasBasico() throws IOException {
        // Comparamos la salida generada con la esperada
        compararSalidaConEsperada(RUTA_ENTRADA + "minusculas_basico.txt\n", "salida-minusculas_basico.txt");
    }

    @Test
    public void testMainMinusculasConTodo() throws IOException {
        // Comparamos la salida generada con la esperada
        compararSalidaConEsperada(RUTA_ENTRADA + "minusculas_con_todo.txt\n", "salida-minusculas_con_todo.txt");
    }

    @Test
    public void testMainMayusculasBasico() throws IOException {
        // Comparamos la salida generada con la esperada
        compararSalidaConEsperada(RUTA_ENTRADA + "mayusculas_basico.txt\n", "salida-mayusculas_basico.txt");
    }

    @Test
    public void testMainMayusculasConTodo() throws IOException {
        // Comparamos la salida generada con la esperada
        compararSalidaConEsperada(RUTA_ENTRADA + "mayusculas_con_todo.txt\n", "salida-mayusculas_con_todo.txt");
    }

    @Test
    public void testMainCaracteresEspeciales() throws IOException {
        // Comparamos la salida generada con la esperada
        compararSalidaConEsperada(RUTA_ENTRADA + "caracteres_especiales.txt\n", "salida-caracteres_especiales.txt");
    }

    @Test
    public void testMainVocalesVoladas() throws IOException {
        // Comparamos la salida generada con la esperada
        compararSalidaConEsperada( RUTA_ENTRADA + "vocales_voladas.txt\n", "salida-vocales_voladas.txt");
    }

    @Test
    public void testMainDigitos() throws IOException {
        // Comparamos la salida generada con la esperada
        compararSalidaConEsperada(RUTA_ENTRADA + "digitos.txt\n", "salida-digitos.txt");
    }

    @Test
    public void testMainVacio() throws IOException {
        // Comparamos la salida generada con la esperada
        compararSalidaConEsperada(RUTA_ENTRADA + "vacio.txt\n", "salida-vacio.txt");
    }

    @Test
    public void testMainQuijote() throws IOException {
        // Comparamos la salida generada con la esperada
        compararSalidaConEsperada(RUTA_ENTRADA_TXT + "quijote.txt\n", "salida-quijote.txt");
    }

    @Test
    public void testMainHamlet() throws IOException {
        // Comparamos la salida generada con la esperada
        compararSalidaConEsperada(RUTA_ENTRADA_TXT + "hamlet.txt\n","salida-hamlet.txt");
    }

    @Test
    public void testMainRegenta() throws IOException {
        // Comparamos la salida generada con la esperada
        compararSalidaConEsperada(RUTA_ENTRADA_TXT + "regenta.txt\n", "salida-regenta.txt");
    }


    private void compararSalidaConEsperada(String nombreEntrada, String nombreSalidaEsperada) throws IOException {

        // Simulamos la entrada de usuario con el nombre del archivo de prueba
        System.setIn(new ByteArrayInputStream(nombreEntrada.getBytes()));

        // Ejecutamos el método main
        Main.main(new String[]{});

        // Verificar que `salidaCapturada` no sea null antes de usarla
        assertNotNull(salidaCapturada, "Error: `salidaCapturada` es null.");

        // Guardar salida generada
        String resultadoGenerado = salidaCapturada.toString();

        // Asegurar que la frase inicial está presente y seguida de un salto de línea
        if (!resultadoGenerado.startsWith("Nombre de un fichero de texto:\n")) {
            resultadoGenerado = "Nombre de un fichero de texto:\n" + resultadoGenerado.replaceFirst("Nombre de un fichero de texto:", "").trim();
        }

        // Guardar la salida corregida en un archivo temporal
        Files.write(Paths.get(RUTA_RESULTADO + "resultado-temporal.txt"), resultadoGenerado.getBytes());

        // Leer la salida esperada desde el archivo
        List<String> lineasEsperadas = Files.readAllLines(Paths.get(RUTA_ENTRADA + nombreSalidaEsperada));
        List<String> lineasGeneradas = Files.readAllLines(Paths.get(RUTA_RESULTADO + "resultado-temporal.txt"));

        // Comparar las líneas de ambos archivos
        assertIterableEquals(lineasEsperadas, lineasGeneradas, "La salida del programa no coincide con la esperada.");
    }
}
