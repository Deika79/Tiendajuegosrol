import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Representa una venta: cliente + una o más líneas con juegos de rol.
 * Calcula el total y guarda la fecha de la operación.
 */
public class Venta {
    private static int contador = 1;

    private String idVenta;
    private Cliente cliente;
    private ArrayList<JuegoRol> lineasDeVenta;
    private LocalDate fecha;

    /**
     * Crea una venta asociada a un cliente.
     * @param cliente cliente que realiza la compra
     */
    public Venta(Cliente cliente) {
        this.idVenta = "V-" + (contador++);
        this.cliente = cliente;
        this.lineasDeVenta = new ArrayList<>();
        this.fecha = LocalDate.now();
    }

    /** @return identificador de la venta (p.ej. V-1) */
    public String getIdVenta() { return idVenta; }

    /** @return cliente asociado */
    public Cliente getCliente() { return cliente; }

    /** @return fecha de la venta */
    public LocalDate getFecha() { return fecha; }

    /**
     * Añade un juego a la venta (1 unidad).
     * @param juego juego a añadir
     */
    public void addJuego(JuegoRol juego) { lineasDeVenta.add(juego); }

    /** @return líneas de venta (lista de juegos) */
    public ArrayList<JuegoRol> getLineasDeVenta() { return lineasDeVenta; }

    /**
     * Calcula el importe total sumando los precios de las líneas.
     * @return total en euros
     */
    public double calcularTotal() {
        double total = 0;
        for (JuegoRol j : lineasDeVenta) total += j.getPrecio();
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Venta " + idVenta + " | Fecha: " + fecha + " | Cliente: " + cliente.getNombre() + " (" + cliente.getDni() + ")\n");
        for (int i = 0; i < lineasDeVenta.size(); i++) {
            JuegoRol j = lineasDeVenta.get(i);
            sb.append("   ").append(i + 1).append(". ").append(j.getTitulo()).append(" [")
                    .append(j.getSistema()).append("] - ")
                    .append(String.format("%.2f", j.getPrecio())).append("€\n");
        }
        sb.append("   Total: ").append(String.format("%.2f", calcularTotal())).append("€");
        return sb.toString();
    }
}
