import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Consola {

    static Scanner scanString = new Scanner(System.in);
    static Scanner scanInt = new Scanner(System.in);
    static Scanner scanFloat = new Scanner(System.in);



    public static void mostrarMensaje(String mensaje){
        System.out.println(mensaje);
    }

    public static String leerMensaje(){
        return scanString.nextLine();
    }

    public static int leerEntero(){
        return scanInt.nextInt();
    }

    public static float leerFloat(){
        return scanFloat.nextFloat();
    }

    public static float conversionFloat(String entrada){
        entrada = entrada.replace(',','.');
        return Float.parseFloat(entrada);
    }

    public static LocalDate leerDate(String entrada){
        LocalDate fecha = null;
        while(fecha==null){
        try {
            fecha = LocalDate.parse(entrada);
        } catch (DateTimeParseException e) {
            System.out.println("Formato de fecha inválido. Usa el formato AAAA-MM-DD.");
            System.out.print("Introduce la fecha de nuevo: ");
            entrada = leerMensaje();
            }
        }
        return fecha;
    }



}
