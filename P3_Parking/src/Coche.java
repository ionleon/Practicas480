

public abstract class Coche {
    private final String matricula;

    public Coche(String matricula) {
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }

    public abstract void registrarEntrada(NORegistroEstancias registro);
    public abstract void registrarSalida(NORegistroEstancias registro);
}
