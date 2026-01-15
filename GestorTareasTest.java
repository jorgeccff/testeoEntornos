import org.junit.jupiter.api.*; // Importa las herramientas de JUnit 5
import static org.junit.jupiter.api.Assertions.*; // Importa las validaciones (assertEquals, assertThrows, etc.)
import java.util.List;

/**
 * Clase de prueba para GestorTareas.
 * El nombre de la clase siempre debe terminar en 'Test' por convención.
 */
class GestorTareasTest {

    private GestorTareas gestor;

    // 1. CONFIGURACIÓN INICIAL (Arrange)
    @BeforeEach
    void setUp() {
        // Esto se ejecuta ANTES de cada test.
        // Así nos aseguramos de que cada prueba empiece con un gestor vacío.
        gestor = new GestorTareas();
    }

    // 2. PRUEBA DE ÉXITO: Agregar tarea
    @Test
    @DisplayName("Debería añadir una tarea a la lista correctamente")
    void testAgregarTarea() {
        // Act (Actuar)
        gestor.agregarTarea("Comprar pan");

        // Assert (Validar)
        List<Tarea> lista = gestor.obtenerTodas();
        assertEquals(1, lista.size(), "La lista debería tener 1 tarea");
        assertEquals("Comprar pan", lista.get(0).getDescripcion());
    }

    // 3. PRUEBA DE LÓGICA: Filtrar pendientes
    @Test
    @DisplayName("Debería devolver solo las tareas que no están completadas")
    void testFiltrarPendientes() {
        // Arrange
        gestor.agregarTarea("Tarea 1");
        gestor.agregarTarea("Tarea 2");

        // Act
        // Marcamos la primera como completada
        gestor.obtenerTodas().get(0).marcarComoCompletada();
        List<Tarea> pendientes = gestor.obtenerPendientes();

        // Assert
        assertEquals(1, pendientes.size(), "Solo debería haber 1 tarea pendiente");
        assertEquals("Tarea 2", pendientes.get(0).getDescripcion());
    }

    // 4. PRUEBA DE ERROR (Edge Case): Descripción vacía
    @Test
    @DisplayName("Debería lanzar error si la descripción está vacía")
    void testErrorDescripcionVacia() {
        // Assert + Act
        // Verificamos que al intentar agregar algo vacío, el sistema "salte" con una
        // excepción
        assertThrows(IllegalArgumentException.class, () -> {
            gestor.agregarTarea("");
        });
    }

    // 5. PRUEBA DE BORRADO
    @Test
    @DisplayName("Debería lanzar error si intentamos borrar una tarea que no existe")
    void testEliminarInexistente() {
        assertThrows(RuntimeException.class, () -> {
            gestor.eliminarTarea("No existo");
        });
    }
}