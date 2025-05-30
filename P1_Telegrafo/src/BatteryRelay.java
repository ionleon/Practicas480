import java.util.InputMismatchException;
import java.util.Scanner;

public class BatteryRelay extends Relay{
    private int capacidadBateria;
    private int cargaBateria;
    private Scanner scan = new Scanner(System.in);

    public BatteryRelay(boolean Estado, int capacidadBateria) {
        super(Estado);
        this.capacidadBateria = capacidadBateria;
        this.cargaBateria = capacidadBateria;
    }

    public void recargarBateria(){
        cargaBateria = capacidadBateria;
    }

    public void amplify_signal(Message message){

        boolean eleccion;
        if (cargaBateria >0){
            super.amplify_signal(message);
            cargaBateria = cargaBateria - 1;
        }else{
            try{
                System.out.println("El rele se ha quedado sin carga, desea recargarlo? TRUE: SI | FALSE: NO");

            eleccion = scan.nextBoolean();
            if(eleccion){
                System.out.println("Se ha recargado la batería.");
                recargarBateria();
                super.amplify_signal(message);

            }else{
                System.out.println("No se ha recargado la batería, el mensaje no se puede transmitir.");
                System.exit(0);
                }
            } catch(InputMismatchException e){
                System.out.println("El input ha sido incorrecto, no se va ha recargar la batería");
                System.exit(0);
            }
        }


    }




}
