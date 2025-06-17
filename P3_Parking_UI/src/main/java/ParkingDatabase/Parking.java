package ParkingDatabase;

import ParkingDatabase.Interfaces.ICoche;
import ParkingDatabase.Interfaces.IEstancias;
import ParkingDatabase.Objetos.CocheNoResidente;
import ParkingDatabase.Objetos.CocheOficial;
import ParkingDatabase.Objetos.CocheResidente;
import ParkingDatabase.Objetos.Estancia;
import ParkingDatabase.Utilidades.Consola;
import org.example.actualjavafxapp.Alertas;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parking {
    private static Map<String, ICoche> cochesRegistrados = new HashMap<>(); // Todos los vehículos registrados
    private static List<CocheOficial> oficiales = new ArrayList<>();
    private static List<CocheResidente> residentes = new ArrayList<>();
    static Map<String, Estancia> estanciasAbiertas = new HashMap<>();



    public static List<CocheOficial> getOficiales() {
        return oficiales;
    }

    public static List<CocheResidente> getResidentes() {
        return residentes;
    }

    public static Map<String, ICoche> getCochesRegistrados(){ return cochesRegistrados; }

    // Métodos clave:

    public static void registrarEntrada(String matricula, LocalDateTime entrada) {

        if(estanciasAbiertas.containsKey(matricula)){
            throw new IllegalArgumentException("Vehiculo ya registrado.");

        }

        ICoche coche = cochesRegistrados.computeIfAbsent(matricula, k -> {
            Consola.mostrarMensaje(String.format("Registrando entrada de coche no residente. Matrícula: %s", matricula));
            estanciasAbiertas.put(matricula, new Estancia(entrada, null));
            return new CocheNoResidente(matricula);

        });


        if (coche instanceof IEstancias){
            Consola.mostrarMensaje("Registrando entrada de coche oficial/residente. Matrícula: " + matricula);
            ((IEstancias) coche).registrarEntrada(entrada);
            estanciasAbiertas.put(matricula, new Estancia(entrada, null));

        }

    }

    public static void registrarSalida(String matricula, LocalDateTime horaSalida) {
        ICoche coche = cochesRegistrados.get(matricula);
        if (coche == null){
            throw new IllegalArgumentException("Vehiculo no registrado");
        }

        Estancia estancia = estanciasAbiertas.get(matricula);
        if (estancia == null){
            throw new IllegalArgumentException("No hay estancia abierta para esta matricula");
        }
        
        estanciasAbiertas.remove(matricula);
        Estancia estanciaCerrada = new Estancia(estancia.getEntrada(),horaSalida);
        
        
        if (coche instanceof CocheNoResidente){

            Alertas.mostrarMensajeAnim(
                    String.format("El coche no residente de matrícula %s va a pagar %.2f €",
                            matricula,
                            ((CocheNoResidente) coche).calcularPago(estanciaCerrada.getMinutos()))
            );
            cochesRegistrados.remove(matricula);
        } else if (coche instanceof CocheResidente) {
            ((CocheResidente) coche).registrarSalida(horaSalida);
        } else if (coche instanceof CocheOficial) {
            ((CocheOficial) coche).registrarSalida(horaSalida);
        }

    }

    public static void altaOficial(String matricula) {
        if (matricula == null) {
            throw new IllegalArgumentException("La matrícula no puede ser nula");
        }
        if (cochesRegistrados.containsKey(matricula)) {
            throw new IllegalArgumentException("La matrícula " + matricula + " ya está registrada");
        }

        CocheOficial nuevoCoche = new CocheOficial(matricula);
        cochesRegistrados.put(matricula, nuevoCoche);
        oficiales.add(nuevoCoche);
    }

    public static void altaResidente(String matricula) {
        if (matricula == null) {
            throw new IllegalArgumentException("La matrícula no puede ser nula");
        }
        if (cochesRegistrados.containsKey(matricula)) {
            throw new IllegalArgumentException("La matrícula " + matricula + " ya está registrada");
        }

        CocheResidente coche = new CocheResidente(matricula);
        cochesRegistrados.put(matricula, coche);
        residentes.add(coche);
    }



    public static void comenzarMes() {
        for (CocheOficial coche : oficiales){
            coche.vaciarEstancias();
        }
        for (CocheResidente coche : residentes){
            coche.resetearMinutos();
        }
    }
}
