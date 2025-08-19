public class JuegoRol {
    private String titulo;     // p.ej. "Manual del Jugador"
    private String sistema;    // p.ej. "D&D 5e", "Pathfinder", "Cthulhu"
    private double precio;
    private int stock;         // cantidad de unidades disponibles

    public JuegoRol(String titulo, String sistema, double precio, int stock) {
        this.titulo = titulo;
        this.sistema = sistema; 
        this.precio = precio;
        this.stock = stock;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getSistema() {
        return sistema;
    }
    public void setSistema(String sistema) {
        this.sistema = sistema;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "JuegoRol{titulo='" + titulo + "', sistema='" + sistema + "', precio=" + String.format("%.2f", precio) +
               "€, stock=" + stock + "}";
    }
}
