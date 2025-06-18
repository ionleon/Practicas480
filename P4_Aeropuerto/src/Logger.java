import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    private static final String ARCHIVO_LOG = "control_aereo.log";
    private static final DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");



    public static void log(String mensaje){

        File archivo = new File(ARCHIVO_LOG);
        if (archivo.length() > 1_000_000) { // Si supera 1MB
            archivo.delete(); // Borra el log antiguo
        }

        try{
            FileWriter writer = new FileWriter(ARCHIVO_LOG, true);
            String linea = LocalDateTime.now().format(formato) + "-" + mensaje + "\n";
            writer.write(linea);
            writer.close();
        } catch (IOException e){
            Consola.mostrarMensaje(" ¡Error al guardar el log! " + e.getMessage());
        }
    }

    public static void imprimirLogs() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_LOG))) {
            System.out.println("\n=== HISTORIAL DE LOGS ===");
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }
            System.out.println("=== FIN DEL HISTORIAL ===\n");
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de logs: " + e.getMessage());
        }
    }

}
