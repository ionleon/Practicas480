import java.util.ArrayList;
import java.util.List;

public class NORegistroEstancias {
    private final Coche coche;
    private final List<Estancia> historial;

    public NORegistroEstancias(Coche coche){
        this.coche = coche;
        this.historial = new ArrayList<>();
    }

    public void registrarEntrada(){
        if (!historial.isEmpty() && ultimaEstancia().esEntrada()) {
            throw new IllegalStateException(
                    "Error: No puede registrar una entrada sin salida previa. " +
                            "Último movimiento fue otra entrada el " + ultimaEstancia().getFecha()
            );
        }
        historial.add(new Estancia(true));
    }


    public void registrarSalida(){
        if (historial.isEmpty() || !ultimaEstancia().esEntrada()) {
            throw new IllegalStateException(
                    "Error: No puede registrar una salida sin entrada previa. " +
                            (historial.isEmpty() ?
                                    "No hay movimientos registrados" :
                                    "Último movimiento fue una salida el " + ultimaEstancia().getFecha())
            );
        }
        historial.add(new Estancia(false));
    }

    private Estancia ultimaEstancia() {
        return historial.get(historial.size() - 1);
    }

    public List<Estancia> getHistorial(){
        return new ArrayList<>(historial);
    }

    public void imprimirHistorial(){
        System.out.println("historial de coche " + coche.getMatricula() + " : ");
        historial.forEach(System.out::println);
    }

    public void limpiarHistorial() {
        this.historial.clear();
    }

    public boolean estaVacio() {
        return historial.isEmpty();
    }

}
