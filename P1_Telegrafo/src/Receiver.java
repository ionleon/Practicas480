import java.util.ArrayList;


public class Receiver{
    private ArrayList<String> finalMssgEncd; //Mensaje Encriptado
    private String finalMssgDcd = new String(""); //Mensaje Descencriptado
    private static String alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZ ";
    private static String[] morse = {".-","-...","-.-.","-..",".","..-.","--.",
                            "....","..",".---","-.-",".-..","--","-.",
                            "---",".--.","--.-",".-.","...","-","..-",
                            "...-",".--","-..-","-.--","--..","/"};
    
    private static ArrayList<String> listMorse = new ArrayList<String>();

    public Receiver(ArrayList<String> finalMssgEncd){
        this.finalMssgEncd.clear();
        this.finalMssgEncd.addAll(finalMssgEncd);
    }

    public Receiver(){}


    
    public void setFinalMssgEncd(ArrayList<String> finalMssgEncd){
        this.finalMssgEncd.clear();
        this.finalMssgEncd.addAll(finalMssgEncd);
    }

    public ArrayList<String> getFinalMssgEncd(){
            return this.finalMssgEncd;
    }
    
    public void setFinalMssgDcd(String finalMssgDcd){
        this.finalMssgDcd = finalMssgDcd;
    }

    public String getFinalMssgDcd(){
        return finalMssgDcd;
    }

    public void receive_signal(Message message){

        if(!message.getSendCable()){
            System.err.println("No hay ningún cable conectado al receptor.");
            System.exit(0);
        }

        if(!message.getSendEcrypted()){
            System.err.println("No se puede transmitir el mensaje sin un transmisor.");
            System.exit(0);
        }

        String tempMssg = "";
        for(String a : morse){
            listMorse.add(a);
        }

        for(String a: message.getMssg()){
            tempMssg += alfabeto.charAt(listMorse.indexOf(a));
        }

        setFinalMssgDcd(tempMssg);        
    }

    public void display_message(){
        System.out.println("El mensaje desencriptado es: " + getFinalMssgDcd());
    } 
}