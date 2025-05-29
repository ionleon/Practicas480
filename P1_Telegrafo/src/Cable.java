public class Cable{
    private static double longitud;
    private static int perdida_km;

    public void setLongitud(double longitud){
        this.longitud = longitud;
    }

    public double getLongitud(){
        return this.longitud;
    }

    public void setPerdidaKm(int perdida_km){
        this.perdida_km = perdida_km;
    }

    public int getPerdidaKm(){
        return perdida_km;
    }

    public Cable (double longitud, int perdida_km){
        this.longitud = longitud;
        this.perdida_km = perdida_km;
    }


    public static void transmit(Message message){
        
        int perdida_total = (int) longitud*perdida_km;
        int senal = message.getSignal() - perdida_total;
        

        if (senal <= 0){
            System.err.println("La señal se ha debilitado demasiado");
            System.exit(0);
        }

        message.setSignal(senal);



    }

}