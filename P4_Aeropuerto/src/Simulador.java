public class Simulador {


    public static void run(){
        TorreDeControl torre = new TorreDeControl();
        while (true){
            mostrarMenu();
            int opcion = Consola.leerEntero();

            switch (opcion){
                case 1 -> registrarAterrizaje(torre);
                case 2 -> registrarDespegue(torre);
                case 3 -> Consola.mostrarMensaje(torre.getEstadoColas());
                case 4 -> {
                    torre.procesarSiguienteEventoTiempo();
                    torre.liberarPistaFallo();
                }
                case 5 -> torre.liberarPistaFallo();
                case 6 -> Logger.imprimirLogs();
                case 7 -> System.exit(0);
                default -> Consola.mostrarMensaje("Opción inválida");

            }
        }
    }

    private static void mostrarMenu(){
        Consola.mostrarMensaje("""
                --- CONTROL AÉREO ---
                1. Registrar aterrizaje
                2. Registrar despegue
                3. Ver colas
                4. Procesar siguiente evento
                5. Liberar pista
                6. Ver logs
                7. Salir
                """);
    }



    private static void registrarAterrizaje(TorreDeControl torre){
        Aeronave avion = crearAeronave();
        torre.registrarAeronaveParaAterrizajeFallo(avion);
        Consola.mostrarMensaje(" Aterrizaje registrado: " + avion);
    }

    private static void registrarDespegue(TorreDeControl torre){
        Aeronave avion = crearAeronave();
        torre.registrarAeronaveParaDespegueFallo(avion);
        Consola.mostrarMensaje(" Despegue registrado: " + avion);
    }

    private static Aeronave crearAeronave(){
        Consola.mostrarMensaje("ID del vuelo: ");
        String id = Consola.leerMensaje();

        Consola.mostrarMensaje("Tipo: 1-Emergencia 2-Militar 3-Pasajeros 4-Carga");
        int tipo = Consola.leerEntero();

        return switch (tipo){
            case 1 -> new Emergencia(id);
            case 2 -> new Militar(id);
            case 3 -> new Pasajeros(id);
            case 4 -> new Carga(id);
            default -> throw new IllegalArgumentException("Tipo inválido");
        };
    }

}
