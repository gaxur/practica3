import es.unizar.eina.vv6f.practica3.ContadorDeLetras;
import static jdk.jpackage.internal.WixAppImageFragmentBuilder.Component.File;
import static org.junit.Assert.*;
import org.junit.Test;

public class ContadorDeLetrasTest {

    @Test
    public void test1() {
        // Ruta de los archivos
        File entrada = new File("src/res/quijote.txt");
        File salidaEsperada = new File("src/test/res/salida-quijote.txt");

        // Verificar que los archivos existen antes de proceder
        assertTrue(entrada.exists(), "El archivo quijote.txt no existe.");
        assertTrue(salidaEsperada.exists(), "El archivo salida-quijote.txt no existe.");

        // Crear un objeto ContadorDeLetras y obtener las frecuencias
        ContadorDeLetras contador = new ContadorDeLetras(entrada);
        int[] frecuenciasGeneradas = contador.frecuencias();

        // Leer la salida esperada y convertirla en un array de enteros
        List<String> lineas = Files.readAllLines(Paths.get(salidaEsperada.getPath()));
        int[] frecuenciasEsperadas = new int[27];

        // Procesar cada línea de salida-esperada.txt para extraer las frecuencias
        int index = 0;
        for (String linea : lineas) {
            if (linea.matches("[A-ZÑ]:\\s+\\d+")) { // Formato esperado "A: 12345"
                frecuenciasEsperadas[index] = Integer.parseInt(linea.split(":")[1].trim());
                index++;
            }
        }

        // Comparar ambos arrays de frecuencias
        assertArrayEquals(frecuenciasEsperadas, frecuenciasGeneradas, "Las frecuencias no coinciden.");
    }

    @Test
    public void test2() {
        File archivo = new File("src/test/res/minusculas_con_todo.txt");
        ContadorDeLetras contador = new ContadorDeLetras(archivo);
        assertEquals(TipoTriangulo.EQUILATERO,
        triangulo.tipo());
    }

    @Test
    public void test3() {
        File archivo = new File("src/test/res/mayusculas_basico.txt");
        ContadorDeLetras contador = new ContadorDeLetras(archivo);
        assertEquals(TipoTriangulo.EQUILATERO,
        triangulo.tipo());
    }

    @Test
    public void test4() {
        File archivo = new File("src/test/res/mayusculas_con_todo.txt");
        ContadorDeLetras contador = new ContadorDeLetras(archivo);
        assertEquals(TipoTriangulo.EQUILATERO,
        triangulo.tipo());
    }

    @Test
    public void tes5() {
        File archivo = new File("src/test/res/caracteres_especiales.txt");
        ContadorDeLetras contador = new ContadorDeLetras(archivo);
        assertEquals(TipoTriangulo.EQUILATERO,
        triangulo.tipo());
    }

    @Test
    public void test6() {
        File archivo = new File("src/test/res/vocales_voladas.txt");
        ContadorDeLetras contador = new ContadorDeLetras(archivo);
        assertEquals(TipoTriangulo.EQUILATERO,
        triangulo.tipo());
    }

    @Test
    public void test7() {
        File archivo = new File("src/test/res/digitos.txt");
        ContadorDeLetras contador = new ContadorDeLetras(archivo);
        assertEquals(TipoTriangulo.EQUILATERO,
        triangulo.tipo());
    }

    @Test
    public void test8() {
        File archivo = new File("src/test/res/vacio.txt");
        ContadorDeLetras contador = new ContadorDeLetras(archivo);
        assertEquals(TipoTriangulo.EQUILATERO,
        triangulo.tipo());
    }

    @Test
    public void test9() {
        File archivo = new File("src/main/res/quijote.txt");
        ContadorDeLetras contador = new ContadorDeLetras(archivo);
        assertEquals(TipoTriangulo.EQUILATERO,
        triangulo.tipo());
    }

    @Test
    public void test10() {
        File archivo = new File("src/main/res/hamlet.txt");
        ContadorDeLetras contador = new ContadorDeLetras(archivo);
        assertEquals(TipoTriangulo.EQUILATERO,
        triangulo.tipo());
    }

    @Test
    public void test10() {
        File archivo = new File("src/main/res/regenta.txt");
        ContadorDeLetras contador = new ContadorDeLetras(archivo);
        assertEquals(TipoTriangulo.EQUILATERO,
        triangulo.tipo());
    }

    @Test
    public void test11() {
        File archivo = new File("src/main/res/prueba");
        ContadorDeLetras contador = new ContadorDeLetras(archivo);
        assertEquals(TipoTriangulo.EQUILATERO,
        triangulo.tipo());
    }

    @Test
    public void test12() {
        File archivo = null;
        ContadorDeLetras contador = new ContadorDeLetras(archivo);
        assertEquals(TipoTriangulo.EQUILATERO,
        triangulo.tipo());
    }
}
