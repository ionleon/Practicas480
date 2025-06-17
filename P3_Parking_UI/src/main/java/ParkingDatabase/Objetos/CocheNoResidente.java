package ParkingDatabase.Objetos;

import ParkingDatabase.Interfaces.ICoche;
import ParkingDatabase.Interfaces.IPagoEstacionamiento;
import ParkingDatabase.Interfaces.TipoVehiculo;

import java.time.LocalDateTime;

public class CocheNoResidente implements ICoche, IPagoEstacionamiento {
    private final String matricula;
    private final double precio = 0.02;
    private LocalDateTime horaEntrada;
    private final TipoVehiculo tipo = TipoVehiculo.NO_RESIDENTE;

    public CocheNoResidente(String matricula) {
        this.matricula = matricula;
    }

    @Override
    public String getMatricula(){
        return matricula;
    }

    @Override
    public TipoVehiculo getTipo(){
        return tipo;
    }

    @Override
    public double calcularPago(long minutos) {
        return minutos * precio; // 0.02€/minuto
    }

}
