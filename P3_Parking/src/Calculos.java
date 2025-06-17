import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class Calculos {

    public double calcularPago(NORegistroEstancias registro, double precio){
        validarRegistro(registro.getHistorial());

        double costoTotal = 0;
        List<Estancia> estancias = registro.getHistorial();

        for (int i = 0; i < estancias.size(); i += 2) {
            if (i + 1 >= estancias.size()) break; // Si queda una entrada sin salida

            LocalDateTime entrada = estancias.get(i).getFecha();
            LocalDateTime salida = estancias.get(i + 1).getFecha();

            long minutos = Duration.between(entrada, salida).toMinutes();
            costoTotal += minutos * precio;
        }

        return costoTotal;
    }

    private void validarRegistro(List<Estancia> estancias) {
        for (int i = 0; i < estancias.size(); i++) {
            boolean movimientoActualEsEntrada = estancias.get(i).esEntrada();

            // Debe alternar entre entrada y salida
            if (i % 2 == 0 && !movimientoActualEsEntrada) {
                throw new IllegalStateException("Se esperaba entrada en la posición " + i);
            }
            if (i % 2 != 0 && movimientoActualEsEntrada) {
                throw new IllegalStateException("Se esperaba salida en la posición " + i);
            }
        }
    }
}
