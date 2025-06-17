import java.time.Duration;
import java.util.List;

public class NOCocheResidente extends Coche implements IPago {

    private final double precioPorMinuto = 0.002;
    private long minutosAcumulados;

    public NOCocheResidente(String matricula) {
        super(matricula);
    }
    @Override
    public void registrarEntrada(NORegistroEstancias registro) {
        registro.registrarEntrada();
    }

    @Override
    public void registrarSalida(NORegistroEstancias registro) {
        registro.registrarSalida();
        if (!registro.estaVacio()) {
            List<Estancia> historial = registro.getHistorial();
            Estancia entrada = historial.get(historial.size() - 2);
            Estancia salida = historial.get(historial.size() - 1);
            acumularTiempo(Duration.between(entrada.getFecha(), salida.getFecha()).toMinutes());
        }
    }

    @Override
    public double getPrecioPorMinuto(){
        return precioPorMinuto;
    }

    @Override
    public void acumularTiempo(long minutos){
        this.minutosAcumulados += minutos;
    }

    @Override
    public double calcularPago(NORegistroEstancias registroEstancias){
        return minutosAcumulados * precioPorMinuto;
    }

    @Override
    public void resetearTiempo() {
        this.minutosAcumulados = 0;
    }

    public long getMinutosAcumulados(){
        return minutosAcumulados;
    }

}
