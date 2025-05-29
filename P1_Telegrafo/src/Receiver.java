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

    /*public Receiver(String finalMssgDcd){
        this.finalMssgDcd.equals(finalMssgDcd);
    }*/
    
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