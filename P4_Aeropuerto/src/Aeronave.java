public abstract class Aeronave {
    private final String id;
    private String tipo;
    private int prioridad;

    public Aeronave(String id, String tipo, int prioridad){
        this.id = id;
        this.tipo = tipo;
        this.prioridad = prioridad;
    }

    public void setPrioridad(int prioridad){ this.prioridad = prioridad; }

    public int getPrioridad() {
        return prioridad;
    }

    @Override
    public String toString(){
        return String.format("%s - ID: %s (Prioridad: %s )", tipo,id,getPrioridad());
    }
}
