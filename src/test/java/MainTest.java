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
        System.setErr(printStream); // 🔹 Redirigir también System.err
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
        //System.err.println(">>> Ejecutando testMainMinusculasBasico()");

        // Simulamos la entrada de usuario con el nombre del archivo de prueba
        String entradaUsuario = RUTA_ENTRADA + "minusculas_basico.txt\n";
        System.setIn(new ByteArrayInputStream(entradaUsuario.getBytes()));

        // Ejecutamos el método main
        Main.main(new String[]{});

        // Comparamos la salida generada con la esperada
        compararSalidaConEsperada("salida-minusculas_basico.txt");
    }


    private void compararSalidaConEsperada(String nombreSalidaEsperada) throws IOException {
        // 🔹 **Verificar que `salidaCapturada` no sea null antes de usarla**
        assertNotNull(salidaCapturada, "Error: `salidaCapturada` es null.");


        // Guardar salida generada en un archivo temporal
        String resultadoGenerado = salidaCapturada.toString();
        Files.write(Paths.get(RUTA_RESULTADO + "resultado-temporal.txt"), resultadoGenerado.getBytes());

        // Leer la salida esperada desde el archivo
        List<String> lineasEsperadas = Files.readAllLines(Paths.get(RUTA_ENTRADA + nombreSalidaEsperada));
        List<String> lineasGeneradas = Files.readAllLines(Paths.get(RUTA_RESULTADO + "resultado-temporal.txt"));

        // Comparar las líneas de ambos archivos
        assertIterableEquals(lineasEsperadas, lineasGeneradas, "La salida del programa no coincide con la esperada.");
    }
}
