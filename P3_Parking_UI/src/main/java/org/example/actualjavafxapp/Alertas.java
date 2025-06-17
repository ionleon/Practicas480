package org.example.actualjavafxapp;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.Optional;

public class Alertas {

    public static void mostrarError(String mensajeError, String instrucciones) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error de Validación");
        alert.setHeaderText(mensajeError);
        alert.setContentText(instrucciones);

        alert.showAndWait();


    }

    public static void mostrarMensaje(String instrucciones) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información de Pago");
        alert.setHeaderText("Operación Exitosa");
        alert.setContentText(instrucciones);

        alert.showAndWait();
    }

    public static void mostrarErrorAnim(String mensajeError, String instrucciones) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error: ");
        alert.setHeaderText(mensajeError);
        alert.setContentText(instrucciones);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(Alertas.class.getResource("/css/style.css").toExternalForm());
        dialogPane.getStyleClass().add("shake");

        // Configurar animaciones
        FadeTransition fadeIn = new FadeTransition(Duration.millis(300), dialogPane);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);

        TranslateTransition shake = new TranslateTransition(Duration.millis(100), dialogPane);
        shake.setByX(5);
        shake.setCycleCount(2);
        shake.setAutoReverse(true);

// Mostrar alerta y animar
        alert.show();
        fadeIn.play();
        shake.play();


    }


    public static void mostrarMensajeAnim(String instrucciones) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información de Pago");
        alert.setHeaderText("Operación Exitosa");
        alert.setContentText(instrucciones);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(Alertas.class.getResource("/css/style.css").toExternalForm());
        dialogPane.getStyleClass().add("shake");

        // Configurar animaciones
        FadeTransition fadeIn = new FadeTransition(Duration.millis(300), dialogPane);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);

        TranslateTransition shake = new TranslateTransition(Duration.millis(100), dialogPane);
        shake.setByX(5);
        shake.setCycleCount(2);
        shake.setAutoReverse(true);

        // Mostrar alerta y animar
        alert.show();
        fadeIn.play();
        shake.play();


    }

    public static Optional<ButtonType> mostrarConfirmAnim(String titulo, String mensaje, String instrucciones) {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(titulo);
            alert.setHeaderText(mensaje);
            alert.setContentText(instrucciones);

            // Aplicar animaciones y estilos (opcional)
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStyleClass().add("dialog-pane");

            // Animación de entrada
            FadeTransition fadeIn = new FadeTransition(Duration.millis(300), dialogPane);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.play();

            // Mostrar y esperar respuesta
            return alert.showAndWait(); // <<--- Esto pausa el programa hasta que el usuario responda

        } catch (Exception e) {
            System.err.println("Error al mostrar alerta: " + e.getMessage());
            return Optional.empty(); // Retorna vacío si hay error
        }
    }

}
