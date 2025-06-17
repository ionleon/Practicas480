import java.time.Duration;
import java.util.List;

public class CocheNoResidente extends Coche implements IPago {

    private static final double PRECIO_POR_MINUTO = 0.02;

    public CocheNoResidente(String matricula) {
        super(matricula);
    }

    @Override
    public void registrarEntrada(NORegistroEstancias registro) {
        registro.registrarEntrada();
    }

    @Override
    public void registrarSalida(NORegistroEstancias registro) {
        registro.registrarSalida();
    }

    @Override
    public double getPrecioPorMinuto() {
        return PRECIO_POR_MINUTO;
    }

    @Override
    public void acumularTiempo(long minutos) {
    }

    @Override
    public double calcularPago(NORegistroEstancias registro) {
        List<Estancia> historial = registro.getHistorial();
        if (historial.size() < 2) return 0;

        Estancia ultimaEntrada = historial.get(historial.size() - 2);
        Estancia ultimaSalida = historial.get(historial.size() - 1);
        long minutos = Duration.between(ultimaEntrada.getFecha(), ultimaSalida.getFecha()).toMinutes();
        return minutos * PRECIO_POR_MINUTO;
    }

    @Override
    public void resetearTiempo() {
    }


}
