public class CocheOficial extends Coche {
    public CocheOficial(String matricula) {
        super(matricula);
    }

    @Override
    public void registrarEntrada(NORegistroEstancias registro) {
        registro.registrarEntrada();
    }

    @Override
    public void registrarSalida(NORegistroEstancias registro) {
        registro.registrarSalida();
    }
}