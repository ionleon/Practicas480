import java.util.ArrayList;


public class Transmitter{
    private boolean Estado;
    private static ArrayList<String> encdMssg = new ArrayList<String>();
    private static String alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZ ";
    private static String[] morse = {".-","-...","-.-.","-..",".","..-.","--.",
                            "....","..",".---","-.-",".-..","--","-.",
                            "---",".--.","--.-",".-.","...","-","..-",
                            "...-",".--","-..-","-.--","--..","/"};

    private static ArrayList<String> listMorse = new ArrayList<String>();

    public void setEstado(boolean Estado){
        this.Estado = Estado;
    }

    public boolean getEstado(){
        return Estado;
    }

    public Transmitter(boolean Estado){
        this.Estado = Estado;
    }




    public void send_signal(Message message){
        if(!getEstado()){
            System.out.println("El transmisor está apagado");
        }

        String tempMssg = "";

        for(String a : message.getMssg()){
            tempMssg += a;
        }

        tempMssg = tempMssg.toUpperCase();
        String[] letters = tempMssg.split("");

        for(String a : letters){

            encdMssg.add(morse[alfabeto.indexOf(a)]);

        }

        message.setMssg(encdMssg);

        message.setSendEcrypted(true);



    }

}