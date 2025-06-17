package ParkingDatabase.Objetos;

import ParkingDatabase.Interfaces.ICoche;
import ParkingDatabase.Interfaces.IEstancias;
import ParkingDatabase.Interfaces.IPagoEstacionamiento;
import ParkingDatabase.Interfaces.TipoVehiculo;

import java.time.LocalDateTime;

public class CocheResidente implements ICoche, IEstancias, IPagoEstacionamiento {
    private final String matricula;
    private final TipoVehiculo tipo = TipoVehiculo.RESIDENTE;
    private long minutosAcumulados;
    private final double precio = 0.002;
    private Estancia estanciaActual; // Estancia abierta


    public CocheResidente(String matricula) {
        this.matricula = matricula;
    }

    public long getMinutosAcumulados(){
        return minutosAcumulados;
    }

    public double getPrecio(){
        return precio;
    }

    public void agregarEstancia(Estancia estancia) {
        minutosAcumulados += estancia.getMinutos();
    }

    public void resetearMinutos(){
        minutosAcumulados = 0L;
    }


    @Override
    public void registrarEntrada(LocalDateTime entrada){
        this.estanciaActual = new Estancia(entrada, null); // Salida null (abierta)
    }

    @Override
    public void registrarSalida(LocalDateTime horaSalida) {
        if (estanciaActual == null) {
            throw new IllegalStateException("No hay una estancia abierta para cerrar");
        }

        Estancia estanciaCerrada = new Estancia(estanciaActual.getEntrada(), horaSalida);
        minutosAcumulados += estanciaCerrada.getMinutos();
        estanciaActual = null;
    }

    @Override
    public double calcularPago(long minutos) {
        return minutos * precio; // 0.002€/minuto
    }

    @Override
    public String getMatricula() {
        return matricula;
    }

    @Override
    public TipoVehiculo getTipo(){
        return tipo;
    }


}
