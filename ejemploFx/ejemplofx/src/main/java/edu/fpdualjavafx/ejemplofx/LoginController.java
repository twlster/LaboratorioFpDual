package edu.fpdualjavafx.ejemplofx;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class LoginController {

	@FXML
	private TextField username;

	@FXML
	private PasswordField password;

	@FXML
	public void login() throws IOException {
		if (username.getText().equals("twlster") && password.getText().equals("1234")) {
			App.setRoot("main", "Main Window");
		} else {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("ERROR");
			alerta.setHeaderText("Ups! Wrong Credentials");
			alerta.show();
		}
	}

	@FXML
	public void closeWindow() throws IOException {
		App.close();
	}
	
	@FXML
	public void enter(KeyEvent key) throws IOException {
		if(key.getCharacter().equals("\r")) {
			login();
		}
	}
}
