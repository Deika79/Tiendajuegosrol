/**
 * Representa un producto de la tienda (juego de rol, manual, etc.). 
 * Incluye precio y stock para control de inventario.
 * @author David García
 * @version 1.0
 */
public class JuegoRol {
    private String titulo;
    private String sistema;
    private double precio;
    private int stock;

    /**
     * Crea un juego de rol.
     * @param titulo título completo (ej. "Manual del Jugador")
     * @param sistema sistema/editorial (ej. "D&D 5e")
     * @param precio precio en euros
     * @param stock unidades disponibles (>= 0)
     */
    public JuegoRol(String titulo, String sistema, double precio, int stock) {
        this.titulo = titulo;
        this.sistema = sistema;
        this.precio = precio;
        this.stock = stock;
    }

    /** @return título del producto */
    public String getTitulo() { return titulo; }
    /** @param titulo nuevo título */
    public void setTitulo(String titulo) { this.titulo = titulo; }

    /** @return sistema/editorial */
    public String getSistema() { return sistema; }
    /** @param sistema nuevo sistema/editorial */
    public void setSistema(String sistema) { this.sistema = sistema; }

    /** @return precio en euros */
    public double getPrecio() { return precio; }
    /** @param precio nuevo precio */
    public void setPrecio(double precio) { this.precio = precio; }

    /** @return stock disponible */
    public int getStock() { return stock; }
    /** @param stock nuevo stock */
    public void setStock(int stock) { this.stock = stock; }

    @Override
    public String toString() {
        return "JuegoRol{titulo='" + titulo + "', sistema='" + sistema + "', precio=" + String.format("%.2f", precio) +
               "€, stock=" + stock + "}";
    }
}
