package ParkingDatabase.Objetos;

import java.time.Duration;
import java.time.LocalDateTime;

public class Estancia {
    private LocalDateTime entrada;
    private LocalDateTime salida;

    public Estancia(LocalDateTime entrada, LocalDateTime salida){
        this.entrada = entrada;
        this.salida = salida;
        if (salida != null && salida.isBefore(entrada)) {
            throw new IllegalArgumentException("Salida debe ser después de entrada.");
        }
    }

    public long getMinutos() {
        if (salida == null) {
            throw new IllegalStateException("La estancia no está cerrada (falta hora de salida)");
        }
        return Duration.between(entrada, salida).toSeconds() * 60;
    }

    public LocalDateTime getEntrada() { return entrada; }
    public LocalDateTime getSalida()  { return salida; }

}
