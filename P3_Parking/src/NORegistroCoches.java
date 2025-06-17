import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NORegistroCoches {
    private static Map<String, Coche> cochesOficiales= new HashMap<>();
    private static Map<String, Coche> cochesResidentes = new HashMap<>();
    private static Map<String, Coche> cochesNoResidentes = new HashMap<>();
    private static Set<String> matriculasRegistradas = new HashSet<>();



    public static Coche registrarCocheOficial(String matricula) {

        CocheOficial nuevo = new CocheOficial(matricula);
        cochesOficiales.put(matricula, nuevo);
        return nuevo;
    }

    public static Coche registrarCocheResidente(String matricula) {

        NOCocheResidente nuevo = new NOCocheResidente(matricula);
        cochesResidentes.put(matricula, nuevo);
        return nuevo;
    }

    public static Coche registrarCocheNoResidente(String matricula) {

        CocheNoResidente nuevo = new CocheNoResidente(matricula);
        cochesNoResidentes.put(matricula, nuevo);
        return nuevo;
    }

    public static Coche obtenerOficial(String id) {
        return cochesOficiales.get(id);
    }

    public static Coche obtenerResidente(String id) {
        return cochesResidentes.get(id);
    }

    public static Coche obtenerNoResidente(String id) {
        return cochesNoResidentes.get(id);
    }


    public static String resgistrarMatricula(String matricula){
        if (matriculasRegistradas.contains(matricula)){
            throw new IllegalArgumentException("Coche con matrícula: " + matricula + " ya esta registrado.");
        }
        matriculasRegistradas.add(matricula);
        return matricula;
    }

    public static boolean matriculaExiste(String matricula){
        return matriculasRegistradas.contains(matricula);
    }

    public static  void liberarId(String matricula){
         matriculasRegistradas.remove(matricula);
    }

    public static int totalMatriculasRegistradas() {
        return matriculasRegistradas.size();
    }

    /* public static void comenzarMes() {
        // Resetear tiempos de residentes
        cochesResidentes.values().forEach(coche -> {
            if (coche instanceof IPago) {
                ((IPago)coche).resetearTiempo();
            }
        });

        // Limpiar estancias de oficiales
        cochesOficiales.values().forEach(coche -> {
            RegistroEstancias registro = obtenerRegistroEstancias(coche.getMatricula());
            if (registro != null) {
                registro.limpiarHistorial();
        });


    } */

    public static String generarInformeResidentes() {
        StringBuilder sb = new StringBuilder();
        sb.append("INFORME DE PAGOS RESIDENTES\n");
        sb.append("============================\n");

        cochesResidentes.values().forEach(coche -> {
            if (coche instanceof NOCocheResidente) {
                NOCocheResidente residente = (NOCocheResidente)coche;
                sb.append(String.format("Matrícula: %s | Minutos: %d | Total a pagar: %.2f€%n",
                        residente.getMatricula(),
                        residente.getMinutosAcumulados(),
                        residente.calcularPago(NORegistroEstancias registro)));
            }
        });

        return sb.toString();
    }



}
