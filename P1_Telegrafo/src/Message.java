import java.util.ArrayList;


public class Message{
    
    private ArrayList<String> mssg = new ArrayList<String>();
    private int signal;
    private String regex = "^[a-zA-Z ]*$";
    private boolean sendEcrypted;
    private boolean sendCable;

    public void setSendEcrypted(boolean sendEcrypted){
        this.sendEcrypted = sendEcrypted;
    }

    public boolean getSendEcryted(){
        return sendEcrypted;
    }

    public void setSendCable(boolean sendCable) {
        this.sendCable = sendCable;
    }

    public boolean getSendCable(){
        return sendCable;
    }

    public void setInitialMessage(String newMssg){
        if(!mssg.isEmpty()){
            this.mssg.clear();
        }

        if(!newMssg.matches(regex)){
                System.out.println("El mensaje debe ser solo palabras separadas por espacios.");
                System.exit(0);
        }

        String[] tempMssg = newMssg.split("");
        
        for(String a : tempMssg){
            this.mssg.add(a);
        }
    }

    public void setMssg(ArrayList<String> mssg){
        this.mssg.clear();
        this.mssg.addAll(mssg);
    }

    public ArrayList<String> getMssg(){
        return mssg;
    }

    public void setSignal(int newSignal){

        if(newSignal>100){
            System.out.println("Intensidad máxima 100%");
            this.signal = 100;
        }
        this.signal = newSignal;
    }

    public int getSignal(){
        return signal;
    }

    public Message(String message, int signal){

        setInitialMessage(message);

        setSignal(signal);

        setSendEcrypted(false);

        setSendCable(false);



    }





}