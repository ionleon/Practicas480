package ParkingDatabase.Objetos;

import ParkingDatabase.Interfaces.ICoche;
import ParkingDatabase.Interfaces.IEstancias;
import ParkingDatabase.Interfaces.TipoVehiculo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CocheOficial implements ICoche, IEstancias {
    private final String matricula;
    private final TipoVehiculo tipo = TipoVehiculo.OFICIAL;
    private List<Estancia> estancias = new ArrayList<>();
    private Estancia estanciaActual; // Para manejar la estancia abierta


    public CocheOficial(String matricula) {
        this.matricula = matricula;
    }

    public void vaciarEstancias(){
        this.estancias.clear();
    }

    public void anadirEstancias(Estancia estancia){
        this.estancias.add(estancia);
    }

    public List<Estancia> getEstancias() {
        return estancias;
    }

    @Override
    public TipoVehiculo getTipo(){
        return tipo;
    }

    @Override
    public String getMatricula() {
        return matricula;
    }

    @Override
    public void registrarEntrada(LocalDateTime horaEntrada) {
        this.estanciaActual = new Estancia(horaEntrada, null);
    }

    @Override
    public void registrarSalida(LocalDateTime horaSalida) {
        if (estanciaActual == null) {
        throw new IllegalStateException("No hay una estancia abierta para cerrar");
        }
        Estancia estanciaCerrada = new Estancia(estanciaActual.getEntrada(), horaSalida);
        estancias.add(estanciaCerrada);
        estanciaActual = null;
    }
}
