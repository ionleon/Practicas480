import java.util.PriorityQueue;
import java.util.Comparator;

public class TorreDeControl {

    private PriorityQueue<Aeronave> colaAterrizajes;
    private PriorityQueue<Aeronave> colaDespegues;
    private boolean pistaLibre;

    private static final double PROB_FALLO_ATERRIZAJE = 0.15; // 15%
    private static final double PROB_FALLO_DESPEGUE = 0.10; // 15%
    private static final double PROB_FALLO_PISTA = 0.15;

    public TorreDeControl(){
        Comparator<Aeronave> comparadorPrioridad = Comparator.comparingInt(Aeronave::getPrioridad);

        this.colaAterrizajes = new PriorityQueue<>(comparadorPrioridad);
        this.colaDespegues = new PriorityQueue<>(comparadorPrioridad);
        this.pistaLibre = true;
    }


    public void registrarAeronaveParaAterrizaje(Aeronave a){
        Logger.log("NUEVO: Registro Aterrizaje :" + a);
        colaAterrizajes.offer(a);
    }

    public void registrarAeronaveParaAterrizajeFallo(Aeronave aeronave) {

        if (Math.random() < PROB_FALLO_ATERRIZAJE) {
            int tipoFallo = (int) (Math.random() * 3);

            switch (tipoFallo) {
                case 0:
                    Consola.mostrarMensaje("[FALLO] " + aeronave + " presenta problemas de motor. ¡Aterrizaje de emergencia!");
                    aeronave.setPrioridad(1);
                    break;
                case 1:
                    Consola.mostrarMensaje("[FALLO] " + aeronave + " sufre un retraso por mal tiempo. Se reasigna.");
                    colaAterrizajes.offer(aeronave);
                    return;
                case 2:
                    Consola.mostrarMensaje("[FALLO] " + aeronave + " tiene fallos en el tren de aterrizaje. ¡Necesita asistencia!");
                    Logger.log("FALLO CRÍTICO: " + aeronave);
                    break;
            }
        }
        colaAterrizajes.offer(aeronave);
    }

    public void registrarAeronaveParaDespegue(Aeronave a){
        Logger.log("NUEVO: Registro Despegue :" + a);
        colaDespegues.offer(a);
    }

    public void registrarAeronaveParaDespegueFallo(Aeronave aeronave) {
        Logger.log("Registro despegue: " + aeronave);

        if (Math.random() < PROB_FALLO_DESPEGUE) {

            int tipoFallo = (int) (Math.random() * 2);

            if (tipoFallo == 0) {
                System.out.println("[FALLO] " + aeronave + " sufre retraso. Vuelve a la cola.");
                colaDespegues.offer(aeronave);
                return;
            } else {
                System.out.println("[FALLO] " + aeronave + " tiene problemas técnicos. Prioridad aumentada!");
                aeronave.setPrioridad(aeronave.getPrioridad() - 1);
            }
        }

        colaDespegues.offer(aeronave);
    }

    /*
    public void procesarSiguienteEvento(){

        if(!pistaLibre) {
            Consola.mostrarMensaje("Pista ocupada, hay que liberarla primero.");
            return;
        }

        if(colaAterrizajes.isEmpty() && colaDespegues.isEmpty()){
            Consola.mostrarMensaje("No hay solicitudes en cola.\n");
        }

        if (!colaAterrizajes.isEmpty()){
            Aeronave proxima = colaAterrizajes.poll();
            usarPista(proxima, "ATERRIZAJE");
        } else if (!colaDespegues.isEmpty()) {
            Aeronave proxima = colaDespegues.poll();
            usarPista(proxima, "DESPEGUE");
        }
    }
    */

    public void procesarSiguienteEventoTiempo(){
        if(!pistaLibre) {
            Consola.mostrarMensaje("La pista está ocupada. Espera...");
            return;
        }

        if(colaAterrizajes.isEmpty() && colaDespegues.isEmpty()){
            Consola.mostrarMensaje("No hay solicitudes en cola.\n");

        }

        try{
            if (!colaAterrizajes.isEmpty()){
                Aeronave proxima = colaAterrizajes.poll();
                usarPistaTiempo(proxima, "ATERRIZAJE");
            } else if (!colaDespegues.isEmpty()) {
                Aeronave proxima = colaDespegues.poll();
                usarPistaTiempo(proxima, "DESPEGUE");
            }
        } catch (Exception e){
            Consola.mostrarMensaje("Error en la simulacion de tiempo: " + e.getMessage());
        }
    }

    public void usarPista(Aeronave a, String operacion){
        pistaLibre = false;
        String evento = operacion + ": " + a;

        Consola.mostrarMensaje(">>> " + evento);
        Logger.log("Inicio -" + evento + "- Fin");
    }

    public void usarPistaTiempo(Aeronave a, String operacion) throws InterruptedException{
        pistaLibre = false;
        String evento = operacion + ": " + a;

        Consola.mostrarMensaje(">>> INICIO " + evento);
        Logger.log("INICIO: " + evento);

        int tiempo = operacion.equals("ATERRIZAJE") ? 3000 : 2000;
        Thread.sleep(tiempo);

        Logger.log("FIN: " + evento);
        Consola.mostrarMensaje("<<< FIN " + operacion + ": " + a + " (" + tiempo/1000 + " segundos)");
    }

    public void liberarPista(){
        if(!pistaLibre){
            Consola.mostrarMensaje("\n Liberando pista.\n");
            pistaLibre = true;
        } else {
            Consola.mostrarMensaje("La pista ya está libre.");
        }
    }

    public void liberarPistaFallo(){

        if (pistaLibre){
            Consola.mostrarMensaje("La pista ya está libre.");
            return;
        }
        if (Math.random() < PROB_FALLO_PISTA) {
            Consola.mostrarMensaje("[FALLO] ¡La pista está bloqueada por mantenimiento urgente!");
            Logger.log("FALLO PISTA: Retraso por mantenimiento.");
        } else {
            Consola.mostrarMensaje("Liberando pista.\n");
            pistaLibre = true;

        }
    }

    public String getEstadoColas() {

        String estadoColas = "Aterrizajes pendientes: " + colaAterrizajes.size() +
                            " | Despegues pendientes: " + colaDespegues.size();

        Logger.log("Imprimiendo Estado Colas - " + estadoColas);
        return estadoColas;
    }


}
