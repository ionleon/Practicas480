import java.util.ArrayList;

public class CarritoCompra {

    private static ArrayList<Producto> carrito = new ArrayList<>();

    public static void anadirProducto(Producto nuevoProducto){

        if (nuevoProducto == null) {
            Consola.mostrarMensaje("No se ha añadido el producto: es nulo.");
            return;
        }

        carrito.add(nuevoProducto);
        Consola.mostrarMensaje("Producto añadido al carrito correctamente.");

    }

    public static String imprimirProductos() {
        if (carrito.isEmpty()) {
            return "El carrito está vacío.";
        }

        StringBuilder sb = new StringBuilder("Contenido del carrito:\n");

        int contador = 1;
        for (Producto producto : carrito) {
            sb.append(contador++)
                    .append(". ")
                    .append(producto.toString())
                    .append("\n");
        }

        return sb.toString();
    }

    public static int calcularCaloriasTotales() {
        int caloriasTotales = 0;

        for (Producto producto : carrito) {
            // Verificamos si el producto es un alimento
            if (producto instanceof IAlimento alimento) {
                // Si lo es, sumamos sus calorías
                caloriasTotales += alimento.getCalorias();
            }
        }
        return caloriasTotales;
    }
}
