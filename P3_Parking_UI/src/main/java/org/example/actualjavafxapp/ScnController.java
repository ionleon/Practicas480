package org.example.actualjavafxapp;

import ParkingDatabase.Parking;
import ParkingDatabase.Utilidades.Consola;
import ParkingDatabase.Utilidades.GestorInformes;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.actualjavafxapp.Alertas;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static ParkingDatabase.Menu.validarFomatoMatricula;


public class ScnController {

    private Stage stage;
    private Scene scene;
    private Parent root;



    // BorderPane interior
    @FXML
    private BorderPane brdPaneIn;

    private Map<String, AnchorPane> panelesCargados = new HashMap<>();


    @FXML
    private TextField txtFieldEntrada;

    //Variables Escena Salida

    @FXML
    private TextField txtFieldSalida;


    //Variables Escena Dar de Alta
    @FXML
    private TextField txtFieldAlta;

    @FXML
    private RadioButton rBttnAltaOficial;

    @FXML
    private RadioButton rBttnAltaResidente;

    //Variables Escena Pago
    @FXML
    private TextArea txtAreaPago;

    @FXML
    private Button btnGuardarPago;

    //Variable para texto invisible
    @FXML
    private Text textoTransparente;


    public AnchorPane cargarPanel(String nombreFxml) throws IOException {
        if (!panelesCargados.containsKey(nombreFxml)) {
            AnchorPane panel = FXMLLoader.load(getClass().getResource(nombreFxml + ".fxml"));
            panelesCargados.put(nombreFxml, panel);
        }
        return panelesCargados.get(nombreFxml);
    }

    public void setPanelCentral(String fxmlPath) {
        try {
            AnchorPane newPanel = cargarPanel(fxmlPath);
            brdPaneIn.setCenter(newPanel); // Reemplaza el centro
        } catch (IOException e) {
            Alertas.mostrarErrorAnim("Error al cargar el panel:", e.getMessage());
            System.err.println("Error al cargar el panel: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public void switchToEntrada(ActionEvent event) throws IOException {
        setPanelCentral("scnEntrada");
    }

    public void switchToSalida(ActionEvent event) throws IOException {
        setPanelCentral("scnSalida");
    }


    public void switchToAlta(ActionEvent event) throws IOException {
        setPanelCentral("scnAlta");
    }



    public void switchToMes(ActionEvent event) throws IOException {
        setPanelCentral("scnMes");
    }



    public void switchToPago(ActionEvent event) throws IOException {
        setPanelCentral("scnPago");
    }

    public void switchToRegsitro(ActionEvent event) throws IOException {
        setPanelCentral("scnRegistro");
    }



    public void switchToMenu(ActionEvent event) throws IOException {
        setPanelCentral("scnMain");
    }




    public void imprimirEntrada(ActionEvent event) throws IOException{
        try{
            String matricula = txtFieldEntrada.getText();
            Parking.registrarEntrada(validarFomatoMatricula(matricula), LocalDateTime.now());

            mostrarMensajeTemporal("Entrada registrada correctamente.");

            Consola.mostrarMensaje("Entrada registrada correctamente");

            System.out.println(matricula);
        }catch (Exception e){
            mostrarErrorTemporal("Error: " + e.getMessage() + " Introduce una matrícula válida de 8 caracteres.");
            System.out.println(e);
        }
    }

    public void imprimirSalida(ActionEvent event) throws IOException{
        try{
            String matricula = txtFieldSalida.getText();
            Parking.registrarSalida(validarFomatoMatricula(matricula), LocalDateTime.now());
            mostrarMensajeTemporal("Salida registrada correctamente");
            Consola.mostrarMensaje("Salida registrada correctamente");
            System.out.println(matricula);
        }catch (Exception e){
            mostrarErrorTemporal("Error: " + e.getMessage() + " Introduce una matrícula válida de 8 caracteres.");
            System.out.println(e);
        }
    }

    public void imprimirAlta(ActionEvent event) throws IOException{

        try{

            String matricula = txtFieldAlta.getText();

            if(rBttnAltaOficial.isSelected()){
                Parking.altaOficial(validarFomatoMatricula(matricula));
                mostrarMensajeTemporal("Se ha dado de alta al coche oficial con matrícula: " + matricula +".");
                System.out.println("Se ha dado de alta al coche oficial con matrícula " + matricula);

            }if(rBttnAltaResidente.isSelected()){
                Parking.altaResidente(validarFomatoMatricula(matricula));
                mostrarMensajeTemporal("Se ha dado de alta al coche residente con matrícula: " + matricula+".");
                System.out.println("Se ha dado de alta al residente con matrícula " + matricula + ". ");

            }
        }catch (Exception e){

            mostrarErrorTemporal("Error: " + e.getMessage() + " Introduce una matrícula válida de 8 caracteres.");
            System.out.println(e);
        }
    }

    public void comenzarMes(ActionEvent event) throws IOException {


        // 2. Mostrar el diálogo y esperar respuesta del usuario
        Optional<ButtonType> resultado = Alertas.mostrarConfirmAnim("Confirmar reinicio","¿Estás seguro de reiniciar el mes?","Esta acción no se puede deshacer." );

        // 3. Si el usuario confirma, ejecutar la lógica del metodo
        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            Parking.comenzarMes();
            mostrarMensajeTemporal("Se ha reiniciado el mes.");
            System.out.println("Se ha reiniciado el mes.");
            // Aquí iría el resto de tu lógica...
        } else {
            mostrarErrorTemporal("Operación cancelada.");
            System.out.println("Operación cancelada.");
        }
    }

    public void imprimirPago(){

        List<String> lista = GestorInformes.generarContenidoInforme("residentes", Parking.getResidentes());
        String texto = String.join("\n", lista);
        txtAreaPago.setText(texto);

    }

    @FXML
    public void guardarPago(){
        try{
            Stage stage = (Stage) btnGuardarPago.getScene().getWindow();
            GestorInformes.generarYGuardarInforme(stage, "residentes", Parking.getResidentes());
            mostrarMensajeTemporal("Se ha guardado el fichero correctamente.");
        }
        catch
        (Exception e){
            mostrarErrorTemporal(e.getMessage());
        }


    }

    @FXML
    private void mostrarMensajeTemporal(String mensaje) {
        textoTransparente.setText(mensaje);
        textoTransparente.setStyle("-fx-fill: #3dda42;");

        // Animación de aparición
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.3), textoTransparente);
        fadeIn.setToValue(1.0);

        // Animación de desaparición después de 3 segundos
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), textoTransparente);
        fadeOut.setToValue(0.0);

        // Secuenciar animaciones
        fadeIn.setOnFinished(e -> delay.play());
        delay.setOnFinished(e -> fadeOut.play());

        fadeIn.play();
    }

    @FXML
    private void mostrarErrorTemporal(String mensaje) {
        textoTransparente.setText(mensaje);
        textoTransparente.setStyle("-fx-fill: #FF5733;");

        // Animación de aparición
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.3), textoTransparente);
        fadeIn.setToValue(1.0);

        // Animación de desaparición después de 3 segundos
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), textoTransparente);
        fadeOut.setToValue(0.0);

        // Secuenciar animaciones
        fadeIn.setOnFinished(e -> delay.play());
        delay.setOnFinished(e -> fadeOut.play());

        fadeIn.play();
    }



}
