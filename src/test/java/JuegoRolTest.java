import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class JuegoRolTest {

    @Test
    void actualizarPrecioYStock() {
        JuegoRol j = new JuegoRol("Manual", "D&D 5e", 39.95, 5);
        j.setPrecio(29.99);
        j.setStock(10);
        assertEquals(29.99, j.getPrecio(), 0.0001);
        assertEquals(10, j.getStock());
    }
}
