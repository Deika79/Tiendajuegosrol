import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

public class App {

    // Colecciones principales
    private static final ArrayList<Cliente> clientes = new ArrayList<>();
    private static final ArrayList<JuegoRol> catalogo = new ArrayList<>();
    private static final ArrayList<Venta> ventas = new ArrayList<>();

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        cargarDatosDeEjemplo(); // opcional: para probar más rápido

        int opcion;
        do {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Gestión de clientes");
            System.out.println("2. Gestión de juegos de rol (catálogo)");
            System.out.println("3. Realizar venta");
            System.out.println("4. Mostrar ventas");
            System.out.println("5. Salir");
            opcion = leerEnteroEnRango("Elige una opción: ", 1, 5);

            switch (opcion) {
                case 1: menuClientes(); break;
                case 2: menuJuegos(); break;
                case 3: crearNuevaVenta(); break;
                case 4: menuVentas(); break;
                case 5: System.out.println("¡Hasta luego!"); break;
            }
        } while (opcion != 5);

        sc.close();
    }

    // ===== MENÚS =====

    private static void menuClientes() {
        int op;
        do {
            System.out.println("\n--- GESTIÓN DE CLIENTES ---");
            System.out.println("1. Alta de cliente");
            System.out.println("2. Baja de cliente");
            System.out.println("3. Modificación de cliente");
            System.out.println("4. Búsqueda por DNI");
            System.out.println("5. Listado de clientes");
            System.out.println("0. Volver");
            op = leerEnteroEnRango("Opción: ", 0, 5);

            switch (op) {
                case 1: altaCliente(); break;
                case 2: bajaCliente(); break;
                case 3: modificarCliente(); break;
                case 4: buscarClientePorDni(); break;
                case 5: listarClientes(); break;
            }
        } while (op != 0);
    }

    private static void menuJuegos() {
        int op;
        do {
            System.out.println("\n--- GESTIÓN DE JUEGOS DE ROL ---");
            System.out.println("1. Alta de juego");
            System.out.println("2. Listado del catálogo");
            System.out.println("3. Búsqueda por título");
            System.out.println("4. Ordenar catálogo (por título / sistema / precio)");
            System.out.println("0. Volver");
            op = leerEnteroEnRango("Opción: ", 0, 4);

            switch (op) {
                case 1: altaJuego(); break;
                case 2: listarCatalogo(); break;
                case 3: buscarJuegoPorTitulo(); break;
                case 4: ordenarCatalogo(); break;
            }
        } while (op != 0);
    }

    private static void menuVentas() {
        int op;
        do {
            System.out.println("\n--- VENTAS ---");
            System.out.println("1. Mostrar TODAS las ventas");
            System.out.println("2. Mostrar ventas por cliente (DNI)");
            System.out.println("3. Mostrar IMPORTE TOTAL por cliente (EXTRA)");
            System.out.println("0. Volver");
            op = leerEnteroEnRango("Opción: ", 0, 3);

            switch (op) {
                case 1: mostrarVentas(); break;
                case 2: mostrarVentasPorCliente(); break;
                case 3: mostrarTotalPorCliente(); break;
            }
        } while (op != 0);
    }

    // ===== CLIENTES =====

    private static void altaCliente() {
        System.out.println("\n[Alta de cliente]");
        String dni = leerDniUnico("DNI (único): ");
        String nombre = leerNoVacio("Nombre: ");
        String telefono = leerTelefono("Teléfono: ");
        String email = leerEmail("Email: ");
        clientes.add(new Cliente(nombre, dni, telefono, email));
        System.out.println("Cliente dado de alta correctamente.");
    }

    private static void bajaCliente() {
        System.out.println("\n[Baja de cliente]");
        String dni = leerNoVacio("DNI del cliente a eliminar: ");
        Cliente c = buscarCliente(dni);
        if (c == null) {
            System.out.println("No existe un cliente con ese DNI.");
        } else {
            clientes.remove(c);
            System.out.println("Cliente eliminado.");
        }
    }

    private static void modificarCliente() {
        System.out.println("\n[Modificar cliente]");
        String dni = leerNoVacio("DNI del cliente a modificar: ");
        Cliente c = buscarCliente(dni);
        if (c == null) {
            System.out.println("No existe un cliente con ese DNI.");
            return;
        }
        System.out.println("Dejar en blanco para mantener el valor actual.");
        String nuevoNombre = leerPosibleVacio("Nuevo nombre (" + c.getNombre() + "): ");
        String nuevoTel = leerPosibleVacio("Nuevo teléfono (" + c.getTelefono() + "): ");
        String nuevoEmail = leerPosibleVacio("Nuevo email (" + c.getEmail() + "): ");

        if (!nuevoNombre.isEmpty()) c.setNombre(nuevoNombre);
        if (!nuevoTel.isEmpty() && validarTelefono(nuevoTel)) c.setTelefono(nuevoTel);
        if (!nuevoEmail.isEmpty() && validarEmail(nuevoEmail)) c.setEmail(nuevoEmail);

        System.out.println("Cliente actualizado: " + c);
    }

    private static void buscarClientePorDni() {
        System.out.println("\n[Buscar cliente por DNI]");
        String dni = leerNoVacio("DNI: ");
        Cliente c = buscarCliente(dni);
        System.out.println(c == null ? "No encontrado." : c.toString());
    }

    private static void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes.");
            return;
        }
        System.out.println("\nListado de clientes (" + clientes.size() + "):");
        // Orden por nombre para claridad
        ArrayList<Cliente> copia = new ArrayList<>(clientes);
        copia.sort(Comparator.comparing(Cliente::getNombre, String.CASE_INSENSITIVE_ORDER));
        for (Cliente c : copia) System.out.println(" - " + c);
    }

    private static Cliente buscarCliente(String dni) {
        for (Cliente c : clientes) if (c.getDni().equalsIgnoreCase(dni)) return c;
        return null;
    }

    // ===== JUEGOS =====

    private static void altaJuego() {
        System.out.println("\n[Alta de juego de rol]");
        String titulo = leerNoVacio("Título: ");
        String sistema = leerNoVacio("Sistema/editorial: ");
        double precio = leerDoublePositivo("Precio (€): ");
        int stock = leerEnteroMin("Stock inicial (>=0): ", 0);
        catalogo.add(new JuegoRol(titulo, sistema, precio, stock));
        System.out.println("Juego añadido al catálogo.");
    }

    private static void listarCatalogo() {
        if (catalogo.isEmpty()) {
            System.out.println("Catálogo vacío.");
            return;
        }
        System.out.println("\nCatálogo de juegos (" + catalogo.size() + "):");
        for (int i = 0; i < catalogo.size(); i++) {
            System.out.println((i + 1) + ". " + catalogo.get(i));
        }
    }

    private static void buscarJuegoPorTitulo() {
        System.out.println("\n[Buscar juego por título]");
        String patron = leerNoVacio("Introduce parte del título: ").toLowerCase();
        int encontrados = 0;
        for (JuegoRol j : catalogo) {
            if (j.getTitulo().toLowerCase().contains(patron)) {
                System.out.println(" - " + j);
                encontrados++;
            }
        }
        if (encontrados == 0) System.out.println("No se encontraron juegos con ese patrón.");
    }

    private static void ordenarCatalogo() {
        if (catalogo.isEmpty()) {
            System.out.println("Catálogo vacío.");
            return;
        }
        System.out.println("\nOrdenar por:");
        System.out.println("1. Título");
        System.out.println("2. Sistema");
        System.out.println("3. Precio ascendente");
        System.out.println("4. Precio descendente");
        int op = leerEnteroEnRango("Opción: ", 1, 4);
        switch (op) {
            case 1:
                catalogo.sort(Comparator.comparing(JuegoRol::getTitulo, String.CASE_INSENSITIVE_ORDER));
                break;
            case 2:
                catalogo.sort(Comparator.comparing(JuegoRol::getSistema, String.CASE_INSENSITIVE_ORDER));
                break;
            case 3:
                catalogo.sort(Comparator.comparingDouble(JuegoRol::getPrecio));
                break;
            case 4:
                catalogo.sort(Comparator.comparingDouble(JuegoRol::getPrecio).reversed());
                break;
        }
        System.out.println("Catálogo ordenado.");
        listarCatalogo();
    }

    // ===== VENTAS =====

    private static void crearNuevaVenta() {
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes. Da de alta al menos uno antes de vender.");
            return;
        }
        if (catalogo.isEmpty()) {
            System.out.println("Catálogo vacío. Da de alta juegos antes de vender.");
            return;
        }
        String dni = leerNoVacio("DNI del cliente: ");
        Cliente c = buscarCliente(dni);
        if (c == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        Venta v = new Venta(c);
        boolean seguir;
        do {
            // Mostrar sólo con stock
            ArrayList<Integer> idxDisponibles = new ArrayList<>();
            System.out.println("\n--- Selección de juegos (stock > 0) ---");
            for (int i = 0; i < catalogo.size(); i++) {
                if (catalogo.get(i).getStock() > 0) {
                    idxDisponibles.add(i);
                    System.out.println((idxDisponibles.size()) + ". " + catalogo.get(i));
                }
            }
            if (idxDisponibles.isEmpty()) {
                System.out.println("No hay stock disponible. No se puede completar la venta.");
                return;
            }
            int eleccion = leerEnteroEnRango("Elige juego por número de la lista: ", 1, idxDisponibles.size());
            int idxReal = idxDisponibles.get(eleccion - 1);
            JuegoRol elegido = catalogo.get(idxReal);

            // Añadimos 1 unidad (si quieres más, repites selección)
            v.addJuego(elegido);
            elegido.setStock(elegido.getStock() - 1); // EXTRA: descontar stock
            System.out.println("Añadido: " + elegido.getTitulo() + ". Stock restante: " + elegido.getStock());

            seguir = leerSiNo("¿Añadir otro juego? (s/n): ");
        } while (seguir);

        ventas.add(v);
        System.out.println("\n=== TICKET DE VENTA ===");
        System.out.println(v);
    }

    private static void mostrarVentas() {
        if (ventas.isEmpty()) {
            System.out.println("No hay ventas registradas.");
            return;
        }
        System.out.println("\nListado de ventas (" + ventas.size() + "):");
        for (Venta v : ventas) {
            System.out.println(v);
            System.out.println("------------------------");
        }
    }

    private static void mostrarVentasPorCliente() {
        if (ventas.isEmpty()) {
            System.out.println("No hay ventas registradas.");
            return;
        }
        String dni = leerNoVacio("DNI del cliente: ");
        int contador = 0;
        for (Venta v : ventas) {
            if (v.getCliente().getDni().equalsIgnoreCase(dni)) {
                System.out.println(v);
                System.out.println("------------------------");
                contador++;
            }
        }
        if (contador == 0) System.out.println("Ese cliente no tiene ventas.");
    }

    private static void mostrarTotalPorCliente() {
        if (ventas.isEmpty()) {
            System.out.println("No hay ventas registradas.");
            return;
        }
        HashMap<String, Double> totales = new HashMap<>();
        for (Venta v : ventas) {
            String dni = v.getCliente().getDni();
            totales.put(dni, totales.getOrDefault(dni, 0.0) + v.calcularTotal());
        }
        System.out.println("\nImporte total acumulado por cliente:");
        // Ordenado por mayor gasto
        ArrayList<String> dnis = new ArrayList<>(totales.keySet());
        Collections.sort(dnis, (a,b) -> Double.compare(totales.get(b), totales.get(a)));
        for (String dni : dnis) {
            Cliente c = buscarCliente(dni);
            String nombre = (c != null) ? c.getNombre() : "(Desconocido)";
            System.out.println(" - " + nombre + " (" + dni + "): " + String.format("%.2f", totales.get(dni)) + "€");
        }
    }

    // ===== UTILIDADES / VALIDACIONES =====

    private static String leerNoVacio(String prompt) {
        String s;
        do {
            System.out.print(prompt);
            s = sc.nextLine().trim();
            if (s.isEmpty()) System.out.println("No puede estar vacío.");
        } while (s.isEmpty());
        return s;
    }

    private static String leerPosibleVacio(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }

    private static int leerEnteroEnRango(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            try {
                int val = Integer.parseInt(input);
                if (val < min || val > max) {
                    System.out.println("Debe estar entre " + min + " y " + max + ".");
                } else {
                    return val;
                }
            } catch (NumberFormatException e) {
                System.out.println("Introduce un número entero válido.");
            }
        }
    }

    private static int leerEnteroMin(String prompt, int min) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            try {
                int val = Integer.parseInt(input);
                if (val < min) {
                    System.out.println("Debe ser >= " + min + ".");
                } else {
                    return val;
                }
            } catch (NumberFormatException e) {
                System.out.println("Introduce un número entero válido.");
            }
        }
    }

    private static double leerDoublePositivo(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            try {
                double val = Double.parseDouble(input);
                if (val < 0) {
                    System.out.println("Debe ser >= 0.");
                } else {
                    return val;
                }
            } catch (NumberFormatException e) {
                System.out.println("Introduce un número válido (usa punto decimal).");
            }
        }
    }

    private static boolean leerSiNo(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim().toLowerCase();
            if (s.equals("s") || s.equals("si") || s.equals("sí")) return true;
            if (s.equals("n") || s.equals("no")) return false;
            System.out.println("Responde 's' o 'n'.");
        }
    }

    // Validaciones básicas (puedes mejorarlas si quieres)
    private static String leerDniUnico(String prompt) {
        while (true) {
            String dni = leerNoVacio(prompt).toUpperCase();
            if (!validarDniBasico(dni)) {
                System.out.println("Formato de DNI no válido (ej: 12345678A o 1234567B).");
                continue;
            }
            if (buscarCliente(dni) != null) {
                System.out.println("Ya existe un cliente con ese DNI.");
                continue;
            }
            return dni;
        }
    }

    private static boolean validarDniBasico(String dni) {
        return Pattern.matches("[0-9]{7,8}[A-Za-z]", dni);
    }

    private static String leerTelefono(String prompt) {
        while (true) {
            String t = leerNoVacio(prompt);
            if (validarTelefono(t)) return t;
            System.out.println("Teléfono no válido (usa solo dígitos, 9-12 cifras aprox.).");
        }
    }

    private static boolean validarTelefono(String t) {
        return Pattern.matches("[0-9]{9,12}", t);
    }

    private static String leerEmail(String prompt) {
        while (true) {
            String e = leerNoVacio(prompt);
            if (validarEmail(e)) return e;
            System.out.println("Email no válido.");
        }
    }

    private static boolean validarEmail(String e) {
        return Pattern.matches("^[\\w\\.-]+@[\\w\\.-]+\\.[A-Za-z]{2,}$", e);
    }

    // ===== Datos de ejemplo (opcional) =====
    private static void cargarDatosDeEjemplo() {
        clientes.add(new Cliente("Alba Martínez", "12345678A", "612345678", "alba@example.com"));
        clientes.add(new Cliente("Carlos Pérez", "87654321B", "698765432", "carlos@example.com"));

        catalogo.add(new JuegoRol("D&D 5e: Manual del Jugador", "Wizards", 39.95, 5));
        catalogo.add(new JuegoRol("La Llamada de Cthulhu: Reglas Básicas", "Chaosium", 34.90, 3));
        catalogo.add(new JuegoRol("Pathfinder 2e: Core Rulebook", "Paizo", 49.99, 4));
        catalogo.add(new JuegoRol("Vampiro: La Mascarada", "Renegade", 44.95, 2));
    }
}
