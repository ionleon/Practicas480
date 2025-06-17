package ParkingDatabase.Interfaces;

public interface ICoche {
    String getMatricula();
    TipoVehiculo getTipo(); // Enum: OFICIAL, RESIDENTE, NO_RESIDENTE
}
