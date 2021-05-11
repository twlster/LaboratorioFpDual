package edu.fpdualjavafx.ejemplofx;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.web.WebView;

public class MainWindowController implements Initializable {

	@FXML
	private WebView webLoader;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		webLoader.getEngine().load("https://www.google.com");
	}

	@FXML
	public void switchToPrimary() throws IOException {
		App.setRoot("login", "Login");
	}

	@FXML
	public void closeWindow() throws IOException {
		App.setRoot("login", "Login");
	}

	@FXML
	public void deleteCache() throws IOException {
		Alert alerta = new Alert(AlertType.CONFIRMATION);
		alerta.setTitle("Delete Cache");
		alerta.setHeaderText("Are you sure want to delete this file cache?");
		Optional<ButtonType> option = alerta.showAndWait();
		if (option.get() == ButtonType.OK) {
			Alert confirmacion = new Alert(AlertType.CONFIRMATION);
			confirmacion.setTitle("Delete Cache");
			confirmacion.setHeaderText("Cache deleted!");
			confirmacion.show();
		} else if (option.get() == ButtonType.CANCEL) {
			Alert cancelation = new Alert(AlertType.WARNING);
			cancelation.setTitle("Delete Cache");
			cancelation.setHeaderText("Cache wasn't deleted.");
			cancelation.show();
		} else {
			System.out.println("Nothing.");
		}
	}

	@FXML
	public void about() throws IOException {
		Alert alerta = new Alert(AlertType.INFORMATION);
		alerta.setTitle("About!");
		alerta.setHeaderText("This is the coolest application you have ever seen!!");
		alerta.show();
	}
}