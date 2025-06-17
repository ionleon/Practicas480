import java.time.LocalDateTime;

public class Estancia {
    private final LocalDateTime fecha;
    private final boolean esEntrada;

    public Estancia(boolean esEntrada) {
        this.fecha = LocalDateTime.now();
        this.esEntrada = esEntrada;
    }

    public LocalDateTime getFecha(){
        return fecha;
    }

    public boolean esEntrada(){
        return esEntrada;
    }

    @Override
    public String toString(){
        return String.format("[%s] %s",
                fecha.toString(),
                esEntrada ? "ENTRADA" : "SALIDA");
    }

}
