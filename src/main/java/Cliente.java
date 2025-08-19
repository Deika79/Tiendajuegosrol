import java.util.Objects;

/**
 * Representa un cliente de la tienda.
 * @author David García
 * @version 1.0
 */
public class Cliente {
    private String nombre;
    private String dni;
    private String telefono;
    private String email;

    /**
     * Crea un cliente.
     * @param nombre nombre completo
     * @param dni DNI único
     * @param telefono teléfono de contacto
     * @param email email de contacto
     */
    public Cliente(String nombre, String dni, String telefono, String email) {
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.email = email;
    }

    /** @return nombre del cliente */
    public String getNombre() { return nombre; }
    /** @param nombre nuevo nombre */
    public void setNombre(String nombre) { this.nombre = nombre; }

    /** @return DNI del cliente */
    public String getDni() { return dni; }
    /** @param dni nuevo DNI */
    public void setDni(String dni) { this.dni = dni; }

    /** @return teléfono del cliente */
    public String getTelefono() { return telefono; }
    /** @param telefono nuevo teléfono */
    public void setTelefono(String telefono) { this.telefono = telefono; }

    /** @return email del cliente */
    public String getEmail() { return email; }
    /** @param email nuevo email */
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "Cliente{nombre='" + nombre + "', dni='" + dni + "', tel='" + telefono + "', email='" + email + "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente)) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(dni, cliente.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }
}
