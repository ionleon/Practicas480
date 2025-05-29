import java.util.Random;

public class Cable{
    private static double longitud;
    private static int perdida_km;
    private static Random random = new Random();
    private static boolean defectuoso;
    private static int probabilidad;
    private static double frecuencia;


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

    public void setProbabilidad(int probabilidad){ this.probabilidad = probabilidad; }
    public int getProbabilidad(){ return probabilidad; }

    public void setDefectuoso(boolean defectuoso){ this.defectuoso = defectuoso;}
    public boolean getDefectuoso(){ return defectuoso; }

    public static double getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(double frecuencia){

        this.frecuencia= frecuencia;
    }

    public Cable (double longitud, int perdida_km){
        this.longitud = longitud;
        this.perdida_km = perdida_km;
        this.defectuoso= false;
        this.probabilidad = 0;
        this.frecuencia=0;
    }

    public Cable(double longitud, int perdida_km, boolean defecutoso, int probabilidad, double frecuencia){
        this.longitud = longitud;
        this.perdida_km = perdida_km;
        this.defectuoso = defecutoso;
        this.probabilidad = probabilidad;
        this.frecuencia = frecuencia;

    }

    public static void transmit(Message message) {

        if(defectuoso){
            for(int i=0;i < (int) (longitud/frecuencia);i++){

                int azar = random.nextInt(0,100);
                if(azar<= probabilidad){
                    System.err.println("Cable defectuoso, se ha perdido la señal.");
                    System.exit(0);
                }

            }

        }

        int perdida_total = (int) longitud*perdida_km;
        int senal = message.getSignal() - perdida_total;

        if (senal <= 0){
            System.err.println("La señal se ha debilitado demasiado");
            System.exit(0);
        }

        message.setSignal(senal);

    }

}