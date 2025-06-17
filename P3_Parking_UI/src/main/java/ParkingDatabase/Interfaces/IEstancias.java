package ParkingDatabase.Interfaces;

import java.time.LocalDateTime;

public interface IEstancias {
    void registrarEntrada(LocalDateTime horaEntrada);
    void registrarSalida(LocalDateTime horaSalida);

}
