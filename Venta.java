import java.time.LocalDate;
import java.util.ArrayList;

public class Venta {
    private static int contador = 1;

    private String idVenta;                // p.ej. V-1, V-2...
    private Cliente cliente;
    private ArrayList<JuegoRol> lineasDeVenta; // Cada línea: un juego (cantidad 1). Puedes añadir el mismo juego varias veces.
    private LocalDate fecha;

    public Venta(Cliente cliente) {
        this.idVenta = "V-" + (contador++);
        this.cliente = cliente;
        this.lineasDeVenta = new ArrayList<>();
        this.fecha = LocalDate.now();
    }

    public String getIdVenta() {
        return idVenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void addJuego(JuegoRol juego) {
        lineasDeVenta.add(juego);
    }

    public ArrayList<JuegoRol> getLineasDeVenta() {
        return lineasDeVenta;
    }

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
