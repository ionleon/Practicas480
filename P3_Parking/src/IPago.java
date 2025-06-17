public interface IPago {

    double calcularPago(NORegistroEstancias registro);
    double getPrecioPorMinuto();
    default void acumularTiempo(long minutos) {}
    default void resetearTiempo() {}
}
