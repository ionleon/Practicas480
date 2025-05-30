public class Relay {
    private boolean Estado;
    private static String alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZ ";
    private static String[] morse = {".-","-...","-.-.","-..",".","..-.","--.",
                            "....","..",".---","-.-",".-..","--","-.",
                            "---",".--.","--.-",".-.","...","-","..-",
                            "...-",".--","-..-","-.--","--..","/"};
    public Relay(boolean Estado){
        this.Estado = Estado;
    }
    
    public void setEstado(boolean Estado){
        this.Estado = Estado;
    }

    public boolean getEstado(){
        return Estado;
    }



    public void amplify_signal(Message message){

        if(!getEstado()){
            System.out.println("El relé está apagado.");
            System.exit(0);
        }
       if(!message.getSendCable()){
           System.err.println("No hay ningún cable conectado al relé.");
           System.exit(0);
       }

       message.setSignal(100);
       message.setSendCable(false);

    }


    
}
