package ParkingDatabase;

import ParkingDatabase.Utilidades.Consola;
import ParkingDatabase.Utilidades.GestorInformes;

import java.time.LocalDateTime;

public class Menu {

    public static void ejecutarMenu(){
        int eleccion;
        do {
            Consola.mostrarMensaje("===============PARKING===============");
            Consola.mostrarMensaje("""
                    1.- Registrar Entrada
                    2.- Registrar salida
                    3.- Dar de alta vehículo oficial
                    4.- Dar de alta vehículo residente
                    5.- Comienza mes
                    6.- Informe pago de residentes
                    7.- Informe vehiculos Oficiales
                    0.- Salir
                    """);
            eleccion = Consola.leerEntero();

            switch(eleccion){
                case 1 -> {
                    Consola.mostrarMensaje("Escribe el numero de matricula: ( Longitud obligatoria 8 caracteres/digitos )");
                    try{
                        Parking.registrarEntrada(validarFomatoMatricula(Consola.leerMensaje()), LocalDateTime.now());
                        Consola.mostrarMensaje("Entrada registrada correctamente");
                    }
                    catch (IllegalArgumentException e) {
                        Consola.mostrarMensaje("Error: " + e.getMessage());
                        Consola.mostrarMensaje("Por favor, introduce una matrícula válida de 8 caracteres.");
                    }
                }
                case 2 -> {
                    Consola.mostrarMensaje("Escribe el numero de matricula:");
                    try{
                        Parking.registrarSalida(Consola.leerMensaje(),LocalDateTime.now());
                        Consola.mostrarMensaje("Salida registrada correctamente");


                    }
                    catch (IllegalArgumentException e){
                        Consola.mostrarMensaje("Error: " + e.getMessage());
                        Consola.mostrarMensaje("Por favor, introduce un matrícula válida.");
                    }
                }

                case 3 ->{
                    Consola.mostrarMensaje("Escribe el numero de matricula: ( Longitud obligatoria 8 caracteres/digitos )");
                    try{
                        Parking.altaOficial(validarFomatoMatricula(Consola.leerMensaje()));
                        Consola.mostrarMensaje("Vehiculo Oficial añadido correctamente.");
                    }
                    catch (IllegalArgumentException e) {
                        Consola.mostrarMensaje("Error: " + e.getMessage());
                        Consola.mostrarMensaje("Por favor, introduce una matrícula válida de 8 caracteres.");
                    }
                }
                case 4 ->{
                    Consola.mostrarMensaje("Escribe el numero de matricula: ( Longitud obligatoria 8 caracteres/digitos )");
                    try{
                        Parking.altaResidente(validarFomatoMatricula(Consola.leerMensaje()));
                        Consola.mostrarMensaje("Vehiculo Residente añadido correctamente.");
                    }
                    catch (IllegalArgumentException e) {
                        Consola.mostrarMensaje("Error: " + e.getMessage());
                        Consola.mostrarMensaje("Por favor, introduce una matrícula válida de 8 caracteres.");
                    }
                }
                case 5 ->{
                    Consola.mostrarMensaje("Se van a elimnar todas las estancias y minutos acumulados de los coches oficiales y residentes registrados. \n" +
                            "Seguir adelante? (s|n)");
                    if (Consola.leerMensaje().equalsIgnoreCase("s")) {
                         Parking.comenzarMes();
                    }

                }
                case 6 ->{
                    Consola.mostrarMensaje("Se va ha crear un informe de los vehiculos residentes registrados. \n" +
                            "Seguir adelante? (s|n)");
                    if (Consola.leerMensaje().equalsIgnoreCase("s")) {
                    GestorInformes.generarInforme("residentes", Parking.getResidentes());
                    }
                }
                case 7 ->{
                    Consola.mostrarMensaje("Se va ha crear un informe de los vehiculos oficiales registrados. \n" +
                            "Seguir adelante? (s|n)");
                    if (Consola.leerMensaje().equalsIgnoreCase("s")) {
                        GestorInformes.generarInforme("oficiales", Parking.getOficiales());
                    }
                }
            }

        }while(eleccion!=0);
    }

    public static String validarFomatoMatricula(String matricula){
        if(matricula == null || matricula.length() != 8){
            throw new IllegalArgumentException("Formato de matrícula invalido.");
        }
        return matricula;
    }


}
