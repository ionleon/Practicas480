package ParkingDatabase.Utilidades;

import ParkingDatabase.Interfaces.ICoche;
import ParkingDatabase.Objetos.CocheOficial;
import ParkingDatabase.Objetos.CocheResidente;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class GestorInformes {
    private static final String DIRECTORIO_INFORMES = "informes/";
    private static final String FORMATO_NOMBRE_ARCHIVO = "informe_%s_%s.txt"; // Ej: informe_residentes_2023-10-25.txt

    // Metodo principal para generar informes
    public static void generarInforme(String tipoInforme, List<? extends ICoche> vehiculos) {
        String nombreArchivo = String.format(FORMATO_NOMBRE_ARCHIVO, tipoInforme, LocalDate.now());
        Path rutaCompleta = Paths.get(DIRECTORIO_INFORMES, nombreArchivo);

        List<String> lineas = generarContenidoInforme(tipoInforme, vehiculos);

        try {
            Files.createDirectories(rutaCompleta.getParent()); // Crea directorio si no existe
            Files.write(rutaCompleta, lineas, StandardOpenOption.CREATE);
            System.out.println("Informe generado: " + rutaCompleta.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error al generar informe: " + e.getMessage());
        }
    }

    // Genera el contenido específico para cada tipo de informe
    public static List<String> generarContenidoInforme(String tipoInforme, List<? extends ICoche> vehiculos) {
        List<String> lineas = new ArrayList<>();

        switch (tipoInforme.toLowerCase()) {
            case "residentes":
                lineas.add("=== INFORME RESIDENTES ===");
                lineas.add("MATRÍCULA | MINUTOS | IMPORTE");
                vehiculos.forEach(c -> {
                    if (c instanceof CocheResidente) {
                        CocheResidente cr = (CocheResidente) c;
                        double importe = cr.getMinutosAcumulados() * 0.002;
                        lineas.add(String.format("%s | %d | %.2f€",
                                cr.getMatricula(),
                                cr.getMinutosAcumulados(),
                                importe));
                    }
                });
                break;

            case "oficiales":
                lineas.add("=== INFORME VEHÍCULOS OFICIALES ===");
                lineas.add("MATRÍCULA | ESTANCIAS REGISTRADAS");
                vehiculos.forEach(v -> {
                    if (v instanceof CocheOficial) {
                        CocheOficial co = (CocheOficial) v;
                        lineas.add(String.format("%s | %d",
                                co.getMatricula(),
                                co.getEstancias().size()));
                    }
                });
                break;

            default:
                lineas.add("Tipo de informe no válido: " + tipoInforme);
        }

        return lineas;
    }

    /**
     * Muestra un diálogo para guardar el informe y lo escribe en la ubicación seleccionada.
     * @param stage Ventana padre para el diálogo
     * @param tipoInforme "residentes" u "oficiales" (para el nombre del archivo)
     * @param contenido Lista de líneas del informe
     * @return true si se guardó correctamente, false si hubo error o el usuario canceló
     */

    public static boolean guardarInforme(Stage stage, String tipoInforme, List<String> contenido) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar informe de " + tipoInforme);
        fileChooser.setInitialFileName(String.format("Informe%s_%s.txt",
                tipoInforme.substring(0, 1).toUpperCase() + tipoInforme.substring(1),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmm"))));
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Archivos de texto (*.txt)", "*.txt")
        );

        File archivo = fileChooser.showSaveDialog(stage);
        if (archivo != null) {
            try {
                Files.write(archivo.toPath(), contenido, StandardCharsets.UTF_8);
                System.out.println("Informe guardado en: " + archivo.getAbsolutePath());
                return true;
            } catch (IOException e) {
                System.err.println("Error al guardar: " + e.getMessage());
                return false;
            }
        }
        return false; // Usuario canceló
    }

    /**
     * Genera el informe y permite al usuario elegir dónde guardarlo.
     * @param stage Ventana padre (para el diálogo)
     * @param tipoInforme "residentes" u "oficiales"
     * @param vehiculos Lista de vehículos
     */

    public static void generarYGuardarInforme(Stage stage, String tipoInforme, List<? extends ICoche> vehiculos) {
        // 1. Generar contenido del informe (sin guardar automáticamente)
        List<String> lineas = generarContenidoInforme(tipoInforme, vehiculos);

        // 2. Mostrar diálogo para guardar
        if (!guardarInforme(stage, tipoInforme, lineas)) {
            System.out.println("El usuario canceló el guardado.");
        }
    }


}
