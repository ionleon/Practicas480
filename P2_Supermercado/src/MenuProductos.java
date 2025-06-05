import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class MenuProductos {

    private static List<Supplier<Producto>> productoEstandar =  new ArrayList<>();
    private static List<ProductoBuilder> productoPeculiar =  new ArrayList<>();

    static {

        productoEstandar.add(() -> new Detergente.Builder().build());
        productoEstandar.add(() -> new Cereales.Builder().build());
        productoEstandar.add(() -> new Vino.Builder().build());

        productoPeculiar.add(new Detergente.Builder());
        productoPeculiar.add(new Cereales.Builder());
        productoPeculiar.add(new Vino.Builder());

    }

    private static Producto  crearCereal(ProductoBuilder builder){

        Producto nuevoProducto = null;

        if (builder instanceof Cereales.Builder cerealBuilder) {
            int subeleccion;
            do {
                Consola.mostrarMensaje("0.- Salir\n1.- Marca\n2.- Precio\n3.- Tipo\n4.- Fecha\n5.- Crear");

                subeleccion = Consola.leerEntero();

                switch(subeleccion) {
                    case 1->{
                        Consola.mostrarMensaje("Introduzca la marca: ");
                        cerealBuilder.marca(Consola.leerMensaje());
                    }

                    case 2 -> {
                        Consola.mostrarMensaje("Introduzca el precio: (Ej. 1.65)");
                        cerealBuilder.precio(Consola.conversionFloat(Consola.leerMensaje()));
                    }

                    case 3 -> {
                        Consola.mostrarMensaje("Introduzca el tipo: ");
                        cerealBuilder.tipo(Consola.leerMensaje());
                    }
                    case 4 -> {
                        Consola.mostrarMensaje("Introduzca la fecha de caducidad (formato AAAA-MM-DD): ");
                        cerealBuilder.caducidad(Consola.leerDate(Consola.leerMensaje()));
                    }
                    case 5 -> {

                        Consola.mostrarMensaje("Resumen actual del producto:");
                        Consola.mostrarMensaje(cerealBuilder.toString());

                        Consola.mostrarMensaje("Se va a crear unos cereales, se utilizaran los datos por defecto en caso de no haber sido modificados. \n" +
                                "Seguir adelante? (s|n)");
                        if (Consola.leerMensaje().equalsIgnoreCase("s")) {
                            nuevoProducto = cerealBuilder.build();
                            return nuevoProducto;
                        }
                    }
                }
            } while (subeleccion != 0 && nuevoProducto == null);
        }
        return  nuevoProducto;
    }

    private static Producto crearDetergente(ProductoBuilder builder) {
        Producto nuevoProducto = null;

        if (builder instanceof Detergente.Builder detergenteBuilder) {
            int subeleccion;
            do {
                Consola.mostrarMensaje("""
                0.- Salir
                1.- Modificar Marca
                2.- Modificar Precio
                3.- Modificar Descuento
                4.- Modificar Volumen
                5.- Modificar Envase
                6.- Crear Detergente
            """);

                subeleccion = Consola.leerEntero();

                switch (subeleccion) {
                    case 1 -> {
                        Consola.mostrarMensaje("Introduzca la marca:");
                        detergenteBuilder.marca(Consola.leerMensaje());
                    }
                    case 2 -> {
                        Consola.mostrarMensaje("Introduzca el precio:");
                        detergenteBuilder.precio(Consola.conversionFloat(Consola.leerMensaje()));
                    }
                    case 3 -> {
                        Consola.mostrarMensaje("Introduzca el descuento (porcentaje, ej: 15 para 15%):");
                        String descuento = Consola.leerMensaje();
                        detergenteBuilder.descuento(Consola.conversionFloat(descuento));
                    }
                    case 4 -> {
                        Consola.mostrarMensaje("Introduzca el volumen (en litros):");
                        detergenteBuilder.volumen(Consola.conversionFloat(Consola.leerMensaje()));
                    }
                    case 5 -> {
                        Consola.mostrarMensaje("Introduzca el tipo de envase:");
                        detergenteBuilder.envase(Consola.leerMensaje());
                    }
                    case 6 -> {
                        Consola.mostrarMensaje("Resumen actual del producto:");
                        Consola.mostrarMensaje(detergenteBuilder.toString());

                        Consola.mostrarMensaje("¿Crear el detergente con estos datos? (s/n)");
                        if (Consola.leerMensaje().equalsIgnoreCase("s")) {
                            nuevoProducto = detergenteBuilder.build();
                            return nuevoProducto;
                        }
                    }
                }
            } while (subeleccion != 0 && nuevoProducto == null);
        }

        return nuevoProducto;
    }

    private static Producto crearVino(ProductoBuilder builder) {
        Producto nuevoProducto = null;

        if (builder instanceof Vino.Builder vinoBuilder) {
            int subeleccion;
            do {
                Consola.mostrarMensaje("""
                0.- Salir
                1.- Modificar Marca
                2.- Modificar Precio
                3.- Modificar Graduación Alcohólica
                4.- Modificar Tipo
                5.- Modificar Fecha de Caducidad
                6.- Modificar Descuento
                7.- Modificar Volumen
                8.- Modificar Envase
                9.- Crear Vino
            """);

                subeleccion = Consola.leerEntero();

                switch (subeleccion) {
                    case 1 -> {
                        Consola.mostrarMensaje("Introduzca la marca:");
                        vinoBuilder.marca(Consola.leerMensaje());
                    }
                    case 2 -> {
                        Consola.mostrarMensaje("Introduzca el precio:");
                        vinoBuilder.precio(Consola.conversionFloat(Consola.leerMensaje()));
                    }
                    case 3 -> {
                        Consola.mostrarMensaje("Introduzca la graduación alcohólica (ej. 12.5):");
                        vinoBuilder.gradosAlcohol(Consola.conversionFloat(Consola.leerMensaje()));
                    }
                    case 4 -> {
                        Consola.mostrarMensaje("Introduzca el tipo de vino (ej. Tinto, Blanco, Rosado):");
                        vinoBuilder.tipo(Consola.leerMensaje());
                    }
                    case 5 -> {
                        Consola.mostrarMensaje("Introduzca la fecha de caducidad (AAAA-MM-DD):");
                        vinoBuilder.caducidad(Consola.leerDate(Consola.leerMensaje()));
                    }
                    case 6 -> {
                        Consola.mostrarMensaje("Introduzca el descuento (porcentaje, ej. 20.5):");
                        float descuento = Consola.conversionFloat(Consola.leerMensaje());
                        vinoBuilder.descuento(descuento);
                    }
                    case 7 -> {
                        Consola.mostrarMensaje("Introduzca el volumen (en litros):");
                        vinoBuilder.volumen(Consola.conversionFloat(Consola.leerMensaje()));
                    }
                    case 8 -> {
                        Consola.mostrarMensaje("Introduzca el tipo de envase:");
                        vinoBuilder.envase(Consola.leerMensaje());
                    }
                    case 9 -> {
                        Consola.mostrarMensaje("Resumen actual del producto:");
                        Consola.mostrarMensaje(vinoBuilder.toString());

                        Consola.mostrarMensaje("¿Crear el vino con estos datos? (s/n)");
                        if (Consola.leerMensaje().equalsIgnoreCase("s")) {
                            nuevoProducto = vinoBuilder.build();
                            return nuevoProducto;
                        }
                    }
                }
            } while (subeleccion != 0 && nuevoProducto == null);
        }

        return nuevoProducto;
    }

    public static void creacionEstandar(int cantidad){


        for(int i = 0; i < cantidad;i++){

            Random r = new Random();
            int min = 1;
            int max = 100;
            int result = r.nextInt(max-min)+min;

            if(result<34){
                CarritoCompra.anadirProducto(productoEstandar.get(0).get());
                Consola.mostrarMensaje("Detergente " + i +" de marca blanca creado.");
            } else if (result<67) {
                CarritoCompra.anadirProducto(productoEstandar.get(1).get());
                Consola.mostrarMensaje("Cereales " + i +" de marca blanca creado.");

            }else{
                CarritoCompra.anadirProducto(productoEstandar.get(2).get());
                Consola.mostrarMensaje("Vino " + i +" de marca blanca creado.");

            }

        }

    }

    public static void menuPrincipal(){

        Consola.mostrarMensaje("==========Menu principal==========");
        int eleccion;
        do {
            Consola.mostrarMensaje("""
                0.- Salir
                1.- Crear Detergente
                2.- Crear Cereales
                3.- Crear Vino
                4.- Imprimir Productos
                5.- Calculo Calórico
                6.- Creación Productos Estandar
            """);
            eleccion= Consola.leerEntero();

            switch(eleccion){

                case 1 -> CarritoCompra.anadirProducto(crearDetergente(productoPeculiar.get(0)));
                case 2 -> CarritoCompra.anadirProducto(crearCereal(productoPeculiar.get(1)));
                case 3 -> CarritoCompra.anadirProducto(crearVino(productoPeculiar.get(2)));
                case 4 -> Consola.mostrarMensaje(CarritoCompra.imprimirProductos());
                case 5 -> Consola.mostrarMensaje("Calorias totales: " + CarritoCompra.calcularCaloriasTotales() + " calorias");
                case 6 -> {
                    Consola.mostrarMensaje("Cuantos productos quieres crear?");
                    creacionEstandar(Consola.leerEntero());
                }

            }
        }while(eleccion != 0);
    }
}
