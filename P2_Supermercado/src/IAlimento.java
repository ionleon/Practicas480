import java.time.LocalDate;

public interface IAlimento {



     void setCaducidad(LocalDate caducidad);

     LocalDate getCaducidad();

     int getCalorias();
}
