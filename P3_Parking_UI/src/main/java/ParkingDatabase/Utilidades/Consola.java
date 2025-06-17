package ParkingDatabase.Utilidades;

import java.util.Scanner;

public class Consola {

    static Scanner scanString = new Scanner(System.in);
    static Scanner scanInt = new Scanner(System.in);


    public static void mostrarMensaje(String mensaje){
        System.out.println(mensaje);
    }

    public static String leerMensaje(){ return scanString.nextLine(); }
    public static int leerEntero() { return scanInt.nextInt(); }

}
