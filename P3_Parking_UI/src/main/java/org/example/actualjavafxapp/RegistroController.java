package org.example.actualjavafxapp;

import ParkingDatabase.Interfaces.ICoche;
import ParkingDatabase.Objetos.CocheOficial;
import ParkingDatabase.Objetos.CocheResidente;
import ParkingDatabase.Parking;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class RegistroController {
    @FXML private ComboBox<String> cbTipoListado;
    @FXML private TableView<ICoche> tableViewCoches;
    @FXML private TableColumn<ICoche, String> colMatricula;
    @FXML private TableColumn<ICoche, String> colTipo;
    @FXML private TableColumn<ICoche, String> colDetalles;

    @FXML
    public void initialize() {
        // Configurar opciones del ComboBox
        cbTipoListado.getItems().addAll(
                "Todos los vehículos registrados",
                "Vehículos oficiales",
                "Vehículos residentes"
        );

        // Configurar las columnas del TableView con lambdas
        colMatricula.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getMatricula()));

        colTipo.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getTipo().toString()));

        colDetalles.setCellValueFactory(cellData -> {
            ICoche coche = cellData.getValue();
            if (coche instanceof CocheOficial) {
                CocheOficial oficial = (CocheOficial) coche;
                return new SimpleStringProperty("Estancias: " + oficial.getEstancias().size());
            } else if (coche instanceof CocheResidente) {
                CocheResidente residente = (CocheResidente) coche;
                double importe = residente.getMinutosAcumulados() * residente.getPrecio();
                return new SimpleStringProperty(
                        "Minutos: " + residente.getMinutosAcumulados() +
                                " - Importe: " + String.format("%.2f€", importe)
                );
            } else {
                // Para otros tipos de vehículos
                return new SimpleStringProperty("No disponible");
            }
        });

        // Listener para cambios en la selección del ComboBox
        cbTipoListado.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                actualizarTabla(newVal);
            }
        });

        // Seleccionar la primera opción por defecto
        if (!cbTipoListado.getItems().isEmpty()) {
            cbTipoListado.getSelectionModel().selectFirst();
        }
    }

    private void actualizarTabla(String tipoListado) {
        ObservableList<ICoche> items = FXCollections.observableArrayList();

        switch (tipoListado) {
            case "Todos los vehículos registrados":
                items.addAll(Parking.getCochesRegistrados().values());
                break;

            case "Vehículos oficiales":
                items.addAll(Parking.getOficiales());
                break;

            case "Vehículos residentes":
                items.addAll(Parking.getResidentes());
                break;
        }

        tableViewCoches.setItems(items);
    }
}