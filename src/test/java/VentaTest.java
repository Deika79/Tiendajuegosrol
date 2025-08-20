import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class VentaTest {

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente("Eva", "11111111C", "611111111", "e@e.com");
    }

    @Test
    void calcularTotalSumaPrecios() {
        Venta v = new Venta(cliente);
        v.addJuego(new JuegoRol("Manual", "D&D 5e", 40.00, 2));
        v.addJuego(new JuegoRol("Aventura", "D&D 5e", 15.50, 1));
        assertEquals(55.50, v.calcularTotal(), 0.0001);
    }

    @Test
    void alAñadirLineasAumentaTamaño() {
        Venta v = new Venta(cliente);
        assertEquals(0, v.getLineasDeVenta().size());
        v.addJuego(new JuegoRol("Cthulhu", "Chaosium", 30.0, 1));
        assertEquals(1, v.getLineasDeVenta().size());
    }
}
