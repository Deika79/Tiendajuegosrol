import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class ClienteTest {

    @Test
    void clientesConMismoDniSonIguales() {
        Cliente a = new Cliente("Ana", "12345678A", "600000000", "a@a.com");
        Cliente b = new Cliente("Otro Nombre", "12345678A", "699999999", "b@b.com");
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void gettersSettersBasicos() {
        Cliente c = new Cliente("Luis", "87654321B", "600111222", "l@l.com");
        c.setEmail("nuevo@mail.com");
        assertEquals("nuevo@mail.com", c.getEmail());
    }
}
